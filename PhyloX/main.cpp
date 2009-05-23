/* 
 * File:   main.cpp
 * Author: uli
 *
 * Created on 23. Mai 2009, 20:54
 */

#include <stdlib.h>
#include <string>
#include <iostream>
#include <fstream>
#include <map>
#include <limits.h>

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

vector<string> names;
vector<string> sequences;

/*
 * 
 */
int
main (int argc, char** argv)
{
    if(argc < 2)
        {
            cout << "Usage: phylox paramfile\n";
        }
    ifstream f (argv[1]);
    string buf;
    size_t index;
    //Read in the data
    for(int i = 0;true;i++)
        {
            if(!f.good()) {break;}
            f >> buf;
            index = buf.find(':');
            data[buf.substr (0,size)] = buf.substr (buf);
            
        }
    size_t species = data.size ();
    uint* speciesDistance = new int[species * species];

    //Compute the Levenshtein distance matrix between each two species

    for(int i = 0; i < species; i++)
        {
            for(int j = 0; j < species; j++)
                {
                    speciesDistance[j * species + i] = levenshtein (sequences[i], sequences[j]);
                }
        }

    cout << "\\documentclass[12pt,a4paper,ngerman]{article}";
    cout << "\\usepackage[T1]{fontenc}";
    cout << "\\usepackage[utf8]{inputenc}";
    cout << "\\usepackage[ngerman]{babel}";
    cout << "\\parindent=0pt";
    cout << "\\pagestyle{empty}";
    cout << "\\usepackage{pstricks-add,graphicx}";
    cout << "\\usepackage[margin=1cm]{geometry}";
    cout << "\\newsavebox\\PSTBox";
    cout << "\\begin{document}";
    cout << "\\psset{framesep=2mm,arrowscale=1.75}";
    cout << "\\begin{pspicture}(-1,-0.25)(8,9)";
    cout << "\\psframe*[linecolor=black!15](1.1,8)(7.5,9.7)";

    uint minDist = UINT_MAX;
    uint val;
    uint *specOrder = new uint[species];
    for(uint i = 0; i < species; i++)
        {
            for(uint j = 0; j < species; j++)
                {
                    val = speciesDistance[j * species + i];
                    if(val < min)
                        {
                            specOrder[j] = i;
                            min = val;
                        }
                }
        }

    bool *drawn = new bool[species];
    for(uint i = 0; i < species; i++)
        {
            drawn[i] = false;
        }
    for(uint i = 0; i < species; i++)
    {
            if(drawn[i]) {continue;}
            cout << "\\Rbox{" << i << "}{" << names[i] << "} \\hspace{5mm}\n";
            cout << "\\Rbox{" << specOrder[i] << "}{" << names[specOrder[i]] << "} \\hspace{10mm}\n";
            drawn[i] == true;
    }

    cout << "\\end{pspicture}\\end{document}";
    delete[] drawn;
    delete[] speciesDistance;
    delete[] specOrder;
    return (EXIT_SUCCESS);
}

