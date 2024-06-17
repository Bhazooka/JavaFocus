#include "PrimeInitialiser.h"
#include<cmath>

void PrimeInitialiser::initialise(UJList& objList)
{
    objList.add(0,2);
    objList.add(1,3);
    objList.add(2,5);
    objList.add(3,7);
    
    int c = 4;
    
    int int1 = 0;
    int int2 = 0;
    int int3 = 0;
    
    if(c<objList.length())
    {
        for(int p = 8; p<72; p++)
        {
            int1 = p%2;
            int2 = p%3;
            int3 = p%5;
            
            if((int1 != 0) && (int2 != 0) && (int3 != 0) && (p%7) != 0)
            {
                objList.add(c,p);
                c++;
            }
        }
    }
    
}