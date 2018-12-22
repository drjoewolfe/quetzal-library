package org.jwolfe.quetzal.library.stack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QueueStackTest {

	@Test
	void testQueueStack() {
		QueueStack<Integer> stack = new QueueStack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		
		assertEquals(3, (int) stack.pop());
		assertEquals(2, (int) stack.pop());
		assertEquals(1, (int) stack.pop());
		assertEquals(null,  stack.pop());
	}

}
