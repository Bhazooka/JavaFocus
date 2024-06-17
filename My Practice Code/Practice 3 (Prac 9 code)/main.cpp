
#include "libCrime.h"
#include <iostream>

using namespace std;
using namespace CrimeSpace;

int main(int argc, char** argv)
{
    //Seeding the random number generator
    srand(time(nullptr));

    //Double check number of command line args.
    if(argc!=5)
    {
        cerr << "Incorrect number of command line arguments" << endl;
        exit(ERR_ARGC);
    }

    //Allocate command line args to variables.
    int intRows = GetInt(argv[1]);
    int intCols = GetInt(argv[2]);
    int intTurns = GetInt(argv[3]);
    int intCountClues = GetInt(argv[4]);

    //Basic range checking
    RangeCheck(intRows,DIM_MIN,DIM_MAX);
    RangeCheck(intCols,DIM_MIN,DIM_MAX);
    RangeCheck(intTurns,MIN_TURNS,MAX_TURNS);
    RangeCheck(intCountClues,MIN_CLUE,(intRows*intCols)/3); //Make sure the maximum clues
    //isn't more than the third amount of space.

    //Initialise the game variable.
    tGame stcGame = InitGame(intRows,intCols,intCountClues,intTurns);
    char chInput = '\0';
    bool blnContinue = true;

    //Main loop
    do
    {
        //Output the world
        PrintWorld(stcGame);
        //Get input
        cin >> chInput;
        chInput = tolower(chInput);
        //Handle input
        switch(chInput)
        {
        case 'w':
            MovePlayer(stcGame,MOVE_UP);
            break;
        case 's':
            MovePlayer(stcGame,MOVE_DOWN);
            break;
        case 'a':
            MovePlayer(stcGame,MOVE_LEFT);
            break;
        case 'd':
            MovePlayer(stcGame,MOVE_RIGHT);
            break;
        case 'q':
            stcGame.enStatus = QUIT;
            break;
        default:
            cerr << "Incorrect option. Please retry" << endl;
            system("pause");
        }
        //Update some basic game information every turn
        Update(stcGame);
        //See if the game needs to stop
        if(stcGame.enStatus!=RUNNING)
            blnContinue = false;
    }while(blnContinue);

    cout << "************************************************************" << endl;
    if(stcGame.enStatus == QUIT)
        cout << "* YOU QUIT THE GAME *" << endl;
    if(stcGame.enStatus == LOST)
        cout << "* YOU DID NOT MANAGE TO COLLECT ALL THE CLUES *" << endl;
    if(stcGame.enStatus == WON)
        cout << "* CONGRATULATIONS. YOU COLLECTED ALL THE CLUES *" << endl;
    cout << "************************************************************" << endl;

    Dealloc(stcGame.arrGame, intRows);

    return SUCCESS;
}

