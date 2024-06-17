#include "libPractice2.h"

namespace CatchSpace
{
    int GetRandom(int intLower, int intUpper)
    {
        int intRange = intUpper - intLower + 1;
        return rand()%intRange + intLower;
    }

    int GetInt(string strNum)
    {
        //User a stringstream class to try and convert
        stringstream ss{strNum};
        int intNum = 0;
        ss >> intNum;
        //If the conversion failed, then ss wil raise its fail flag
        if(ss.fail())
        {
            cerr << "Could not convert parameter to integer" << endl;
            exit(ERR_CONVERT);
        }
        return intNum;
    }

    void PlaceFeature(t_TwoArray arrGame,int intRows, int intCols, int intFeature, int intCount)
    {
        //For each of the number of features
        for(int n=0;n<intCount;n++)
        {
            //Place the feature
            int intRow = GetRandom(0,intRows-1);
            int intCol = GetRandom(0,intCols-1);
            while(arrGame[intRow][intCol]!=EMPTY)
            {
                intRow = GetRandom(0,intRows-1);
                intCol = GetRandom(0,intCols-1);
            }
            arrGame[intRow][intCol] = intFeature;
        }
    }

    t_TwoArray InitGame(int intRows, int intCols)
    {
        t_TwoArray arrGame;
        //Allocate memory and initialise everything to empty
        arrGame = new t_OneArray[intRows];
        for(int r=0;r<intRows;r++)
        {
            arrGame[r]= new int[intCols];
            for(int c=0;c<intCols;c++)
            {
                arrGame[r][c] = EMPTY;
            }
        }

        PlaceFeature(arrGame,intRows, intCols,MAGIC,COUNT_MAGIC);
        PlaceFeature(arrGame,intRows, intCols,PLAYER,1);
        PlaceFeature(arrGame,intRows, intCols,ENEMY,1);
        return arrGame;
    }

    void Pause()
    {
        cin.ignore(100,'\n');
        cout << "Press Enter to continue" << endl;
        cin.get();
    }

    void PrintWorld(t_TwoArray arrGame, int intRows, int intCols, int intMagic)
    {
        system("cls");
        for(int r=0;r<intRows;r++)
        {
            for(int c=0;c<intCols;c++)
            {
                int intFeature = arrGame[r][c];
                cout << FEATURES[intFeature] << " ";
            }
            cout << endl;
        }
        cout << "w) Move Up" << endl
             << "s) Move Down" << endl
             << "a) Move Left" << endl
             << "d) Move Right" << endl
             << "q) Quit" << endl
             << "Collected Magic Items:" << intMagic << endl;
    }

}
