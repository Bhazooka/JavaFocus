#ifndef CONTINUOUSRENDERER_H_INCLUDED
#define CONTINUOUSRENDERER_H_INCLUDED

#include <cassert>

#include "Render.h"
#include "UJImage.h"


class UJImage;

class ContinuousRenderer : public Renderer
{
public:
    ContinuousRenderer();
    ContinuousRenderer(int intMax);
    void draw();


    static const int MAX_INT = 255;



};


#endif // CONTINUOUSRENDERER_H_INCLUDED
