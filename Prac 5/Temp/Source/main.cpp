#include <iostream>
#include "libPrac5.h"

using namespace std;
using namespace PracticeSpace;

int main()
{
    const int SUCCESS = 0;
    bool blnContinue = true;

    vector<int> VecData;
    vector<int> vecSmall;
    vector<int> vecLarge;
    vector<int> vecEqual;
    vector<int> vecSum;


    do
    {
        //system("cls");

        //Prompt for an option
        cout<< "1) Generate Random numbers."<<endl
            << "2) Display numbers smaller than input."<<endl
            << "3) Display numbers larger than input."<<endl
            << "4) Display numbers equal to the input."<<endl
            << "5) Display the sum of the digits in the vector."<<endl
            << "6) End Program."<<endl;


        char chOption = '\0';
        cin>> chOption;

        switch(chOption)
        {
            case '1':
            {
                VecData.clear();
                VecData = GenNums();

                cout<<"Random numbers: ";
                DisplayVec(VecData);

                cout<< "Sorted list: ";
                Sort(VecData);
                DisplayVec(VecData);

                break;
            }

            case '2':
            {
                vecSmall.clear();
                vecSmall = Small(VecData);

                cout<<"Numbers smaller than the number entered: ";
                DisplayVec(vecSmall);

                break;
            }

            case '3':
            {
                vecLarge.clear();
                vecLarge = Large(VecData);

                cout<< "Numbers larger than the number entered: ";
                DisplayVec(vecLarge);

                break;
            }

            case '4':
            {
                vecEqual.clear();
                vecEqual = Equal(VecData);

                cout<<"Numbers Equal to the number entered: ";
                DisplayVec(vecEqual);

                break;
            }

            case '5':
            {
                vecSum.clear();
                vecSum = SumDigits(VecData);

                cout<<"Sums is: ";
                DisplayVec(vecSum);

                break;
            }

            case '6':
            {
                cout<< "Close."<<endl;
                blnContinue= false;
                break;
            }

            default:
            {
                cerr<< "Invalid value"<<endl;
            }
        }

    }while(blnContinue);
    return 0;
}
