#include "libPrac5.h"

namespace PracticeSpace
{

//Generate numbers
int GenRan(int intLow, int intHigh)
{
    int intRange = intHigh - intLow + 1;
    int intValue = rand() % intRange;
    intValue += intLow;

    return intValue;
}

vector<int> GenNums()
{
    vector<int> vecNums;

    cout<<"Enter amount of numbers to be generated: ";
    int intNum = 0;
    cin>> intNum;

    cout<<"Enter lower bound: ";
    int intLowest = 0;
    cin>> intLowest;

    cout<<"Enter upper bound: ";
    int intHighest = 0;
    cin>> intHighest;

    for(int n=0; intNum>n;n++)
    {
        int intValue = GenRan(intLowest, intHighest);
        vecNums.push_back(intValue);
    }

    return vecNums;
}

void DisplayVec(vector<int> vecNums)
{
    for(int c : vecNums)
    {
        cout << c << " ";
    }
    cout<< endl;
}

void Sort(vector<int>& vecData)
{
    sort(vecData.begin(),vecData.end());
}

vector<int> Small(vector<int> vecNums)
{
    vector<int> vecSmaller;
    int intSize = vecNums.size();

    cout<< "These are the numbers larger than the number you intputed: ";
    int intSmallest = 0;
    cin>>intSmallest;

    for(int n=0; n < intSize; n++)
    {
        if(vecNums[n]<intSmallest)
            {
                vecSmaller.push_back(vecNums[n]);
            }
    }
    return vecSmaller;
}

vector<int> Large(vector<int> vecNums)
{
    vector<int> vecLarger;
    int intSize = vecNums.size();

    cout<<"These are the numbers smaller than the number you intputed: ";
    int intLarger = 0;
    cin>> intLarger;

    for(int n = 0; n < intSize; n++)
    {
        if(vecNums[n]>intLarger)
        {
            vecLarger.push_back(vecNums[n]);
        }
    }
    return vecLarger;
}

vector<int> Equal(vector<int> vecNums)
{
    vector<int> vecEqual;
    int intSize = vecNums.size();

    cout<<"These are the numbers equal to the number you inputed: ";
    int intEqual = 0;
    cin>>intEqual;

    for(int n = 0; n < intSize; n++)
    {
        if(vecNums[n]==intEqual)
        {
            vecEqual.push_back(vecNums[n]);
        }
    }
    return vecEqual;
}

vector<int> SumDigits(vector<int> vecNums)
{
    vector<int> vecDigit;
    int intSize = vecNums.size();

    for(int n = 0 ; n < intSize; n++)
    {
        int intSum = 0;
        vector<int> vecValue;
        vecValue.push_back(vecNums[n]);
        for(char v : vecValue)
        {
            intSum += v;
        }
        vecDigit.push_back(intSum);
    }
    return vecDigit;
}
}
