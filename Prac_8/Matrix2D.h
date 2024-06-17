#ifndef MATRIX2D_H
#define MATRIX2D_H

#include <iostream>
#include <string>
#include <fstream>

using namespace std;

 enum ERROR_CODE{
    SUCCESS,
    ERROR_ARGS,
    ERROR_RANGE
};

  class Matrix2D{
    public:
    Matrix2D();
    Matrix2D(int intRows, int intCols, int intDefault);
    Matrix2D(const Matrix2D& objOriginal);

    const Matrix2D& operator=(const Matrix2D& objRHS);
    int& operator()(int intRow, int intCol);
    friend std::ostream& operator<<(std::ostream& osLHS, const Matrix2D& objRHS);
    Matrix2D operator++();

    int getRows() const;
    int getCols() const;

    void readValuesFromTXT(string strPath);
    void outputRowSumsToConsole();

    static const int DEFAULT_ROWS = 2;
    static const int DEFAULT_COLS = 2;
    static const int DEFAULT_VALUE = 0;
    static const int MIN_DIMENSION_SIZE = 2;
    static const int MAX_DIMENSION_SIZE = 100000;


 ~Matrix2D();
 private:
    std::string toString() const;
    int getValueAt(int intRow, int intCol) const;
    void setValueAt(int intRow, int intCol, int intValue);
    void alloc(int intRows, int intCols, int intDefaultValue);
    void dealloc();
    void clone(const Matrix2D& objOriginal);
    void enforceRange(int intArg, int intMin, int intMax) const;
    int** _data;
    int _rows;
    int _cols;
   };

 #endif // MATRIX2D_H
