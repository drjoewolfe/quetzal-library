package org.jwolfe.quetzal.library.set;

public class IntDisjointSet {

    int[] parent;
    int size;

    public IntDisjointSet(int size) {
        this.size = size;
        parent = new int[size];
    }

    public boolean makeSet(int i) {
        if (i >= size) {
            return false;
        }

        parent[i] = i;
        return true;
    }

    public int find(int i) {
        if (i >= size) {
            return Integer.MIN_VALUE;
        }

        if (i == parent[i]) {
            return i;
        }

        parent[i] = find(parent[i]);
        return parent[i];
    }

    public void union(int i, int j) {
        if (i >= size || j >= size || i == j) {
            return;
        }

        int iRoot = find(i);
        int jRoot = find(j);

        parent[jRoot] = iRoot;
    }
}
