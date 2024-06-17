#include <iostream>
#include "libPrac9.h"

using namespace std;
using namespace Crimespace;

int main(int argc, char** argv)
{
    srand(time(nullptr));

    if(argc!=5)
    {
        cerr << "Not enough arguments to commence this program. Need 4 arguments" << endl
             <<"[Number of Rows] [Number of Cols] [Number of Clues] [Number of Turns]" << endl;
        exit(ERR_ARG);
    }

    int intRows = ConvertToInt(argv[1]); //Rows and Cols are the size of the enviroment
    int intCols = ConvertToInt(argv[2]); //Rows and Cols are the size of the environment
    int intClues = ConvertToInt(argv[3]);//Number of clues
    int intTurns = ConvertToInt(argv[4]);//Number of turns left


    bool blnContinue = true;
    bool blnInspect = false;
    bool blnEnd = false;
    int intPClues = intClues * 2;

    int ClueCount = intClues;


    twoArr arrSpace = InitWorld(intRows, intCols, intClues, intPClues);
    char chInput = '\0';

    do
    {
        DisplaySpace(arrSpace, intRows, intCols, intTurns, blnInspect);
        cin >> chInput;
        switch(tolower(chInput))
        {
        case 'w':
        case 's':
        case 'a':
        case 'd':
        case 'i':
            Movement(arrSpace, intRows, intCols, intTurns, blnInspect, chInput);
            break;

        case 'q':
            blnContinue = false;
            break;
        }
        blnEnd = TestEnd(intTurns, ClueCount);
    }while(blnContinue && !blnEnd);

    DisplaySpace(arrSpace, intRows, intCols, intTurns, blnInspect);

    if(blnEnd)
        cout << "WELL DOWN, YOU FOUND ALL THE CLUES!" << endl;

    DeallocMem(arrSpace, intRows);


    return 0;
}
