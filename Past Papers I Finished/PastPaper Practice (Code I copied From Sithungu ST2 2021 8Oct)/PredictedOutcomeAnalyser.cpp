#include "PredictedOutcomeAnalyser.h"
#include "VotingGrid.h"

#include <sstream>

using namespace std;


string PredictedOutcomeAnalyser::analyse(VotingGrid& objGrid)
{
    stringstream ssReturn;
    double dblTotalVoters = 0.0;
    for(int r = 0; r < objGrid.getRows(); r++)
    {
        for(int c = 0; c < objGrid.getCols(); c++)
        {
            VotingDistrict recTemp = objGrid(r, c);
            dblTotalVoters += recTemp.dblUAPSupport + recTemp.dblAPUSupport;
            if(recTemp.dblUAPSupport > recTemp.dblAPUSupport && recTemp.dblUAPSupport > recTemp.dblUndecided)
                ssReturn << "U ";
            else if(recTemp.dblAPUSupport > recTemp.dblUAPSupport && recTemp.dblAPUSupport > recTemp.dblUndecided)
                ssReturn << "A ";
            else if(recTemp.dblUndecided > recTemp.dblUAPSupport && recTemp.dblUndecided > recTemp.dblAPUSupport)
                ssReturn << "? ";
        }
        ssReturn << endl;
    }
    ssReturn << "Total voters: " << dblTotalVoters << endl;
    return ssReturn.str();
}
