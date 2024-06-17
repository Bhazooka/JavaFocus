#ifndef LIBPRAC5_H_INCLUDED
#define LIBPRAC5_H_INCLUDED

#include <ctime>
#include <cstdlib>
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
namespace PracticeSpace
{
//gets a value from the standard output stream
int GenRan(int intLow, int intHigh);

//checks for conversion errors
vector<int> GenNums();

//Generates random numbers based on values from the standard output stream
void DisplayVec(vector<int> vecNums);

void Sort(vector<int>& vecData);

//create a vector of random numbers
 vector<int> Small(vector<int> vecNums);

//prints vector to standard output stream
vector<int> Large(vector<int> vecNums);

//Calculates the mean
vector<int> Equal(vector<int> vecNums);

//Calculates the median
 vector<int> SumDigits(vector<int> vecNums);

}



#endif // LIBPRAC5_H_INCLUDED
