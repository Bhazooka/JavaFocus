#include <iostream>

using namespace std;

int main()
{
//    int luckyNumbers[5] = {2,3,5,7,9};
//    cout << luckyNumbers << endl;
//    cout << &luckyNumbers[0] << endl;  //"&" means the address. The first element in the index is the name of the array. //This line means display the address of the first element in the array
//    cout << &luckyNumbers[2] << endl;  //This line means display the address of the second element in the array
//
//
//    cout << luckyNumbers[0] << endl;   //This line means display the value of the first element in the array
//    cout << luckyNumbers[2] << endl;   //Display the value of the second value in the array
//
//
//    cout << *(luckyNumbers + 2) << endl; //This line of code and line 14 are the same. It uses the * to dereference the value. It says use the address of the first element and then add 2. the star just means display the value
//
//
//
//    for (int i = 0; i < 5; i++)       //This is a forloop to display the entire array.
//    {
//        cout << luckyNumbers[i] << " " ;
//    }


    int arrSize;
    cout << "How many numbers would you like to display?: ";
    cin >> arrSize;

    int Numbers [arrSize];
    cout << "Enter " << arrSize << " values for the array: ";

    for(int i = 0; i < arrSize; i++)
    {
        cin >> Numbers[i];
    }

    for(int i = 0; i < arrSize; i++)
    {
        cout << Numbers[i] << " " << endl;

        cout << &Numbers[i] << " "; //Displays the address of the array
    }


    system("pause>0");

    return 0;
}
