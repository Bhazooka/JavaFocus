#ifndef OPERATORS_H_INCLUDED
#define OPERATORS_H_INCLUDED


struct VotingDistrict
{

};

class VotingGrid
{

};





///Operator =
const VotingGrid& operator=(const VotingGrid& objRHS);

const VotingGrid& VotingGrid::operator=(const VotingGrid& objRHS){
    if(this != &objRHS){
        dealloc();
        alloc(objRHS._rows, objRHS._cols);
        clone(objRHS);
    }
    return *this;
}






///Operator<<  outstream
friend std::ostream& operator<<(std::ostream& osLHS, const VotingGrid& objRHS);

ostream& operator<<(ostream& osLHS, const VotingGrid& objRHS){
    for(int r = 0; r < objRHS._rows; r++){
        for(int c = 0; c < objRHS._cols; c++){
            osLHS << objRHS._securityZones[r][c].intID << ' ';
        }
        osLHS << endl;
    }
    return osLHS;
}






///Operator>> insertion
friend std::istream& operator>>(std::istream& isLHS, VotingGrid& objRHS);

istream& operator>>(istream& isLHS, VotingGrid& objRHS){
   int intRows, intCols;
   isLHS >> intRows >> intCols;
   objRHS.dealloc();
   objRHS.alloc(intRows, intCols);

   for(int r = 0; r < intRows; r++)
   {
       for(int c = 0; c < intCols; c++)
        {
           //Stuff in the struct
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







///Operator()
VotingDistrict& operator()(int intRow, int intCol);

VotingDistrict& VotingGrid::operator()(int intRow, int intCol)
{
    enforceRange(intRow, 0, _rows - 1);
    enforceRange(intCol, 0, _cols - 1);
    return _grid[intRow][intCol];
}

//We used this version to work with pixels
void operator()(int intRow, int intCol, Pixel defaultPixel);

void UJImage::operator()(int intRow, int intCol, Pixel defaultPixel)
{
    enforceRange(intRow, 0, _rows);
    enforceRange(intCol, 0, _cols);
    _pixels[intRow][intCol] = defaultPixel;
}





///Operator[]
std::string operator[](int intIndex)const;

std::string VotingGrid::operator[](int intIndex)const
{
    enforceRange(intRows, 0, _rows-1);
    enforceRange(intCols, 0, _cols-1);

    return _cities[intIndex]._name;
}





///Operator==
bool operator==(const VotingGrid& objRHS);

bool VotingGrid::operator==(const VotingGrid& objRHS)
{
    if(_size != objRHS._size)return false;
    for(int r=0; r<_size; r++)
    {
        for(int c=0; c<_size; c++)
        {
            if(_routes[r][c] != objRHS._routes[r][c])return false;
        }
    }
    for(int i=0; i<_size; i++)
    {
        if(_cities[i]._name != objRHS._cities[i]._name || _cities[i]._risk != objRHS._cities[i]._risk)return false;
    }

    return true;
}





///Operator++. idk if this works, took it from my old code
VotingGrid operator++(int);

VotingGrid VotingGrid::operator++(int)
{
    VotingGrid objOld;
    for(int r = 0; r < _rows; r++)
    {
        for(int c = 0; c < _cols; c++)
        {
            if(_data[r][c] < 10)
                _data[r][c]++;
        }
    }
    return objOld;

}




///Operator!
void operator!();

void VotingGrid::operator!()
{
    for(int r = 0; r < _rows; r++)
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












///Load and save

///Load 1
void loadInfoFromTXT(std::string strFileName);

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



///Save 1
void saveInfoToDAT(std::string strFileName);

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






#endif // OPERATORS_H_INCLUDED
