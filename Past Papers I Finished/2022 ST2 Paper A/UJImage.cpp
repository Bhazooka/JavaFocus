#include "UJImage.h"



UJImage::UJImage() : UJImage(DEF_ROWS,DEF_COLS,DEF_PIX) {}

UJImage::UJImage(int intRows, int intCols, UJPixel recPixels)
{
    init(intRows, intCols, recPixels);
}

UJImage::UJImage(const UJImage& objOriginal)
{
    *this = objOriginal;
}

UJImage::~UJImage()
{
    dealloc();
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


void UJImage::operator()(int intRow, int intCol, UJPixel defaultPixel)
{
    enforceRange(intRow, 0, _rows);
    enforceRange(intCol, 0, _cols);
    _pixel[intRow][intCol] = defaultPixel;
}

/*
string UJImage::operator[](int intIndex)const
{
    enforceRange(intIndex, 0, _index-1);
    return _pixel[intIndex]._
}
*/

ostream& operator<<(ostream& osLHS, const UJImage& objRHS)
{
    for(int r = 0; r < objRHS._rows; r++)
        {
        for(int c = 0; c < objRHS._cols; c++)
        {
            osLHS << objRHS._pixel[r][c].int_Intensity << ' ';
        }
        osLHS << endl;
    }
    return osLHS;
}


string UJImage::toPGM() const
{
    stringstream ssPGM;
    ssPGM << "P2" << endl;
    ssPGM << _rows << ' ' << _cols << endl;
    ssPGM << 255 << endl;
    for(int r = 0; r < _rows; r++)
    {
        for(int c = 0 ; c < _cols ; c++)
        {
            ssPGM << _pixel[r][c].int_Intensity << ' ';
        }
        ssPGM << endl;
    }
    return ssPGM.str();
}



void UJImage::loadFromTxt(std::string FileName)
{

}

void UJImage::saveInfoToDAT(std::string FileName)
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

int UJImage::getRows() const
{
    return _rows;
}

int UJImage::getCols() const
{
    return _cols;
}

void UJImage::init(int intRows, int intCols, UJPixel recPixel)
{
    _pixel = new UJPixel*[_rows];
    for(int r = 0; r < _rows; r++)
    {
        _pixel[r] = new UJPixel[_cols];
        for(int c = 0; c < _cols; c++)
        {
            _pixel[r][c] = recPixel;
        }
    }
}

void UJImage::dealloc()
{
    for(int r = 0; r < _rows; r++)
    {
        delete[] _pixel[r];
    }
    delete[] _pixel;

}

void UJImage::enforceRange(int intVal, int intMin, int intMax)
{
    if(intVal > intMax || intVal < intMin)
    {
        cerr << "OUt of range";
        exit(-1);
    }
}

int UJImage::genRand(int intUpper, int intLower)
{
    int Range = intUpper - intLower + 1;
    return rand()% Range + intLower;
}



