/*
 * This application calculates solutions for a^x + b^x = c^x
 */

#include <stdlib.h>
#include <cmath>
#include <stdio.h>
#include <iostream>
#include <MersenneTwister.h>

#include <cln/integer.h>
#include <cln/integer_io.h>

using namespace std;
using namespace cln;

//Operation defins
#define root(n,x) pow(x, 1.0/n)
#define inc(x) x = x+1;

#define ATRIES
#define BTRIES 100
#define ABCLIMIT 150
#define XYZLIMIT 20
#define ABCSV 2
#define XYZSV 2

#define cdc cl_decimal_string


//Switches
//#define DEBUG
#define OPFIRST

//Statements to switch abc and xyz at compile time e.g. for behaviour: operators change first

typedef unsigned long long ull;
typedef unsigned long ul;


//ull rpw(ull b, ull e) //Recursive power, slow but using 64-bit ull numbers
//{
//    ull pow = 1;
//    #pragma omp parallel for reduction(*:pow)
//    for(ull i = 0; i < e; i++)
//    {
//        pow *= b;
//    }
//    return pow;
//}
//
//ull rpwa(ull b, ull e, ull b2, ull e2) //Recursive power, slow but using 64-bit ull numbers
//{
//    ull pow = 1;
//    #pragma omp parallel for reduction(*:pow)
//    for(ull i = 0; i < e; i++)
//    {
//        pow *= b;
//    }
//    ull pow2 = 1;
//    for(ull i = 0; i < e; i++)
//    {
//        pow *= b;
//    }
//    return pow;
//}



/** Testing main function**/
//int main()
//{
//    cout << "x^y=" << rpw(13,15) << endl;
//}

/*
 *
 */
int main(int argc, char** argv)
{
    FILE *f = fopen("/ram/formulasd.txt", "w");

    cl_I a = ABCSV;
    cl_I b = ABCSV;
    cl_I c = ABCSV;

    cl_I x = XYZSV;
    cl_I y = XYZSV;
    cl_I z = XYZSV;

    //Main loop
    for (; z <= XYZLIMIT; inc(x))
    {
        for (a = ABCSV; a <= ABCLIMIT; inc(a))
        {
            for (b = ABCSV; b <= ABCLIMIT; inc(b))
            {
                for (c = ABCSV; c <= ABCLIMIT; inc(c))
                {
                    #ifdef DEBUG
                        cout << "a: " << a << "    x: " << x << "      ";
                        cout << "b: " << b << "    y: " << y << "      ";
                        cout << "c: " << c << "    z: " << z << endl;
                    #endif
                    if(    a == x || a == b || a == y || a == c || a == z
                        || x == b || x == y || x == c || x == z
                        || b == y || b == c || b == z
                        || y == c || y == z
                        || c == z) {continue;}

                    if((a == c && x == z) || (b == c && y == z)) {continue;}

                    cl_I leftSide = expt_pos(a, x) + expt_pos(b, y);
                    cl_I rightSide = expt_pos(c, z);

                    if (leftSide == rightSide)
                    {
                        //Check if !exp == a^x + b^x = b^x
                        printf("Found solution: %s^%s + %s^%s = %s^%s\n", cdc(a), cdc(x), cdc(b), cdc(y), cdc(c), cdc(z));
                        fprintf(f, "%s^%s + %s^%s = %s^%s\n", cdc(a), cdc(x), cdc(b), cdc(y), cdc(c), cdc(z));
                    }
                }
            }
        }
        //Overflow handling
        if (x > XYZLIMIT)
        {
            x = XYZSV;
            inc(y);
            if (y > XYZLIMIT)
            {
                y = XYZSV;
                inc(z); //z > XYZLIMIT checked in for declaration
            }
        }
    }
    fclose(f);
    return (EXIT_SUCCESS);
}

