#ifndef VOTINGGRID_H
#define VOTINGGRID_H

#include <string>
#include <vector>

class VotingAnalyser;

#pragma pack(push)
#pragma pack(1)
struct VotingDistrict
{
    int intRow;
    int intCol;
    int intPopulation;
    double dblUAPSupport;
    double dblAPUSupport;
    double dblUndecided;

};
#pragma pack(pop)


class VotingGrid
{
public:
    VotingGrid();
    VotingGrid(int intRows, int intCols);
    VotingGrid(const VotingGrid& objOriginal);
    ~VotingGrid();

    void loadTXT(std::string strFileName);
    void saveTXT(std::string strFileName);

    const VotingGrid& operator=(const VotingGrid& objRHS);
    void operator!();
    VotingDistrict& operator()(int intRow, int intCol);

    std::string analyse();

    int getRows() const;
    int getCols() const;

    static const int DEFAULT_ROWS = 2;
    static const int DEFAULT_COLS = 2;

private:

    void alloc(int intRows, int intCols);
    void dealloc();
    void clone(const VotingGrid& objOriginal);
    void enforceRange(int intArg, int intMin, int intMax);
    int _rows;
    int _cols;

    VotingDistrict** _grid;
    std::vector<VotingAnalyser*>_analysers;


};


#endif // VOTINGGRID_H
