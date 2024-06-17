#include "Canvas2D.h"
#include <cstdlib>
#include <sstream>
#include <cmath>
#include <string>

using namespace std;

Canvas2D::Canvas2D() : Canvas2D(DEFAULT_SIZE, DEFAULT_SIZE)   //Default size for the max number of cols and rows
{
}

Canvas2D::Canvas2D(int intRows, int intCols)
{
    CanvasCheck(intRows, 0, MAX_SIZE);
    CanvasCheck(intCols, 0, MAX_SIZE);

    _rows = intRows;
    _cols = intCols;

    _pixels = new Face*[_rows];
    for(int r = 0; r < _rows; r++)
    {
        _pixels[r] = new Face[_cols];
        for(int c = 0; c < _cols; c++)
        {
            Face BlackCanvas;
            _pixels[r][c] = BlackCanvas;
        }

    }
}

Canvas2D::Canvas2D(const Canvas2D& Object) : Canvas2D(Object._rows, Object._cols)
{
    for(int r = 0; r < _rows; r++)
    {
        for(int c = 0; c < _cols; c++)
        {
            _pixels[r][c] = Object._pixels[r][c];
        }
    }

}


Face Canvas2D::getPixelColour(int intRow, int intCol)
{
    CanvasIndex(intRow, intCol);    //This function checks to see if the pixel location is within canvas bounds
    return _pixels[intRow][intCol];  //If it is, return that location
}

void Canvas2D:: setPixelColour(int intRow, int intCol, Face FaceColour)
{
    CanvasIndex(intRow, intCol);
    _pixels[intRow][intCol] = FaceColour;
}


int Canvas2D:: getRows()
{
    return _rows;
}

int Canvas2D:: getCols()
{
    return _cols;
}


std::string Canvas2D::toPPM()
{
    stringstream ssPPM;
    ssPPM << "P3" << endl           //Header that says this is a colour image
          << _cols << ' ' << _rows << endl  //The size of the image
          << "255" << endl;         //Maximum inensity for everything

    for(int r = 0; r < _rows; r++)
    {
        for (int c = 0; c < _cols; c++)
        {
            Face objPixel = _pixels[r][c];
            ssPPM << objPixel.getRed() << ' '
                  << objPixel.getGreen() << ' '
                  << objPixel.getBlue() << ' ';
        }
        ssPPM << endl;
    }
    return ssPPM.str();
}

double distance(int intRow1, int intCol1, int intRow2, int intCol2)
{
    return sqrt(pow(intRow1 - intRow2, 2) +
                pow(intCol1 - intCol2, 2));
}


void Canvas2D::drawFaceCircum(int intRow, int intCol, double dblRadius, Face FaceColour)
{
    for(int r = 0; r < _rows; r++)
    {
        for (int c = 0; c < _cols; c++)
        {
            if(distance(r, c, intRow, intCol) < dblRadius)
            {
                _pixels[r][c] = FaceColour;
            }
        }
    }

}


Canvas2D::~Canvas2D()
{
    for(int r = 0; r < _rows; r++)
    {
        delete [] _pixels[r];
    }
    delete [] _pixels;
}

void Canvas2D::CanvasCheck(int intVal, int intMin, int intMax)
{
    if(intVal < intMin || intVal > intMax)
    {
        exit(ERR_INDEX);
    }
}

void Canvas2D::CanvasIndex(int intRow, int intCol)
{
    CanvasCheck(intRow, 0, _rows);
    CanvasCheck(intCol, 0, _cols);
};
