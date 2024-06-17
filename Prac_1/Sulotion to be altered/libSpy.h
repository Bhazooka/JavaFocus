#ifndef LIBSPY_H_INCLUDED
#define LIBSPY_H_INCLUDED

#include <iostream>
#include <cstdlib>
#include <ctime>
#include <cassert>
#include <sstream>
#include <cctype>


using namespace std;

namespace SpySpace
{
typedef int* t_1DArray;
typedef int** t_2DArray;

enum enFeatures
{
    EMPTY,
    WALL,
    ENEMY_UP,
    ENEMY_DOWN
};

enum enErrors
{
    ERR_ARGC = -1,
    ERR_CONV = -2,
    ERR_RANGE = -3
};

enum gState
{
    RUNNING,
    WON,
    LOST,
    QUIT
};

const char FEATURES[4] = {'.','|','^','!'};
const char F_PLAYER = 'P';

struct stcGame
{
    //variables belonging to the structure
    t_2DArray arrGame;
    int intRows;
    int intCols;
    int intPRow;
    int intPCol;
    gState state;
    bool blnSpotted;
    bool blnSameCol;
    int intMove;

    // function pointers for the environment, player and enemy movement
    void (*PrintWorldPtr)(stcGame game);
    void (*MovePlayerPtr)(stcGame& game, char chInput);
    void (*MoveEnemiesPtr)(stcGame& game);
    t_2DArray (*AllocMemPtr)(int intRows, int intCols);
};

void Pause();
int GetInt(string strNum);

stcGame* CreateObj(int intRows, int intCols);
void Destruct(stcGame& game);

//function pointers



}



#endif // LIBSPY_H_INCLUDED
