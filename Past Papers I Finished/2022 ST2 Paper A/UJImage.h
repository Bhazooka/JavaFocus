#ifndef UJIMAGE_H_INCLUDED
#define UJIMAGE_H_INCLUDED

#include <iostream>
#include <cstdlib>
#include <sstream>
#include <string>
#include <fstream>
#include <random>


using namespace std;

//padding disable
#pragma pack(push)
#pragma pack(1)
struct UJPixel
{
    bool blnVisibility;
    int int_Intensity;  //min=0 , max=255
    int intMin = 0;
    int intMax = 255;

};
#pragma pack(pop)


class UJImage
{
public:
    //Constructors
    UJImage();
    UJImage(int intRows, int intCols, UJPixel recPixels);
    UJImage(const UJImage& objOriginal);
    //Destructor
    ~UJImage();

    //operator overloading
    const UJImage& operator=(const UJImage& objRHS);
    std::string operator[](int intIndex)const;
    void operator()(int intRow, int intCol, UJPixel defaultPixel);

    friend std::ostream& operator<<(std::ostream& osLHS, const UJImage& objRHS);

    //file handling
    void loadFromTxt(std::string FileName);
    void saveInfoToDAT(std::string FileName);

    //random number generator function
    int genRand(int intUpper, int intLower);

    int getRows() const;
    int getCols() const;

    void draw();

    string toPGM() const;

    static const int DEF_ROWS = 100;
    static const int DEF_COLS = 100;
    const UJPixel DEF_PIX = {false,0,0,255};

private:
    int _index;
    int _rows;
    int _cols;
    UJPixel** _pixel;
    void init(int intRows, int intCols, UJPixel recPixel);
    void dealloc();
    void enforceRange(int intVal, int intMin, int intMax);

};



#endif // UJIMAGE_H_INCLUDED
