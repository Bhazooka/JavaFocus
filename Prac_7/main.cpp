#include <iostream>

#include "GenericStorage2D.h"

using namespace std;

int main()
{

    GenericStorage2D<int> objGenericStorage2D(5,5);
    cout << endl;


    int intCounter = 0;
    for(int r = 0; r < objGenericStorage2D.getRows(); r++)
    {
        for(int c = 0; c < objGenericStorage2D.getCols(); c++)
        {
            objGenericStorage2D[r][c] = ++intCounter;
        }
    }



    for(int r = 0; r < objGenericStorage2D.getRows(); r++)
    {
        for(int c = 0; c < objGenericStorage2D.getCols(); c++)
        {
            cout << objGenericStorage2D[r][c] << ' ';
        }
        cout << endl;
    }


    GenericStorage2D<int> objClone;
    objClone = objGenericStorage2D;

    cout << "Testing << operator" << endl;
    cout << objClone << endl;

    GenericStorage2D<char> objInput;
    cout << "Testing >> operator" << endl;
    cin >> objInput;
    cout << objInput;


    cout << "Testing == Operator";
    objGenericStorage2D == objClone;
    if(objGenericStorage2D == objClone)
    {
        return true;
    }
    else
    {
        return false;
    }


    return 0;
}
