#include <iostream>
#include "libPrac7.h"

using namespace std;
using namespace HistogramSpace;

int main(int argc, char** argv)
{
    srand(time(nullptr));

    int MINVALUE = 0;
    int MAXVALUE = 0;
    int intNum = 0;
    int intRandomVal = 0;
    bool blnContinue = true;


    intNum = ConvertToInteger(argv[1]);
    Histogram arrHistogram;


    cout<< "A: Generate Random numbers in array." << endl
        << "H: Display Horizontal Histogram." << endl
        << "V: Display Vertical Histogram." << endl
        << "Q: Quit." << endl
        << "R: Restart." << endl;

    cout << endl;

    cout << "Select Option: ";

    do
    {
        char chInput = '\0';
        cin >> chInput;
        switch(chInput)
        {

        case 'a':
        case 'A':
            do
            {
                cout << "Enter lower bound: ";
                cin >> MINVALUE;

                while(cin.fail())
                {
                    cin.clear();
                    string strJunk = "";
                    cin >> strJunk;
                    cerr << "Invalid input. " << endl;
                    cout << "Enter lower bound value: ";
                    cin >> MINVALUE;
                }

                cout << "Enter upper bound: ";
                cin >> MAXVALUE;

                while(cin.fail())
                {
                    cin.clear();
                    string strJunk = "";
                    cin >> strJunk;
                    cerr << "Invalid input." << endl;
                    cout << "Enter upper bound value: ";
                    cin >> MAXVALUE;
                }

            }while(MINVALUE > MAXVALUE);

            GenVal(arrHistogram, intNum, MINVALUE, MAXVALUE);
            Display(arrHistogram, intNum, intRandomVal);

            cout << endl;
            break;

        case 'H':
        case 'h':
            DisplayHorizontal(arrHistogram, MINVALUE, MAXVALUE, intNum);
            cout << endl;
            break;

        case 'V':
        case 'v':
            DisplayVerticle(arrHistogram, MINVALUE, MAXVALUE, intNum);
            cout << endl;
            break;

        case 'Q':
        case 'q':
            blnContinue = false;
            break;

        default:
            cout << "Invalid option. ";
            break;

        case 'R':
        case 'r':
            cout << endl;
            Reset(arrHistogram, intNum);
            cout << "Your array has been reset to: ";
            Display(arrHistogram, intNum, intRandomVal);
            cout << endl;
            break;
        }
    }while(blnContinue);


    return SUCCESS;
}
