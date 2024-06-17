#include "Matrix.h"
#include <string>
#include <sstream>

Matrix2D::Matrix2D()
{

}

Matrix2D::Matrix2D(int intRows, int intCols, int intVal)
{
    _rows = intRows;
    _cols = intCols;
    allocMem(intRows, intCols, intVal);
}

void Matrix2D::allocMem(int intRows, int intCols, int intVal)
{
    Matrix = new int* [intRows];
    for(int r = 0; r < _rows; r++)
    {
        Matrix[r] = new int[intCols];
        for(int c = 0; c < _cols; c++)
        {
            Matrix[r][c] = intVal;
        }
    }
}

Matrix2D::Matrix2D(const Matrix2D& self)
{
    _rows = self._rows;
    _cols = self._cols;

    Matrix = new int* [self._rows];
    for(int r = 0; r < _rows; r++)
    {
        Matrix[r] = new int[self._cols];
        for(int c = 0; c < _cols;c++)
        {
            Matrix[r][c] = DEFAULT_VALS;
        }
    }
    duplicate(self);
}

int Matrix2D::getRows() const
{
    return _rows;
}

int Matrix2D::getCols() const
{
    return _cols;
}

int Matrix2D::getMatrix (int intRows, int intCols)
{
    return Matrix[intRows][intCols];
}

void Matrix2D::setMatrix(int intRows, int intCols, int intVal)
{
    Matrix[intRows][intCols] = intVal;

}

std::string Matrix2D::toString()
{
    std::stringstream ss;
    for(int r = 0; r < _rows; r++)
    {
        for(int c = 0; c < _cols; c++)
        {
            ss << Matrix[r][c] << ' ';

        }
        ss << std::endl;
    }
    return ss.str();
}

void Matrix2D::duplicate(const Matrix2D& self)
{
    for(int r = 0; r < _rows; r++)
    {
        for(int c = 0; c < _cols; c++)
        {
            Matrix[r][c] = self.Matrix[r][c];
        }
    }

}

Matrix2D::~Matrix2D()
{
    for(int r = 0; r < _rows; r++)
    {
        delete[] Matrix[r];
    }
    delete Matrix;
    Matrix = nullptr;

}
