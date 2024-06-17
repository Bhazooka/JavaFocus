#include "libPrac10.h"

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

    void RangeCheck(int intValue, int intLower, int intUpper)
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

    //To place objects in the world
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

    //Algorithm to spawn player in the mid region of the Map
    void PlacePlayer(tWorld& stcWorld, int intAmount, int intObject, int& intRow, int& intCol)
    {
        intRow = RandNum(0, stcWorld.intRows/4) + stcWorld.intRows/3;
        intCol = RandNum(0, stcWorld.intCols/4) + stcWorld.intCols/3;
        while(stcWorld.arrWorld[intRow][intCol]!=EMPTY)
        {
            intRow = RandNum(0, stcWorld.intRows/4) + stcWorld.intRows/3;
            intCol = RandNum(0, stcWorld.intCols/4) + stcWorld.intCols/3;
        }
        stcWorld.arrWorld[intRow][intCol] = PLAYER;
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
        stcWorld.Sticks = 0;
        stcWorld.Flint = 0;
        stcWorld.Logs = 0;
        stcWorld.Axes = 0;
        stcWorld.FK = 0;

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

        //Chacnce of bush spawning
        for(int r=0; r<intRows; r++)
        {
            for(int c = 0; c < intCols; c++)
            {
                if(RandNum(1,100) <= CHANCE_BUSH)
                {
                    PlaceObject(stcWorld, intBushes, BUSH, intRow, intCol);
                }
            }
        }

        //Place The player
        PlacePlayer(stcWorld, 1, PLAYER, intRow, intCol);
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
        cout << endl;
        cout << "Collect 4 sticks and 1 log to craft Axe" << endl
             << "Collect 3 sticks amd 2 flint to craft Fire Kit" << endl << endl;
        cout << "w) UP          " << setw(22) << "p) Craft Axes " << endl
             << "e) UP+LEFT     " << setw(25) << "o) Craft Fire Kit" << endl
             << "d) RIGHT       " << setw(20) << "Sticks (#): " << stcWorld.Sticks << endl
             << "c) DOWN+RIGHT  " << setw(20) << "Flint (*): " << stcWorld.Flint << endl
             << "x) DOWN        " << setw(20) << "Logs (&): " << stcWorld.Logs << endl
             << "z) DOWN+LEFT   " << setw(20) << "Axes: " << stcWorld.Axes << endl
             << "a) LEFT        " << setw(20) << "Fire Kits: " << stcWorld.FK<< endl
             << "q) UP+LEFT     " << setw(20) << "Turns Left: " << stcWorld.intTurns << endl
             << "-) QUIT GAME   " << endl;

    }


    bool IsInWorld(int intRows, int intCols, int intRow, int intCol)
    {
        return (intRow >= 0 && intRow < intRows && intCol >= 0 && intCol < intCols);
    }


    void MovePlayer(tWorld& stcWorld, int chInput)
    {
        int intDRow = stcWorld.intPlayerRow;
        int intDCol = stcWorld.intPlayerCol;

        switch(chInput)
        {
        case 'w':
            intDRow--;
            break;

        case 'e':
            intDRow--;
            intDCol++;
            break;

        case 'd':
            intDCol++;
            break;

        case 'c':
            intDRow++;
            intDCol++;
            break;

        case 'x':
            intDRow++;
            break;

        case 'z':
            intDRow++;
            intDCol--;
            break;

        case 'a':
            intDCol--;
            break;

        case 'q':
            intDRow--;
            intDCol--;
            break;

        }

        if(IsInWorld(stcWorld.intRows, stcWorld.intCols, intDRow, intDCol))
        {
            //collect items
            //If theres an object in the destination then collect it and increment counter
            int intDObj = stcWorld.arrWorld[intDRow][intDCol];
            if(intDObj == BUSH)
            {
                stcWorld.arrWorld[intDRow][intDCol] = EMPTY;
                stcWorld.Sticks++;
            }

            else if(intDObj == TREE)
            {
                stcWorld.arrWorld[intDRow][intDCol] = EMPTY;
                stcWorld.Logs++;
            }
            else if(intDObj == FLINT)
            {
                stcWorld.arrWorld[intDRow][intDCol] = EMPTY;
                stcWorld.Flint++;
            }
            else
            {
                stcWorld.arrWorld[intDRow][intDCol] = EMPTY;
            }

            if(stcWorld.arrWorld[intDRow][intDCol]==EMPTY)
            {
                stcWorld.arrWorld[stcWorld.intPlayerRow][stcWorld.intPlayerCol]=EMPTY;
                stcWorld.arrWorld[intDRow][intDCol] = PLAYER;
                stcWorld.intPlayerRow = intDRow;
                stcWorld.intPlayerCol = intDCol;
            }
        }
    }

    //Function to craft Axe
    void CraftAxe(tWorld& stcWorld)
    {
        if(stcWorld.Sticks < 4 || stcWorld.Logs < 1)
        {
            stcWorld.Sticks - 0;
            stcWorld.Logs - 0;
            stcWorld.Axes + 0;
        }
        else
        {
            stcWorld.Sticks --;
            stcWorld.Sticks --;
            stcWorld.Sticks --;
            stcWorld.Sticks --;

            stcWorld.Logs --;
            stcWorld.Axes ++;
        }
    }

    //Function to craft Fire Kit
    void CraftFK(tWorld& stcWorld)
    {
        if(stcWorld.Sticks < 3 || stcWorld.Flint < 2)
        {
            stcWorld.Sticks - 0;
            stcWorld.Logs - 0;
            stcWorld.FK + 0;
        }
        else
        {
            stcWorld.Sticks --;
            stcWorld.Sticks --;
            stcWorld.Sticks --;

            stcWorld.Flint --;
            stcWorld.Flint --;

            stcWorld.FK ++;
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
