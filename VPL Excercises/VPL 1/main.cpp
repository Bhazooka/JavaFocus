#include "UJString.h"

#include <iostream>
using namespace StringSpace;
using namespace std;

int main()
{
    // Creating a UJString object.
    UJString objString = UJString(13);
    // Modifying the contents of the string.
    objString.setCharAt(0, 'H');
    objString.setCharAt(1, 'e');
    objString.setCharAt(2, 'l');
    objString.setCharAt(3, 'l');
    objString.setCharAt(4, '0');
    objString.setCharAt(5, ',');
    objString.setCharAt(6, ' ');
    objString.setCharAt(7, 'W');
    objString.setCharAt(8, 'o');
    objString.setCharAt(9, 'r');
    objString.setCharAt(10, 'l');
    objString.setCharAt(11, 'd');
    objString.setCharAt(12, '!');

    // Retrieving the contents of the string.
    for(int i = 0; i < objString.getLength(); i++){
        std::cout << objString.getCharAt(i);
    }
    return 0;
}