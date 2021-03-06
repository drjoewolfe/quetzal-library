package org.jwolfe.quetzal.library.queue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LinkedListQueueTest {

	@Test
	void testLinkedListQueue() {
		LinkedListQueue queue = new LinkedListQueue();
		queue.enqueue(14);
		queue.enqueue(22);
		queue.enqueue(10);
		queue.enqueue(-6);
		
		queue.displayQueue();
		
		assertEquals(14, queue.dequeue());
		assertEquals(22, queue.dequeue());
		assertEquals(10, queue.dequeue());
		assertEquals(-6, queue.dequeue());
	}

}
