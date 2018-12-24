package org.jwolfe.quetzal.library.trie;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.trie.SuffixTree;

class SuffixTreeTest {

	@Test
	void testSuffixTree() {
		SuffixTree tree = new SuffixTree();
		tree.construct("abcabxabcd$");		
		
		SuffixNode root = tree.getRoot();
		assertNotNull(root);		
		assertSuffixNodeChildren(root, 'a', 'b', 'c', 'd', 'x', '$');

		SuffixNode node;
		
		node = root.getChildren()['a'];
		assertSuffixNode(node, 0, 1);
		assertSuffixNodeChildren(node, 'c', 'x');
		
		node = root.getChildren()['b'];
		assertSuffixNode(node, 1, 1);
		assertSuffixNodeChildren(node, 'c', 'x');
		
		node = root.getChildren()['c'];
		assertSuffixNode(node, 2, 2);
		assertSuffixNodeChildren(node, 'a', 'd');
		
		node = root.getChildren()['d'];
		assertSuffixNode(node, 9, 10);
		
		node = root.getChildren()['x'];
		assertSuffixNode(node, 5, 10);
		
		node = root.getChildren()['$'];
		assertSuffixNode(node, 10, 10);
	}
	
	void assertSuffixNode(SuffixNode node, int startIndex, int endIndex) {
		assertEquals(startIndex, (int) node.getStartIndex());
		assertEquals(endIndex, node.getEndIndex().intValue());
	}

	void assertSuffixNodeChildren(SuffixNode node, char... children) {
		Arrays.asList(children);
		
		for (int i = 0; i < 256; i++) {
			boolean nullTest = true;
			for (char c : children) {
				if(i == c) {
					nullTest = false;
					assertNotNull(node.getChildren()[i]);
					break;
				}
			}
			
			if(nullTest) {
				assertNull(node.getChildren()[i]);
			}						
		}
	}
}
