#include "Canvas2D.h"

#include <sstream>
#include <cmath>

namespace CanvasSpace
{

    Canvas::Canvas() : Canvas(1024,1024, {DEFAULT_RED,DEFAULT_GREEN,DEFAULT_BLUE})
    {

    }

    Canvas::Canvas(int intRows, int intCols, RGBPixel recDefault)
    {
        AllocMem(intRows, intCols, recDefault);
    }

    Canvas::Canvas(const Canvas& self)
    {
        AllocMem(self._rows, self._cols, {DEFAULT_RED, DEFAULT_GREEN, DEFAULT_BLUE});

        for(int r = 0; r < _rows; r++)
        {
            for(int c = 0; c < _cols; c++)
            {
                _pixels[r][c] = self._pixels[r][c];
            }
        }
    }

    string Canvas::toPPM() const
    {
        stringstream ssPPM;
        ssPPM << "P3" << endl
              << _cols<< ' ' << _rows << endl
              << 255 << endl;
        for(int r = 0; r < _rows; r++)
        {
            for(int c = 0; c < _cols; c++)
            {
                ssPPM << _pixels[r][c].intRed << ' '
                      << _pixels[r][c].intGreen << ' '
                      << _pixels[r][c].intBlue << ' ';
            }
            ssPPM << endl;
        }
        return ssPPM.str();
    }

    void Canvas::CreateFace(int intRadius, int intCenter, int intRadiusVal, RGBPixel recPixel) const
    {
        for(int r = 0; r < _rows; r++)
        {
            for(int c = 0; c < _cols; c++)
            {
                if(distance(r, intRadius, c, intRadiusVal) <= intRadiusVal)
                {
                    _pixels[r][c] = recPixel;
                }
            }
        }
    }



    int Canvas::getRows() const
    {
        return _rows;
    }

    int Canvas::getCols() const
    {
        return _cols;
    }

    RGBPixel Canvas::getPixel(int intRow, int intCol) const
    {
        RangeCheck(intRow, 0, _rows - 1);
        RangeCheck(intCol, 0, _cols - 1);
        return _pixels[intRow][intCol];
    }

    void Canvas::setPixel(int intRow, int intCol, RGBPixel recPixel)
    {
        RangeCheck(intRow, 0, _rows - 1);
        RangeCheck(intCol, 0, _cols - 1);
        _pixels[intRow][intCol] = recPixel;
    }


    Canvas::~Canvas()
    {
        for(int r = 0; r < _rows; r++)
        {
            delete[] _pixels[r];
        }
        delete _pixels;
        _pixels = nullptr;
    }

    double Canvas::distance(double intRow, double intCenterRow, double intCol, double intCenterCCol) const
    {
        return sqrt(pow(intRow-intCenterRow,2) + pow(intCol-intCenterCCol,2));
    }

    void Canvas::RangeCheck(int intValue, int intMin, int intMax) const
    {
        if(intValue < intMin || intValue > intMax)
        {
            cerr << "Error: " << intValue << endl;
            exit(ERR_RANGE);
        }
    }

    void Canvas::AllocMem(int intRows, int intCols, RGBPixel recDefault)
    {
        _rows = intRows;
        _cols = intCols;
        _pixels = new RGBPixel*[_rows];
        for(int r = 0; r < _rows; r++)
        {
            _pixels[r] = new RGBPixel[_cols];
            for(int c = 0; c < _cols; c++)
            {
                _pixels[r][c] = recDefault;
            }
        }
    }



}
