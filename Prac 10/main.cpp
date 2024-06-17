#include <iostream>
#include "libPrac10.h"

using namespace std;
using namespace RecipeSpace;

int main(int argc, char** argv)
{
    srand(time(nullptr));

    if(argc!=7)
    {
        cerr << "Not enough arguments to Run application" << endl;
        exit(ERR_ARGC);
    }

    int intRows = ConvToInt(argv[1]);
    int intCols = ConvToInt(argv[2]);
    int intTrees = ConvToInt(argv[3]);
    int intFlint = ConvToInt(argv[4]);
    int intBushes = ConvToInt(argv[5]);
    int intTurns = ConvToInt(argv[6]);

    tWorld stcWorld = InitWorld(intRows, intCols, intTrees, intFlint, intBushes, intTurns);

    char chInput = '\0';
    bool blnContinue = true;

    do
    {
        Display(stcWorld);

        cin >> chInput;
        chInput = tolower(chInput);

        switch(chInput)
        {
        case 'w':

        case 'e':

        case 'd':

        case 'c':

        case 'x':

        case 'z':

        case 'a':

        case 'q':
            MovePlayer(stcWorld, chInput);
            break;
        case 'p':
            CraftAxe(stcWorld);
            break;
        case 'o':
            CraftFK(stcWorld);
            break;
        case '-':
            blnContinue = false;
            break;



        default:
            cerr << "Invalid Option. Try again" << endl;
            system("pause");
        }

        Update(stcWorld);

        //If the status of the game is not running, terminate the game
        if(stcWorld.enStatus!=RUNNING)
        {
            blnContinue = false;
        }

    }while(blnContinue);


    DeallocMem(stcWorld.arrWorld, intRows);



    return 0;
}
