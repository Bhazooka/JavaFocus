#include "libRandom.h"

namespace RandomSpace
{
    int ConvertToInt(string strNum)
    {
        int intNum;
        stringstream ss {strNum};
        ss >> intNum;
        if(ss.fail())
        {
            cerr << "Coundn't convert argument strings to integer" ;
            exit(ERR_CONV);
        }

        return intNum;
    }

    //GenRandom
    int GenRand(int intLow, int intHigh)
    {
        int intRange = intHigh - intLow + 1;
        return rand()%intRange + intLow;
    }

    //RangeCheck
    void RangeCheck (int intValue, int intLow, int intHigh)
    {
        if(intValue < intLow || intValue > intHigh)
        {
            cerr << "The value should be below " << intHigh << ", and greater than " << intLow << "." << endl;
            exit(ERR_RANGE);
        }
    }

    //allocate mem
    twoDarr AllocMem(int intRows, int intCols)
    {
        twoDarr arrWorld;
        arrWorld = new oneDarr[intRows];
        for(int r=0; r<intRows; r++)
        {
            arrWorld[r]= new int[intCols];
            for (int c=0; c<intCols; c++)
            {
                arrWorld[r][c] = EMPTY;
            }
        }
        return arrWorld;
    }

    //place objects
    void PlaceObjects(recWorld& stcWorld, int intAmount, int intObject, int& intRow, int& intCol)
    {
        for(int a = 0; a<intAmount; a++)
        {
            intRow = GenRand(0, stcWorld.intRows-1);
            intCol = GenRand(0, stcWorld.intCols-1);
            while(stcWorld.arrWorld[intRow][intCol]!= EMPTY)
            {
                intRow = GenRand(0, stcWorld.intRows-1);
                intCol = GenRand(0, stcWorld.intCols-1);
            }
            stcWorld.arrWorld[intRow][intCol] = intObject;
        }
    }

    //Initialise world
    recWorld InitWorld(int intRows, int intCols, int intTrees, int intBushes, int intFlintS, int intTurns, int intRange)
    {
        recWorld stcWorld;

        stcWorld.arrWorld = AllocMem(intRows, intCols);
        stcWorld.intRows = intRows;
        stcWorld.intCols = intCols;

        stcWorld.intTrees = intTrees;
        stcWorld.intBushes = intBushes;
        stcWorld.intFlintS = intFlintS;
        stcWorld.intTurns = intTurns;
        stcWorld.Logs = 0;
        stcWorld.Sticks = 0;
        stcWorld.Flint = 0;
        stcWorld.GameStatus = RUNNING;

        int WorldArea = intRows * intCols;
        int TotalObj = intTrees + intBushes + intFlintS;
        if(WorldArea < TotalObj)
        {
            cerr << "Theres not enough space in the game for all the objects";
            exit(ERR_SPACE);
        }

        int intRow = 0;
        int intCol = 0;
        //Place the objects
        PlaceObjects(stcWorld, intTrees, TREE, intRow, intCol);
        PlaceObjects(stcWorld, intBushes, BUSH, intRow, intCol);
        PlaceObjects(stcWorld, intFlintS, FLINT, intRow, intCol);

        PlaceObjects(stcWorld, 1, PLAYER, intRow, intCol);
        stcWorld.intPlayerRow = intRow;
        stcWorld.intPlayerCol = intCol;

        PlaceObjects(stcWorld, 1, ENEMY, intRow, intCol);
        stcWorld.intEnemyRow = intRow;
        stcWorld.intEnemyCol = intCol;

        //This is for the enemy player.
        stcWorld.intRange  = intRange;

        return stcWorld;
    }


    //Print
    void Display(recWorld stcWorld)
    {
        system("cls");
        for(int r=0; r<stcWorld.intRows; r++)
        {
            for(int c = 0; c<stcWorld.intCols; c++)
            {
                cout << OBJECTS[stcWorld.arrWorld[r][c]] << " ";
            }
            cout << endl;
        }

        cout << "w, a, s, d" << endl
             << "Number of turns left: " << stcWorld.intTurns << endl;
    }


    //Is in world
    bool IsInWorld(int intRows, int intCols, int intRow, int intCol)
    {
        return(intRow >= 0 && intRow < intRows &&
               intCol >= 0 && intCol < intCols);
    }

