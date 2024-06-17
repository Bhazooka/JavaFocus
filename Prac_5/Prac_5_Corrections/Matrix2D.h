#ifndef MATRIX2D_H_INCLUDED
#define MATRIX2D_H_INCLUDED

#include <string>

enum ERROR_CODE
{
    SUCCESS,
    ERROR_ARGS,
    ERROR_RANGE
};

//1. Create matrix2D Class
class Matrix2D
{
public:
    //2.1 Dinamically allocated 2 dimensional integer array (Member Function). Basically the functions that will do the allocating of mem
    Matrix2D();                                             //3. no args constructor
    Matrix2D(int intRows, int intCols, int intDefaultValue);//4. Fully parameterized const takes a set of three integers representing rows, cols, initial value
    Matrix2D(const Matrix2D& objOriginal);                  //5. Copy constructor

    //9.A toString function without parameters and returns a string representation of the internal array
    std::string toString() const;

    //7. Accessor member function for rows and cols and individual values in underlying array
    int getRows() const;
    int getCols() const;
    int getValueAt(int intRow, int intCol) const;
    //8. Mutator for changing the underlying values in the array (by way of their rows/cols values)
    void setValue(int intRow, int intCol, int intValue);

    static const int DEFAULT_ROWS = 2;
    static const int DEFAULT_COLS = 2;
    static const int DEFAULT_VALUE = 0;
    static const int MIN_DIMENSION_SIZE = 0;
    static const int MAX_DIMENSION_SIZE = 1000000;

    ~Matrix2D();    //6. Destructor

private:
    void alloc(int intRows, int intCols, int intDefaultValue);
    void dealloc();
    void clone(const Matrix2D& objOriginal);
    void enforceRange(int intArg, int intMin, int intMax) const;
    //2.1 Dinamically allocated 2 dimensional integer array (Member Variable)
    int** _data;
    int _rows;
    int _cols;

};


#endif // MATRIX2D_H_INCLUDED
