#include "UJString.h"

namespace StringSpace
    {
        //consructors
            UJString :: UJString() : UJString(DEFAULT_LENGTH)
            {
                
            }
            
            UJString :: UJString(int intLength)
            {
                alloc(intLength, DEFAULT_CHAR);
                
            }
            
            UJString :: UJString(const UJString& objOriginal)
            {
                clone(objOriginal);
            }
            
            UJString :: ~UJString()
            {
                dealloc();
            }
            
            //Accessor Methods
            char UJString ::  getCharAt(int intIndex) 
            {
                enforceRange(intIndex, 0 , _intLength - 1);
                return _Word[intIndex];
            }
            
            int UJString ::  getLength() const
            {
                return _intLength;
            }
            
            //Mutator Methods
            void UJString ::  setCharAt(int intIndex, char chrValue)
            {
                enforceRange(intIndex, 0 , _intLength - 1);
                _Word[intIndex] = chrValue;
            }
            
            
            //Utility functions
            void UJString ::  alloc(int intLength, char chrValue)
            {
                _intLength = intLength;
                _chrValue = chrValue;
                
                _Word = new char [_intLength];
                
                for (int p = 0; p < _intLength; p++)
                {
                    _Word[p] =_chrValue;
                }
            }
            
            void UJString ::  clone(const UJString& objOriginal)
            {
                for (int p = 0 ; p < _intLength ; p++)
                {
                    _Word[p] = objOriginal._Word[p];
                }
            }
            void UJString ::  dealloc()
            {
                delete _Word;
            }
            
            void UJString ::  enforceRange(int intArg, int intMin, int intMax)
            {
                if (intArg < intMin || intArg > intMax)
                {
                    cerr << intArg << " must be in [" << intMin << ", " << intMax << "]" << endl;
                    exit(-2);
                }
            }
    }