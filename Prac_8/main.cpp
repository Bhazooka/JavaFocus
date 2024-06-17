#include "Exceptions.h"
#include "Matrix2D.h"

#include <iostream>

using namespace std;

int main()
{
    // Testing the fully parameterised constructor.
    Matrix2D objMatrix(10, 10, 1);
    // Testing the copy constructor.
    Matrix2D objCopy = objMatrix;
    // Testing the overloaded assignment operator.
    Matrix2D objSmallerSizedMatrix(2, 2, 5);
    objSmallerSizedMatrix = objCopy;
    try{
        objSmallerSizedMatrix.readValuesFromTXT("array_values.txt");
        objSmallerSizedMatrix.outputRowSumsToConsole();
    }catch(FileException& fe){
        cerr << "A FileException Occurred." << endl;
    }catch(RangeException& re){
        cerr << "A RangeException Occurred." << endl;
    }catch(Exception& e){
        cerr << "An Exception Occurred." << endl;
    }catch(...){
        cerr << "An unknown error occurred." << endl;
    }
    return 0;
}
