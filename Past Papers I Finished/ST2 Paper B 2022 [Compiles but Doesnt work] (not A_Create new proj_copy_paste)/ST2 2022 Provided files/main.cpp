/*
 * Application entry point.
 * You are welcome to modify this file
 * as you wish according to your preferences.
 */
#include "UJImage.h"

#include <fstream>

using namespace std;

void convToRGB(UJPixel& recPixel);

int main()
{
    /*
     * Testing the constructor for initialising an object using
     * the information provided in the init.txt.
     */
    UJImage objImage, objImage2;
    // Converting all the pixels to the same RGB colour.
    for(int r = 0; r < objImage.getRows(); r++){
        for(int c = 0; c < objImage.getCols(); c++){
            UJPixel recPixel = objImage.getPixel(r, c);
            convToRGB(recPixel);
            objImage.setPixel(r, c, recPixel);
        }
    }
    // Testing the drawing functionality.
    objImage.draw();
    // objLHS = objRHS.
    objImage2 = objImage;
    /*
     * osLHS << objRHS
     * ++objRHS.
     */
    cout << ++objImage2 << endl;
    // Testing the functionality for saving the pixels to a binary file of records.
    objImage.saveToDAT("pixels.dat");
    return SUCCESS;
}

void convToRGB(UJPixel& recPixel){
    recPixel.intRed = 10;
    recPixel.intGreen = 100;
    recPixel.intBlue = 60;
}
