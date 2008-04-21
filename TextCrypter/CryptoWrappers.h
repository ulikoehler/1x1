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

string botanEncrypt(string plaintext, string passphrase, string cipher)
{
    ///Adapter code
    stringstream ptss(plaintext);
    stringstream ctss;
    ///Botan code
    LibraryInitializer init;
    S2K* s2k = get_s2k("PBKDF2(SHA-1)");
    s2k->set_iterations(4096);
    s2k->new_random_salt(8);
    SecureVector<byte> the_salt = s2k->current_salt();
    SymmetricKey master_key = s2k->derive_key(48, passphrase);

    KDF* kdf = get_kdf("KDF2(SHA-1)");
    SymmetricKey key = kdf->derive_key(20, master_key, "cipher key");
    SymmetricKey mac_key = kdf->derive_key(20, master_key, "hmac key");
    InitializationVector iv = kdf->derive_key(8, master_key, "cipher iv");
    Pipe pipe
    (
        new Fork
        (
                get_cipher(cipher, key, iv, ENCRYPTION),
            new MAC_Filter("HMAC(SHA-1)", mac_key)
        )
    );
    ctss << (const char*)the_salt.ptr();

    pipe.start_msg();
    ptss >> pipe;
    pipe.end_msg();

    SecureVector<byte> hmac = pipe.read_all(1);
    ctss << (const char*)hmac.ptr();

    return ctss.str();

}





///
///LibTomCrypt section
///

//void tomTwofishEncrypt(string plaintext, string password) //UNDER DEVELOPMENT!!
//{
//    ///Get password uchar array
//    unsigned char password[256];
//    string passwordString = lexical_cast<string>(passwordField->GetValue().c_str());
//    hash_state md;
//    sha256_init(&md);
//    sha256_process(&md, (unsigned char*)passwordString.c_str(), passwordString.length());
//    sha256_done(&md, password);
//    ///Setup serpent algorithm
//    symmetric_key skey;
//    twofish_setup(password, 256, 0, &skey);
//    ///Get text and encrypt
//    unsigned char* ciphertext;
//    string plaintext = lexical_cast<string>(inputField->GetValue().c_str());
//    twofish_ecb_encrypt(&inputPlaintext, ciphertext, &skey);
//    string ciphertextString((const char*)ciphertext, plaintext.length());
//    ///"Print" encoded ciphertext to GUI
//    outputField->SetValue(wxString(ciphertextString.c_str(), wxConvUTF8));
//}
//
//
//void tomTwofishDecrypt(string ciphertext, string password
//{
//
//}
