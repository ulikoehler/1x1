/*
 * This application calculates solutions for a^x + b^x = c^x
 */

#include <stdlib.h>
#include <cmath>
#include <stdio.h>
#include <iostream>

#include <cln/integer.h>
#include <cln/integer_io.h>

using namespace std;
using namespace cln;

//Operation defins
#define root(n,x) pow(x, 1.0/n)
#define inc(x) x = x+1

#define BLIMIT 150
#define RLIMIT 30
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
    long numSolutions = 0; //Number of correct solutions
    ull numTried = 0;

    FILE *f = fopen("/ram/formulas.txt", "w");

    cl_I a = ABCSV;
    cl_I b = ABCSV;
    cl_I c = ABCSV;

    cl_I x = XYZSV;
    cl_I y = XYZSV;
    cl_I z = XYZSV;

    //Main loop
    for (; z <= RLIMIT; inc(x))
    {
        for (a = ABCSV; a <= BLIMIT; inc(a))
        {
            for (b = ABCSV; b <= BLIMIT; inc(b))
            {
                for (c = ABCSV; c <= BLIMIT; inc(c))
                {
                    #ifdef DEBUG
                    cout << "a: " << a << "    x: " << x << "      ";
                    cout << "b: " << b << "    y: " << y << "      ";
                    cout << "c: " << c << "    z: " << z << endl;
                    #endif

                    cl_I leftSide = expt_pos(a, x) + expt_pos(b, y);
                    cl_I rightSide = expt_pos(c, z);


                    if (leftSide == rightSide)
                    {
                        //Check if !exp == a^x + b^x = b^x
                        if((a == c && x == z) || (b == c && y == z)) {continue;}
                        inc(numSolutions);
                        printf("Found solution %lu: %s^%s + %s^%s = %s^%s\n",numSolutions, cdc(a), cdc(x), cdc(b), cdc(y), cdc(c), cdc(z));

                        fprintf(f, "%s^%s + %s^%s = %s^%s\n", cdc(a), cdc(x), cdc(b), cdc(y), cdc(c), cdc(z));

                        if(numTried % 10000 == 0)
                        {
                            printf("10000 expressions processed\n");
                        }
                    }
                    inc(numTried);
                }
            }
        }
        //Overflow handling
        if (x > RLIMIT)
        {
            x = XYZSV;
            inc(y);
            if (y > RLIMIT)
            {
                y = XYZSV;
                inc(z); //z > RLIMIT checked in for declaration
            }
        }
    }
    cout << "Expressions tried: " << numTried << endl;
    cout << "Solutions found: " << numSolutions << endl;
    fclose(f);
    return (EXIT_SUCCESS);
}

