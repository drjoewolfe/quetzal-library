package org.jwolfe.quetzal.library.tree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.utilities.Utilities;

class FenwickTreeTest {

	@Test
	void testFenwickTree() {
		int[] arr = Utilities.constructArray(2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9);
		
		FenwickTree tree = new FenwickTree();
		tree.construct(arr);
		assertEquals(12, tree.getSum(5));
		tree.update(3, 6);
		assertEquals(18, tree.getSum(5));
	}
}
