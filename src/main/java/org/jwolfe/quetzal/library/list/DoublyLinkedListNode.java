package org.jwolfe.quetzal.library.list;

public class DoublyLinkedListNode {
    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
    
    int data;

    public DoublyLinkedListNode getNext() {
        return next;
    }

    public void setNext(DoublyLinkedListNode next) {
        this.next = next;
    }

    DoublyLinkedListNode next;
    
    public DoublyLinkedListNode getPrevious() {
		return previous;
	}

	public void setPrevious(DoublyLinkedListNode previous) {
		this.previous = previous;
	}

	DoublyLinkedListNode previous;

	public DoublyLinkedListNode(int data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }
}
