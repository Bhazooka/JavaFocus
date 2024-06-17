#include "Matrix2D.h"

#include <iostream>
#include <string>
#include <cstdlib>

using namespace std;

//forward declaration of functions.     I personally dont like using forward declarations and prefer coding it out straight at the top. But it makes code more readable i guess
bool matrixEquals(const Matrix2D& objLHS, const Matrix2D& objRHS);
void populateMatrix(Matrix2D& objMatrix);
void displayMatrix(const Matrix2D& objMatrix);

//9. Main function which demonstrates the functionality of the Matrix2D Class
int main()
{
    Matrix2D objMatrix(5, 5, 1);
    populateMatrix(objMatrix);
    Matrix2D objClone(objMatrix);

    if(matrixEquals(objClone, objMatrix))
    {
        displayMatrix(objClone);
    }

    return SUCCESS;
}

//The functions we use in the main
bool matrixEquals(const Matrix2D& objLHS, const Matrix2D& objRHS)
{
    if(objLHS.getRows() != objRHS.getRows())
        return false;
    if(objLHS.getCols() != objRHS.getCols())
        return false;

    for(int r = 0; r < objLHS.getRows(); r++)
    {
        for(int c = 0; c < objLHS.getCols(); c++)
        {
            if(objLHS.getValueAt( r , c ) != objRHS.getValueAt( r , c))
                return false;
        }
    }
    return true;
}

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

void displayMatrix(const Matrix2D& objMatrix)
{
    cout << objMatrix.toString();
}

