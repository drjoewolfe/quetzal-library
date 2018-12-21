package org.jwolfe.quetzal.library.queue;

import org.jwolfe.quetzal.library.list.LinkedListNode;
import org.jwolfe.quetzal.library.utilities.Utilities;

public class LinkedListQueue {
	
	LinkedListNode front;
	LinkedListNode rear;
	
	public LinkedListQueue() {
		front = null;
		rear = null;
	}

	public void enqueue(int key) {
		LinkedListNode node = new LinkedListNode(key);
		
		if(rear == null) {
			front = rear = node;
		}
		else {
			rear.setNext(node);
			rear = node;
		}
	}
	
	public int dequeue() {
		if(front == null) {
			return Integer.MIN_VALUE;
		}
		
		var data = front.getData();
		front = front.getNext();
		if(front == null) {
			rear = null;
		}
		
		return data;
	}
	
	public void displayQueue() {
		Utilities.printLinkedList(front);
	}
}
