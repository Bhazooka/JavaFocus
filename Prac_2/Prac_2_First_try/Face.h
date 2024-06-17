#ifndef FACE_H_INCLUDED
#define FACE_H_INCLUDED

class Face
{
public:
    Face();
    Face(int intRed, int intGreen, int intBlue);

    int getRed();
    int getGreen();
    int getBlue();

    void setRed(int intRed);
    void setGreen(int intGreen);
    void setBlue(int intBlue);

    const static int ERR_COLOUR_RANGE = - 1;
    const static int ERR_MAX_COLOUR = 255;
    const static int ERR_MIN_COLOUR = 0;

private:
    int _red;
    int _green;
    int _blue;

    void ColourRange(int intVal);

};

#endif // FACE_H_INCLUDED
