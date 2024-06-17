#ifndef COMPOSITEINITIALISER_H
#define COMPOSITEINITIALISER_H

#include "Initialiser.h"
#include "UJList.h"

class UJList;

class CompositeInitialiser : public Initialiser
{
public:
    virtual void initialise(UJList& objList);
};

#endif