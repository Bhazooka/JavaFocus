#include "BattleGroundAnalyser.h"

#include "VotingGrid.h"

#include <cmath>
#include <iostream>
#include <sstream>

using namespace std;

string BattleGroundAnalyser::analyse(VotingGrid& objGrid)
{
    stringstream ssReturn;
    for(int r = 0; r < objGrid.getRows(); r++)
    {
        for(int c = 0; c < objGrid.getCols(); c++)
        {
            double dblDifference = objGrid(r, c).dblUAPSupport - objGrid(r, c).dblAPUSupport;


            if(dblDifference < 5)
            {
                ssReturn << "! ";
            }
            else if(dblDifference > 5 && dblDifference <= 10)
            {
                ssReturn << "* ";
            }
            else if(dblDifference > 10 && dblDifference <= 20)
            {
                ssReturn << ". ";
            }
            ssReturn << endl;
        }

    }

    return ssReturn.str();

}
