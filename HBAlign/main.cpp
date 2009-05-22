/* 
 * File:   main.cpp
 * Author: uli
 *
 * Created on 20. Mai 2009, 14:41
 */

#include <cstdlib>
#include <cmath>
#include <string>
#include <iostream>

using namespace std;


uint pI = 1; //Insertion penalty
uint pD = 1; //Deletion penalty
uint pS = 1; //Substitution penalty

string hirschberg(string& x, string& y)
{
    size_t n,m;

    uint c,s;
    int sub, del, ins;

    n = x.length ();
    m = y.length ();
    uint mid = floor (n/2);
    if(n <= 1 && m <= 1)
        {
            if(x[0] == y[0])
                {return x;}
            else {return y;}
        }
    else if(n == 1)
        {
            return y;
        }
    else if(m == 1)
        {
            string ret;
            ret.append(n, '-');
            return ret;
        }
    /**
     * Fwd levenshtein
     */
    uint* fwdArray = new uint[m+1];
    fwdArray[0] = 0;
    for(int i = 1; i <= m; i++)
        {
            fwdArray[i] = fwdArray[i-1] + pI;
        }

    for(int i = 1; i <= n; i++)
        {
            s = fwdArray[0];
            fwdArray[0] += pD;
            c = fwdArray[0];
            for(int j = 1; j <= m; j++)
                {
                    sub = s + (x[i] == y[j] ? 0 : pS);
                    del = fwdArray[j] + pD;
                    ins = c + pI;
                    c = min(sub, min(del,ins));
                    s = fwdArray[j];
                    fwdArray[j] = c;
                }
        }
    /**
     * Backward levenshtein
     */
    uint* backwdArray = new uint[m+1];
    backwdArray[m] = 0;
    for(int i = m-1; i >= 0; i--)
        {
            backwdArray[i] = backwdArray[i-1] + pI;
        }

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

    int cut;
    //n=j1; m=j2
    for(int j = 1; j <= m;j++)
        {
           if(j == 1) {cut = j;}
           else if ((fwdArray[j] + backwdArray[j]) < (fwdArray[cut] + backwdArray[cut]))
               {
                   cut = j;
               }
        }

    delete[] fwdArray;
    delete[] backwdArray;
    
    string ap = x.substr (0,mid);
    string bp = y.substr(0,cut);
    string cp = x.substr (mid);
    string dp = y.substr (cut);
    cout << "HB " << ap << " " << bp << " " << cp << " " << dp << endl;
    return hirschberg (ap,bp)
            + hirschberg (cp,dp);
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

    cout << hirschberg (x,y);




    return (EXIT_SUCCESS);
}

