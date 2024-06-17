#include "UJImage.h"
#include <sstream>
#include <fstream>
#include <iostream>


UJImage::UJImage() : UJImage(DEF_ROWS, DEF_COLS, {0,0,0})
{
}

UJImage::UJImage(int intRows, int intCols, UJPixel recPixel)
{
    init(intRows, intCols, recPixel);
}

UJImage::UJImage(const UJImage& objOriginal)
{
    *this = objOriginal;
}

UJImage::~UJImage()
{
    dealloc();
}

void UJImage::draw()
{

}

int UJImage::getRow() const
{
    return _rows;
}

int UJImage::getCol() const
{
    return _cols;
}

const UJImage& UJImage::operator=(const UJImage& objRHS)
{
    if(this != &objRHS)
    {
        dealloc();

        _pixel = new UJPixel*[objRHS._rows];
        for(int r = 0; r < _rows; r++)
        {
            _pixel[r] = new UJPixel[objRHS._cols];
            for(int c = 0; c < _cols; c++)
            {
                _pixel[r][c] = objRHS._pixel[r][c];
            }
        }
    }
    return *this;
}

ostream& operator<<(ostream& osLHS, const UJImage& objRHS)
{
    for(int r = 0; r < objRHS._rows; r++)
    {
        for(int c = 0; c < objRHS._cols; c++)
        {
            osLHS << objRHS._pixel[r][c].Red << ' '
                  << objRHS._pixel[r][c].Green << ' '
                  << objRHS._pixel[r][c].Blue << ' ';
        }
        osLHS << endl;
    }
    return osLHS;
}


UJImage UJImage::operator++()
{
    for(int r = 0; r < _rows; r++)
    {
        for(int c = 0; c < _cols; c++)
        {
            _pixel[r][c].Red++;
            _pixel[r][c].Green++;
            _pixel[r][c].Blue++;
        }
    }
    return *this;
}



string UJImage::toPPM() const
{
    stringstream ssPPM;
    ssPPM << "P3" << endl;
    ssPPM << _cols << ' ' << _rows << endl;
    ssPPM << 255 << endl;
    for(int r = 0; r < _rows; r++)
    {
        for(int c = 0 ; c < _cols ; c++)
        {
            ssPPM << _pixel[r][c].Red << ' '
                  << _pixel[r][c].Green << ' '
                  << _pixel[r][c].Blue << ' ' ;
        }
        ssPPM << endl;
    }
    return ssPPM.str();
}

void UJImage::loadtxt(std::string FileName)
{
    ifstream fReader(FileName);
    if(fReader.fail()){
        cerr << "COULD NOT OPEN FILE" << endl;
        exit(-1);
    }
    int intRows = 0;
    int intCols = 0;
    int intSeed[5];
    for(int i = 0; i <= 5; i++)
    {
        intSeed[i] = 0;
    }
    UJPixel recPixels = {0,0,0};

    fReader >> intRows;
    fReader >> intCols;
    for(int i = 0; i <= 5; i++)
    {
        fReader >> intSeed[i];
    }

    dealloc();
    init(intRows, intCols, recPixels);

    UJPixel Pixels;
    int Row;
    int Col;
    int Seed[5];

    while(fReader >> Row >> Col >> Seed[1] >> Seed[2] >> Seed[3] >> Seed[4] >> Seed[5])
    {
        UJPixel recPixel;
        recPixel.intRow = Row;
        recPixel.intCol = Col;
        for(int i = 0; i <= 5; i++)
        {
            recPixel.intSeed[i] = Seed[i];
        }
        _pixel[Row][Col] = recPixel;
    }
    fReader.close();
}


void UJImage::savetxt(std::string FileName)
{
    fstream fWriter(FileName, ios::binary | ios::out);
    if(fWriter.fail())
    {
        cerr << "COULD NOT OPEN FILE" << endl;
        exit(-1);
    }

    for(int r = 0; r < _rows; r++)
    {
        for(int c = 0; c < _cols; c++)
        {
           fWriter.write(reinterpret_cast<char*>(&_pixel[r][c]), sizeof(UJPixel));
        }
    }
    fWriter.close();
}


void UJImage::enforceRange(int intVal, int intMin, int intMax)
{
    if(intVal > intMax || intVal < intMin)
    {
        cerr << "Value out of bound";
        exit(-1);
    }
}


void UJImage::init(int intRows, int intCols, UJPixel recPixel)
{
    _rows = intRows;
    _cols = intCols;
    _pixel = new UJPixel*[intRows];
    for(int r = 0; r < intRows; r++)
    {
        _pixel[r] = new UJPixel[intCols];
        for(int c = 0; c < intCols; c++)
        {
            _pixel[r][c] = recPixel;
        }
    }

    //this->_intersector = new DisjuntionIntersector(recCircleParams, recCircle2Params);
    //this->_intersector = new ConjunctionIntersctor(recCircleParams, recCircle2Params);

}


void UJImage::dealloc()
{
    for(int r = 0; r < _rows; r++)
    {
        delete[] _pixel[r];
    }
    delete[] _pixel;
}
