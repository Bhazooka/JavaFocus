#ifndef LIBZORK_H_INCLUDED
#define LIBZORK_H_INCLUDED

#include <iostream>
#include <cstdlib>
#include <ctime>
#include <cmath>
#include <sstream>
#include <cctype>

using namespace std;

namespace ZorkSpace
{
    //Constant values
    const int EMPTY = 0;
    const int BATTERY = 1;
    const int PLAYER = 2;
    const int BATTERY_PLAYER = 3; //Player + Battery, PLAYER is displayed above
    const int GRUE = 4;
    const int PIT = 5;
    const int GURE_PLAYER = 6; //Player + grue, GRUE is displayed above



    const char OBJECTS[7] = {'_','*','P','P','G','O','G'};

    //Constant error Values
    const int ERR_RANGE = -1;
    const int ERR_CONV = -2;

    //Creating Array
    typedef int* D1Arr;
    typedef int** D2Arr;

    int ConvertToInt(string strNum);
    D2Arr InitWorld(int intRows, int intCols);
    void PopulateArray (D2Arr arrWorld, int intRows, int intCols, int intObjects, int intCount);
    void PrintWorld(D2Arr arrWorld, int intRows, int intCols, int& BatteryCount);
    void Movement(D2Arr arrWorld, int intRows, int intCols, int &intBattery, char chInput);
    void DeallocMem(D2Arr &arrWorld, int intRows);

}

#endif // LIBZORK_H_INCLUDED
