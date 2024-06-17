#include "VotingGrid.h"
#include "PotentialFraudAnalyser.h"

#include <cmath>
#include <sstream>

using namespace std;


string PotentialFraudAnalyser::analyse(VotingGrid& objGrid)
{
    stringstream ssReturn;
    for(int r = 0; r < objGrid.getRows(); r++)
    {
        for(int c = 0; c < objGrid.getCols(); c++)
        {
            double dblTotalSupport = objGrid(r, c).dblUAPSupport
                                   + objGrid(r, c).dblAPUSupport
                                   + objGrid(r, c).dblUndecided;
            double dblDifference = abs(100.0 - dblTotalSupport);
            if(dblDifference > 1)
            {
                ssReturn << "District ID: (" << objGrid(r, c).intRow
                         << ", "
                         << objGrid(r, c).intCol << ") Potential Fraud."
                         << endl;
            }
        }
    }


    return ssReturn.str();
}
