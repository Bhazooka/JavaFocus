#include "libPrac10_80%Code.h"

namespace RecipeSpace
{
    int ConvToInt(string strNum)
    {
        int intNum;
        stringstream ss {strNum};
        ss >> intNum;
        if (ss.fail())
        {
            cerr << "Couldnt convert arguments to integers" << endl;
            exit(ERR_CONV);
        }
        return intNum;
    }

    int RandNum(int intLower, int intUpper)
    {
        int intRange = intUpper - intLower + 1;
        return rand()%intRange + intLower;
    }

    void intRangeCheck(int intValue, int intLower, int intUpper)
    {
        if (intValue < intLower || intValue > intUpper)
        {
            cerr << intValue << " is Out of bounds. It should be less than " << intUpper << " and Greater than " << intLower << endl;
            exit(ERR_RANGE);
        }
    }

    D2Arr AllocMem(int intRows, int intCols)
    {
        D2Arr arrWorld;
        arrWorld = new D1Arr[intRows];
        for(int r = 0; r < intRows; r++)
        {
            arrWorld[r] = new int[intCols];
            for(int c = 0; c < intCols; c++)
            {
                arrWorld[r][c] = EMPTY;
            }
        }
        return arrWorld;
    }

    void PlaceObject(tWorld& stcWorld, int intAmount, int intObject, int& intRow, int& intCol)
    {
        for(int a=0; a<intAmount; a++)
        {
            intRow = RandNum(0, stcWorld.intRows-1);
            intCol = RandNum(0, stcWorld.intCols-1);
            while(stcWorld.arrWorld[intRow][intCol]!=EMPTY)
            {
                intRow = RandNum(0, stcWorld.intRows-1);
                intCol = RandNum(0, stcWorld.intCols-1);
            }
            stcWorld.arrWorld[intRow][intCol] = intObject;
        }
    }

    tWorld InitWorld(int intRows, int intCols, int intTrees, int intFlints, int intBushes, int intTurns)
    {
        tWorld stcWorld;

        stcWorld.arrWorld = AllocMem(intRows, intCols);
        stcWorld.intRows = intRows;
        stcWorld.intCols = intCols;
        stcWorld.intTrees = intTrees;
        stcWorld.intFlints = intFlints;
        stcWorld.intBushes = intBushes;
        stcWorld.intTurns = intTurns;
        stcWorld.enStatus = RUNNING;
        stcWorld.MaterialCount = 0;

        int WorldArea = intRows * intCols;
        int intTotalObjects = intTrees + intFlints + intBushes;
        if(intTotalObjects >= WorldArea)
        {
            cerr << "Too many objects, not enough space in world" << endl;
            exit(ERR_SPACE);
        }

        int intRow = 0;
        int intCol = 0;

        //Populate Array with trees
        PlaceObject(stcWorld, intTrees, TREE, intRow, intCol);
        PlaceObject(stcWorld, intFlints, FLINT, intRow, intCol);
        PlaceObject(stcWorld, intBushes, BUSH, intRow, intCol);
        //Place Player
        PlaceObject(stcWorld, 1, PLAYER, intRow, intCol);
        stcWorld.intPlayerRow = intRow;
        stcWorld.intPlayerCol = intCol;

        return stcWorld;
    }


    void Display(tWorld stcWorld)
    {
        system("cls");

        for (int r=0; r < stcWorld.intRows; r++)
        {
            for(int c = 0; c < stcWorld.intCols; c++)
            {
                cout << OBJECTS[stcWorld.arrWorld[r][c]] << " ";
            }
            cout << endl;
        }
        cout << "w) UP" << endl
             << "e) UP+LEFT" << endl
             << "d) RIGHT" << endl
             << "c) DOWN+RIGHT" << endl
             << "x) DOWN" << endl
             << "z) DOWN+LEFT" << endl
             << "a) LEFT" << endl
             << "q) UP+LEFT" << endl
             << "s) CRAFT ITEM" << endl
             << "Materials: " << stcWorld.MaterialCount << endl
             << "Number of Turns Left: " << stcWorld.intTurns << endl;
    }


    bool IsInWorld(int intRows, int intCols, int intRow, int intCol)
    {
        return (intRow >= 0 && intRow < intRows && intCol >= 0 && intCol < intCols);
    }


    void MovePlayer(tWorld& stcWorld, int intMovement)
    {
        int intDRow = stcWorld.intPlayerRow;
        int intDCol = stcWorld.intPlayerCol;

        switch(intMovement)
        {
        case MOVE_UP:
            intDRow--;
            break;

        case MOVE_UP_RIGHT:
            intDRow--;
            intDCol++;
            break;

        case MOVE_RIGHT:
            intDCol++;
            break;

        case MOVE_DOWN_RIGHT:
            intDRow++;
            intDCol++;
            break;

        case MOVE_DOWN:
            intDRow++;
            break;

        case MOVE_DOWN_LEFT:
            intDRow++;
            intDCol--;
            break;

        case MOVE_LEFT:
            intDCol--;
            break;

        case MOVE_UP_LEFT:
            intDRow--;
            intDCol++;
            break;

        }

        if(IsInWorld(stcWorld.intRows, stcWorld.intCols, intDRow, intDCol))
        {
            if(stcWorld.arrWorld[intDRow][intDCol]==EMPTY)
            {
                stcWorld.arrWorld[stcWorld.intPlayerRow][stcWorld.intPlayerCol]=EMPTY;
                stcWorld.arrWorld[intDRow][intDCol] = PLAYER;
                stcWorld.intPlayerRow = intDRow;
                stcWorld.intPlayerCol = intDCol;
            }
        }
    }


    void Update(tWorld& stcWorld)
    {
        stcWorld.intTurns--;
        if(stcWorld.intTurns < 0)
        {
            stcWorld.enStatus = LOST;
        }

    }

    void DeallocMem(D2Arr& arrWorld, int intRows)
    {
        for (int r = 0; r < intRows; r++)
            delete[] arrWorld[r];

        delete[] arrWorld;
        arrWorld = nullptr;
    }

}
