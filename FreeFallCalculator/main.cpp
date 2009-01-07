/* 
 * File:   main.cpp
 * Author: uli
 *
 * Created on 7. Januar 2009, 16:42
 */

#include <stdlib.h>
#include <iostream>
#include <boost/format.hpp>

using namespace std;
using namespace boost;

/*
 * 
 */
int
main (int argc, char** argv)
{
    /**
     * Variable predeclaration
     */
    static long double h; //Start height
    static long double interval; //Time interval
    static long double t = 0.0L; //Time already calculated
    static long double v = 0.0L; //Velocity
    static long double a; //Acceleration
    /**
     * Get parameters from stdin
     */
    cout << "h (m): ";
    cin >> h;
    cout << "Interval: ";
    cin >> interval;
    cout << "Acceleration:";
    cin >> a;
    /**
     * Main loop: Calculates until we have reached the ground
     */
    while(h > 0)
        {
            v += a * interval;
            h -= v * interval;
            t += interval;
        }

    cout << format ("Process took %.30Lf seconds") % t << endl;
    return (EXIT_SUCCESS);
}

