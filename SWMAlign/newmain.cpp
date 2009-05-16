/* 
 * File:   newmain.cpp
 * Author: uli
 *
 * Created on 16. Mai 2009, 17:35
 */

#include <stdlib.h>
#include <string>
#include <iostream>
#include <cstdio>

using namespace std;
//using namespace std::tr1;

template<class T>
class Matrix2d
{
public:

    Matrix2d (const size_t x, const size_t y)
    {
        this->xExt = x;
        this->yExt = y;
        array = new T[x * y];
        //Init all elements to zero
        for(int i = 0; i < (x*y); i++)
            {
                array[i] = 0;
            }
    }

    ~Matrix2d ()
    {
        delete array;
    }

    T
    getAt(size_t ix, size_t iy)
    {
        return array[(iy * xExt) +  ix];
    }
    void
    setAt(size_t ix, size_t iy, T val)
    {
        array[(iy * xExt) +  ix] = val;
    }

private:
    T* array;
    size_t xExt;
    size_t yExt;
};

uint pI = 4;
uint pD = 5;
uint pS = 1;

class Point
{
    uint x;
    uint y;
};

#define val(a,b,c) (a<<1 + b<<2 + c<<3)
#define insval(v) (v & 1)
#define subval(v) (v & 2)
#define delval(v) (v & 4)

string seq1, seq2;

void traceback(int x, int y, Matrix2d<char>& sources, string s)
{
    printf("x: %i y: %i s: %i\n", x, y, (int)sources.getAt (x,y));
    if(x == 0 && y == 0)
        {
            cout << s << endl;
        }
    char c = sources.getAt (x,y);
    if(insval(c) > 0)
        {
            traceback(x-1, y, sources, s + seq2.at (y));
        }
    if(subval(c) > 0)
        {
            traceback(x-1, y-1, sources, s + "x");
        }
    if(delval(c) > 0)
        {
            traceback(x, y-1, sources, s + "-");
        }
}
/*
 * 
 */
int
main (int argc, char** argv)
{

    //Read in the sequences
    cout << "Sequence 1: "; cin >> seq1;
    cout << "Sequence 2: "; cin >> seq2;
    size_t s1Len = seq1.length ();
    size_t s2Len = seq2.length ();
    //Init the matrix
    Matrix2d<uint> mat(s1Len, s2Len);
    Matrix2d<char> sources(s1Len, s2Len);
    //Main loop
    for(size_t x = 1;x < s1Len; x++)
        {
            for(size_t y = 1; y < s2Len; y++)
                {
                    uint ins = mat.getAt(x-1,y) + pI;
                    uint sub = mat.getAt(x-1,y-1) + pS;
                    uint del = mat.getAt(x,y-1) + pD;

                    //Find the minimum
                    uint min = ins;
                    if(sub < min)
                        {
                            min = sub;
                        }
                    if(del < min)
                        {
                            min = del;
                        }

                    mat.setAt (x,y,min);

                    char s = 0;
                    if(ins == min)
                        {
                            s += 1;
                        }
                    if(sub == min)
                        {
                            s += 2;
                        }
                    if(del == min)
                        {
                            s += 4;
                        }

                    //char s = val(ins == min, sub == min, del == min);
                    sources.setAt(x,y,s);
                }
        }

    traceback(s1Len-1, s2Len-1, sources, "");
    cout << "Score: " << mat.getAt(s1Len-1,s2Len-1) << endl;
    return (EXIT_SUCCESS);
}

