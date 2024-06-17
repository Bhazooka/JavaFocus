#ifndef LIBPRAC2_H_INCLUDED
#define LIBPRAC2_H_INCLUDED

#include <iostream>
#include <cstdlib>
#include <ctime>
#include <cassert>
#include <sstream>
#include <cctype>


using namespace std;

namespace Prac2
{

    enum eFeatures
    {
        EMPTY,
        WALL,
        ENEMY_UP,
        ENEMY_DOWN
    };

    enum eErrors
    {
        ERR_ARGC = -1,
        ERR_CONV = -2,
        ERR_RANGE = -3
    };

    enum GameSatus
    {
        RUNNING,
        WON,
        LOST,
        QUIT
    };

    const char FEATURES[4] = {'.','|','^','!'};
    const char F_PLAYER = 'P';

    typedef int* t_1DArray;
    typedef int** t_2DArray;

    struct stcGame
    {
        //variables belonging to the structure
        t_2DArray arrGame;
        int intRows;
        int intCols;
        int intPRow;
        int intPCol;
        GameStatus state;
        bool blnSpotted;
        bool blnSameCol;
        int intMove;


        //variable function pointers
        t_2DArray (*AllocPtr)(int intRows, int intCols);
        void (*DisplayPtr)(stcGame game);
        void (*PlayerMovementPtr)(stcGame& game, char chInput);
        void (*EnemyMovementPtr)(stcGame& game);
        void (*Pause)();
    };


    int GetInt(string strNum);

    stcGame* CreateObj(int intRows, int intCols);
    void Destruct(stcGame& game);

}



#endif // LIBPRAC2_H_INCLUDED

