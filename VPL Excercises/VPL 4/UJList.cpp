#include "UJList.h"

 //constructors
    UJList :: UJList() : UJList(DEFAULT_LENGTH, DEFAULT_VALUE)
    {
        
    }
    
    UJList :: UJList(int intLength, bool blnValue)
    {
        alloc(intLength, blnValue);
    }
    UJList :: UJList(const UJList& objOriginal)
    {
        alloc(objOriginal._length, objOriginal._value);
        clone(objOriginal);
    }
    
    //destructor
    UJList :: ~UJList()
    {
        dealloc();
    }
    
    //Operator Overloading
    //Assignment Operator
    const UJList& UJList :: operator=(const UJList& objRHS)
    {
        if(this != &objRHS)
        {
            dealloc();
            alloc(objRHS._length, objRHS._value);
            clone(objRHS);
        }
        return *this;
    }
    
    //Array-indexing operator
    bool& UJList :: operator[](int intIndex)
    {
        return _list[intIndex];
    }
    
    //Stream-insertion operator
    std:: ostream& operator <<(std::ostream& osLHS, const UJList& objRHS)
    {
        for(int r = 0; r < objRHS.length(); r++)
        {
            if(objRHS._list[r] == true )
            {
            osLHS << "T ";
            }else
            {
                osLHS <<"F ";
            }
        }
        osLHS << std::endl;
        return osLHS;
    }
    
    //Negation operator
    UJList UJList :: operator!()
    {
        UJList objOut(*this);
        for(int r = 0; r < objOut._length; r++)
        {
            objOut._list[r] = !objOut._list[r];
        }
        return objOut;
    }
    
    //Member Functions
    //Accessor methods
    
    int UJList :: length() const
    {
        return _length;
    }
    
    
    //Utility Functions
    void UJList :: alloc(int intLength, bool blnValue)
    {
        _length = intLength;
        _value = blnValue;
        _list = new bool[_length];
        for(int l = 0; l < _length; l++)
        {
            _list[l] = _value;
        }
    }
    
    void UJList :: dealloc()
    {
        delete _list;
    }
    
    void UJList :: clone(const UJList& objOriginal)
    {
        for (int p = 0;  p < _length; p++)
        {
            _list[p] = objOriginal._list[p];
        }
    }