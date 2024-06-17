#ifndef CANVASCAPPER_H_INCLUDED
#define CANVASCAPPER_H_INCLUDED

//Canvas Capper class
class CanvasCapper
{
public:
    CanvasCapper();
    CanvasCapper(int intRed, int intGreen, int intBlue);

    int getRed();
    int getGreen();
    int getBlue();

    void setRed(int intRed);
    void setGreen(int intGreen);
    void setBlue(int intBlue);

    //Constant Max intensity and Min intensity
    const static int ERR_COLOUR_RANGE = - 1;
    const static int ERR_MAX_COLOUR = 255;
    const static int ERR_MIN_COLOUR = 0;

private:
    int _red;
    int _green;
    int _blue;
    //Void function to Cap the colour range, Takes in a value to be evaluated
    void applyRangeRules(int intVal);

};

#endif // CANVASCAPPER_H_INCLUDED
