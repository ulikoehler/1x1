/* 
 * File:   newmain.cpp
 * Author: uli
 *
 * Created on 16. Mai 2009, 17:35
 */

#include <stdlib.h>
#include <string>
#include <iostream>

using namespace std;
//using namespace std::tr1;

class Matrix2d
{
public:

    Matrix2d (const uint& x, const uint& y)
    {
        this->xExt = x;
        this->yExt = y;
        array = new uint[x * y];
    }

    ~Matrix2d ()
    {
        delete array;
    }

    
    uint*
    operator[](const size_t index)
    {
        return array + (index * yExt);
    }

    uint*
    operator()(size_t ix, size_t iy)
    {
        return array + ((iy * xExt) +  ix);
    }

private:
    uint* array;
    uint xExt;
    uint yExt;
};

/*
 * 
 */
int
main (int argc, char** argv)
{
    uint insertionPenalty;
    string seq1, seq2;

    //Read in the sequences
    cout << "Sequence 1: "; cin >> seq1;
    cout << "Sequence 2: "; cin >> seq2;
    int s1Len = seq1.length ();
    int s2Len = seq2.length ();
    //Init the matrix
    Matrix2d mat(s1Len, s2Len);
    //Init the first row and col to zero
    for(int i = 0; i < s1Len; i++) //Row
        {
            mat[i][0] = 0;
        }
    for(int i = 0; i < s2Len; i++) //Col
        {
            mat[0][i] = 0;
        }
    return (EXIT_SUCCESS);
}

