/*
 * This application calculates solutions for a^x + b^x = c^x
 */

#include <stdlib.h>
#include <cmath>
#include <stdio.h>
#include <iostream>

#include <cln/integer.h>
#include <cln/integer_io.h>
#include <cln/ring.h>
#include <cln/modinteger.h>

#include <boost/thread/thread.hpp>

using namespace std;
using namespace cln;
using namespace boost;

//Operation defins
#define root(n,x) pow(x, 1.0/n)
#define inc(x) x=x+1

#define ATRIES 100
#define BTRIES 100
#define ABCLIMIT 150
#define XYZLIMIT 20
#define ABCSV 2
#define XYZSV 2
#define MODN 3
#define DISPLAYINTERVAL 5

#define cdc cl_decimal_string


//Switches
//#define DEBUG
#define OPFIRST
#define DISPLAYSTATS

//Statements to switch abc and xyz at compile time e.g. for behaviour: operators change first

typedef unsigned long long ull;
typedef unsigned long ul;

/** Testing main function**/
//int main()
//{
//    cout << "x^y=" << rpw(13,15) << endl;
//}

/*
 *
 */
ull tried = 0;


int main(int argc, char** argv)
{
    //Variable declarations
    cl_I a;
    cl_I b;
    cl_I c;

    cl_I x = XYZSV;
    cl_I y = XYZSV;
    cl_I z = XYZSV;

    //Used for mod operations
    cl_modint_ring modProvider = find_modint_ring(MODN);

    int display();

    #ifdef DISPLAYSTATS
        //Start stats thread
        thread statsThread(&display);
    #endif

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

                    if((a == c && x == z) || (b == c && y == z)) {continue;}

                    if(modProvider->canonhom(a) != 0 || modProvider->canonhom(b) != 0 || modProvider->canonhom(b) != 0) {continue;}

                    if(    a == x || a == b || a == y || a == c || a == z
                        || x == b || x == y || x == c || x == z
                        || b == y || b == c || b == z
                        || y == c || y == z
                        || c == z) {continue;}


                    cl_I leftSide = expt_pos(a, x) + expt_pos(b, y);
                    cl_I rightSide = expt_pos(c, z);


                    if (leftSide == rightSide)
                    {
                        //Check if !exp == a^x + b^x = b^x
                        printf("Found solution: %s^%s + %s^%s = %s^%s\n", cdc(a), cdc(x), cdc(b), cdc(y), cdc(c), cdc(z));
                    }
                    //#ifdef DISPLAYSTATS
                        tried++;
                    //#endif
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

    endwin();
    return (EXIT_SUCCESS);
}

int display()
{
    while(true)
    {
        deleteln();
        sleep(DISPLAYINTERVAL);
        cerr << "Expressions tried: " << tried;
    }
    return 0;
}
