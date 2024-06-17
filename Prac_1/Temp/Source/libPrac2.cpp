#include "libPrac2.h"

namespace Prac2
{
    //random number generator
    int GenRand(int intLower, int intUpper)
    {
        int intRange = intUpper-intLower+1;
        return rand()%intRange+intLower;
    }

    void Pause()
    {
        cin.ignore(100,'\n');
        cout << "Press Enter to continue" << endl;
        cin.get();
    }

    //memory allocation implementation
    t_2DArray AllocMem(int intRows, int intCols)
    {
        t_2DArray arrGame = new t_1DArray[intRows];
        for(int r=0; r<intRows; r++)
        {
            arrGame[r] = new int[intCols];
            for(int c=0; c<intCols; c++)
            {
                arrGame[r][c] = EMPTY;
            }
        }
        return arrGame;
    }

    // placing game features randomly
    void PlaceFeature(stcGame& game, int intNumFeatures, int intFeature, int intColFrom, int intColTo)
    {
        for(int n=0; n<intNumFeatures; n++)
        {
            int intRow = GenRand(0,game.intRows-1);
            int intCol = GenRand(intColFrom,intColTo);
            while(game.arrGame[intRow][intCol]!=EMPTY)
            {
                intRow = GenRand(0,game.intRows-1);
                intCol = GenRand(intColFrom,intColTo);
            }
            game.arrGame[intRow][intCol] = intFeature;
        }
    }

    void PlaceEnemies(stcGame& game)
    {
        for(int c=1; c<game.intCols-1; c++)
        {
            if(c%2==1)
            {
                int intRow = GenRand(0,game.intRows-1);
                game.arrGame[intRow][c] = GenRand(2,3);
            }
        }
    }

    //convert to string
    int GetInt(string strNum)
    {
        stringstream ss {strNum};
        int intNum;
        ss >> intNum;
        if(ss.fail())
        {
            cerr << "Cannot convert string to int" << endl;
            exit(ERR_CONV);
        }
        return intNum;
    }

    bool IsInWorld(int intRows, int intCols, int intRow, int intCol)
    {
        return (intRow>=0&&intRow<intRows &&
                intCol>=0&&intCol<intCols);
    }

    void Copy(t_2DArray arrFrom, t_2DArray arrTo, int intRows, int intCols, int intExcept)
    {
        for(int r=0; r<intRows; r++)
        {
            for(int c=0; c<intCols; c++)
            {
                arrTo[r][c] = EMPTY;
                if(arrFrom[r][c]<intExcept)
                    arrTo[r][c] = arrFrom[r][c];
            }
        }
    }

    //enemy movement
    void MoveEnemy(stcGame& game, t_2DArray arrTemp, int intRow, int intCol, int intFeature)
    {
        int intDRow = intRow;
        int intDCol = intCol;
        if(intFeature==ENEMY_UP)
            intDRow--;
        else
            intDRow++;

        if(IsInWorld(game.intRows,game.intCols,intDRow,intDCol))
        {
            arrTemp[intDRow][intDCol]=intFeature;
            arrTemp[intRow][intCol]=EMPTY;
        }
        else
        {
            if(intFeature==ENEMY_UP)
                intFeature=ENEMY_DOWN;
            else
                intFeature=ENEMY_UP;
            arrTemp[intRow][intCol]=intFeature;
        }

    //See if the enemy is in the same column as the player
        if(intCol==game.intPCol)
        {
            game.blnSameCol = true;
        }
    }


    /// implementation functions of function pointers declared in struct


    void MovePlayer(stcGame& game, char chInput)
    {
    //Reset the move counter if spotted and finished moving
        if(game.blnSpotted && game.intMove==0)
            game.intMove = 2;

    //If not spotted, ensure the movement is normal
        if(!game.blnSpotted)
            game.intMove = 1;

        if(--game.intMove==0)
        {
            int intDRow = game.intPRow;
            int intDCol = game.intPCol;
            switch(chInput)
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
            if(IsInWorld(game.intRows,game.intCols,intDRow,intDCol))
            {
    //See if the destination contains an enemy
                if(game.arrGame[intDRow][intDCol]>=ENEMY_UP)
                {
                    game.state = LOST;
                    return;
                }

    //See if the existing location contains an enemy
                if(game.arrGame[game.intPRow][game.intPCol]>=ENEMY_UP)
                {
                    game.state = LOST;
                    return;
                }

                if(game.arrGame[intDRow][intDCol]!=WALL)
                {
                    game.intPRow = intDRow;
                    game.intPCol = intDCol;
                }

    //See if we reached the safe space
                if(game.intPCol==0)
                    game.state = GameStatus::WON;
            }

        }

    }

