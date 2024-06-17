#include <iostream>
#include "libPrac6.h"

using namespace std;
using namespace BattleSpace;

int main()
{
    srand(time(0));
    int arrSLOT1[SLOTS];
    int arrSLOT2[SLOTS];

    Convert(arrSLOT1, arrSLOT2);
    char arrPointer[SLOTS];
    bool blnContinue = true;
    bool blnComplete = false;

    Initialise(arrSLOT1);
    Convert(arrSLOT1, arrSLOT2);
    PointerPosition(arrPointer);


    char chMove = '\0';
    do
    {
    system("cls");

    OutPutGame(arrSLOT1);
    OutPutPointer(arrPointer);
    OutPutGame(arrSLOT2);

    cin >> chMove;
    switch(chMove)
    {
        case 'a':
        case 'A':
        {
            Move(arrPointer, chMove);
            arrChange(arrSLOT1, arrPointer);
            OutPutGame(arrSLOT1);
            OutPutPointer(arrPointer);
            blnComplete = Finish(arrSLOT1,arrSLOT2);

            if(blnComplete)
            blnContinue = false;

            break;
        }
        case 'd':
        case 'D':
        {
            Move(arrPointer, chMove);
            arrChange(arrSLOT1, arrPointer);
            OutPutGame(arrSLOT1);
            OutPutPointer(arrPointer);
            blnComplete = Finish(arrSLOT1,arrSLOT2);

            if(blnComplete)
            blnContinue = false;

            break;
        }
        case 'e':
        case 'E':
        {
            cout << " Terminating Program." << endl;
            blnContinue = false;

        }
        default:
        {
            cerr << "Invalid Option, Select A to move right or D to move left";
            Pause();
        }
    }

    }while(blnContinue);



   if(blnComplete)
    {
        cout << "COMPLETE!" << endl;
    }
    else
    {
        cout << "Exiting Program" << endl;
    }

 return 0;
}
