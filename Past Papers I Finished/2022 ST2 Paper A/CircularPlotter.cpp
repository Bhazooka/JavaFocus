#include "CircularPlotter.h"
#include <cmath>

/*
CircularPlotter::CircularPlotter()
{

}

CircularPlotter::CircularPlotter(int intRaduis)
{

}
*/

//overiding draw function
void CircularPlotter::draw(int x, int y)
{
    int r = RADIUS;
    r = sqrt(pow(x,2)+ pow(y,2));
}
