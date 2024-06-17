#ifndef PREDICTEDOUTCOMEANALYSER_H
#define PREDICTEDOUTCOMEANALYSER_H

#include "VotingAnalyser.h"

class PredictedOutcomeAnalyser : public VotingAnalyser
{
public:
    virtual std::string analyse(VotingGrid& objGrid);
};


#endif //PREDICTEDOUTCOMEANALYSER_H
