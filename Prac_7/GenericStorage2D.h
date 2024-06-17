#ifndef GENERICSTORAGE2D_H_INCLUDED
#define GENERICSTORAGE2D_H_INCLUDED

#include <iostream>

#include "Row.h"

template <typename T>
class GenericStorage2D
{
public:
    GenericStorage2D();
    GenericStorage2D(int intRows, int intCols);
    GenericStorage2D(const GenericStorage2D<T>& objOrig);

    int getRows() const;
    int getCols() const;

    GenericStorage2D<T>& operator=(GenericStorage2D<T>& objRHS);
    Row<T>& operator[](int intIndex);

    bool operator==(const GenericStorage2D<T>& objRHS);
    bool operator!=(const GenericStorage2D<T>& objRHS);

    template <typename T1>
    friend std::ostream& operator<<(std::ostream& sLHS, GenericStorage2D<T1>& objRHS);

    template <typename T1>
    friend std::istream& operator>>(std::istream& sLHS, GenericStorage2D<T1>& objRHS);

    static const int ERROR_GenericStorage2D_RANGE = -3;
    static const int MAX_ROWS = 100000;
    static const int DEFAULT_ROWS = 10;

    ~GenericStorage2D();
private:
    void enforceRange(int intValue, int intMin, int intMax) const;
    void reDim(int intRows, int intCols);
    void copyState(GenericStorage2D<T>& objOrig);
    void Dealloc();
    int _rows;
    int _cols;
    Row<T>** _info;
};

#include "GenericStorage2D.imp"

#endif // GENERICSTORAGE2D_H_INCLUDED
