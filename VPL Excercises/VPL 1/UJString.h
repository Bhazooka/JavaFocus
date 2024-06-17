#ifndef UJSTRING_H_INCLUDED
#define UJSTRING_H_INCLUDED

#include <iostream>

using namespace std;
namespace StringSpace
{
    class UJString
    {
        public:
            //consructors
            UJString();
            UJString(int intLength);
            UJString(const UJString& objOriginal);
            ~UJString();
            
            //Accessor Methods
            char getCharAt(int intIndex) ;
            int getLength() const;
            
            //Mutator Methods
            void setCharAt(int intIndex, char chrValue);
            
            //Constants
            static const int DEFAULT_LENGTH = 25;
            static const char DEFAULT_CHAR ='\0';
            
        private:
            //Utility functions
            void alloc(int intLength, char chrValue);
            void clone(const UJString& onjOriginal);
            void dealloc();
            void enforceRange(int intArg, int intMin, int intMax);
            
            //Member Variables
            int _intLength;
            char _chrValue;
            char* _Word;
    };
}

#endif //UJSTRING_H_INCLUDED