#ifndef PIXELPLOTTER_H_INCLUDED
#define PIXELPLOTTER_H_INCLUDED

#include "UJImage.h"

class UJImage;

class PixelPlotter
{
public:
    PixelPlotter();
    PixelPlotter(int intRows, int intCols);
    //delegation must override draw function
    virtual void draw() = 0;
};


#endif // PIXELPLOTTER_H_INCLUDED
