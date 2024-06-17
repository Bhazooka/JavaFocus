#ifndef UJLIST_H
#define UJLIST_H

 #include <iostream>
 #include "PrimeInitialiser.h"
 
 
 class PrimeInitialiser;
 
 using namespace std;
 
 enum StatusCode
 {
     SUCESS,
     ERROR_RANGE,
 };
 // something missing here
 class UJList
 {
     public:
     UJList(); //
     UJList( int length , int Value);
     UJList(const UJList& objList);
    // void initialise();
     
     int get(int index)const;
     int length()const;
     void add(int index, int num);
     int Prime_num;
     
      
     
     static const int DEFAULT_LENGTH=10000;
     static const int DEFAULT_VALUE=10000;
     
     ~UJList();
     
     
     private:
     
     void Clone(const UJList& objList);
     void enforceRange(int intValue, int Min, int Max)const;
     void allocMem(int length , int Value);
     void Dealloc();
     
  PrimeInitialiser* _initialiser; 
     
     int* _1DArray;
     int _length;
     int _value;
     
    
 };


#endif