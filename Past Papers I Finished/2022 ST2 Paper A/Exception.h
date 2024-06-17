#ifndef EXCEPTION_H_INCLUDED
#define EXCEPTION_H_INCLUDED

#include <iostream>

class Exception
{
public:
    virtual std::string message();
};

//Inheritance from base (general) Exception class
class FileInputException : public Exception
{
public:
    virtual std::string message();
};

//Inheritance from base (general) Exception class
class FileOutputException : public Exception
{
public:
    virtual std::string message();
};





#endif // EXCEPTION_H_INCLUDED
