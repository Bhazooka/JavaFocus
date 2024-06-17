#include <iostream>
#include "VotingGrid.h"

using namespace std;

int main()
{
    VotingGrid objGrid;
    objGrid.loadTXT("input.txt");
    cout << objGrid.analyse() << endl;

    return 0;
}
