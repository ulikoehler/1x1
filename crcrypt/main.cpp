/* 
 * File:   main.cpp
 * Author: uli
 *
 * Created on 14. April 2009, 19:59
 */

#include <iterator>


#include <stdlib.h>
#include <boost/crc.hpp>
#include <string>
#include <fstream>
#include <iostream>
#include <tr1/random>

using namespace boost;
using namespace std;
using namespace std::tr1;

/*
 * 
 */
int
main (int argc, char** argv)
{
    if(argc < 3 || argc > 4)
        {
            
        }
    crc_32_type result;
    string password;
    string passwordVerify;
    cout << "Password: ";
    cin >> password;
    cout << "Verify: ";
    cin >> passwordVerify;
    //Verify the password
    if(password != passwordVerify)
        {
            cout << "Password verification error!\n";
            return 1;
        }
    result.process_bytes (password.c_str (), password.length ());

    uint seed = result.checksum ();

    cout << "Seed: " << seed << endl;

    mt19937 rng(seed);
    
    char buffer;

    ifstream fin(argv[1]);
    ofstream fout(argv[2]);

    while(fin.good())
        {
            fin.get(buffer);
            //XOR the buffer with the next random number
            buffer ^= rng() % 255;
            fout << buffer;
        }


    return (EXIT_SUCCESS);
}

