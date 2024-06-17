#ifndef CANVAS2D_H_INCLUDED
#define CANVAS2D_H_INCLUDED

#include <iostream>

using namespace std;
namespace CanvasSpace
{
    struct RGBPixel
    {
        int intRed;
        int intGreen;
        int intBlue;
    };

    enum ERROR
    {
        SUCCESS,
        ERR_RANGE
    };

    class Canvas
    {
        public:

            Canvas();   //Defualt Constructor
            Canvas(int intRows, int intCols, RGBPixel recDefault);
            Canvas(const Canvas& self);

            string toPPM() const;

            void CreateFace(int intRadius, int intCenter, int intRadiusVal, RGBPixel recPixel) const; //Function to draw the face circumference

            int getRows() const;
            int getCols() const;
            RGBPixel getPixel(int intRow, int intCol) const;

            void setPixel(int intRow, int intCol, RGBPixel recPixel);

            ~Canvas();  //Destructor

            static const int DEFAULT_RED = 0;
            static const int DEFAULT_GREEN = 0;
            static const int DEFAULT_BLUE = 0;



        private:

            void AllocMem(int intRows, int intCols, RGBPixel recDefault);
            void RangeCheck(int intValue, int intMin, int intMax) const;
            double distance(double intRow, double intCenterRow, double intCol, double intCenterCCol) const;
            int _rows;
            int _cols;
            RGBPixel** _pixels;


    };

}


#endif // CANVAS2D_H_INCLUDED
