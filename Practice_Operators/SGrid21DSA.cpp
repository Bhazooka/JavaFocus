//main.cpp
//***********************************************************************************************************************
#include "SecurityGrid.h"

#include <iostream>

using namespace std;

int main(){
    SecurityGrid objGrid, objGridCopy;
    objGrid.loadInfoFromTXT("input.txt");
    objGridCopy = objGrid;
    objGrid.audit();
    objGridCopy.audit();

    cout << "Security Grid IDs: " << endl;
    cout << objGrid << endl;

    cout << "Security Grid (Copy) IDs: " << endl;
    cout << objGridCopy << endl;
    return SUCCESS;
}
//***********************************************************************************************************************


//SecurityGrid.h
//***********************************************************************************************************************
#ifndef SECURITYGRID_H
#define SECURITYGRID_H

#include <iostream>
#include <string>
#include <vector>

#pragma pack(push)
#pragma pack(1)
struct SecurityZone{
    int intID;
    int intGroup;
    char arrName[16];
    char chSensorType;
    int intNumActivations;
    bool blnBypassed;
};
#pragma pack(pop)

enum ERROR_CODE{
    ERROR_RANGE = -2,
    ERROR_FILE,
    SUCCESS
};

class Auditor;

class SecurityGrid{
public:
    SecurityGrid();
    SecurityGrid(int intRows, int intCols);
    SecurityGrid(const SecurityGrid& objOriginal);
    ~SecurityGrid();
    const SecurityGrid& operator=(const SecurityGrid& objRHS);
    friend std::ostream& operator<<(std::ostream& osLHS, const SecurityGrid& objRHS);
    friend std::istream& operator>>(std::istream& isLHS, SecurityGrid& objRHS);

    void audit() const;

    void loadInfoFromTXT(std::string strFileName);
    int countRecordsFromBIN(std::string strFileName);

    int getRows() const;
    int getCols() const;
    SecurityZone getSecurityZone(int intRow, int intCol) const;

    static const int DEFAULT_ROWS = 2;
    static const int DEFAULT_COLS = 2;
private:
    void alloc(int intRows, int intCols);
    void dealloc();
    void clone(const SecurityGrid& objOriginal);
    void enforceRange(int intArg, int intMin, int intMax) const;
    std::vector<Auditor*> _auditors;
    SecurityZone** _securityZones;
    int _rows;
    int _cols;
};

#endif // SECURITYGRID_H
//***********************************************************************************************************************




//SecurityGrid.cpp
//***********************************************************************************************************************
#include "SecurityGrid.h"
#include "GroupAuditor.h"
#include "SensorAuditor.h"
#include "GridAuditor.h"

#include <cassert>
#include <cstdlib>
#include <fstream>

using namespace std;

SecurityGrid::SecurityGrid() : SecurityGrid(DEFAULT_ROWS, DEFAULT_COLS)
{}

SecurityGrid::SecurityGrid(int intRows, int intCols){
    alloc(intRows, intCols);
}

SecurityGrid::SecurityGrid(const SecurityGrid& objOriginal){
    alloc(objOriginal._rows, objOriginal._cols);
    clone(objOriginal);
}

SecurityGrid::~SecurityGrid(){
    dealloc();
}

const SecurityGrid& SecurityGrid::operator=(const SecurityGrid& objRHS){
    if(this != &objRHS){
        dealloc();
        cout << "DEBUG: " <<  objRHS._rows << endl;
        cout << "DEBUG: " <<  objRHS._cols << endl;
        alloc(objRHS._rows, objRHS._cols);
        clone(objRHS);
    }
    return *this;
}

ostream& operator<<(ostream& osLHS, const SecurityGrid& objRHS){
    for(int r = 0; r < objRHS._rows; r++){
        for(int c = 0; c < objRHS._cols; c++){
            osLHS << objRHS._securityZones[r][c].intID << ' ';
        }
        osLHS << endl;
    }
    return osLHS;
}

istream& operator>>(istream& isLHS, SecurityGrid& objRHS){
   int intRows, intCols;
   isLHS >> intRows >> intCols;
   objRHS.dealloc();
   objRHS.alloc(intRows, intCols);
   for(int r = 0; r < intRows; r++){
       for(int c = 0; c < intCols; c++){
           SecurityZone recTemp;
           isLHS >> recTemp.intID
                 >> recTemp.intGroup
                 >> recTemp.arrName
                 >> recTemp.chSensorType;
            recTemp.intNumActivations = 0;
            recTemp.blnBypassed = false;
            objRHS._securityZones[r][c] = recTemp;
       }
   }
   return isLHS;
}

