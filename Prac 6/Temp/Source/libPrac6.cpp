#include "libPrac6.h"

namespace BattleSpace
{

     int GenRand(int intLowest, int intHighest)
     {
        int intRange = intHighest - intLowest + 1;
        return rand()%intRange + intLowest;
     }

     void Pause()
    {
        cin.ignore(100,'\n');
        cout << "Press Enter to continue" << endl;
        cin.get();
    }



    void Initialise(int arrNums[])
    {
        //Add 0's and 1's
        for(int i = 0; i < SLOTS; i++)
        {
            arrNums[i] = GenRand(ZERO, ONE);
        }

    }



    void OutPutGame(int arrNums[])
    {
        for(int i = 0; i < SLOTS; i++)
        {
            cout << arrNums[i];
        }
        cout << endl;
    }


    //Inputs a position for pointer
    void PointerPosition(char arrNums[])
    {
         for(int i = 0; i < SLOTS; i++)
         {
             arrNums[i] = ' ';
         }
        arrNums[0] = '^';
    }


    //Outputs the pointer
    void OutPutPointer(char arrNums[])
    {
         for(int i = 0; i < SLOTS; i++)
        {
            cout << arrNums[i];
        }
         cout << endl
         << "A: LEFT" << endl
         << "D: RIGHT" << endl
         << "E: EXIT" << endl;
    }


    //Tracks pointer
    int FindPointer(char arrNums[])
    {
        int intPosition = 0;
        for(int i = 0; i < SLOTS; i++)
        {
            if(arrNums[i] == '^')
                intPosition = i;
        }
        return intPosition;
    }


    //Function to manage movement
    void Move(char arrNums1[], char chMove)
    {
        int intCurrentPosition = -1;
        intCurrentPosition = FindPointer(arrNums1);
        int intDestination = intCurrentPosition;

        if(intCurrentPosition == -1)
        {
            cerr << "Could not find the value. Exiting the program now" << endl;
            exit(ERR_NOT_FOUND);
        }

        switch(chMove)
        {

            case 'a':
            case 'A':
            {
                intDestination --;
                break;
            }

            case 'd':
            case 'D':
            {
                intDestination++;
                break;
            }

            default:
            {
                cerr << "Error";
                exit(ERR_NOT_FOUND);
            }

        }

        if(intDestination >= 0 && intDestination < SLOTS)
        {
           arrNums1[intDestination] = '^';
           arrNums1[intCurrentPosition] = ' ';
        }

    }


    //Change the value in first array slots
    void arrChange(int arrNums[], char arrNums1[])
    {
        int intPosition = FindPointer(arrNums1);

        if(arrNums[intPosition] == 1)
        {
            arrNums[intPosition] = 0;
        }
        else
        {
            arrNums[intPosition] = 1;
        }

    }


    //Change the value in second arry slot
    void Convert(int arrNums[], int SecondArr[])
    {

        for(int i = 0; i < SLOTS; i++)
        {
            if(arrNums[i] == 0)
            {
                SecondArr[i] = 1;
            }
            else
            {
                SecondArr[i] = 0;
            }

        }
    }


    //Function that checks if the game is over
    bool Finish(int arrNums[], int SecondArr[])
    {
        bool blnComplete = false;
        int intCount = 0;
        for(int i = 0; i < SLOTS; i++)
        {
            if(arrNums[i] == SecondArr[i])

                intCount++;
        }

        if (intCount == 20)

            blnComplete = true;

            return blnComplete;
    }

}
