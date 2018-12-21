package org.jwolfe.quetzal.library.queue;

import java.util.Arrays;

import org.jwolfe.quetzal.library.utilities.Utilities;

public class CircularQueue {
	int[] items;
	int size;
	int front;
	int rear;
	
	public CircularQueue(int size) {
		this.size = size;
		this.items = new int[size];
		Arrays.fill(items, Integer.MIN_VALUE);
		front = -1;
		rear = -1;
	}

	public void enqueue(int item) {
		if((front ==0 && rear == size - 1)
			|| rear == front - 1) {
			// Queue full
			return;
		}
		else if(front == -1) {
			// First element
			front = 0;
			rear = 0;
			items[rear] = item;
		}
		else if(rear == size - 1) {
			rear = 0;
			items[rear] = item;
		}
		else {
			items[++rear] = item;
		}		
	}
	
	public int dequeue() {
		if(front == -1) {
			// Empty queue
			return Integer.MIN_VALUE;
		}
		
		int data = items[front];
		items[front]= Integer.MIN_VALUE;
		if(front == rear) {
			// Queue is empty now
			front = -1;
			rear = -1;
		}
		else if(front == size - 1) {
			front = 0;
		}
		else {
			front++;
		}
		
		return data;
	}
	
	public void displayQueue() {
		if(front == -1) {
			System.out.println("Queue is empty.");
			return;
		}
		
		Utilities.printArray(items);
		
		if(rear >= front) {
			for (int i = front; i <= rear; i++) {
				System.out.print(items[i] + " ");
			}
		}
		else {
			for (int i = front; i < size; i++) {
				System.out.print(items[i] + " ");
			}
			
			for (int i = 0; i <= rear; i++) {
				System.out.print(items[i] + " ");
			}
		}
	}
}
