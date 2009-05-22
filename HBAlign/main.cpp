/* 
 * File:   main.cpp
 * Author: uli
 *
 * Created on 20. Mai 2009, 14:41
 */

#include <stdlib.h>
#include <string>
#include <iostream>

using namespace std;


uint pI = 1; //Insertion penalty
uint pD = 1; //Deletion penalty
uint pS = 1; //Substitution penalty

inline int
subP (char c1, char c2)
{
    if (c1 == c2)
        {
            return 0;
        }
    else
        {
            return 1;
        }
}


inline uint levenshteinDistance(const string& x, const string& y)
{
    size_t xlen = x.length ();
    size_t ylen = y.length ();
    uint* array = new uint[ylen+1];
    array[0] = 0;
    for(int i = 1; i <= ylen; i++)
        {
            array[i] = array[i-1] + pI;
        }

    uint c,s;
    int sub, del, ins;
    for(int i = 1; i <= xlen; i++)
        {
            s = array[0];
            array[0] += pD;
            c = array[0];
            for(int j = 1; j <= ylen; j++)
                {
                    sub = s + subP(x[i],y[j]);
                    del = array[j] + pD;
                    ins = c + pI;
                    c = min(sub, min(del,ins));
                    s = array[j];
                    array[j] = c;
                }
        }
    return array[ylen];

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
    cout << levenshteinDistance(x, y) << endl;
    return (EXIT_SUCCESS);
}