void SecurityGrid::audit() const{
    for(Auditor* auditor : _auditors){
        auditor->audit(*this);
    }
}

void SecurityGrid::loadInfoFromTXT(std::string strFileName){
    ifstream fReader(strFileName);
    if(fReader.fail()){
        cerr << "Could not open file " << strFileName << endl;
        exit(ERROR_FILE);
    }
    fReader >> *this;
    fReader.close();
}

int SecurityGrid::countRecordsFromBIN(string strFileName){
    fstream fReader(strFileName, ios::binary | ios::in);
     if(fReader.fail()){
        cerr << "Could not open file " << strFileName << endl;
        exit(ERROR_FILE);
    }
    fReader.seekg(0, ios::end); // Move to the end of the file.
    int intFileSize = fReader.tellg();
    int intNumElements = intFileSize / sizeof(SecurityZone);
    fReader.close();
    return intNumElements;
}

int SecurityGrid::getRows() const{
    return _rows;
}

int SecurityGrid::getCols() const{
    return _cols;
}

SecurityZone SecurityGrid::getSecurityZone(int intRow, int intCol) const{
    enforceRange(intRow, 0, _rows - 1);
    enforceRange(intCol, 0, _cols - 1);
    return _securityZones[intRow][intCol];
}

void SecurityGrid::alloc(int intRows, int intCols){
    _rows = intRows;
    _cols = intCols;
    _securityZones = new SecurityZone*[_rows];
    for(int r = 0; r < _rows; r++){
        _securityZones[r] = new SecurityZone[_cols];
    }
    _auditors.push_back(new GroupAuditor);
    _auditors.push_back(new SensorAuditor);
    _auditors.push_back(new GridAuditor);
}

void SecurityGrid::dealloc(){
    for(int r = 0; r < _rows; r++){
      delete [] _securityZones[r];
    }
    delete _securityZones;
    for(Auditor* auditor : _auditors)
        delete auditor;
    _auditors.clear();
}

void SecurityGrid::clone(const SecurityGrid& objOriginal){
    assert(_rows == objOriginal._rows);
    assert(_cols == objOriginal._cols);
    for(int r = 0; r < _rows; r++){
        for(int c = 0; c < _cols; c++){
            _securityZones[r][c] = objOriginal._securityZones[r][c];
        }
    }
}

void SecurityGrid::enforceRange(int intArg, int intMin, int intMax) const{
    if(intArg < intMin || intArg > intMax){
        cerr << "Range Error"<< endl;
        exit(ERROR_RANGE);
    }
}
//***********************************************************************************************************************


//Auditor.h
//***********************************************************************************************************************
#ifndef AUDITOR_H
#define AUDITOR_H

class SecurityGrid;

class Auditor{
public:
    virtual void audit(const SecurityGrid& objGrid) const = 0;
};
#endif // AUDITOR_H
//***********************************************************************************************************************



//SensorAuditor.h
//***********************************************************************************************************************
#ifndef SENSORAUDITOR_H
#define SENSORAUDITOR_H

#include "Auditor.h"

class SensorAuditor : public Auditor{
public:
    virtual void audit(const SecurityGrid& objGrid) const;
};
#endif // SENSORAUDITOR_H
//***********************************************************************************************************************




//SencsorAuditor.cpp
//***********************************************************************************************************************
#include "SensorAuditor.h"
#include "SecurityGrid.h"

#include <iostream>
#include <vector>

using namespace std;

void SensorAuditor::audit(const SecurityGrid& objGrid) const{
    vector<SecurityZone> vecSensorTypeI;
    vector<SecurityZone> vecSensorTypeA;
    vector<SecurityZone> vecSensorTypeM;

    for(int r = 0; r < objGrid.getRows(); r++){
        for(int c = 0; c < objGrid.getCols(); c++){
            SecurityZone recTemp = objGrid.getSecurityZone(r, c);
            if(recTemp.chSensorType == 'I'){
                vecSensorTypeI.push_back(recTemp);
            }else if(recTemp.chSensorType == 'A'){
                vecSensorTypeA.push_back(recTemp);
            }else{
                vecSensorTypeM.push_back(recTemp);
            }
        }
    }

    cout << "----------- Sensor Type Auditor -----------" << endl;
    cout << "SensorType I NotBypassed: " << endl;
    for(SecurityZone recTemp : vecSensorTypeI){
        if(!recTemp.blnBypassed){
            cout << "ZoneID: " << recTemp.intID
                 << endl;
        }
    }

    cout << "SensorType A Not Bypassed: " << endl;
    for(SecurityZone recTemp : vecSensorTypeA){
        if(!recTemp.blnBypassed){
            cout << "ZoneID: " << recTemp.intID
                 << endl;
        }
    }

    cout << "SensorType M Not Bypassed: " << endl;
    for(SecurityZone recTemp : vecSensorTypeM){
        if(!recTemp.blnBypassed){
            cout << "ZoneID: " << recTemp.intID
                 << endl;
        }
    }
}
//***********************************************************************************************************************




