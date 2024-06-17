#ifndef EXCEPTION_H_INCLUDED
#define EXCEPTION_H_INCLUDED

#include <iostream>
#include <string>


class Exception
{
public:
    virtual std::string message();
};


class OutFileOpenException : public Exception
{
public:
    virtual std::string message();
};


class InFileOpenException : public Exception
{
public:
    virtual std::string message();
};



#endif // EXCEPTION_H_INCLUDED
