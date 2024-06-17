#ifndef CIRCULARPLOTTER_H_INCLUDED
#define CIRCULARPLOTTER_H_INCLUDED

#include "PixelPlotter.h"
#include "UJImage.h"

class UJImage;
class PixelPlotter;
//inheritance
class CircularPlotter : public PixelPlotter
{
public:
    CircularPlotter();
    CircularPlotter(int intRaduis);
    //override draw functoin
    void draw(int x, int y);

    //testing random number for radius
    static const int RADIUS = 5;

};

#endif // CIRCULARPLOTTER_H_INCLUDED
