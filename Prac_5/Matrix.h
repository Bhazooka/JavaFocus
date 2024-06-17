#ifndef MATRIX_H_INCLUDED
#define MATRIX_H_INCLUDED

#include <sstream>
#include <iostream>
#include <cstdlib>


class Matrix2D
{
public:
    //constructors
    Matrix2D();                                     //Default
    Matrix2D(int intRows, int intCols, int intVal); //Parameterised
    Matrix2D(const Matrix2D& self);                 //Copy

    //Accessor Members
    int getRows() const;
    int getCols() const;
    int getMatrix (int intRows, int intCols);
    //Mutatotr Members
    void setMatrix(int intRows, int intCols, int intVal);

    std::string toString();

    static const int DEFAULT_VALS = 1;

    ~Matrix2D();    //destructor

private:
    //Member functions
    void duplicate(const Matrix2D& self);
    void allocMem(int intRows, int intCols, int intVal);
    //value place holders
    int _rows;
    int _cols;

    int** Matrix;   //2d array

};




#endif // MATRIX_H_INCLUDED
