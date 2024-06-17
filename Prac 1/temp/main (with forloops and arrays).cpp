#include <iostream>

using namespace std;

int main()

{
    
    string month[12] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    int Rainfall[12] = {83,12,33,49,76,52,58,21,86,9,38,86};
    int sum = 0;
    double avg = 0;
    
    cout<< "Month---Rainfall" << '\n';
    
    for (int x = 0; x <= 11; x++)
    {
        cout << month[x] << "-----" << Rainfall[x] << endl;
    }
    
    for (int x = 0; x <= 11 ;x++)
    {
        sum = sum + Rainfall[x];
        
    }
    avg = sum/12;
    cout << "Average: " << avg << " mm" << endl;
    cout << "Average: " << avg *  0.03937007874 << " inch";
    
    return 0;
    
    
}
