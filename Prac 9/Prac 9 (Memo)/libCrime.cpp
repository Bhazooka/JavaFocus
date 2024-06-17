#include "libCrime.h"

namespace CrimeSpace
{
    //Returns a random int number between intLow and intHigh
    int GetRand(int intLow, int intHigh)
    {
        assert(intHigh>intLow);
        int intRange = intHigh - intLow + 1;
        return rand()%intRange + intLow;
    }

    //Converts strNum to int. Exits if fail
    int GetInt(string strNum)
    {
        stringstream ss {strNum};
        int intNum;
        ss >> intNum;
        if(ss.fail())
        {
            cerr << "Could not convert string to int" << endl;
            exit(ERR_CONV);
        }
        return intNum;
    }

    //Checks if intVal is between intMin and intMax
    void RangeCheck(int intVal, int intMin, int intMax)
    {
        if(intVal<intMin || intVal > intMax)
        {
            cerr << intVal << " should be between " << intMin << " and " << intMax << endl;
            exit(ERR_RANGE);
        }

    }

    //Returns a new 2D array, initialised with empty spaces
    t2DArray AllocMem(int intRows, int intCols)
    {
        t2DArray arrGame;
        arrGame = new t1DArray[intRows];
        for(int r=0;r<intRows;r++)
        {
            arrGame[r] = new int[intCols];
            for(int c=0;c<intCols;c++)
            {
                arrGame[r][c] = EMPTY;
            }
        }
        return arrGame;
    }

    //Place intCount number of intFeatures in the game. Updates the intRow and intCol with the
    //last placed location
    void PlaceFeature(tGame& stcGame, int intCount, int intFeature, int& intRow, int& intCol)
    {
        for(int n=0;n<intCount;n++)
        {
            intRow = GetRand(0,stcGame.intRows-1);
            intCol = GetRand(0,stcGame.intCols-1);
            while(stcGame.arrGame[intRow][intCol]!=EMPTY)
            {
                intRow = GetRand(0,stcGame.intRows-1);
                intCol = GetRand(0,stcGame.intCols-1);
            }
            stcGame.arrGame[intRow][intCol] = intFeature;
        }
    }

    //Creates a new tGame struct with initial game values.
    tGame InitGame(int intRows, int intCols, int intClues, int intTurns)
    {
        //Struct for the game
        tGame stcGame;
        //Allocates memory for the arrGame member
        stcGame.arrGame = AllocMem(intRows,intCols);
        //Sets the varias initial values.
        stcGame.intRows = intRows;
        stcGame.intCols = intCols;
        stcGame.intTotalClues = intClues;
        stcGame.intCluesFound = 0;
        stcGame.intTurns = intTurns;
        stcGame.enStatus = RUNNING;

        //Ensures that there is enough space for the total number of real and potential clues
        int intTotalSpace = intRows * intCols;
        int intTotalFeatures = intClues + (intClues * 2);
        if(intTotalFeatures>=intTotalSpace)
        {
            cerr << "There is not enough space in the game for all the features" << endl;
            exit(ERR_SPACE);
        }

        //Places the different features in the 2D array
        int intRow = 0;
        int intCol = 0;

        //Place the clues
        PlaceFeature(stcGame,intClues,CLUE_HIDDEN,intRow, intCol);
        //Place the potential clues
        PlaceFeature(stcGame,intClues*2,CLUE_POTENTIAL,intRow, intCol);
        //Place the player
        PlaceFeature(stcGame,1,PLAYER,intRow,intCol);
        stcGame.intPRow = intRow;
        stcGame.intPCol = intCol;

        return stcGame;
    }

