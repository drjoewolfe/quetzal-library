package org.jwolfe.quetzal.library.stack;

import java.util.LinkedList;
import java.util.Queue;

public class QueueStack<T> {
	Queue<T> queue1;
	Queue<T> queue2;
	
	public QueueStack() {
		this.queue1 = new LinkedList<>();
		this.queue2 = new LinkedList<>();
	}

	public void push(T item) {
		// Push heavy implementation.
		// Alternate implementation 1: Pop heavy.
		// Alternate implementation 2: Dancing Queues.
		
		while(!queue1.isEmpty()) {
			queue2.offer(queue1.poll());
		}
		
		queue1.offer(item);
		
		while(!queue2.isEmpty()) {
			queue1.offer(queue2.poll());
		}
	}
	
	public T pop() {
		return queue1.poll();
	}
}
