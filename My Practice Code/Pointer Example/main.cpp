#include <iostream>

using namespace std;

int main()
{
    /*int* intNum;
    cout << intNum; */



    int* intNum;

    int intNum2 = 5;

    intNum = &intNum2;

    cout << *intNum << endl;
    cout << intNum2 << endl;


    return 0;
}
