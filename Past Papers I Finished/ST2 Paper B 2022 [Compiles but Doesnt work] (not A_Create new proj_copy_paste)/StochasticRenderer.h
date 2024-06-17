#ifndef STOCHASTICRENDERER_H_INCLUDED
#define STOCHASTICRENDERER_H_INCLUDED

#include "UJImage.h"
#include "Render.h"

class UJImage;

class StochasticRenderer : public Renderer
{
public:
    void draw();
    int genRand(int intLowerint, int intUpper);
};




#endif // STOCHASTICRENDERER_H_INCLUDED
