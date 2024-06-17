#include <iostream>
#include "libRandom.h"

using namespace std;
using namespace RandomSpace;

int main(int argc, char** argv)
{
    srand(time(nullptr));

    if(argc != 8)
    {
        cerr << "Theres not enough arguments to run this program" ;
        exit(ERR_ARGC);
    }

    int intRows = ConvertToInt(argv[1]);
    int intCols = ConvertToInt(argv[2]);
    int intTrees = ConvertToInt(argv[3]);
    int intBushes = ConvertToInt(argv[4]);
    int intFlintS = ConvertToInt(argv[5]);
    int intTurns = ConvertToInt(argv[6]);
    int intRange = ConvertToInt(argv[7]);

    assert(intRows > 0);
    assert(intCols > 0);
    assert(intTrees > 0);
    assert(intBushes > 0);
    assert(intFlintS > 0);
    assert(intTurns > 0);
    assert(intRange > 0);

    recWorld stcWorld = InitWorld(intRows, intCols, intTrees, intBushes, intFlintS, intTurns, intRange);

    char Choice = '\0';
    bool blnCont = true;

    do
    {
        Display(stcWorld);

        cin >> Choice;
        Choice = tolower(Choice);

        switch(Choice)
        {
        case 'w':
        case 's':
        case 'a':
        case 'd':
            MovePlayer(stcWorld, Choice);
            break;
        case 'q':
            blnCont = false;
            break;
        default:
            cerr << "Invalid Option, Try again";
            break;
            system("pause");
        }

        MoveEnemy(stcWorld);
        Update(stcWorld);

        if(stcWorld.GameStatus != RUNNING)
        {
            blnCont = false;
        }

    }while(blnCont);

    DeallocateMem(stcWorld.arrWorld, stcWorld.intRows);

    return SUCCESS;
}
