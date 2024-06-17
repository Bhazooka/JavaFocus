#include "Matrix2D.h"

#include <cassert>
#include <iostream>
#include <sstream>
#include <string>

 using namespace std;

    Matrix2D::Matrix2D() : Matrix2D(DEFAULT_ROWS, DEFAULT_COLS, DEFAULT_VALUE){}

    Matrix2D::Matrix2D(int intRows, int intCols, int intDefaultValue){
    alloc(intRows, intCols, intDefaultValue);
 }

    Matrix2D::Matrix2D(const Matrix2D& objOriginal) : Matrix2D(objOriginal._rows,
    objOriginal._cols, DEFAULT_VALUE){
    clone(objOriginal);
 }

    const Matrix2D& Matrix2D::operator=(const Matrix2D& objRHS){
    if(this != &objRHS){ // Check for self-assignment.
    dealloc();
    alloc(objRHS._rows, objRHS._cols, DEFAULT_VALUE);
    clone(objRHS);
 }
 return *this;
 }

    int& Matrix2D::operator()(int intRow, int intCol){
    enforceRange(intRow, 0, _rows - 1);
    enforceRange(intCol, 0, _cols - 1);
    return _data[intRow][intCol];
 }

    ostream& operator<<(ostream& osLHS, const Matrix2D& objRHS){
    /*
    * We can use the already existing
    * toString member function from P5.
    */
    osLHS << objRHS.toString() << endl;
    return osLHS;
 }

    Matrix2D Matrix2D::operator++(){
    for(int r = 0; r < _rows; r++){
    for(int c = 0; c < _cols; c++){
    // increment every value from the array by 1.
    _data[r][c] += 1;
    }
 }
    return *this;
  }

    string Matrix2D::toString() const{
    stringstream ssReturn;
    for(int r = 0; r < _rows; r++){
    for(int c = 0; c < _cols; c++){
    ssReturn << _data[r][c] << ' ';
 }
    ssReturn << endl;
 }
    return ssReturn.str();
 }

    int Matrix2D::getRows() const{
    return _rows;
 }

    int Matrix2D::getCols() const{
    return _cols;
 }

    int Matrix2D::getValueAt(int intRow, int intCol) const{
    enforceRange(intRow, 0, _rows - 1);
    enforceRange(intCol, 0, _cols - 1);
    return _data[intRow][intCol];
 }

    void Matrix2D::setValueAt(int intRow, int intCol, int intValue){
    enforceRange(intRow, 0, _rows - 1);
    enforceRange(intCol, 0, _cols - 1);
    _data[intRow][intCol] = intValue;
 }

    void Matrix2D::alloc(int intRows, int intCols, int intDefaultValue){
    enforceRange(intRows, MIN_DIMENSION_SIZE, MAX_DIMENSION_SIZE);
    enforceRange(intCols, MIN_DIMENSION_SIZE, MAX_DIMENSION_SIZE);
    _rows = intRows;
    _cols = intCols;
    _data = new int*[_rows];
    for(int r = 0; r < _rows; r++){
    _data[r] = new int[_cols];
    for(int c = 0; c < _cols; c++){
    _data[r][c] = intDefaultValue;
 }
 }
 }

    void Matrix2D::dealloc(){
    assert(_data != nullptr);
    for(int r = 0; r < _rows; r++){
    delete [] _data[r];
    }
    delete [] _data;
 }

    void Matrix2D::clone(const Matrix2D& objOriginal){
    for(int r = 0; r < _rows; r++){
    for(int c = 0; c < _cols; c++){
    _data[r][c] = objOriginal._data[r][c];
    }
    }
    }

    void Matrix2D::enforceRange(int intArg, int intMin, int intMax) const{
    if(intArg < intMin || intArg >intMax){
    cerr << intArg << " must be in [" << intMin
    << ", " << intMax << "]" << endl;
    exit(ERROR_RANGE);
    }
 }

    Matrix2D::~Matrix2D(){
    dealloc();
 }

 void Matrix2D::readValuesFromTXT(string strPath)
 {
     ifstream ifReader;
    int intRow, intCol, intVal;
     ifReader.open(strPath);

     while(ifReader.eof()==false)
     {
         ifReader>>intRow>>intCol>>intVal;
         enforceRange(intRow,0,_rows);
         enforceRange(intCol,0,_cols);
         _data[intRow][intCol]=intVal;
     }




 }

 void Matrix2D::outputRowSumsToConsole()
 {
     int total=0;

     for(int r=0;r<_rows;r++)
     {     total=0;
         for(int c=0;c<_cols;c++)
         {
             total+=_data[r][c];


         }
         cout<<total<<" ";

     }
 }

