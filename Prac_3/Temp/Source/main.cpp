//Used sirs code
#include <iostream>

#include "Canvas2D.h"

using namespace std;

int main()
{
    Canvas2D ObjFace(1024,1024);
    CanvasCapper Grey(180,250,180);
    ObjFace.drawFaceCircum(612,612,100, Grey);
    Canvas2D objCopy(ObjFace);
    cout << objCopy.toPGM() << endl;

    return 0;
}

