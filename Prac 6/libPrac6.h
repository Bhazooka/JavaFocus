#ifndef LIBPRAC6_H_INCLUDED
#define LIBPRAC6_H_INCLUDED

#include <iostream>
#include <cstdlib>
#include <ctime>
#include <cmath>

using namespace std;

namespace BattleSpace
{
    const int  SLOTS = 20;

    const int ONE = 1;
    const int ZERO = 0;
    const int POINTER = 10;

    const int MOVE_LEFT = 1;
    const int MOVE_RIGHT = 2;

    const int ERR_NOT_FOUND = -100;

    void Pause();
    void Initialise(int arrNums[]);
    void OutPutGame(int arrNums[]);
    void OutPutPointer(char arrNums[]);
    void PointerPosition(char arrNums[]);
    void PointerArray(int arrNums[]);
    void Move(char arrNums1[], char chMove);
    void arrChange(int arrNums[], char arrNums1[]);
    void Convert(int arrNums[], int SecondArr[]);
    bool Finish(int arrNums[], int SecondArr[]);


}

#endif // LIBPRAC6_H_INCLUDED
