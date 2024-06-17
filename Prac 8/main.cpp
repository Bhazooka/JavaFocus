#include "libZork.h"
#include <iostream>


using namespace std;
using namespace ZorkSpace;

int main(int argc, char** argv)
{
    srand(time(nullptr));

    for (int i = 0; i < argc; i++)
    {
        cout << "Argument " << i << ":" << argv[i] << endl;
    }

    if (argc != 4)
    {
        cerr << "There should be 3 arguments" << endl;
        cerr << "Rows || Cols || Batteries" << endl;
    }

    //Convert arguments to integers
    int intRows = ConvertToInt(argv[1]); //20 Rows
    int intCols = ConvertToInt(argv[2]); //15 Cols
    int intBattery = ConvertToInt (argv[3]); //10 Baterries


    char Choice = '\0';
    bool blnContinue = true;
    D2Arr arrWorld = InitWorld(intRows, intCols);
    PopulateArray(arrWorld, intRows, intCols, BATTERY, intBattery);
    do
    {
        PrintWorld(arrWorld, intRows, intCols, intBattery);
        cin >> Choice;
        Choice = tolower(Choice);
        switch(Choice)
        {
        case 'w':
        case 's':
        case 'a':
        case 'd':
            if(intBattery > 0)
            {
                Movement(arrWorld, intRows, intCols, intBattery, Choice);
            }
            break;
        default:
            cerr << "Incorrect output" << endl;
            system("pause");
        }

    }while(blnContinue);



    PrintWorld(arrWorld, intRows, intCols, intBattery);

    DeallocMem(arrWorld, intRows);





    return 0;
}
