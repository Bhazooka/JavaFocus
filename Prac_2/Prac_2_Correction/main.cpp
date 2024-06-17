#include "Canvas2D.h"

#include <ctime>
#include <iostream>


using namespace CanvasSpace;

int main()
{

    Canvas2D objCanvas(700, 700, 255);

    objCanvas.drawCircle(350, 350, 300, objCanvas.getBGColour() - 100);

    objCanvas.drawCircle(225, 225, 50, 0);

    objCanvas.drawCircle(225, 475, 50, 0);

    objCanvas.drawRectangle(325, 325, 50, 50, 0);

    objCanvas.drawRectangle(450, 250, 50, 200, 0);

    cout << objCanvas.toPGM() << endl;

    return 0;
}
