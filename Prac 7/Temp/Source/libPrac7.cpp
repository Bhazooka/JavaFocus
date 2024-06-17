#include "libPrac7.h"

namespace HistogramSpace
{
    //convert arguments strings to integers
    int ConvertToInteger(string strInput)
    {
        int intConverted = -1;
        stringstream ss {strInput};
        ss >> intConverted;
        if(ss.fail())
        {
            cerr << "Coulnd't convert to an integer" << endl;
            exit(ERR_CONV);
        }
        return intConverted;
    }

    //Generate Random values for the array
    int GenRandom(int intLower, int intUpper)
    {
        int intRange = intUpper - intLower + 1;
        return rand()%intRange + intLower;
    }


    //Generate random values into each array slots
    void GenVal(Histogram arrHistogram,int intNum,int intLower, int intUpper)
    {
        //intNum = 21;
        for (int h = 0; h < intNum; h++)
        {
            int RandomVal = GenRandom(intLower, intUpper);
            arrHistogram[h] = RandomVal;

        }
    }


    //Display all the items inside the array
    void Display(Histogram arrHistogram, int intNum, int RandomVal)
    {
        //intNum = 21;
        for (int i = 0; i < intNum; i++)
        {
            cout << arrHistogram[i] << " ";
        }
    }


    //Determine the characters to be displayed
    char OutPutCharacter(int MIN, int MAX, int RandomValue)
    {
        double difference = MAX - MIN;

        double BottomRange =  difference * (1/3) + MIN;
        double MiddleRange =  difference * (2/3) + MIN;
        double TopRange =  difference + MIN;

        if(RandomValue <= BottomRange)
        {
            return CH_BOTTOM;
        }
        else if(RandomValue >= TopRange)
        {
            return CH_TOP;
        }
        else
            return CH_MID;
    }


    //Find the maximum value
    int Lowest(Histogram arrHistogram, int intNum)
    {
        //intNum = 21;
        int minNumber = arrHistogram[0];
        for (int h = 0; h < intNum; h++)
        {
            if(arrHistogram[h] < minNumber)
            {
                minNumber = arrHistogram[h];
            }
        }
        return minNumber;
    }


    //Find the max values
    int Highest(Histogram arrHistogram, int intNum)
    {
        int maxNumber = arrHistogram[0];
        for (int h = 0; h < intNum; h++)
        {
            if(arrHistogram[h] > maxNumber)
            {
                maxNumber = arrHistogram[h];
            }
        }
        return maxNumber;
    }


    //Reset values to 0
    void Reset(Histogram arrHistogram, int intNum)
    {
        for (int i = 0; i < intNum; i++)
        {
            //Histogram arrHistogram = new int[20];
            arrHistogram[i] = INIT;
        }
    }


    //Display horizontal
    void DisplayHorizontal(Histogram arrHistogram, int intLower, int intUpper, int intNum)
    {
        cout << endl;
        int MAXarr = Highest(arrHistogram, intNum);
        int MINarr = Lowest(arrHistogram, intNum);
        for(int i = 0; i < intNum; i++)
        {
            cout << setw(2) << arrHistogram[i] << "|";
            for(int h = 0; h < arrHistogram[i]; h++)
            {
                cout << OutPutCharacter(MINarr, MAXarr, arrHistogram[i]);
                cout << " ";
            }
            cout << endl;
        }

        for(int i = 0; i <= intNum; i++)
        {
            cout << "---";
            cout << setw(1);
        }
        cout << endl;

        cout << setw(4);
        for(int i = 0; i <= MAXarr; i++)
        {
            cout << i+1 << " ";
        }
    }

    //Display verticle
    void DisplayVerticle(Histogram arrHistogram, int intLower, int intUpper,int intNum)
    {
        int MAXarr = Highest (arrHistogram, intNum);
        int MINarr = Lowest (arrHistogram, intNum);

        for(int r = MAXarr; r >= 1; r--)
        {
            cout << setw(2);
            for(int c = 0; c <= intNum; c++)
            {
                cout << OutPutCharacter(MINarr, MAXarr, arrHistogram[c]);
                cout << endl;
            }
        }

        for(int i = 0; i <= intNum; i++)
        {
            cout << setw(2) << "--";
        }
        cout << endl;

        for(int i = 0; i <= intNum - 1; i++)
        {
            cout << arrHistogram[i] << " ";
        }
    }

//    void Reset(TwoD arrHistogram, int intRows, int intCols)
//    {
//
//    }
}
