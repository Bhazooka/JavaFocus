#include "Face.h"

#include <cstdlib>

using namespace std;

//constructor
Face::Face() : Face(0,0,0)
{
}

Face::Face(int intRed, int intGreen, int intBlue)
{
    setRed(intRed);
    setGreen(intGreen);
    setBlue(intBlue);
}

int Face::getRed()
{
    return _red;
}

int Face::getGreen()
{
    return _green;
}

int Face::getBlue()
{
    return _blue;
}

//This functions takes the value of green assigned in the _green variable and passes it to intGreen variable
void Face::setRed(int intRed)
{
    _red = intRed;
}

void Face::setGreen(int intGreen)
{
    _green = intGreen;
}

void Face::setBlue(int intBlue)
{
    _green = intBlue;
}

void Face::ColourRange(int intVal)
{
    if(intVal < ERR_MIN_COLOUR || intVal > ERR_MAX_COLOUR)
    {
        exit(ERR_COLOUR_RANGE);
    }
}