//GridAuditor.h
//***********************************************************************************************************************
#ifndef GRIDAUDITOR_H
#define GRIDAUDITOR_H

#include "Auditor.h"

class GridAuditor : public Auditor{
 public:
    virtual void audit(const SecurityGrid& objGrid) const;
};
#endif // GRIDAUDITOR_H
//***********************************************************************************************************************




//GridAuditor.cpp
//***********************************************************************************************************************
#include "GridAuditor.h"
#include "SecurityGrid.h"

#include <iostream>
#include <vector>

using namespace std;

void GridAuditor::audit(const SecurityGrid& objGrid) const{
    cout << "----------- Grid Auditor -----------" << endl;
    for(int r = 0; r < objGrid.getRows(); r++){
        for(int c = 0; c < objGrid.getCols(); c++){
            SecurityZone recTemp = objGrid.getSecurityZone(r, c);
            if(!recTemp.blnBypassed){
                cout << '*' << ' ';
            }else{
                cout << '.' << ' ';
            }
        }
        cout << endl;
    }
}
//***********************************************************************************************************************



//GroupAuditor.h
//***********************************************************************************************************************
#ifndef GROUPAUDITOR_H
#define GROUPAUDITOR_H

#include "Auditor.h"

class GroupAuditor : public Auditor{
   virtual void audit(const SecurityGrid& objGrid) const;
};

#endif // GROUPAUDITOR_H
//***********************************************************************************************************************


//GeoupAuditor.cpp
//***********************************************************************************************************************
#include "GroupAuditor.h"
#include "SecurityGrid.h"

#include <vector>

using namespace std;

void GroupAuditor::audit(const SecurityGrid& objGrid) const{
    vector<SecurityZone> vecSensorTypeI;
    vector<SecurityZone> vecSensorTypeA;
    vector<SecurityZone> vecSensorTypeM;

    // Start with Group 0.
    for(int r = 0; r < objGrid.getRows(); r++){
        for(int c = 0; c < objGrid.getCols(); c++){
            SecurityZone recTemp = objGrid.getSecurityZone(r, c);
            if(recTemp.chSensorType == 'I'){
                vecSensorTypeI.push_back(recTemp);
            }else if(recTemp.chSensorType == 'A'){
                vecSensorTypeA.push_back(recTemp);
            }else{
                vecSensorTypeM.push_back(recTemp);
            }
        }
    }

    cout << "----------- Group Auditor -----------" << endl;
    cout << "Group 0:" << endl;
    cout << "SensorType: I" << endl;
    for(SecurityZone recTemp : vecSensorTypeI){
        if(recTemp.intGroup == 0){
            cout << "ZoneID: " << recTemp.intID
                 << endl << "Activations: "
                 << recTemp.intNumActivations << endl;
        }
    }

    cout << "SensorType: A" << endl;
    for(SecurityZone recTemp : vecSensorTypeA){
        if(recTemp.intGroup == 0){
            cout << "ZoneID: " << recTemp.intID
                 << endl << "Activations: "
                 << recTemp.intNumActivations << endl;
        }
    }

    cout << "SensorType: M" << endl;
    for(SecurityZone recTemp : vecSensorTypeM){
        if(recTemp.intGroup == 0){
            cout << "ZoneID: " << recTemp.intID
                 << endl << "Activations: "
                 << recTemp.intNumActivations << endl;
        }
    }

    cout << "Group 1:" << endl;

    cout << "SensorType: I" << endl;
    for(SecurityZone recTemp : vecSensorTypeI){
        if(recTemp.intGroup == 1){
            cout << "ZoneID: " << recTemp.intID
                 << endl << "Activations: "
                 << recTemp.intNumActivations << endl;
        }
    }

    cout << "SensorType: A" << endl;
    for(SecurityZone recTemp : vecSensorTypeA){
        if(recTemp.intGroup == 1){
            cout << "ZoneID: " << recTemp.intID
                 << endl << "Activations: "
                 << recTemp.intNumActivations << endl;
        }
    }

    cout << "SensorType: M" << endl;
    for(SecurityZone recTemp : vecSensorTypeM){
        if(recTemp.intGroup == 1){
            cout << "ZoneID: " << recTemp.intID
                 << endl << "Activations: "
                 << recTemp.intNumActivations << endl;
        }
    }
}
//***********************************************************************************************************************

