#include "Exception.h"

std::string Exception::message()
{
    return "Error found";
}

std::string FileInputException::message()
{
    return "File Coulnt open via input";
}

std::string FileOutputException::message()
{
    return "File Coulnt open via input";
}
