#include "Exception.h"

std::string Exception::message()
{
    return "General Error";
}

std::string OutFileOpenException::message()
{
    return "Error, couldnt open file via output file stream";
}

std::string InFileOpenException::message()
{
    return "Error, couldnt open file via input file stream";
}
