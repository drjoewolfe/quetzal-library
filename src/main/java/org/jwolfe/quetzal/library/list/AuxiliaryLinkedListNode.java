package org.jwolfe.quetzal.library.list;

public class AuxiliaryLinkedListNode {
    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
    
    int data;

    public AuxiliaryLinkedListNode getNext() {
        return next;
    }

    public void setNext(AuxiliaryLinkedListNode next) {
        this.next = next;
    }

    AuxiliaryLinkedListNode next;

    public AuxiliaryLinkedListNode getAuxiliary() {
		return auxiliary;
	}

	public void setAuxiliary(AuxiliaryLinkedListNode random) {
		this.auxiliary = random;
	}
    
	AuxiliaryLinkedListNode auxiliary;

	public AuxiliaryLinkedListNode(int data) {
        this.data = data;
        this.next = null;
        this.auxiliary = null;
    }
}
