/*
 * The purpose of this file is to test
 * the UJList data structure. This file is
 * generated automatically by the VPL each time
 * your program is executed.
 */
#include "CompositeInitialiser.h"
#include "PrimeInitialiser.h"
#include "UJList.h"

#include <iostream>
#include <sstream>
using namespace std;

UJList createUJList(Initialiser& objInit, int intListLength){
    UJList objList(intListLength, 0);
    objInit.initialise(objList);
    return objList;
}

int main()
{
    int intListLength = 20;
    // Create an initialiser.
    CompositeInitialiser objCompInit;
    PrimeInitialiser objPrimeInit;
    // Create a list.
    UJList objListPrime = createUJList(objPrimeInit, intListLength);
    UJList objListComp = createUJList(objCompInit, intListLength);
    // Display list contents.
    stringstream ssOut;
    for(int i = 0; i < objListPrime.length(); i++){
        ssOut << objListPrime.get(i) << ' ';
    }
    for(int i = 0; i < objListComp.length(); i++){
         ssOut << objListComp.get(i) << ' ';
    }
    cout << ssOut.str();
    return SUCESS;
}