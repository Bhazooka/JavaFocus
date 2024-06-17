#include "UJList.h"
/*
 * Application entry point.
 * The purpose of this file is to test
 * the UJList data structure. This file is
 * generated automatically by the VPL each time
 * your program is executed.
 */
 #include <iostream>
 using namespace std;

int main()
{
    // Create list.
    UJList objList = UJList(20, 0);
    objList.initialise();
    // Display list contents.
    for(int i = 0; i < objList.length(); i++){
        cout << objList.get(i) << ' ';
    }
    return SUCESS;
}