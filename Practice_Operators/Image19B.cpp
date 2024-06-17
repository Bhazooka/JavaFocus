//main.cpp
//***********************************************************************************************************************
#include <iostream>
#include "UJImage.h"

using namespace std;

int main()
{
    int intRows = 400;
    int intCols = 400;
    Pixel orangePixel = {200, 150, 10};
    CircleParams leftCircle = {200, 150, 100};
    CircleParams rightCircle = {200, 250, 100};

    UJImage* newImage = new UJImage(intRows, intCols, leftCircle, rightCircle, orangePixel);

    newImage->intersect();

    cout << newImage->toPPM();
    return 0;
}
//***********************************************************************************************************************

//UJImage.h
//***********************************************************************************************************************
#ifndef UJIMAGE_H_INCLUDED
#define UJIMAGE_H_INCLUDED

#include "CommonLib.h"
#include "Intersector.h"

#include <cstring>
#include <iostream>
#include <string>

using namespace std;

/* This code commented out is found in the CommonLib
#pragma pack(push)
#pragma pack(1)
struct Pixel
{
    int intRed;
    int intGreen;
    int intBlue;
};
#pragma pack(pop)

struct CircleParams
{
    int intRow;
    int intCol;
    int intRadius;
};
*/

class Intersector;

class UJImage
{
public:
    UJImage();
    UJImage(int intRows, int intCols, Pixel recDefault);
    UJImage(int intRows, int intCols, CircleParams recCircleParams, CircleParams recCircle2Params, Pixel recDefault);
    UJImage(const UJImage& objOriginal);
    ~UJImage();

    void intersect();

    const UJImage& operator= (const UJImage& objRHS);
    //Pixel& operator()(int intRow, int intCol);
    Pixel& operator[](int intIndex);
    void operator()(int intRow, int intCol, Pixel defaultPixel);

    void PPMtoDAT(std::string strFileName);

    int getRows() const;
    int getCols() const;
    string toPPM() const;

private:
    int _rows;
    int _cols;
    Pixel** _pixels;
    Intersector* _intersector;
    void enforceRange(int intValue, int intMin, int intMax) const;
    void init(int intRows, int intCols, CircleParams recCircleParams, CircleParams recCircle2Params, Pixel recDefault);
    void dealloc();


};
#endif // UJIMAGE_H_INCLUDED

//***********************************************************************************************************************


//UJImage.cpp
//***********************************************************************************************************************
#include "UJImage.h"
#include "ConjunctionInterector.h"
#include "DisjunctionInterector.h"

#include <sstream>

void UJImage::init(int intRows, int intCols, CircleParams recCircleParams, CircleParams recCircle2Params, Pixel recDefault)
{
    _rows = intRows;
    _cols = intCols;
    _pixels = new Pixel*[intRows];
    for(int r = 0; r < intRows; r++)
    {
        _pixels[r] = new Pixel[intCols];
        for(int c = 0; c < intCols; c++)
        {
            _pixels[r][c] = recDefault;
        }
    }

    this->_intersector = new DisjuntionIntersector(recCircleParams, recCircle2Params);
    this->_intersector = new ConjunctionIntersctor(recCircleParams, recCircle2Params);

}


UJImage::UJImage(int intRows, int intCols, CircleParams recCircleParams, CircleParams recCircle2Params, Pixel recDefault)
{
    init(intRows, intCols, recCircleParams, recCircle2Params, recDefault);
}


UJImage::UJImage(int intRows, int intCols, Pixel recDefault) : UJImage(intRows, intCols, {0,0,0}, {0,0,0}, recDefault)
{
}

UJImage::UJImage() : UJImage(0, 0, {0,0,0}, {0,0,0}, {0,0,0})
{
}


UJImage::UJImage(const UJImage& objOriginal)
{
    *this = objOriginal;
}


void UJImage::dealloc()
{
    for(int r = 0; r < _rows; r++)
    {
        delete[] _pixels[r];
    }
    delete[] _pixels;
}


UJImage::~UJImage()
{
    dealloc();
    delete _intersector;
}

void UJImage::intersect()
{
    _intersector->intersect(*this); //delegation
}

const UJImage& UJImage::operator=(const UJImage& objRHS)
{
    if(this != &objRHS)
    {
        dealloc();

        _pixels = new Pixel*[objRHS._rows];
        for(int r = 0; r < _rows; r++)
        {
            _pixels[r] = new Pixel[objRHS._cols];
            for(int c = 0; c < _cols; c++)
            {
                _pixels[r][c] = objRHS._pixels[r][c];
            }
        }
    }
    return *this;
}


/*
Pixel& UJImage::operator()(int intRow, int intCol)
{

}
*/

void UJImage::operator()(int intRow, int intCol, Pixel defaultPixel)
{
    enforceRange(intRow, 0, _rows);
    enforceRange(intCol, 0, _cols);
    _pixels[intRow][intCol] = defaultPixel;
}


Pixel& UJImage::operator[](int intIndex)
{
    //int intRow = (intIndex % _cols);
}

void UJImage::PPMtoDAT(string strFileName)
{

}

int UJImage::getRows() const
{
    return _rows;
}

int UJImage::getCols() const
{
    return _cols;
}


void UJImage::enforceRange(int intValue, int intMin, int intMax) const
{
    if(intValue < intMin || intValue >= intMax)
    {
        cerr << "Value out of bound";
        exit(-1);
    }
}

string UJImage::toPPM() const
{
    stringstream ssPPM;
    ssPPM << "P3" << endl;
    ssPPM << _cols << ' ' << _rows << endl;
    ssPPM << 255 << endl;
    for(int r = 0; r < _rows; r++)
    {
        for(int c = 0 ; c < _cols ; c++)
        {
            ssPPM << _pixels[r][c].intRed << ' '
                  << _pixels[r][c].intRed << ' '
                  << _pixels[r][c].intRed << ' ' ;
        }
        ssPPM << endl;
    }
    return ssPPM.str();
}

