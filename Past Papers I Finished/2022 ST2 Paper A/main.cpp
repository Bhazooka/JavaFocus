/*
 * Application entry point.
 * You are welcome to modify this file
 * as you wish according to your preferences.
 */
#include "UJImage.h"

#include <iostream>

using namespace std;

int main(){
    // Testing the constructor with parameters.
    UJImage objImage, objImage2;
    // Testing the functionality for reading pixels from a text file.
    objImage.loadFromTxt("pixels.txt");
    // Testing the overloading function invocation operator.
    for(int r = 0; r < objImage.getRows(); r++){
        for(int c = 0; c < objImage.getCols(); c++){
            // objRHS(r, c) = y.
            //++objImage(r, c).int_Intensity;
        }
    }
    // Testing the drawing functionality.
    //objImage.draw();
    // ObjLHS = objRHS.
    objImage2 = objImage;
    // osLHS << objRHS.
    cout << objImage2;
    // Testing the functionality for saving the pixels to a binary file of records.
    objImage2.saveInfoToDAT("pixels.dat");
    return 0;
}
