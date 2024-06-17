#include <iostream>

using namespace std;

int main()
{
    const int Jan = 83;
    const int Feb = 12;
    const int Mar = 33;
    const int Apr = 49;
    const int May = 76;
    const int Jun = 52;
    const int Jul = 58;
    const int Aug = 21;
    const int Sep = 86;
    const int Oct = 9;
    const int Nov = 38;
    const int Dec = 86;

    cout << "Month--Rainfall"<<endl;
    cout << "Jan----" << Jan << endl;
    cout << "Feb----" << Feb << endl;
    cout << "Mar----" << Mar << endl;
    cout << "Apr----" << Apr << endl;
    cout << "May----" << May << endl;
    cout << "Jun----" << Jun << endl;
    cout << "Jul----" << Jul << endl;
    cout << "Aug----" << Aug << endl;
    cout << "Sep----" << Sep << endl;
    cout << "Oct----" << Oct << endl;
    cout << "Nov----" << Nov << endl;
    cout << "Dec----" << Dec << endl;

    int TotalRainfall = Jan + Feb + Mar + Apr + May + Jun + Jul + Aug + Sep + Oct + Nov + Dec;

    double Avg = TotalRainfall/12;

    cout << "Average Rainfall: " << Avg << "mm" << endl;
    cout << "Average Rainfall: " << Avg * 0.03937007874 << "inch" << endl;


    return 0;
}
