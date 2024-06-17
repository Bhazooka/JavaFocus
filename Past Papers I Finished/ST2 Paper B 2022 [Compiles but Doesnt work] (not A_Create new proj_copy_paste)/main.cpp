/*
 * Application entry point.
 * You are welcome to modify this file
 * as you wish according to your preferences.
 */
#include "UJImage.h"
#include "Exception.h"

#include <fstream>

using namespace std;

void convToRGB(UJPixel& recPixel);

int main()
{
    try
    {
    /*
     * Testing the constructor for initialising an object using
     * the information provided in the init.txt.
     */
    UJImage objImage, objImage2;
    // Converting all the pixels to the same RGB colour.
    for(int r = 0; r < objImage.getRow(); r++){
        for(int c = 0; c < objImage.getCol(); c++){
            UJPixel recPixel = {0,0,0};
            //toPPM(recPixel);
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
    objImage.savetxt("pixels.dat");
    }
    catch(InFileOpenException& fe)
    {
        cerr << fe.message() << endl;
    }
    catch(OutFileOpenException& fe)
    {
        cerr << fe.message() << endl;
    }
    catch(Exception& fe)
    {
        cerr << fe.message() << endl;
    }

    return 0;

}

void convToRGB(UJPixel& recPixel){
    recPixel.Red = 10;
    recPixel.Green = 100;
    recPixel.Blue = 60;
}
