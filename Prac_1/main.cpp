#include "libPrac2.h"

using namespace std;
using namespace Prac2;

int main(int argc, char** argv)
{
    srand(time(nullptr));
    bool blnContinue = true;
    char chInput = '\0';

    if(argc!=3)
    {
        cerr << "Incorrect number of command line args" << endl;
        exit(ERR_ARGC);
    }

    int intRows = GetInt(argv[1]);
    int intCols = GetInt(argv[2]);
//Do some range checking here.

    stcGame* game = CreateObj(intRows,intCols);
    do
    {
        game -> DisplayPtr;
        cin >> chInput;
        chInput = tolower(chInput);
        switch(chInput)
        {
        case 'w':
        case 's':
        case 'a':
        case 'd':
            game -> PlayerMovementPtr;
            break;
        case 'q':
            game -> state = QUIT;
            break;
        default:
            cerr << "Please select a valid input" << endl;
            game -> Pause();
        }
        game -> EnemyMovementPtr;
        if(game -> state!=RUNNING)
        {
            blnContinue = false;
        }

    }
    while(blnContinue);

    return 0;
}
