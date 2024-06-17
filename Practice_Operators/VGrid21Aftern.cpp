//main.cpp
//***********************************************************************************************************************
#include "VotingGrid.h"

#include <iostream>
using namespace std;

int main(){
    VotingGrid objGrid;
    objGrid.loadInfoFromTXT("input.txt");
    cout << objGrid.analyse() << endl;
    return SUCCESS;
}
//***********************************************************************************************************************


//VotingGrid.h
//***********************************************************************************************************************
#ifndef VOTINGGRID_H
#define VOTINGGRID_H

#include <string>
#include <vector>

enum ERROR_CODE{
    ERROR_RANGE = -1,
    SUCCESS
};

#pragma pack(push)
#pragma pack(1)
struct VotingDistrict{
    int intRow;
    int intCol;
    int intPopulation;
    double dblUAPSupport;
    double dblAPUSupport;
    double dblUndecided;
};
#pragma pack(pop)

class VotingAnalyser;

class VotingGrid{
public:
    VotingGrid();
    VotingGrid(int intRows, int intCols);
    VotingGrid(const VotingGrid& objOriginal);
    ~VotingGrid();

    const VotingGrid& operator=(const VotingGrid& objRHS);
    void operator!();
    VotingDistrict& operator()(int intRow, int intCol);

    void loadInfoFromTXT(std::string strFileName);
    void saveInfoToDAT(std::string strFileName);

    std::string analyse();

    int getRows() const;
    int getCols() const;

    static const int DEFAULT_ROWS = 2;
    static const int DEFAULT_COLS = 2;
private:
    void alloc(int intRows, int intCols);
    void clone(const VotingGrid& objOriginal);
    void dealloc();
    void enforceRange(int intArg, int intMin, int intMax) const;
    int _rows;
    int _cols;
    VotingDistrict** _grid;
    std::vector<VotingAnalyser*> _analysers;
};

#endif // VOTINGGRID_H
//***********************************************************************************************************************




//VotingGrid.cpp
//***********************************************************************************************************************
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

VotingGrid::VotingGrid() : VotingGrid(DEFAULT_ROWS, DEFAULT_COLS){
}

VotingGrid::VotingGrid(int intRows, int intCols){
    alloc(intRows, intCols);
}

VotingGrid::VotingGrid(const VotingGrid& objOriginal){
    alloc(objOriginal._rows, objOriginal._cols);
    clone(objOriginal);
}

VotingGrid::~VotingGrid(){
    dealloc();
}

const VotingGrid& VotingGrid::operator=(const VotingGrid& objRHS){
    if(this != &objRHS){
        dealloc();
        alloc(objRHS._rows, objRHS._cols);
        clone(objRHS);
    }
    return *this;
}


void VotingGrid::operator!(){
    for(int r = 0; r < _rows; r++){
        for(int c = 0; c < _cols; c++){
            _grid[r][c].intPopulation = 0;
            _grid[r][c].dblUAPSupport = 0;
            _grid[r][c].dblAPUSupport = 0;
            _grid[r][c].dblUndecided = 100;
        }
    }
}

VotingDistrict& VotingGrid::operator()(int intRow, int intCol){
    enforceRange(intRow, 0, _rows - 1);
    enforceRange(intCol, 0, _cols - 1);
    return _grid[intRow][intCol];
}

