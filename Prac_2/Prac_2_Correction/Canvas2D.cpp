#include "Canvas2D.h"

#include <sstream>
#include <cmath>
#include <iostream>

namespace CanvasSpace
{
    Canvas2D::Canvas2D() : Canvas2D(DEFAULT_ROWS, DEFAULT_COLS, DEFAULT_BG_COL)
    {

    }

    Canvas2D::Canvas2D(int intRows, int intCols, int intBGColour)
    {
        alloc(intRows, intCols, intBGColour);
    }

    Canvas2D::Canvas2D(const Canvas2D& objOriginal) //the & passes by reference
    {
        alloc(objOriginal._rows, objOriginal._cols, DEFAULT_BG_COL);
        clone(objOriginal);
    }

    string Canvas2D::toPGM() const
    {
        stringstream ssPPM;
        ssPPM << "P2" << endl
              << _cols << ' ' << _rows << endl
              << 255 << endl;
        for(int r = 0; r < _rows; r++)
        {
            for(int c = 0; c < _cols; c++)
            {
                ssPPM << _pixels[r][c] << ' ';
            }
            ssPPM << endl;
        }
        return ssPPM.str();
    }

    void Canvas2D::drawCircle(int intCRow, int intCCol, int intRadius, int intPixel)
    {
        for(int r = 0; r < _rows; r++)
        {
            for (int c = 0; c < _cols; c++)
            {
                if(distance(r, intCRow, c, intCCol) <= intRadius)
                {
                    _pixels[r][c] = intPixel;
                }
            }
        }
    }

    void Canvas2D::drawRectangle(int intRow, int intCol, int intHeight, int intLength, int intPixel)
    {
        for(int r = 0; r < _rows; r++)
        {
            for(int c = 0; c < _cols; c++)
            {
                if(r >= intRow && r <= intRow + intHeight)
                {
                    if(c >= intCol && c <= (intCol + intLength))
                        _pixels[r][c] = intPixel;
                }


            }

        }
    }

    int Canvas2D::getBGColour() const
    {
        return _bgColour;
    }

    Canvas2D::~Canvas2D()
    {
        dealloc();
    }

    void Canvas2D::alloc(int intRows, int intCols, int intBGColour)
    {
        _rows = intRows;
        _cols = intCols;
        _bgColour = intBGColour;
        _pixels = new int*[_rows];
        for(int r = 0; r < _rows; r++)
        {
            _pixels[r] = new int[_cols];
            for(int c = 0; c < _cols; c++)
            {
                _pixels[r][c] = _bgColour;
            }
        }

    }

    void Canvas2D::dealloc()
    {
        for(int r = 0; r < _rows; r++)
        {
            delete[] _pixels[r];
        }
        delete[] _pixels;
    }


    void Canvas2D::clone(const Canvas2D& objOriginal)
    {
        for(int r = 0; r < _rows; r++)
        {
            for(int c = 0; c < _cols; c++)
            {
                _pixels[r][c] = objOriginal._pixels[r][c];
            }
        }
    }

    void Canvas2D::enforceRange(int intArg, int intMin, int intMax) const
    {
        if(intArg < intMin || intArg > intMax)
        {
            cerr << intArg << " must be in [" << intMin << ", " << intMax << "]" << endl;
            exit(ERROR_RANGE);
        }
    }

    double Canvas2D::distance(int intX1, int intX2, int intY1, int intY2) const
    {
        return sqrt(pow(intX1 - intX2, 2) + pow(intY1 - intY2, 2));
    }
}