    //Outputs the world
    void PrintWorld(tGame stcGame)
    {
        assert(stcGame.arrGame!=nullptr);
        system("cls");

        Investigate(stcGame);
        for(int r=0;r<stcGame.intRows;r++)
        {
            for(int c=0;c<stcGame.intCols;c++)
            {
                if(stcGame.arrGame[r][c]==EMPTY)
                {
                    stcGame.arrGame[r][c] = EMPTY;
                }


                    cout << FEATURES[stcGame.arrGame[r][c]] << " ";

            }

            cout << endl;
        }
        cout<< "w) Move Up" << endl
            << "s) Move Down" << endl
            << "a) Move Left" << endl
            << "d) Move Right" << endl
            << "p) Investigate" << endl
            << "q) Quit" << endl
            << "Clues collected: " << stcGame.intCluesFound << endl
            << "Turns left:" << stcGame.intTurns << endl;
    }

    //Will pause the game
    void Pause()
    {
        cin.ignore(100,'\n');
        cout << "Press Enter to continue" << endl;
        cin.get();
    }

    //Returns true if intRow and intCol is within the boundaries of the intRows and intCols
    bool IsInWorld(int intRows, int intCols, int intRow, int intCol)
    {
        return (intRow>=0 && intRow < intRows &&
        intCol>=0 && intCol < intCols);
    }

    //Moves the player
    void MovePlayer(tGame& stcGame, int intMovement)
    {
        assert(stcGame.arrGame!=nullptr);
        //Get the current row and col of the player and set to potential destination
        int intDRow = stcGame.intPRow;
        int intDCol = stcGame.intPCol;
        //Modify potential destination, given the movement
        switch(intMovement)
        {
        case MOVE_UP:
            intDRow--;
            break;
        case MOVE_DOWN:
            intDRow++;
            break;
        case MOVE_LEFT:
            intDCol--;
            break;
        case MOVE_RIGHT:
            intDCol++;
            break;
        }

    //Confirms that the destination location is in the world
        if(IsInWorld(stcGame.intRows,stcGame.intCols,intDRow,intDCol))
        {
            //Move only if the destination is empty
            if(stcGame.arrGame[intDRow][intDCol]==EMPTY)
            {
                stcGame.arrGame[stcGame.intPRow][stcGame.intPCol]=EMPTY;
                stcGame.arrGame[intDRow][intDCol] = PLAYER;
                stcGame.intPRow = intDRow;
                stcGame.intPCol = intDCol;
            }
            if(stcGame.arrGame[intDRow][intDCol]==STAR)
            {
                stcGame.arrGame[stcGame.intPRow][stcGame.intPCol]=EMPTY;
                stcGame.arrGame[intDRow][intDCol] = PLAYER;
                stcGame.intPRow = intDRow;
                stcGame.intPCol = intDCol;

            }
        }
    }

    //Search for clues around the player
    void Investigate(tGame& stcGame)
    {
        assert(stcGame.arrGame!=nullptr);
        //Loop through each area around the player
        for(int r=stcGame.intPRow-1;r<=stcGame.intPRow+1;r++)
        {
            for(int c=stcGame.intPCol-1;c<=stcGame.intPCol+1;c++)
            {
                //Make sure the area is in the array before trying to access
                if(IsInWorld(stcGame.intRows,stcGame.intCols,r,c))
                {
                    //Change real hidden clues into real clues. Update the found clues.
                    if(stcGame.arrGame[r][c]==EMPTY)
                    {
                        stcGame.arrGame[r][c]=STAR;
                        stcGame.intCluesFound++;
                    }


                }
            }
        }
    }

    //Decrement the number of turns.
    //Test if all the clues were found.
    void Update(tGame& stcGame)
    {
        stcGame.intTurns--;
        if(stcGame.intTurns<0)
            stcGame.enStatus = LOST;
        if(stcGame.intCluesFound==stcGame.intTotalClues)
            stcGame.enStatus = WON;
    }

    //Deallocate the memory associated with a 2D array.
    void Dealloc(t2DArray& arrGame, int intRows)
    {
        assert(arrGame!=nullptr);
        for(int r=0;r<intRows;r++)
            delete[] arrGame[r];

        delete[] arrGame;
        arrGame = nullptr;
    }
}
