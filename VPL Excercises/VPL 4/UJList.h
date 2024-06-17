#ifndef UJLIST_H
#define UJLIST_H
#include <iostream>

enum ProgramStatus
{
    SUCESS    
};

class UJList
{
public:
    //constructors
    UJList();
    UJList(int intLength, bool blnValue);
    UJList(const UJList& objOriginal);
   
   //Destructor
    ~UJList();
    
    //Member Functions
    //Accessor methods
    int length() const;
    
    //Operator Overloading
    //Assignment Operator
    const UJList& operator=(const UJList& objRHS);
    
    //Array-indexing operator
    bool& operator[](int intIndex);
    
    //Stream-insertion operator
    friend std:: ostream& operator <<(std::ostream& osLHS, const UJList& objRHS);
    
    //Negation operator
    UJList operator!();
    
    //Constants
    static const int DEFAULT_LENGTH = 14;
    static const bool DEFAULT_VALUE = false;
    
private:
        //Member Functions
        void alloc(int intLength, bool blnvalue);
        void clone(const UJList& objOriginal);
        void dealloc();

        //Member variable
        int _length;
        bool _value;
        bool* _list;
    
};

#endif //UJLIST_H