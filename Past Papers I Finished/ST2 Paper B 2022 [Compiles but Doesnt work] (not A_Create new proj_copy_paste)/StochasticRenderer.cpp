#include "StochasticRenderer.h"
#include "UJImage.h"


void StochasticRenderer::draw()
{

}

int StochasticRenderer::genRand(int intLower, int intUpper)
{
    int intRange = intUpper - intLower + 1;
    return rand()% intRange + intLower;
}