void VotingGrid::loadInfoFromTXT(std::string strFileName){
    ifstream fReader(strFileName);
    if(fReader.fail()){
        cerr << "COULD NOT OPEN FILE" << endl;
        exit(-1);
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
    while(fReader >> UAPSupport >> APUSupport >> intPopulation >> intRow >> intCol){
        VotingDistrict recDistrict;
        recDistrict.dblUAPSupport = UAPSupport;
        recDistrict.dblAPUSupport = APUSupport;
        recDistrict.dblUndecided = abs(100 - (UAPSupport + APUSupport));
        recDistrict.intPopulation = intPopulation;
        recDistrict.intRow = intRow;
        recDistrict.intCol = intCol;
        _grid[intRow][intCol] = recDistrict;
    }
    fReader.close();
}

void VotingGrid::saveInfoToDAT(std::string strFileName){
    fstream fWriter(strFileName, ios::binary | ios::out);
     if(fWriter.fail()){
        cerr << "COULD NOT OPEN FILE" << endl;
        exit(-1);
    }
     for(int r = 0; r < _rows; r++){
        for(int c = 0; c < _cols; c++){
           fWriter.write(reinterpret_cast<char*>(&_grid[r][c]), sizeof(VotingDistrict));
        }
    }
    fWriter.close();
}

string VotingGrid::analyse(){
    stringstream ssReturn;
    for(VotingAnalyser* a : _analysers)
       ssReturn << a->analyse(*this) << endl;
     return ssReturn.str();
}

int VotingGrid::getRows() const{
    return _rows;
}

int VotingGrid::getCols() const{
    return _cols;
}

void VotingGrid::alloc(int intRows, int intCols){
    _rows = intRows;
    _cols = intCols;
    _analysers.push_back(new BattleGroundAnalyser);
    _analysers.push_back(new PotentialFraudAnalyser);
    _analysers.push_back(new PredictedOutcomeAnalyser);
    _grid = new VotingDistrict*[_rows];
    for(int r = 0; r < _rows; r++){
        _grid[r] = new VotingDistrict[_cols];
    }
}

void VotingGrid::clone(const VotingGrid& objOriginal){
    assert(objOriginal._rows == _rows);
    assert(objOriginal._cols == _cols);
    for(int r = 0; r < _rows; r++){
        for(int c = 0; c < _cols; c++){
            _grid[r][c] = objOriginal._grid[r][c];
        }
    }
}

void VotingGrid::dealloc(){
    for(int r = 0; r < _rows; r++)
        delete [] _grid[r];
    delete [] _grid;

    for(VotingAnalyser* a : _analysers)
        delete a;
    /*
     * Remove the pointers from the list because they
     * no longer useful (they are pointing to deallocated
     * memory addresses).
     */
    _analysers.clear();
}

void VotingGrid::enforceRange(int intArg, int intMin, int intMax) const{
    if(intArg < intMin || intArg > intMax){
        cerr << intArg << " must be in ["
             << intMin << ", " << intMax
             << "]" << endl;
        exit(ERROR_RANGE);
    }
}

//***********************************************************************************************************************


//VotingAnalyser.h
//***********************************************************************************************************************
#ifndef VOTINGANALYSER_H
#define VOTINGANALYSER_H

#include <string>

class VotingGrid;
class VotingAnalyser{
public:
    virtual std::string analyse(VotingGrid& objGrid) = 0;
};
#endif // VOTINGANALYSER_H
//***********************************************************************************************************************



//BattleGroundAnalyser.h
//***********************************************************************************************************************
#ifndef BATTLEGROUNDANALYSER_H
#define BATTLEGROUNDANALYSER_H

#include "VotingAnalyser.h"

class BattleGroundAnalyser : public VotingAnalyser{
public:
    virtual std::string analyse(VotingGrid& objGrid);
};
#endif // BATTLEGROUNDANALYSER_H
//***********************************************************************************************************************




//BattleGroundAnalyser.cpp
//***********************************************************************************************************************
#include "BattleGroundAnalyser.h"
#include "VotingGrid.h"

#include <cmath>
#include <iostream>
#include <sstream>

using namespace std;

string BattleGroundAnalyser::analyse(VotingGrid& objGrid){
    stringstream ssReturn;
    for(int r = 0; r < objGrid.getRows(); r++){
        for(int c = 0; c < objGrid.getCols(); c++){
            double dblDifference =
                    objGrid(r, c).dblUAPSupport - objGrid(r, c).dblAPUSupport;
            if(dblDifference < 5){
                ssReturn << "! ";
            }else if(dblDifference > 5 && dblDifference <= 10){
                ssReturn << "% ";
            }else if(dblDifference > 10 && dblDifference <= 20){
                ssReturn << "* ";
            }else{
                ssReturn << ". ";
            }
        }
        ssReturn << endl;
    }
    return ssReturn.str();
}
//***********************************************************************************************************************




//PotentialAnalyser.h
//***********************************************************************************************************************
#ifndef POTENTIALFRAUDANALYSER_H
#define POTENTIALFRAUDANALYSER_H

#include "VotingAnalyser.h"

class PotentialFraudAnalyser : public VotingAnalyser{
public:
    virtual std::string analyse(VotingGrid& objGrid);
};
#endif // POTENTIALFRAUDANALYSER_H
//***********************************************************************************************************************




//PotentialAnalyser.cpp
//***********************************************************************************************************************
#include "PotentialFraudAnalyser.h"
#include "VotingGrid.h"

#include <cmath>
#include <sstream>

using namespace std;

string PotentialFraudAnalyser::analyse(VotingGrid& objGrid){
    stringstream ssReturn;
    for(int r = 0; r < objGrid.getRows(); r++){
        for(int c = 0; c < objGrid.getCols(); c++){
            double dblTotalSupport = objGrid(r, c).dblUAPSupport
                                     + objGrid(r, c).dblAPUSupport
                                     + objGrid(r, c).dblUndecided;
            double dblDifference = abs(100.0 - dblTotalSupport);
            if(dblDifference > 1){
                ssReturn << "District ID: (" << objGrid(r, c).intRow
                     << ", "
                     << objGrid(r, c).intCol << ") Potential Fraud."
                     << endl;
            }
        }
    }
    return ssReturn.str();
}
//***********************************************************************************************************************



//PredictedOutcome.h
//***********************************************************************************************************************
#ifndef PREDICTEDOUTCOMEANALYSER_H
#define PREDICTEDOUTCOMEANALYSER_H

#include "VotingAnalyser.h"

class PredictedOutcomeAnalyser : public VotingAnalyser{
public:
    virtual std::string analyse(VotingGrid& objGrid);
};
#endif // PREDICTEDOUTCOMEANALYSER_H
//***********************************************************************************************************************


//PredictedOutcome.cpp
//***********************************************************************************************************************
#include "PredictedOutcomeAnalyser.h"
#include "VotingGrid.h"

#include <sstream>

using namespace std;

string PredictedOutcomeAnalyser::analyse(VotingGrid& objGrid){
    stringstream ssReturn;
    double dblTotalVoters = 0.0;
    for(int r = 0; r < objGrid.getRows(); r++){
        for(int c = 0; c < objGrid.getCols(); c++){
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
//***********************************************************************************************************************

