#ifndef POTENTIALFRAUDANALYSER_H
#define POTENTIALFEAUDANALYSER_H

#include "VotingAnalyser.h"

class PotentialFraudAnalyser : public VotingAnalyser
{
public:
    virtual std::string analyse(VotingGrid& objGrid);
};


#endif //POTENTIALFRAUDANALYSER_H
