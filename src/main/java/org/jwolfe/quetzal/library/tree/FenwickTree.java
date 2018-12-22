package org.jwolfe.quetzal.library.tree;

import java.util.Arrays;

public class FenwickTree {
	int[] tree;

	public void construct(int[] arr) {
		int n = arr.length;

		// The Fenwick tree follows 1-based indexing
		tree = new int[n + 1];
		Arrays.fill(tree, 0);

		for (int i = 0; i < n; i++) {
			update(i, arr[i]);
		}
	}

	public int getSum(int tillIndex) {
		if (tree == null) {
			return Integer.MIN_VALUE;
		}

		int index = tillIndex + 1;
		int sum = 0;
		while (index > 0) {
			sum += tree[index];
			index -= (index & -index);
		}

		return sum;
	}

	public void update(int index, int delta) {
		if (tree == null) {
			return;
		}

		index++;
		while (index < tree.length) {
			tree[index] += delta;
			index += (index & -index);
		}
	}
}
