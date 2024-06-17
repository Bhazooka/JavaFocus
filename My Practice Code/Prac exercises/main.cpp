#include <iostream>

using namespace std;

void swap(int& a, int b)
{
    a = 100;
    b = 200;

    cout << "a: " << a <<"\t b:" << b << "\n";
}

int main()
{
    int a = 10;
    int b = 20;

    swap(a,b);
    cout << "a: " << a <<"\t b:" << b << "\n";

    return 0;
}






