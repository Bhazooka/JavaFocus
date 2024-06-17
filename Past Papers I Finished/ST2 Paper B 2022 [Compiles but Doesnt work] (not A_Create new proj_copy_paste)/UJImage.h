#ifndef UJIMAGE_H_INCLUDED
#define UJIMAGE_H_INCLUDED

#include <iostream>
#include <sstream>
#include <cstdlib>
#include <vector>
#include <cmath>
#include <cstring>
#include <random>

using namespace std;

#pragma pack(push)
#pragma pack(1)
struct UJPixel
{
    int intRow;
    int intCol;


    int Red;
    int Green;
    int Blue;
    int intSeed[5];
};
#pragma pack(pop)


class UJImage
{
public:
    UJImage();
    UJImage(int intRows, int intCols, UJPixel recPixel);
    UJImage(const UJImage& objOriginal);
    ~UJImage();

    void draw();

    int getRow() const;
    int getCol() const;

    const UJImage& operator=(const UJImage& objRHS);
    friend std::ostream& operator<<(std::ostream& osLHS, const UJImage& objRHS);
    UJImage operator++();


    string toPPM() const;   //P3 PPM
    void loadtxt(std::string FileName);
    void savetxt(std::string FileName);

    static const int DEF_ROWS = 10;
    static const int DEF_COLS = 10;


private:

    int _rows;
    int _cols;

    void enforceRange(int intVal, int intMin, int intMax);
    void init(int intRows, int intCols, UJPixel recPixel);
    void dealloc();
    UJPixel** _pixel;

};




#endif // UJIMAGE_H_INCLUDED
