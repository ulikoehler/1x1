#include <openssl/rsa.h>
#include <openssl/rand.h>
#include <openssl/evp.h>
#include <openssl/bio.h>

#define exp 65536

typedef enum
{
    CIPHER_AES_128 = 0,
    CIPHER_AES_192 = 1,
    CIPHER_AES_256 = 2,
    CIPHER_DES3 = 0,
} rsaCipher;

void seedRand(unsigned int bytes)
{
    #ifdef linux /// /dev/random is available
        #ifdef GOODRANDOM //If defined, seed from /dev/random instead of /dev/urandom
            //RAND_load_file("/dev/random", bytes);
        #else
            //RAND_load_file("/dev/urandom", bytes);
        #endif
    #else ///Other OSs Automatically seeded; TODO: Maybe check if /dev/urandom is available

    #endif
}

///CipherID:
void generateRsaKey(uint keylen, rsaCipher cipher, char *password, char *filename)
{
        BN_GENCB cb;
        int ret=1;
        int i,num=keylen;
        long l;
        const EVP_CIPHER *enc=NULL;
        unsigned long f4=RSA_F4;
        char *passargout = NULL, *passout = NULL;
    #ifndef OPENSSL_NO_ENGINE
        char *engine=NULL;
    #endif
        char *inrand=NULL;
        BIO *out=NULL;
        BIGNUM *bn = BN_new();
        RSA *rsa = RSA_new();

        if(!bn || !rsa) goto err;

        BN_GENCB_set(&cb, genrsa_cb, bio_err);

        if (bio_err == NULL)
            if ((bio_err=BIO_new(BIO_s_file())) != NULL)
                BIO_set_fp(bio_err,stderr,BIO_NOCLOSE|BIO_FP_TEXT);

        if (!load_config(bio_err, NULL))
            goto err;
        if ((out=BIO_new(BIO_s_file())) == NULL)
            {
            BIO_printf(bio_err,"unable to create BIO for output\n");
            goto err;
            }

            switch(cipher)
            {
            case CIPHER_AES_128: {enc=EVP_aes_128_cbc();break;}
            case CIPHER_AES_192: {enc=EVP_aes_192_cbc();break;}
            case CIPHER_AES_256: {enc=EVP_aes_256_cbc();break;}
            default: break;
            }

        ERR_load_crypto_strings();

        if(!app_passwd(bio_err, NULL, passargout, NULL,

        if (filename == NULL)
            {
            BIO_set_fp(out,stdout,BIO_NOCLOSE);
    #ifdef OPENSSL_SYS_VMS
            {
            BIO *tmpbio = BIO_new(BIO_f_linebuffer());
            out = BIO_push(tmpbio, out);
            }
    #endif
            }
        else
            {
            if (BIO_write_filename(out,filename) <= 0)
                {
                perror(filename);
                goto err;
                }
            }

        if (!app_RAND_load_file(NULL, bio_err, 1) && inrand == NULL
            && !RAND_status())
            {
            BIO_printf(bio_err,"warning, not much extra random data, consider using the -rand option\n");
            }

        BIO_printf(bio_err,"Generating RSA private key, %d bit long modulus\n", num);

        if(!BN_set_word(bn, f4) || !RSA_generate_key_ex(rsa, num, bn, &cb))
            goto err;

        app_RAND_write_file(NULL, bio_err);

        /* We need to do the following for when the base number size is <
         * long, esp windows 3.1 :-(. */
        l=0L;
        for (i=0; i<rsa->e->top; i++)
            {
    #ifndef SIXTY_FOUR_BIT
            l<<=BN_BITS4;
            l<<=BN_BITS4;
    #endif
            l+=rsa->e->d[i];
            }
        BIO_printf(bio_err,"e is %ld (0x%lX)\n",l,l);
        {
        PW_CB_DATA cb_data;
        cb_data.password = passout;
        cb_data.prompt_info = filename;
        if (!PEM_write_bio_RSAPrivateKey(out,rsa,enc,NULL,0,
            (pem_password_cb *)password_callback,&cb_data))
            goto err;
        }

        ret=0;
    err:
        if (bn) BN_free(bn);
        if (rsa) RSA_free(rsa);
        if (out) BIO_free_all(out);
        if(passout) OPENSSL_free(passout);
        if (ret != 0)
            ERR_print_errors(bio_err);
        apps_shutdown();
        OPENSSL_EXIT(ret);
        }

    static int MS_CALLBACK genrsa_cb(int p, int n, BN_GENCB *cb)
        {
        char c='*';

        if (p == 0) c='.';
        if (p == 1) c='+';
        if (p == 2) c='*';
        if (p == 3) c='\n';
        BIO_write(cb->arg,&c,1);
        (void)BIO_flush(cb->arg);
    #ifdef LINT
        p=n;
    #endif
        return 1;
}
