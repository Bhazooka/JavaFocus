#ifndef MATRIX2D_H_INCLUDED
#define MATRIX2D_H_INCLUDED

#include <string>

enum ERROR_CODE
{
    SUCCESS,
    ERROR_RANGE
};

//1.Create matrix2D Class
class Matrix2D
{
public:
    //2.1 Dinamically allocated 2 dimensional integer array (Member Function). Basically the functions that will do the allocating of mem
    Matrix2D();                                             //3. no args constructor
    Matrix2D(int intRows, int intCols, int intDefaultValue);//4. Fully parameterized const takes a set of three integers representing rows, cols, initial value
    Matrix2D(const Matrix2D& objOriginal);                  //5. Copy constructor

    //Operator overloading.
    //Basically just changing what each operators can do...
    Matrix2D& operator=(const Matrix2D& objRHS);
    int& operator()(int intRow, int intCol);
    friend std::ostream& operator<<(std::ostream& sLHS, const Matrix2D& objRHS);
    Matrix2D operator++(int);

    //Accessor member function for rows and cols and individual values in underlying array
    int getRows() const;
    int getCols() const;
    int getValueAt(int intRow, int intCol) const;
    //Mutator for changing the underlying values in the array (by way of their rows/cols values)
    void setValue(int intRow, int intCol, int intValue);

    static const int DEFAULT_DIM = 3;
    static const int DEFAULT_VAL = 0;
    static const int MIN_SIZE = 0;
    static const int MAX_SIZE = 100;

    ~Matrix2D();    //6. Destructor

private:
    void alloc(int intRows, int intCols, int intDefaultValue);
    void dealloc();
    void clone(const Matrix2D& objOriginal);
    void RangeCheck(int intArg, int intMin, int intMax) const;
    //2.1 Dinamically allocated 2 dimensional integer array (Member Variable)
    typedef int** data2D;
    data2D _data;
    int _rows;
    int _cols;
    int _val;

};


#endif // MATRIX2D_H_INCLUDED
