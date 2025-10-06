#include <iostream>
using namespace std;

struct Node {
    int data;
    Node* next;
    Node* prev;
};

Node* head = NULL;

void insertBeginning(int x) {
    Node* n = new Node();
    n->data = x;
    n->prev = NULL;
    n->next = head;
    if (head != NULL)
        head->prev = n;
    head = n;
    cout << "Inserted " << x << " at the beginning.\n";
}

void insertEnd(int x) {
    Node* n = new Node();
    n->data = x;
    n->next = NULL;
    n->prev = NULL;
    if (head == NULL) {
        head = n;
    } else {
        Node* temp = head;
        while (temp->next != NULL)
            temp = temp->next;
        temp->next = n;
        n->prev = temp;
    }
    cout << "Inserted " << x << " at the end.\n";
}


