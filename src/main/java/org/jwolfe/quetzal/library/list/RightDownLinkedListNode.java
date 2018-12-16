package org.jwolfe.quetzal.library.list;

public class RightDownLinkedListNode {
    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    int data;

    public RightDownLinkedListNode getNext() {
        return next;
    }

    public void setNext(RightDownLinkedListNode next) {
        this.next = next;
    }

    RightDownLinkedListNode next;

    public RightDownLinkedListNode getDown() {
        return down;
    }

    public void setDown(RightDownLinkedListNode down) {
        this.down = down;
    }

    RightDownLinkedListNode down;

    public RightDownLinkedListNode(int data) {
        this.data = data;
        this.next = null;
        this.down = null;
    }
}
