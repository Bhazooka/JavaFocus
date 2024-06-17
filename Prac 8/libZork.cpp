#include "libZork.h"

namespace ZorkSpace
{
    int ConvertToInt(string strNum)
    {
        int intNum = 0;
        stringstream ss {strNum};
        ss >> intNum;
        if(ss.fail())
        {
            cerr << "Coulnt Convert arrguments to integers" << endl;
            exit(ERR_CONV);
        }
        return intNum;
    }

    int GenRand(int intLower, int intUpper)
    {
        int intRange = intUpper - intLower + 1;
        return rand() % intRange + intLower;
    }


    void PopulateArray (D2Arr arrWorld, int intRows, int intCols, int intObject,int intCount)
    {
        for(int n = 0; n < intCount; n++) //Count represents the number of a certain object
        {
            int intRow = GenRand(0, intRows - 1);//Because array start from 0. eg value 19 starts counting from 0 to 19 = 20
            int intCol = GenRand(0, intCols - 1);
            while(arrWorld[intRow][intCol] != EMPTY)
            {
                intRow = GenRand(0, intRows - 1);
                intCol = GenRand(0, intCols - 1);
            }
            arrWorld[intRow][intCol] = intObject;
        }
    }


    D2Arr InitWorld(int intRows, int intCols)
    {
        D2Arr arrWorld;
        //Allocate memory and initialise
        arrWorld = new D1Arr[intRows];
        for (int r = 0; r < intRows; r++)
        {
            arrWorld[r] = new int[intCols];
            for(int c = 0; c < intCols; c++)
            {
                arrWorld[r][c] = EMPTY;
            }
        }
        //PopulateArray(arrWorld, intRows, intCols, BATTERY, BatteryCount);
        PopulateArray(arrWorld, intRows, intCols, PLAYER, 1);
        PopulateArray(arrWorld, intRows, intCols, GRUE, 1);
        PopulateArray(arrWorld, intRows, intCols, PIT, 10);
        return arrWorld;
    }


    //Function to display the world on the screen and controls
    void PrintWorld(D2Arr arrWorld, int intRows, int intCols, int& BatteryCount)
    {

        system("cls");
        for (int r = 0; r < intRows; r++)
        {
            for (int c = 0; c < intCols; c++)
            {
                int intObjects = arrWorld[r][c];
                cout << OBJECTS[intObjects] << " ";
            }
            cout << endl;
        }
        cout <<endl;
        cout << "____Controls____" << endl << endl
             << "w: UP" << endl
             << "s: DOWN" << endl
             << "a: LEFT" << endl
             << "d: RIGHT" << endl
             << "t: Tourch On/Off" << endl
             << "Battery Count: " << BatteryCount << endl;
    }

    bool FindObject(D2Arr arrWorld, int intRows, int intCols, int& intRow, int& intCol, int intObject)
    {
        intRow = -1;
        intCol = -1;

        for(int r = 0; r < intRows; r++)
        {
            for(int c = 0; c < intCols; c++)
            {
                if(arrWorld[r][c] == intObject)
                {
                    intRow = r;
                    intCol = c;
                    return true;
                }
            }
        }
        return false;
    }

    //Checks if the character is in the world
    bool IsInWorld(int intRows, int intCols, int intRow, int intCol)
    {
        return (intRow>=0 && intRow < intRows &&
                intCol>=0 && intCol< intCols);
    }

    //Movement
    void Movement(D2Arr arrWorld, int intRows, int intCols, int &intBattery, char chInput)
    {
        int intPRow = 0;
        int intPCol = 0;
        if(!FindObject(arrWorld, intRows, intCols, intPRow, intPCol, PLAYER))
            return; //return true or false

        int intDCol = intPCol;
        int intDRow = intPRow;
        switch(chInput)
        {
        case 'w':
            intDRow --;
            intBattery--;
            break;
        case 's':
            intDRow ++;
            intBattery--;
            break;
        case 'a':
            intDCol --;
            intBattery--;
            break;
        case 'd':
            intDCol ++;
            intBattery--;
            break;

        }

        if(IsInWorld(intRows, intCols, intDRow, intDCol))
        {
            if(arrWorld[intDRow][intDCol] == BATTERY)
            {
                intBattery += 3;
                arrWorld[intDRow][intDCol] = PLAYER;
            }

            else if(arrWorld[intDRow][intDCol] == PIT)
            {
                arrWorld[intDRow][intDCol] = PIT;
            }

            else if(arrWorld[intDRow][intDCol] == PIT)
            {
                arrWorld[intDRow][intDCol] = PIT;
            }

            else
            {
                arrWorld[intDRow][intDCol] = arrWorld[intDRow][intDCol] + PLAYER;
            }
            arrWorld[intPRow][intPCol] = arrWorld[intPRow][intPCol] - PLAYER;
        }

    }

    void DeallocMem(D2Arr &arrWorld, int intRows)
    {
        for(int r=0; r<intRows; r++)
        {
            delete[] arrWorld[r];
        }
        delete[] arrWorld;
        arrWorld = nullptr;
    }


}
