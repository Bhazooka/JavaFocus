#include <iostream>

using namespace std;


struct Node
{
    int _data;
    Node *next;
};

int main()
{
    Node *head = new Node;
    Node *first = new Node;
    Node* tail = new Node;

    head->_data = 1;
    head-> next = first;


    first->_data = 2;
    (*first).next = tail;


    tail->_data = 3;
    tail->next = nullptr;


    while(head != nullptr)
    {
        cout<< head->_data << " ";

        head = head->next;
    }







    return 0;
}
