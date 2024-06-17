#ifndef BATTLEGROUNDANALYSER_H
#define BATTLEGROUNDANALYSER_H

#include "VotingAnalyser.h"

class BattleGroundAnalyser : public VotingAnalyser
{
public:
    virtual std::string analyse(VotingGrid& objGrid);
};


#endif //BATTLEGROUNDANALYSER_H
