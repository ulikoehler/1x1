#include "CryptoWrappers.h"
#include <iostream>
using namespace std;

int main()
{
    LibraryInitializer init;
    string password;
    string text;
    cout << "Passwort:";
    cin >> password;
    cout << "Text:" << endl;
    cin >> text;
    cout << "CT:" << endl;
    string ciphertext = botanEncrypt(text, password, "Blowfish/CBC/PKCS7");
    cout << ciphertext << endl;
    system("pause");
    cout << endl << "Plaintext:" << endl;
    cout << botanDecrypt(ciphertext, password, "Blowfish/CBC/PKCS7") << endl;
    system("pause");
    return 0;
}
