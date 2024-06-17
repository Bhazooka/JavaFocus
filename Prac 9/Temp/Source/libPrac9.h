#ifndef LIBPRAC9_H_INCLUDED
#define LIBPRAC9_H_INCLUDED

#include <iostream>
#include <cstdlib>
#include <ctime>
#include <cmath>
#include <sstream>
#include <cctype>
#include <cassert>

using namespace std;

namespace Crimespace
{
    enum
    {

    };

    //Errors
    enum tERROR
    {
        ERR_CONV,
        ERR_RANGE,
        ERR_ARG
    };

    //Game status
    enum tStatus
    {
        WIN,
        LOST,
        RUNNING,
        QUIT
    };

    enum tObjects
    {
        EMPTY,
        PLAYER,
        CLUES,
        PCLUE //potential clues
    };


    const char OBJECTS[4] = {'_','P','*','*'};
    const char iOBJECTS[4] = {'_','P','$','_'}; // After inspecting the clues

    enum tMOVE
    {
        UP,
        DOWN,
        LEFT,
        RIGHT
    };

    //Array alias
    typedef int* oneArr;
    typedef int** twoArr;

    //Function headers
    int ConvertToInt(string strNum);
    twoArr InitWorld(int intRows, int intCols, int intClues, int intPClues);
    void DisplaySpace(twoArr arrSpace, int intRows, int intCols, int intTurns, bool InspectClue);
    void Movement(twoArr arrSpace, int intRows, int intCols, int& intTurns, bool blnInspect, char chInput);
    bool TestEnd(int intTurns, int ClueCount);
    void DeallocMem(twoArr arrSpace, int intRows);





}

#endif // LIBPRAC9_H_INCLUDED
