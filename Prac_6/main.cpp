#include "Matrix2D.h"
#include <iostream>
#include <string>
#include <cstdlib>

using namespace std;

//Function from memo
void populateMatrix(Matrix2D& objMatrix);

int main()
{
    Matrix2D objMatrix(Matrix2D::DEFAULT_DIM, Matrix2D::DEFAULT_DIM, Matrix2D::DEFAULT_VAL);
    populateMatrix(objMatrix);


    //Test if Overloaded stream insertion operator (<<) works correctly
    cout << "Testing '<<' operator" << endl;
    cout << objMatrix << endl;

    //"Testing Overloaded (=) operator"
    Matrix2D BlankMatrix;
    cout << "Testing Overloaded '=' operator: DEEP COPY" << endl
         << "Displaying content within Object BlankMatrix Before" << endl
         << BlankMatrix;

    BlankMatrix = objMatrix;
    cout << "Displaying content within Object BlankMatrix After" << endl
         << BlankMatrix << endl;

    //Test if Overloaded Invocation Operator (()) works
    cout << "Testing '()' operator" << endl
         << "To test this, We change a specific index to value 0" << endl
         << "This number is at index (0,0)" << endl;

    objMatrix(0,0) = 0;
    for(int r = 0; r < objMatrix.getRows(); r++)
    {
        for(int c = 0; c < objMatrix.getCols(); c++)
        {
            cout << objMatrix(r, c) << ' ';
        }
        cout << endl;
    }

    //Testing for Overloaded ++ operator
    Matrix2D BlankMatrix2;
    cout << endl << "Testing Overloaded (++) Operator" << endl;
    cout << "Content in BlankMatrix Before" << endl
         << BlankMatrix2;

    BlankMatrix2++;
    cout << "Content in BlankMatrix After" << endl
         << BlankMatrix2;

    return SUCCESS;
}


//Function from MEMO
void populateMatrix(Matrix2D& objMatrix)
{
    for(int r = 0; r < objMatrix.getRows(); r++)
    {
        for(int c = 0; c < objMatrix.getCols(); c++)
        {
            objMatrix.setValue(r, c, rand() % 10);
        }
    }
}

