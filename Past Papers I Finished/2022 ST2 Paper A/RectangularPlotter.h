#ifndef RECTANGULARPLOTTER_H_INCLUDED
#define RECTANGULARPLOTTER_H_INCLUDED

#include "UJImage.h"
#include "PixelPlotter.h"

//inheritance
class RectangularPlotter : public PixelPlotter
{
public:
    //constructor
    RectangularPlotter();

    //override draw method
    void draw();
};



#endif // RECTANGULARPLOTTER_H_INCLUDED
