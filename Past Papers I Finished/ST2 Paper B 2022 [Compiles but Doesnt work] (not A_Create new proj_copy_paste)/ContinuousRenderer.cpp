#include "ContinuousRenderer.h"
#include "UJImage.h"

//Using Constructor to set Max Intensity
ContinuousRenderer::ContinuousRenderer() : ContinuousRenderer(MAX_INT)
{
}

ContinuousRenderer::ContinuousRenderer(int intMax)
{
    assert(intMax >= 0 && intMax <= 255);
}


void ContinuousRenderer::draw()
{

}
