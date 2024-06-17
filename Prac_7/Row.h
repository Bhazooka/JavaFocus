#ifndef ROW_H_INCLUDED
#define ROW_H_INCLUDED

#ifndef ROW_H
#define ROW_H

static const int DEFAULT_COLS = 10;
static const int MAX_COLS = 100000;

template <typename T>
class Row
{
public:
    Row();
    Row(const Row<T>& objOrig);
    Row(int intLength);

    Row<T>& operator=(const Row<T>& objRHS);

    T& operator[](int intIndex);

    int getLength() const;

    static const int ERROR_ROW_LENGTH = -1;
    static const int ERROR_ROW_INDEX = -2;

    ~Row();
private:
    void reDim(int intLength);
    int _length;
    T* _info;
};

#include "Row.imp"

#endif

#endif // ROW_H_INCLUDED
