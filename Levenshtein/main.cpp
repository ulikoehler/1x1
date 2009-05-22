/* 
 * File:   main.cpp
 * Author: uli
 *
 * Created on 22. Mai 2009, 21:37
 */

#include <stdlib.h>
#include <string>
#include <iostream>
using namespace std;


uint pI = 1; //Insertion penalty
uint pD = 1; //Deletion penalty
uint pS = 1; //Substitution penalty

uint levenshtein(string& x, string& y)
{
    uint n = x.length ();
    uint m = y.length ();
    uint* backwdArray = new uint[m+1];
    
    backwdArray[m] = 0;
    for(int i = m-1; i >= 0; i--)
        {
            backwdArray[i] = backwdArray[i-1] + pI;
        }

    uint c,s;
    int sub, del, ins;
    for(int i = n-1; i >= 0; i--)
        {
            s = backwdArray[m];
            backwdArray[m] += pD;
            c = backwdArray[m];
            for(int j = m-1; j >= 0; j--)
                {
                    sub = s + (x[i+1] == y[j+1] ? 0 : pS);
                    del = backwdArray[j] + pD;
                    ins = c + pI;
                    c = min(sub, min(del,ins));
                    s = backwdArray[j];
                    backwdArray[j] = c;
                }
        }
    uint buf = backwdArray[0];
    delete[] backwdArray;
    return buf;
}

/*
 * 
 */
int
main (int argc, char** argv)
{
    string x,y;
    cout << "x: "; cin >> x;
    cout << "y: "; cin >> y;
    cout << levenshtein (x,y);
    return (EXIT_SUCCESS);
}

