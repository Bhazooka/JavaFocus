#ifndef VOTINGANALYSER_H
#define VOTINGANALYSER_H

#include <string>


class VotingGrid;


class VotingAnalyser
{
public:
    virtual std::string analyse(VotingGrid& objGrid) = 0;
};





#endif //VOTINGANALYSER_H