//***********************************************************************************************************************


//Intersector.h
//***********************************************************************************************************************
#ifndef INTERSECTOR_H_INCLUDED
#define INTERSECTOR_H_INCLUDED

#include "UJImage.h"
#include <vector>
#include "CommonLib.h"

using namespace std;

class UJImage;

class Intersector
{
public:
    Intersector();
    Intersector(CircleParams recCircleParams, CircleParams recCircle2Params);
    virtual void intersect(UJImage& objImage) = 0;

    double euclideanDistance(int x1, int x2, int y1, int y2);

    static const int DEFAULT_ROW = 0;
    static const int DEFAULT_COL = 0;
    static const int DEFAULT_RADIUS = 0;

protected:
    vector<CircleParams> circles;

};

#endif // INTERSECTOR_H_INCLUDED

//***********************************************************************************************************************


//Intersector.cpp
//***********************************************************************************************************************
#include "Intersector.h"
#include <cmath>

Intersector::Intersector() : Intersector({DEFAULT_ROW, DEFAULT_COL, DEFAULT_RADIUS}, {DEFAULT_ROW, DEFAULT_COL, DEFAULT_RADIUS})
{

}

Intersector::Intersector(CircleParams recCircleParams, CircleParams recCircle2Params)
{
    circles.push_back(recCircleParams);
    circles.push_back(recCircle2Params);
}


double Intersector::euclideanDistance(int x1, int x2, int y1, int y2)
{
    int x = pow((x1 - x2) , 2);
    int y = pow((y1 - y2) , 2);
    return sqrt(x + y);
}

//***********************************************************************************************************************


//DisjunctionIntersector.h
//***********************************************************************************************************************
#ifndef DISJUNCTIONINTERECTOR_H_INCLUDED
#define DISJUNCTIONINTERECTOR_H_INCLUDED

#include "Intersector.h"

class UJImage;

class DisjuntionIntersector : public Intersector
{
public:
    DisjuntionIntersector();
    DisjuntionIntersector(CircleParams recCircleParam, CircleParams recCircle2Param);

    virtual void intersect(UJImage& objImage);

};

#endif // DISJUNCTIONINTERECTOR_H_INCLUDED

//***********************************************************************************************************************


//DisjunctionIntersector.cpp
//***********************************************************************************************************************
#include "DisjunctionInterector.h"

DisjuntionIntersector::DisjuntionIntersector() : Intersector()
{
}

DisjuntionIntersector::DisjuntionIntersector(CircleParams recCircleParams, CircleParams recCircle2Params) : Intersector(recCircleParams, recCircle2Params)
{
}

void DisjuntionIntersector::intersect(UJImage& objImage)
{
    for(int r = 0; r < objImage.getRows(); r++)
    {
        for(int c = 0; c < objImage.getCols(); c++)
        {
            if(euclideanDistance(r, circles[0].intRow, c, circles[0].intCol) <= circles[0].intRadius &&
               euclideanDistance(r, circles[1].intRow, c, circles[1].intCol) <= circles[1].intRadius ||
               euclideanDistance(r, circles[0].intRow, c, circles[0].intCol) > circles[0].intRadius &&
               euclideanDistance(r, circles[1].intRow, c, circles[1].intCol) > circles[1].intRadius)
            {
                objImage(r, c, {255,255,255});
            }

        }
    }
}
//***********************************************************************************************************************


//ConjunctionIntersector.h
//***********************************************************************************************************************
#ifndef CONJUNCTIONINTERECTOR_H_INCLUDED
#define CONJUNCTIONINTERECTOR_H_INCLUDED


#include "Intersector.h"

class UJImage;

class ConjunctionIntersctor : public Intersector
{
public:
    ConjunctionIntersctor();
    ConjunctionIntersctor(CircleParams recCircleParams, CircleParams recCircle2Params);

    virtual void intersect(UJImage& objImage);

};

#endif // CONJUNCTIONINTERECTOR_H_INCLUDED

//***********************************************************************************************************************


//ConjunctionIntersector.cpp
//***********************************************************************************************************************
#include "ConjunctionInterector.h"

ConjunctionIntersctor::ConjunctionIntersctor() : Intersector()
{
}

ConjunctionIntersctor::ConjunctionIntersctor(CircleParams recCircleParams, CircleParams recCircle2Params) : Intersector(recCircleParams, recCircle2Params)
{
}

//overriden intersect function
void ConjunctionIntersctor::intersect(UJImage& objImage)
{
    for(int r = 0; r < objImage.getRows(); r++)
    {
        for(int c = 0; c < objImage.getCols(); c++)
        {
            if(euclideanDistance(r, circles[0].intRow, c, circles[0].intCol) <= circles[0].intRadius &&
               euclideanDistance(r, circles[1].intRow, c, circles[1].intCol) <= circles[1].intRadius)
            {
                //if the intersect, do nothing
            }
            else
            {
                objImage(r, c, {255, 255, 255});
            }
        }
    }
}
//***********************************************************************************************************************



//CommonLib.h
//***********************************************************************************************************************
#ifndef COMMONLIB_H_INCLUDED
#define COMMONLIB_H_INCLUDED

#pragma pack(push)
#pragma pack(1)
struct Pixel
{
    int intRed;
    int intGreen;
    int intBlue;
};
#pragma pack(pop)

struct CircleParams
{
    int intRow;
    int intCol;
    int intRadius;
};

#endif // COMMONLIB_H_INCLUDED

//***********************************************************************************************************************



