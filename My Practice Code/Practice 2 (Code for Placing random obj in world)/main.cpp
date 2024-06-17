#include "libPractice2.h"
#include <iostream>

using namespace std;
using namespace CatchSpace;

int main(int argc, char** argv)
{

    srand(time(nullptr));
    if(argc!=3)
    {
        cerr << "Incorrect command line arguments" <<endl;
        cerr << "Please run " << argv[0] << " <TotalRows> <TotalCols>" << endl;
        exit(ERR_ARGC);
    }

    //Get initial variables
    int intRows = GetInt(argv[1]);
    int intCols = GetInt(argv[2]);


    //Basic Range Check
    if(intRows>MAX_SIZE ||
       intCols>MAX_SIZE ||
       intRows < MIN_SIZE ||
       intCols < MIN_SIZE)
    {
        cerr << "Command line arguments must be greater than " <<  MIN_SIZE << " and less than " << MAX_SIZE << endl;
        exit(ERR_RANGE);
    }

    //Initialise other variables
    int intMagic = 0;
    bool blnContinue = true;
    char chInput = '\0';
    t_TwoArray arrGame = InitGame(intRows, intCols);
    do
    {
        //Print World
        PrintWorld(arrGame, intRows, intCols, intMagic);
        //Get input
        cin >> chInput;
        //Handle input
        chInput = tolower(chInput);
        switch(chInput)
        {
            case 'w':
            case 's':
            case 'a':
            case 'd':
                //Handle the movement of the player
                break;
            case 'q':
                blnContinue = false;
                break;
            default:
                cerr << "Incorrect option" << endl;
                Pause();
        }
    }while(blnContinue);


    return 0;

}