    //display
    void PrintWorld(stcGame game)
    {
        system("clear");
        for(int r=0; r<game.intRows; r++)
        {
            for(int c=0; c<game.intCols; c++)
            {
                if(r==game.intPRow && c==game.intPCol)
                    cout << F_PLAYER;
                else
                    cout <<FEATURES[game.arrGame[r][c]];
                cout << " ";
            }
            cout << endl;
        }
        cout << "w: Move Up" << endl
             << "s: Move Down" << endl
             << "a: Move Left" << endl
             << "d: Move Right" << endl
             << "q: Quit" << endl;
    }

    void MoveEnemies(stcGame& game)
    {
    //Assume the enemy and the player is not in the same col
        game.blnSameCol = false;
        game.blnSpotted = false;

    //Move each of the enemies
        t_2DArray arrTemp = AllocMem(game.intRows,game.intCols);
        Copy(game.arrGame,arrTemp,game.intRows,game.intCols,ENEMY_UP);

        for(int r=0; r<game.intRows; r++)
        {
            for(int c=1; c<game.intCols-1; c+=2)
            {
                if(game.arrGame[r][c]>=ENEMY_UP)
                    MoveEnemy(game, arrTemp, r, c, game.arrGame[r][c]);
            }
        }
        Copy(arrTemp,game.arrGame,game.intRows,game.intCols,999);
        Destruct(game);

    //Modify the player movement if the enemy and the player is in the same column.
        if(game.blnSameCol)
        {
    //Find the row the enemy is in
            int intERow = 0;
            for(int r=0; r<game.intRows; r++)
            {
                if(game.arrGame[r][game.intPCol]==enFeatures::ENEMY_DOWN ||
                        game.arrGame[r][game.intPCol] == enFeatures::ENEMY_UP)
                    intERow = r;

            }

            int intFeature = game.arrGame[intERow][game.intPCol];
            if(intFeature==ENEMY_UP)
            {
                if(game.intPRow<intERow)
                {
                    game.blnSpotted=true;
                    return;
                }
                game.blnSpotted = false;
                return;
            }

            if(intFeature==ENEMY_DOWN)
            {
                if(game.intPRow>intERow)
                {
                    game.blnSpotted=true;
                    return;
                }
                game.blnSpotted = false;
                return;
            }
        }
    }


    ///Creation and destruction functions

    stcGame* CreateObj(int intRows, int intCols)
    {

        ///function pointer declaration
        void (*DisplayPtr)(stcGame game) = PrintWorld;
        void (*PlayerMovementPtr)(stcGame& game, char chInput) = MovePlayer;
        void (*EnemyMovementPtr)(stcGame& game) = MoveEnemies;
        t_2DArray (*AllocPtr)(int intRows, int intCols) = AllocMem;

        //initialising the walls of the game
        t_2DArray arrGame = (*AllocPtr)(intRows,intCols);
        for(int r=0; r<intRows; r++)
        {
            for(int c=1; c<intCols-1; c++)
            {
                if(c%2==0)
                    arrGame[r][c] = WALL;
            }
        }
        stcGame game;

        game.arrGame = arrGame;

        //Place the player
        game.intPCol = intCols-1;
        game.intPRow = GenRand(0,intRows-1);
        game.intRows = intRows;
        game.intCols = intCols;
        game.state = RUNNING;
        game.blnSpotted = false;
        game.blnSameCol = false;
        game.intMove = 1;

        //Place the enemies
        PlaceEnemies(game);
        //PlaceFeature(game,intEnemies,ENEMY,1,intCols-2);

        //Open the doors
        for(int c=1; c<intCols-1; c++)
        {
            if(c%2==0)
            {
                int intRow = GenRand(0,intRows-1);
                game.arrGame[intRow][c] = EMPTY;
            }
        }
       return game;
    }

    void Destruct(stcGame *game)
    {
        for(int r=0; r< game -> intRows; r++)
            delete[] game -> arrGame[r];
        delete[] game;
        game = nullptr;
    }

}
