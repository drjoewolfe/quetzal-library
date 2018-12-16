package org.jwolfe.quetzal.library.list;

public class LinkedListNode {
    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public LinkedListNode getNext() {
        return next;
    }

    public void setNext(LinkedListNode next) {
        this.next = next;
    }

    int data;
    LinkedListNode next;

    public LinkedListNode(int data) {
        this.data = data;
        this.next = null;
    }
}
