#include <iostream>
#include "Matrix.h"

using namespace std;

//Default values
static const int DEFAULT_ROWS = 10;
static const int DEFAULT_COLS = 10;

int main()
{
    Matrix2D ObjtwoD(DEFAULT_ROWS, DEFAULT_COLS, 0);
    Matrix2D ObjtwoD_2(ObjtwoD);
    cout << ObjtwoD.toString() << endl;


    return 0;
}
