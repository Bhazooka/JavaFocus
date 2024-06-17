#ifndef RENDER_H_INCLUDED
#define RENDER_H_INCLUDED

#include "UJImage.h"

class Renderer
{
public:
    Render();
    Render(int intRow, int intCol);

    virtual void draw() = 0;

};



#endif // RENDER_H_INCLUDED
