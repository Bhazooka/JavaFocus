#include "VotingGrid.h"
#include "BattleGroundAnalyser.h"
#include "PredictedOutcomeAnalyser.h"
#include "PotentialFraudAnalyser.h"


#include <cassert>
#include <cmath>
#include <cstdlib>
#include <fstream>
#include <iostream>
#include <sstream>

using namespace std;


VotingGrid::VotingGrid() : VotingGrid(DEFAULT_ROWS,DEFAULT_COLS)
{

}

VotingGrid::VotingGrid(int intRows, int intCols)
{
    alloc(intRows, intCols);
}

VotingGrid::VotingGrid(const VotingGrid& objOriginal)
{
    alloc(objOriginal._rows, objOriginal._cols);
    clone(objOriginal);
}

VotingGrid::~VotingGrid()
{
    dealloc();
}



void VotingGrid::loadTXT(std::string strFileName)
{
    ifstream fReader(strFileName);
    if(fReader.fail())
    {
        cerr << "Couldnt open file" << endl;
        exit(-3);
    }

    int intRows = 0;
    int intCols = 0;
    fReader >> intRows;
    fReader >> intCols;

    dealloc();
    alloc(intRows, intCols);

    double UAPSupport, APUSupport;
    int intPopulation;
    int intRow;
    int intCol;

    while(fReader >> UAPSupport >> APUSupport >> intPopulation >> intRow >> intCol)
    {
        VotingDistrict recDistrict;
        recDistrict.dblUAPSupport = UAPSupport;
        recDistrict.dblAPUSupport = APUSupport;
        recDistrict.dblUndecided = abs(100 - (UAPSupport + APUSupport));
        recDistrict.intRow = intRow;
        recDistrict.intCol = intCol;
        _grid[intRow][intCol] = recDistrict;

    }
    fReader.close();


}



void VotingGrid::saveTXT(std::string strFileName)
{
    fstream fWriter(strFileName, ios::binary | ios::out);
    if(fWriter.fail())
    {
        cerr << "COULD NOT OPEN FILE" << endl;
        exit(-1);
    }
    for(int r = 0; r < _rows; r++)
    {
        for(int c = 0; c < _cols; c++)
        {
            fWriter.write(reinterpret_cast<char*>(&_grid[r][c]), sizeof(VotingDistrict));
        }
    }
    fWriter.close();
}


//need to make the class for voting analyser
string VotingGrid::analyse()
{
    stringstream ssReturn;
    for(VotingAnalyser* a : _analysers)
    {
        ssReturn << a->analyse(*this) << endl;
    }
    return ssReturn.str();
}




int VotingGrid::getRows() const
{
    return _rows;
}



int VotingGrid::getCols() const
{
    return _cols;
}

const VotingGrid& VotingGrid::operator=(const VotingGrid& objRHS)
{
    if(this != &objRHS)
    {
        dealloc();
        alloc(objRHS._rows, objRHS._cols);
        clone(objRHS);
    }

    return *this;
}

void VotingGrid::operator!()
{
    for(int r = 0; _rows; r++)
    {
        for(int c = 0; c < _cols; c++)
        {
            _grid[r][c].intPopulation = 0;
            _grid[r][c].dblUAPSupport = 0;
            _grid[r][c].dblAPUSupport = 0;
            _grid[r][c].dblUndecided = 100;
        }
    }

}

VotingDistrict& VotingGrid::operator()(int intRow, int intCol)
{
    enforceRange(intRow, 0, _rows - 1);
    enforceRange(intCol, 0, _cols - 1);
    return _grid[intRow][intCol];
}


void VotingGrid::alloc(int intRows, int intCols)
{
    _rows = intRows;
    _cols = intCols;

    _analysers.push_back(new BattleGroundAnalyser);
    _analysers.push_back(new PotentialFraudAnalyser);
    _analysers.push_back(new PredictedOutcomeAnalyser);

    _grid = new VotingDistrict*[_rows];
    for(int r = 0; r < _rows; r++)
    {
        _grid[r] = new VotingDistrict[_cols];
    }

}

void VotingGrid::dealloc()
{
    for(int r = 0; r < _rows; r++)
    {
        delete[] _grid[r];
    }
    delete[] _grid;

    for(VotingAnalyser* a : _analysers)
    {
        delete a;
    }
    _analysers.clear();
}

void VotingGrid::clone(const VotingGrid& objOriginal)
{
    assert(objOriginal._rows);
    assert(objOriginal._cols);
    for(int r = 0; r < _rows; r++)
    {
        for(int c = 0; c < _cols; c++)
        {
            _grid[r][c] = objOriginal._grid[r][c];
        }
    }
}

void VotingGrid::enforceRange(int intArg, int intMin, int intMax)
{
    if(intArg > intMax || intArg < intMin)
    {
        cerr << "The value is out of bound" << endl;
        exit(-1);
    }

}
