#include "Matrix2D.h"

#include <cassert>
#include <iostream>
#include <sstream>
#include <string>

using namespace std;

//3.Perform constructor chaining
Matrix2D::Matrix2D() : Matrix2D(DEFAULT_ROWS, DEFAULT_COLS, DEFAULT_VALUE)
{
}

//4.Now that we have our alloc function done, we use it here
Matrix2D::Matrix2D(int intRows, int intCols, int intDefaultValue)
{
    //These values will be passed into the function and the task of allocating mem and creating the 2D array will be delegated to the alloc function
    alloc(intRows, intCols, intDefaultValue);
}

//5.now that we have our clone function we can use it in here
Matrix2D::Matrix2D(const Matrix2D& objOriginal) : Matrix2D(objOriginal._rows, objOriginal._cols, DEFAULT_VALUE)
{
    clone(objOriginal);
}

//6.
string Matrix2D::toString() const
{
    stringstream ssReturn;
    for(int r = 0; r < _rows; r++)
    {
        for(int c = 0; c < _cols; c++)
        {
            ssReturn << _data[r][c] << ' ';
        }
        ssReturn << endl;
    }
    return ssReturn.str();
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

//7.
int Matrix2D::getValueAt(int intRow, int intCol) const
{
    enforceRange(intRow, 0, _rows - 1); //Make sure the value of rows is between 0 and total number of rows - 1 (because we start counting from 0)
    enforceRange(intCol, 0, _cols - 1);
    return _data[intRow][intCol];
}

//8.
void Matrix2D::setValue(int intRow, int intCol, int intValue)
{
    enforceRange(intRow, 0, _rows - 1); //Make sure the value of rows is between 0 and total number of rows - 1 (because we start counting from 0)
    enforceRange(intCol, 0, _cols - 1);
    _data[intRow][intCol] = intValue;
}



//****************************************************************************************************************************************
//*******I suggest you start with the functions that you'll actually use in your other functions******

//2.Alloc mem will be used in the matrix2D parameterised constructor to allocate mem. Alloc comes after enforce range because we use enforce range to check values in it.
void Matrix2D::alloc(int intRows, int intCols, int intDefaultValue)
{
    enforceRange(intRows, MIN_DIMENSION_SIZE, MAX_DIMENSION_SIZE);
    enforceRange(intCols, MIN_DIMENSION_SIZE, MAX_DIMENSION_SIZE);

    //Here we pass the values of our variables to allocate rows and cols, into the private variables from our class
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

//4. clone function. IDK what this is really used for
void Matrix2D::clone(const Matrix2D& objOriginal)
{
    for(int r = 0; r <_rows; r++)
    {
        for(int c = 0; c < _cols; c++)
        {
            _data[r][c] = objOriginal._data[r][c];  //Data at [row_variable][col_variable] = dereferenced value from Matrix2D 2 dimentional array with allias called objOriginal at its current [row_variable][col_variable]
        }
    }
}

//1. start at enforce range function, which will be used to check validate rows and cols values to alloc mem
void Matrix2D::enforceRange(int intArg, int intMin, int intMax) const
{
    if(intArg < intMin || intArg > intMax)
    {
        cerr << intArg << "Must be in [" << intMin
             << ", " << intMax << "]" << endl;

        exit(ERROR_RANGE);
    }
}
//****************************************************************************************************************************************


//I think the destructor should go at the end based on some memos
//lastly. Now that we have our dealloc function, we use it in the destructor
Matrix2D::~Matrix2D()
{
    dealloc();

}
