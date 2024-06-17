
#ifndef Canvas2D2D_H_INCLUDED
#define Canvas2D2D_H_INCLUDED

#include <cstdlib>
#include <string>
#include "CanvasCapper.h"


class Canvas2D
{
public:
    Canvas2D();
    Canvas2D(int intRows, int intCols);
    Canvas2D(const Canvas2D& Object);

    CanvasCapper getPixelColour(int intRow, int intCol);
    void setPixelColour(int intRow, int intCOl, CanvasCapper FaceColour);

    int getRows();
    int getCols();

    std::string toPGM();

    void drawFaceCircum(int intRow, int intCol, double dblRadius, CanvasCapper FaceColour);

    ~Canvas2D();

    static const int ERR_INDEX = -2;
    static const int MAX_SIZE = 1000000;
    static const int DEFAULT_SIZE = 100;

private:
    void CanvasIndex(int intRow, int intCol);
    void CanvasCheck(int intVal, int intMin, int intMax);

    int _rows;
    int _cols;
    CanvasCapper** _pixels;

};



#endif // Canvas2D2D_H_INCLUDED
