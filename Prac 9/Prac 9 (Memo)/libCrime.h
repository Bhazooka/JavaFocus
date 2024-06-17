#ifndef LIBCRIME_H_INCLUDED
#define LIBCRIME_H_INCLUDED

#define NDEBUG //This is added to disable all assert instructuion

#include <iostream>
#include <cstdlib>
#include <ctime>
#include <sstream>
#include <cctype>
#include <cassert>

using namespace std;

namespace CrimeSpace
{
    //Enumeration that describes error messages
    enum tErrors
    {
        SUCCESS,
        ERR_ARGC,
        ERR_CONV,
        ERR_RANGE,
        ERR_SPACE
    };
    //Enumeration that declares the different type of features.
    enum tFeatures
    {
        EMPTY,
        PLAYER,
        CLUE_REAL,
        CLUE_HIDDEN, //Hidden real clue, will be transformed into CLUE_REAL
        CLUE_POTENTIAL,
        STAR
    };

    //Describes the movement direction. Abstracts away the keys from the movement function.
    enum tMovement
    {
        MOVE_LEFT,
        MOVE_RIGHT,
        MOVE_UP,
        MOVE_DOWN
    };
    //Enumeration declaring the different states of the game.
    enum tStatus
    {
        RUNNING,
        QUIT,
        WON,
        LOST
    };

    //One D array of features, with corresponding features in tFeatures.
    const char FEATURES[6] = {'.','P','!','X','X','*'};

    //Basic range variable information
    const int DIM_MIN = 5;
    const int DIM_MAX = 30;
    const int MIN_TURNS = 5;
    const int MAX_TURNS = 100;
    const int MIN_CLUE = 1;

    //Aliases for one and two D arrays.
    typedef int* t1DArray;
    typedef int** t2DArray;

    //Struct that defines the information in the game
    struct tGame
    {
        t2DArray arrGame; //The Two-D array that will store the features.
        int intRows; //Total Rows
        int intCols; //Total Cols
        int intPRow; //Player Row
        int intPCol; //Player Col
        int intTotalClues; //Total Clues in the game world
        int intCluesFound; //Number of clues found
        int intTurns; //Number of turns left
        tStatus enStatus; //Status of the game
    };

    tGame InitGame(int intRows, int intCols, int intClues, int intTurns);
    int GetInt(string strNum);
    void PrintWorld(tGame stcGame);
    void MovePlayer(tGame& stcGame, int intMovement);
    void Pause();
    void Investigate(tGame& stcGame);
    void Update(tGame& stcGame);
    void Dealloc(t2DArray& arrGame, int intRows);
    void RangeCheck(int intVal, int intMin, int intMax);
}

#endif // LIBCRIME_H_INCLUDED
