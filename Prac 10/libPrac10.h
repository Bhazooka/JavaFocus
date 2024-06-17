#ifndef LIBPRAC10_H_INCLUDED
#define LIBPRAC10_H_INCLUDED

#include <cstdlib>
#include <cctype>
#include <cmath>
#include <ctime>
#include <sstream>
#include <cassert>
#include <iostream>
#include <iomanip>

using namespace std;

namespace RecipeSpace
{
    enum tError
    {
        ERR_CONV,
        ERR_RANGE,
        ERR_SPACE,
        ERR_ARGC

    };

    enum tObjects
    {
        EMPTY,
        PLAYER,         //player = 1 (P)
        TREE,           //Tree = 2 (&)
        PLAYER_TREE,    //P+T = 3 (P)
        FLINT,          //Flint = 4 (*)
        PLAYER_FLINT,   //P+F = 5 (P)
        BUSH,           //Bush = 6 (#)
        PLAYER_BUSH     //P+B = 7 (P)
    };
    //Characters
    const char OBJECTS[8] = {'.','P','&','P','*','P','#','P'};

    enum tMove
    {
        MOVE_UP,            //0
        MOVE_UP_LEFT,       //1
        MOVE_LEFT,          //2
        MOVE_DOWN_LEFT,     //3
        MOVE_DOWN,          //4
        MOVE_DOWN_RIGHT,    //5
        MOVE_RIGHT,         //6
        MOVE_UP_RIGHT       //7
    };

    enum tStatus
    {
        RUNNING,
        WIN,
        LOST,
        QUIT
    };

    //Alias for arrays
    typedef int* D1Arr;
    typedef int** D2Arr;

    struct tWorld
    {
        D2Arr arrWorld;
        int intRows;
        int intCols;
        int intPlayerRow;
        int intPlayerCol;
        int intBushes;
        int intTrees;
        int intFlints;
        int intTurns;
        int Logs;       //Material Collected from Trees
        int Flint;      //Material Collected from Flint
        int Sticks;     //Material Collected from Bushes
        int Axes;
        int FK;
        tStatus enStatus;
    };

    const int CHANCE_BUSH = 2; //Chance of a bush in the world

    //Function headers
    int ConvToInt(string strNum);
    tWorld InitWorld(int intRows, int intCols, int intTrees, int intFlints, int intBushes, int intTurns);
    void intRangeCheck(int intValue, int intLower, int intUpper);
    void Display(tWorld stcWorld);
    void Update(tWorld& stcWorld);
    void DeallocMem(D2Arr& arrWorld, int intRows);
    void MovePlayer(tWorld& stcWorld, int chInput);
    void CraftAxe(tWorld& stcWorld);
    void CraftFK(tWorld& stcWorld);

}

#endif // LIBPRAC10_H_INCLUDED
