#ifndef CANVAS2D_H_INCLUDED
#define CANVAS2D_H_INCLUDED

#include <string>


using namespace std;

namespace CanvasSpace{
    enum ERROR_CODE{
        SUCCESS,
        ERROR_RANGE
    };

    class Canvas2D
    {
    public:
        Canvas2D();
        Canvas2D(int intRows, int intCols, int intBGColour);
        Canvas2D(const Canvas2D& objOriginal);

        string toPGM() const;

        void drawCircle(int intCRow, int intCCol, int intRadius, int intPixel);
        void drawRectangle(int intRow, int intCol, int intHeight, int intLength, int intPixel);

        int getBGColour() const;

        ~Canvas2D();

        static const int DEFAULT_ROWS = 500;
        static const int DEFAULT_COLS = 500;
        static const int DEFAULT_BG_COL = 132;
        static const int MAX_DIMENSION_SIZE = 100000;

    private:
        void alloc(int intRows, int intCols, int intBGColour);
        void dealloc();
        void clone(const Canvas2D& objOriginal);
        void enforceRange(int intArg, int intMin, int intMax) const;
        double distance(int intX1, int intX2, int intY1, int intY2) const;

        int _rows;
        int _cols;
        int _bgColour;
        int** _pixels;
    };

}





#endif // CANVAS2D_H_INCLUDED
