#ifndef EXCEPTIONS_H_INCLUDED
#define EXCEPTIONS_H_INCLUDED

#include <string>
/*
 * Exception classes can be declared in the same
 * header file.
 */
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

#endif // EXCEPTIONS_H_INCLUDED




int main()
{
    try
    {
        /*
         * Code that could result in an exception being thrown must be
         * placed in a try-catch block/
         */
        vector<Module> vecModules = readModulesFromTXT("data/modules.txt");
        vector<Student> vecStudents = readStudentsFromTXT("data/students.txt");
        cout << "DEBUG: " << vecStudents.size() << endl;
        for(Student s : vecStudents){
            vecModules[0].addStudent(s);
        }
        for(Module m : vecModules){
            cout << m.toString() << endl;
        }
        vecModules[0].saveAveragesToTXT("data/average.txt");
    /*
     * Exceptions are caught in a bottom-up order along the
     * inheritance hierarchy (from more specific exceptions to more
     * general ones).
     */
    }

    catch(InFileOpenException& e) // Specific exception
    {
        cerr << e.message() << endl;
    }

    catch(OutFileOpenException& e) // Specific excption
    {
        cerr << e.message();
    }

    catch(Exception& e) // General exception
    {
        cerr << e.message() << endl;
    }

    catch(...)// Most general exception in C++. You must ALWAYS include this catch block last.
    {
        cerr << "An unknown error occurred." << endl;
    }
    return 0;
}

