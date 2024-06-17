#ifndef LIBPRAC7_H_INCLUDED
#define LIBPRAC7_H_INCLUDED

#include <iostream>
#include <cstdlib>
#include <cmath>
#include <ctime>
#include <iomanip>
#include <sstream>
#include <cassert>
#include <iomanip>

using namespace std;

namespace HistogramSpace
{
    //const errors
    const int ERR_CONV = -1;
    const int ERR_RANGE = -2;

    //const values
    const int INIT = 0;
    const int SUCCESS = 0;

    //const characters
    const char CH_BOTTOM = '!';
    const char CH_MID = '@';
    const char CH_TOP = '#';

    //Array definition
    typedef int Histogram[20];

    //functions
    int ConvertToInteger(string strInput);
    int GenRandom(int intLower, int intUpper);
    void Reset(Histogram arrHistogram, int intNum);
    void GenVal(Histogram arrHistogram,int intNum,int intLower, int intUpper);
    void Display(Histogram arrHistogram, int intNum, int RandomVal);
    char OutPutCharacter(int MIN, int MAX, int RandomValue);
    void DisplayVerticle(Histogram arrHistogram, int intLower, int intUpper,int intNum);
    void DisplayHorizontal(Histogram arrHistogram, int intLower, int intUpper, int intNum);
    //void Reset();


}

#endif // LIBPRAC7_H_INCLUDED
