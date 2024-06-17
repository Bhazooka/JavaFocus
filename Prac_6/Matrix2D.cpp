#include "Matrix2D.h"

#include <cassert>
#include <iostream>
#include <sstream>
#include <string>

using namespace std;

//3.Perform constructor chaining
Matrix2D::Matrix2D() : Matrix2D(DEFAULT_DIM, DEFAULT_DIM, DEFAULT_VAL)
{
}

//4.Now that we have our alloc function done, we use it here
Matrix2D::Matrix2D(int intRows, int intCols, int intDefaultValue)
{
    //These values will be passed into the function and the task of allocating mem and creating the 2D array will be delegated to the alloc function
    alloc(intRows, intCols, intDefaultValue);
}

//5.now that we have our clone function we can use it in here
Matrix2D::Matrix2D(const Matrix2D& objOriginal) : Matrix2D(objOriginal._rows, objOriginal._cols, DEFAULT_VAL)
{
    clone(objOriginal);
}


/*
Operator overloading
After overloading this operator, we'll now be able to assign an entire to another object (make an object equal to another object).
This is currently not possible with the default "=" operator function.
All operators are functions
*/
Matrix2D& Matrix2D::operator=(const Matrix2D& objRHS)
{
    if(this != &objRHS)
    {
        dealloc();
        alloc(objRHS._rows, objRHS._cols, objRHS._val); //This step is cruitial, once the mem is freed, you need to reallocate to create new copy
        clone(objRHS);
    }
}

int& Matrix2D::operator()(int intRow, int intCol)
{
    //as much as i hate range checking in these practicals, Its good programming practice
    RangeCheck(intRow, 0, _rows - 1);
    RangeCheck(intCol, 0, _cols - 1);
    //This says: retrieve the data at the location of the variable intRow and intCol.
    //This data will be used to assign to a new object, demonstrating a deep copy, to display, demosnstrating << operator, to add demonstrating ++
    return _data[intRow][intCol];

}

std::ostream& operator<<(std::ostream& sLHS, const Matrix2D& objRHS)
{
    for(int r = 0; r < objRHS._rows; r++)
    {
        for(int c = 0; c < objRHS._cols; c++)
        {
            int recData = objRHS._data[r][c];     //If something goes wrong then its probs this line
            sLHS << recData << ' ' ;
        }
        sLHS << endl;
    }
    return sLHS;
}

//This Operator (++) will now allow us to increment all the numbers in a matrix (2D array)
//Our numbers are initially 0 all over, we are expecting it to be 1 after using this operator on the object
Matrix2D Matrix2D::operator++(int)
{
    Matrix2D objOld;
    for(int r = 0; r < _rows; r++)
    {
        for(int c = 0; c < _cols; c++)
        {
            if(_data[r][c] < 10)
                _data[r][c]++;
        }
    }
    return objOld;

}

//7. Now you can come back here and start with returning the rows and cols
//all you do here is return the rows
int Matrix2D::getRows() const
{
    return _rows;
}
//8. Return cols
int Matrix2D::getCols() const
{
    return _cols;
}

int Matrix2D::getValueAt(int intRow, int intCol) const
{
    RangeCheck(intRow, 0, _rows - 1); //Make sure the value of rows is between 0 and total number of rows - 1 (because we start counting from 0)
    RangeCheck(intCol, 0, _cols - 1);
    return _data[intRow][intCol];
}


void Matrix2D::setValue(int intRow, int intCol, int intValue)
{
    RangeCheck(intRow, 0, _rows - 1); //Make sure the value of rows is between 0 and total number of rows - 1 (because we start counting from 0)
    RangeCheck(intCol, 0, _cols - 1);
    _data[intRow][intCol] = intValue;
}



//****************************************************************************************************************************************
//*******I suggest you start with the functions that you'll actually use in your other functions (Helper functions)******

//2.Alloc mem will be used in the matrix2D parameterised constructor to allocate mem. Alloc comes after enforce range because we use enforce range to check values in it.
void Matrix2D::alloc(int intRows, int intCols, int intDefaultValue)
{
    RangeCheck(intRows, MIN_SIZE, MAX_SIZE);
    RangeCheck(intCols, MIN_SIZE, MAX_SIZE);

    //Here we pass the values into the private variables from our class, to allocate rows and cols
    _rows = intRows;
    _cols = intCols;

    //here we're creating our 2D array, and initializing its data to defaultValue in the entire array
    _data = new int*[_rows];
    for(int r = 0; r < _rows; r++)
    {
        _data[r] = new int[_cols];
        for(int c = 0; c < _cols; c++)
        {
            _data[r][c] = intDefaultValue;
        }
    }

}

//3. One of the easiest functions Dealloc, which will be used to deallocate mem when done, the function is used in the Deconstructor (~Matrix2D)
void Matrix2D::dealloc()
{
    assert(_data != nullptr);
    for(int r = 0; r < _rows; r++)
    {
        delete[] _data[r];
    }
    delete[] _data;
}

//4. clone function.
void Matrix2D::clone(const Matrix2D& objOriginal)
{
    for(int r = 0; r <_rows; r++)
    {
        for(int c = 0; c < _cols; c++)
        {
            //Data at [row_variable][col_variable] = dereferenced value from Matrix2D 2D array with allias called objOriginal at its current [row_variable][col_variable]
            _data[r][c] = objOriginal._data[r][c];
        }
    }
}

//1. start at enforce range function, which will be used to validate rows and cols values to alloc mem
void Matrix2D::RangeCheck(int intArg, int intMin, int intMax) const
{
    if(intArg < intMin || intArg > intMax)
    {
        cerr << intArg << "Must be in [" << intMin
             << ", " << intMax << "]" << endl;

        exit(ERROR_RANGE);
    }
}
//****************************************************************************************************************************************


//lastly. Now that we have our dealloc function, we use it in the destructor
Matrix2D::~Matrix2D()
{
    dealloc();
}