    //Movement
    void MovePlayer(recWorld& stcWorld, char Choice)
    {
        int intDRow = stcWorld.intPlayerRow;
        int intDCol = stcWorld.intPlayerCol;

        switch(Choice)
        {
        case 'w':
            intDRow--;
            break;
        case 's':
            intDRow++;
            break;
        case 'a':
            intDCol--;
            break;
        case 'd':
            intDCol++;
            break;
        }

        if(IsInWorld(stcWorld.intRows, stcWorld.intCols, intDRow, intDCol))
        {
            int DObject = stcWorld.arrWorld[intDRow][intDCol];
            if(DObject == TREE)
            {
                stcWorld.arrWorld[intDRow][intDCol] = EMPTY;
                stcWorld.Logs++;
            }
            else if(DObject == BUSH)
            {
                stcWorld.arrWorld[intDRow][intDCol] = EMPTY;
                stcWorld.Sticks++;
            }
            else if(DObject == FLINT)
            {
                stcWorld.arrWorld[intDRow][intDCol] = EMPTY;
                stcWorld.Flint++;
            }
            else //This allows the enemy to end game when play touches enemy.
            {
                stcWorld.arrWorld[intDRow][intDCol] = EMPTY;
            }

            if(stcWorld.arrWorld[intDRow][intDCol] == EMPTY)
            {
                stcWorld.arrWorld[stcWorld.intPlayerRow][stcWorld.intPlayerCol] = EMPTY;
                stcWorld.arrWorld[intDRow][intDCol] = PLAYER;

                stcWorld.intPlayerRow = intDRow;
                stcWorld.intPlayerCol = intDCol;
            }
        }
    }

    bool IsInRange(recWorld& stcWorld)
    {
        int y = abs(stcWorld.intPlayerRow - stcWorld.intEnemyRow); //I added absolut value to keep the function positive
        int x = abs(stcWorld.intPlayerCol - stcWorld.intEnemyCol);

        int r = sqrt((x*x)+(y*y));
        if(r < stcWorld.intRange)
        {
            return true;
        }

        return false;
    }

    //Enemy movement
    void MoveEnemy(recWorld& stcWorld)
    {
        int intDRow = stcWorld.intEnemyRow;
        int intDCol = stcWorld.intEnemyCol;

        if(IsInRange(stcWorld))
        {
            if(stcWorld.intPlayerRow < stcWorld.intEnemyRow)
                intDRow--;
            if(stcWorld.intPlayerRow > stcWorld.intEnemyRow)
                intDRow++;
            if(stcWorld.intPlayerCol < stcWorld.intEnemyCol)
                intDCol--;
            if(stcWorld.intPlayerCol > stcWorld.intEnemyCol)
                intDCol++;
        }
        else
        {
            int intMoveEnemyRow = GenRand(-1,1);
            int intMoveEnemyCol = GenRand(-1,1);
            intDRow = intDRow + intMoveEnemyRow;
            intDCol = intDCol + intMoveEnemyCol;
        }

        if(IsInWorld(stcWorld.intRows, stcWorld.intCols, intDRow, intDCol))
        {
            if( stcWorld.intEnemyRow == stcWorld.intPlayerRow && stcWorld.intEnemyCol == stcWorld.intPlayerCol)
            {
                stcWorld.GameStatus = LOSE;
                return;
            }

            if(stcWorld.arrWorld[intDRow][intDCol] == EMPTY)
            {
                stcWorld.arrWorld[stcWorld.intEnemyRow][stcWorld.intEnemyCol] = EMPTY;
                stcWorld.arrWorld[intDRow][intDCol] = ENEMY;

                stcWorld.intEnemyRow = intDRow;
                stcWorld.intEnemyCol = intDCol;
            }
        }
    }

    //test if the game has ended
    bool Update(recWorld& stcWorld)
    {
        int intTotalObjects = stcWorld.intTrees + stcWorld.intBushes + stcWorld.intFlintS;
        if(stcWorld.Flint + stcWorld.Logs + stcWorld.Sticks == intTotalObjects)
        {
            return true;
            stcWorld.GameStatus = WIN;
        }
        else
            return false;

    }

    //dealocate memory
    twoDarr DeallocateMem(twoDarr& arrWorld, int intRows)
    {
        assert(arrWorld!=nullptr);
        for(int r=0; r< intRows; r++)
        {
            delete[] arrWorld[r];
        }
        delete[] arrWorld;
        arrWorld = nullptr;
        //return arrWorld;
    }
}
