/*
 * The purpose of this file is to test
 * the UJList data structure. This file is
 * generated automatically by the VPL each time
 * your program is executed.
 */
#include "UJList.h"

#include <iostream>

using namespace std;

int main()
{
    // Testing the parameterised constructor.
    
    UJList objList(3, true);
    // Testing the copy constructor.
    UJList objCopy = objList;
    // Testing the = operator.
    UJList objListLarger(100, false);
    objListLarger = objCopy;
    //Testing the [] operator.
    objListLarger[1] = false;
    // Testing the ! and << operators.
    cout << !objListLarger;
    
    return 0;
    
}
