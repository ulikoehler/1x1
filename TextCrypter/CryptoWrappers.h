#include <boost/lexical_cast.hpp>
#include <string>
#include <sstream>
#include <botan/botan.h>
using namespace boost;
using namespace std;
using namespace Botan;

///
///Botan section
///

//typedef unsigned char byte;
#define byte unsigned char //Bugfix

string Base64_Encode(string input)
{
    stringstream iss(input); //Input stringstream
    stringstream rss; //Return stringstream
    Pipe B64Encoder(new Base64_Encoder());
    B64Encoder.start_msg();
    iss >> B64Encoder;
    B64Encoder.end_msg();
    SecureVector<byte> b64b = B64Encoder.read_all(); //Base64 bytes
    return (const char*)b64b.begin();
}

string Base64_Decode(string input)
{
    stringstream iss(input); //Input stringstream
    stringstream rss; //Return stringstream
    Pipe B64Decoder(new Base64_Decoder());
    B64Decoder.start_msg();
    iss >> B64Decoder;
    B64Decoder.end_msg();
    SecureVector<byte> b64b = B64Decoder.read_all(); //Base64 bytes
    return (const char*)b64b.begin();
}

string botanEncrypt(string plaintext, string passphrase, string cipher)
{
    ///Adapter code
    stringstream ptss(plaintext);
    stringstream ctss;
    ///Botan code
    S2K* s2k = get_s2k("PBKDF2(SHA-1)");
    s2k->set_iterations(4096);
    s2k->new_random_salt(8);
    SecureVector<byte> the_salt = s2k->current_salt();
    SymmetricKey master_key = s2k->derive_key(48, passphrase);

    KDF* kdf = get_kdf("KDF2(SHA-1)");
    SymmetricKey key = kdf->derive_key(20, master_key.bits_of(), "cipher key");
    SymmetricKey mac_key = kdf->derive_key(20, master_key.bits_of(), "hmac key");
    InitializationVector iv = kdf->derive_key(8, master_key.bits_of(), "cipher iv");
    Pipe pipe
    (
        new Fork
        (
                get_cipher(cipher, key, iv, ENCRYPTION), new Base64_Encoder(),
                new MAC_Filter("HMAC(SHA-1)", mac_key)
        )
    );
    ctss << (const char*)the_salt.begin();

    pipe.start_msg();
    ptss >> pipe;
    pipe.end_msg();

    SecureVector<byte> hmac = pipe.read_all(1);
    ctss << (const char*)hmac.begin();

    return Base64_Encode(ctss.str());
}

string botanDecrypt(string ciphertext, string passphrase, string cipher)
{
    ///Adapter code
    stringstream ptss(Base64_Decode(ciphertext));
    stringstream ctss;
    ///Botan code
    S2K* s2k = get_s2k("PBKDF2(SHA-1)");
    s2k->set_iterations(4096);
    s2k->new_random_salt(8);
    SecureVector<byte> the_salt = s2k->current_salt();
    SymmetricKey master_key = s2k->derive_key(48, passphrase);

    KDF* kdf = get_kdf("KDF2(SHA-1)");
    SymmetricKey key = kdf->derive_key(20, master_key.bits_of(), "cipher key");
    SymmetricKey mac_key = kdf->derive_key(20, master_key.bits_of(), "hmac key");
    InitializationVector iv = kdf->derive_key(8, master_key.bits_of(), "cipher iv");
    Pipe pipe
    (
        new Fork
        (
                get_cipher(cipher, key, iv, DECRYPTION), new Base64_Encoder(),
                new MAC_Filter("HMAC(SHA-1)", mac_key)
        )
    );
    ctss << (const char*)the_salt.begin();

    pipe.start_msg();
    ptss >> pipe;
    pipe.end_msg();

    SecureVector<byte> hmac = pipe.read_all(1);
    ctss << (const char*)hmac.begin();

    return Base64_Encode(ctss.str());
}
