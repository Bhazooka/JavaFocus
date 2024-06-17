#include "UJImage.h"
#include <random>
#include <iostream>

using namespace std;

UJImage::UJImage(): UJImage(DEFAULT_DIMENTION, DEFAULT_DIMENTION, 0, 0)
{
    
}

UJImage::UJImage(int intRows, int intCols, int intFocalRow, int intFocalCol)
{
    enforceRange(intRows, 1, 100);
    enforceRange(intCols, 1, 100);
    _rows = intRows;
    _cols = intCols;
    
    enforceRange(intFocalRow, 0, _rows);
    enforceRange(intFocalCol, 0, _cols);
    _focalRow = intFocalRow;
    _focalCol = intFocalCol;
    
    _pixels = new Pixel*[_rows];
    for(int r = 0; r < _rows; r++)
    {
        _pixels[r] = new Pixel[_cols];
        for(int c = 0; c < _cols; c++)
        {
            int tempMin = randRange(0, PIXEL_MAX-10);
            int tempMax = randRange(tempMin + 1, PIXEL_MAX);
            double tempInt = tempMin * 1.0;
            _pixels[r][c] = {tempInt, tempMin, tempMax};
        }
    }
}

UJImage::UJImage(const UJImage& objOrig): UJImage(objOrig._rows, objOrig._cols, objOrig._focalRow, objOrig._focalCol)
{
    for(int r = 0; r < _rows; r++)
    {
        for(int c = 0; c < _cols; c++)
        {
            _pixels[r][c] = objOrig._pixels[r][c];
        }
    }
}
    
UJImage::~UJImage()
{
    for(int r = 0; r < _rows; r++)
    {
        delete[] _pixels[r];
    }
    delete[] _pixels;
}
    
int UJImage::getRows()
{
    return _rows;
}

int UJImage::getCols()
{
    return _cols;
}

int UJImage::getFocalRow()
{
    return _focalRow;
}

int UJImage::getFocalCol()
{
    return _focalCol;
}
    
void UJImage::plot()
{
    
}

void UJImage::enforceRange(int intValue, int intMin, int intMax)
{
    if(intValue > intMax)
    {
        cerr << "Value too high" << endl;
        exit(-1);
    }
    
    if(intValue < intMin)
    {
        cerr << "Value too low" << endl;
        exit(-2);
    }
}


int UJImage::randRange(int intMin, int intMax)
{
    int intRange = intMax - intMin;
    return rand()%intRange + intMin;
}



