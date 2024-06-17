#include "CanvasCapper.h"

#include <cstdlib>

using namespace std;

//constructor
CanvasCapper::CanvasCapper() : CanvasCapper(0,0,0)
{
}

CanvasCapper::CanvasCapper(int intRed, int intGreen, int intBlue)
{
    setRed(intRed);
    setGreen(intGreen);
    setBlue(intBlue);
}

int CanvasCapper::getRed()
{
    return _red;
}

int CanvasCapper::getGreen()
{
    return _green;
}

int CanvasCapper::getBlue()
{
    return _blue;
}

//This functions takes the value of green assigned in the _green variable and passes it to intGreen variable
void CanvasCapper::setRed(int intRed)
{
    _red = intRed;
}

void CanvasCapper::setGreen(int intGreen)
{
    _green = intGreen;
}

void CanvasCapper::setBlue(int intBlue)
{
    _green = intBlue;
}

//IMplementation of void function
void CanvasCapper::applyRangeRules(int intVal)
{
    if(intVal < ERR_MIN_COLOUR || intVal > ERR_MAX_COLOUR)
    {
        exit(ERR_COLOUR_RANGE);
    }
}

