#include "libPrac9.h"

namespace Crimespace
{
    int GetRand(int intLower, int intUpper)
    {
        int intRange = intUpper - intLower + 1;
        return rand()%intRange + intLower;
    }

    //Function to convert string arguments to integers
    int ConvertToInt(string strNum)
    {
        int intNum;
        stringstream ss {strNum};
        ss >> intNum;
        if(ss.fail())
        {
            cerr << "Error, could'nt convert arguments to integers" << endl;
            exit(ERR_CONV);
        }

        return intNum;

    }

    //Function to find empty space in the array to place features
    void FindSpace(twoArr arrSpace, int intRows, int intCols, int& intRow, int& intCol)
    {
        intRow = GetRand(0, intRows - 1);
        intCol = GetRand(0, intCols - 1);
        while(arrSpace[intRow][intCol]!=EMPTY)
        {
            intRow = GetRand(0, intRows - 1);
            intCol = GetRand(0, intCols - 1);
        }
    }

    //Range check
    void RangeCheck(int intValue, int intLower, int intUpper)
    {
        if (intValue > intUpper || intValue < intLower)
            cerr << "Your value shouldn't be greater than " << intUpper <<
                 " and less than "<< intLower << endl;
    }


    twoArr InitWorld(int intRows, int intCols, int intClues, int intPClues)
    {
        twoArr arrSpace;
        //Allocate memory
        arrSpace = new oneArr[intRows];
        for(int r=0; r<intRows; r++)
        {
            arrSpace[r] = new int[intCols];
            for(int c=0; c<intCols; c++)
            {
                arrSpace[r][c] = EMPTY;
            }
        }

        int intRow = 0;
        int intCol = 0;
        //Find an empty space and place the player
        FindSpace(arrSpace, intRows, intCols, intRow, intCol);
        arrSpace[intRow][intCol] = PLAYER;

        //For each clue, Find an empty space and place the clue
        for(int n=0; n<intClues; n++)
        {
            FindSpace(arrSpace, intRows, intCols, intRow, intCol);
            arrSpace[intRow][intCol] = CLUES;
        }
        //For each potential clue, Find an empty space and place the potential clue
        for(int j=0; j< 2 * intPClues; j++)
        {
            FindSpace(arrSpace, intRows, intCols, intRow, intCol);
            arrSpace[intRow][intCol] = PCLUE;
        }

        return arrSpace;
    }

    //function to find the location of the player in the array
    void LocatePlayer(twoArr arrSpace, int intRows, int intCols, int &intRow, int &intCol)
    {
        intRow = -1;
        intCol = -1;
        for(int r=0; r<intRows; r++)
        {
            for(int c=0; c < intCols; c++)
            {
                if(arrSpace[r][c]==PLAYER)
                {
                    intRow = r;
                    intCol = c;
                    return;
                }

            }
        }
    }

    void DisplaySpace(twoArr arrSpace, int intRows, int intCols, int intTurns, bool InspectClue)
    {
        //Clear the screen, for update
        system("cls");

        int intPRow = -1;
        int intPCol = -1;
        LocatePlayer(arrSpace, intRows, intCols, intPRow, intPCol);

        for (int r=0; r<intRows; r++)
        {
            for(int c = 0; c < intCols; c++)
            {
                char chDisplay = '\0';

                chDisplay = OBJECTS[arrSpace[r][c]];

                if(InspectClue)
                {
                    if((abs(r-intPRow)<=1) && (abs(c-intPCol)<=1))
                    {
                        chDisplay = iOBJECTS[arrSpace[r][c]];
                    }
                }

                cout << chDisplay << " ";
            }
            cout << endl;
        }

        cout << "UP: w" << endl
             << "DOWN: s" << endl
             << "LEFT: a" << endl
             << "RIGHT: d" << endl
             << "INSPECT: i" << endl
             << "QUIT: q" << endl
             << "Turns left: " << intTurns << endl
             << "Clues left: " << endl;
    }

    bool InWorld(int intRows, int intCols, int intRow, int intCol)
    {
        //return true if object exists within a location where 0 <= row <= TotalRows and 0 <= col <= Total Cols
        return (intRow >= 0 && intRow < intRows &&
                intCol >= 0 && intCol < intCols);
    }

    void Movement(twoArr arrSpace, int intRows, int intCols, int& intTurns, bool blnInspect, char chInput)
    {
        int intPRow = -1;
        int intPCol = -1;
        LocatePlayer(arrSpace, intRows, intCols, intPRow, intPCol);

        int intDRow = intPRow;
        int intDCol = intPCol;

        switch(tolower(chInput))
        {
        case 'w':
            intDRow--;
            intTurns--;
            break;
        case 's':
            intDRow++;
            intTurns--;
            break;
        case 'a':
            intDCol--;
            intTurns--;
            break;
        case 'd':
            intDCol++;
            intTurns--;
            break;

        case 'i':
            blnInspect;
            break;
        }

        arrSpace[intDRow][intDCol] == PLAYER;
        arrSpace[intPRow][intPCol] == EMPTY;

    }

    bool TestEnd(int intTurns, int ClueCount)
    {
        //If there are no longer any clues to discover, end game
        if(ClueCount <= 0)
            return true;
        //If theres no turns left, end game
        if(intTurns <= 0)
            return true;

        return false;
    }

    void DeallocMem(twoArr arrSpace, int intRows)
    {
        for(int r=0; r<intRows; r++)
        {
            delete[] arrSpace[r];
        }
        delete[] arrSpace;
        arrSpace = nullptr;
    }



}
