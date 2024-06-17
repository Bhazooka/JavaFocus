#include "UJList.h"

    UJList::UJList():UJList(  DEFAULT_LENGTH ,DEFAULT_VALUE)
    {
        
    }
    
     UJList::UJList( int length , int Value)
     {
         _length= length;
         _value= Value;
         
         allocMem(_length , _value);
     }
     
     void UJList::initialise()
     {
        _initialiser-> initialise(*this);
     }
 
 
     UJList::UJList(const UJList& objList)
     {
         Clone(objList);
     }
         
     int UJList::get(int index)const
     {
         return _1DArray[index];
     }
     int UJList::length() const
     {
         return _length;
     }
     
     UJList::~UJList()
     {
         Dealloc();
     }
     
    void UJList::add(int index, int num)
    {  
        
        
        
            _1DArray[index]=num;
        
    }
     void  UJList::Clone(const UJList& objList)
     {
          if ( objList._length == _length )
        {   
            //alloc memory for this object array;
            
            //then clone
            for(int r = 0; r< _length; r++)
            {
                objList._1DArray[r] = _1DArray[r];
            }
        }
     }
     void UJList::enforceRange(int intValue, int Min, int Max)const
     {
         if(intValue<Min || intValue>Max)
         {
             cerr<< intValue<<"is <" << Min << " is > " << Max <<endl;
             exit(ERROR_RANGE);
         }
     }
     void  UJList::allocMem(int length , int Value)
     {
         _value= Value;
         _1DArray = new int[length];
         
          for(int r = 0; r< length; r++)
          {
              _1DArray[r] = Value;
          }
          _initialiser= new PrimeInitialiser;
     }
     void  UJList::Dealloc()
     {
      //  code missing here
         delete [] _1DArray;
         _1DArray= nullptr;
     }