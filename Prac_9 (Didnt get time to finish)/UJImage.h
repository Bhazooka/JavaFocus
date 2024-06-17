#ifndef UJIMAGE_H
#define UJIMAGE_H

struct Pixel
{
    double dblIntensity;
    int intMin;
    int intMax;
};

enum
{
    
};

class UJImage
{
    
public:
    UJImage();
    UJImage(int intRows, int intCols, int intFocalRow, int intFocalCol);
    UJImage(const UJImage& objOrig);
    
    ~UJImage();
    
    int getRows();
    int getCols();
    int getFocalRow();
    int getFocalCol();
    
    void plot();
    
    static const int DEFAULT_DIMENTION = 50;
    static const int PIXEL_MAX = 100;
    
private:
    int _rows;
    int _cols;
    int _focalRow;
    int _focalCol;
    Pixel** _pixels;
    void enforceRange(int intValue, int intMin, int intMax);
    int randRange(int intMin, int intMax);
};

#endif