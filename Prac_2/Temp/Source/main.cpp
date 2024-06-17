#include <iostream>
#include "Canvas2D.h"

using namespace std;
using namespace CanvasSpace;

int main()
{
    Canvas Face;
    int intCRow = 250;
    int intCCol = 250;
    int intRadius = 100;
    RGBPixel recGreyFace {128,128,128};

    RGBPixel DrawLEye{0,0,0};
    RGBPixel DrawREye{0,0,0};

    Canvas CreateObj(Face);
    CreateObj.CreateFace(intCRow, intCCol, intRadius, recGreyFace);
    CreateObj.CreateFace(125, 150, 25, DrawLEye);
    CreateObj.CreateFace(275, 150, 25, DrawREye);

    cout << CreateObj.toPPM() << endl;


    return 0;
}
