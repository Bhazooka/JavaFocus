#ifndef LIBRANDOM_H_INCLUDED
#define LIBRANDOM_H_INCLUDED

#include <iostream>
#include <cstdlib>
#include <sstream>
#include <cctype>
#include <ctime>
#include <cmath>
#include <cstring>
#include <iomanip>
#include <cassert>

using namespace std;

namespace RandomSpace
{
    typedef int* oneDarr;
    typedef int** twoDarr;

    enum E_Error
    {
        ERR_CONV, ERR_RANGE, ERR_SPACE, ERR_ARGC
    };

    enum E_Objects
    {
        EMPTY, PLAYER, TREE, BUSH, FLINT, ENEMY
    };

    const char OBJECTS[6] = {'.','P','$','#','*','E'};

    enum E_Status
    {
        RUNNING, WIN, LOSE, QUIT, SUCCESS
    };

    struct recWorld
    {
        twoDarr arrWorld;
        int intRows;
        int intCols;
        int intPlayerRow;
        int intPlayerCol;

        int intEnemyRow;
        int intEnemyCol;
        int intRange;

        int intTrees;
        int intBushes;
        int intFlintS;
        int intTurns;
        int Logs;
        int Sticks;
        int Flint;
        E_Status GameStatus;
    };

    int ConvertToInt(string strNum);
    int GenRand(int intLow, int intHigh);
    void RangeCheck (int intValue, int intLow, int intHigh);

    twoDarr AllocMem(int intRows, int intCols);

    void PlaceObjects(recWorld& stcWorld, int intAmount, int intObject, int& intRow, int& intCol);
    recWorld InitWorld(int intRows, int intCols, int intTrees, int intBushes, int intFlintS, int intTurns, int intRange);


    void Display(recWorld stcWorld);

    bool IsInWorld(int intRows, int intCols, int intRow, int intCol);
    void MovePlayer(recWorld& stcWorld, char Choice);

    bool IsInRange(recWorld& stcWorld);
    void MoveEnemy(recWorld& stcWorld);

    bool Update(recWorld& stcWorld);

    twoDarr DeallocateMem(twoDarr& arrWorld, int intRows);

}

#endif // LIBRANDOM_H_INCLUDED
