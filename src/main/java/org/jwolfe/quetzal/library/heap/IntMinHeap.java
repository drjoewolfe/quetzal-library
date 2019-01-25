package org.jwolfe.quetzal.library.heap;

public class IntMinHeap {
    int capacity;
    int heapSize;
    int[] elements;

    public IntMinHeap(int capacity) {
        this.capacity = capacity;
        this.heapSize = 0;
        this.elements = new int[capacity];
    }

    public void insert(int[] keys) {
        for (int key : keys) {
            insert(key);
        }
    }

    public void insert(int key) {
        if (heapSize == capacity) {
            return;
        }

        heapSize++;

        int index = heapSize - 1;
        elements[index] = key;
        while (index > 0
                && elements[index] < elements[parent(index)]) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    public void delete(int key) {
        // find key
        int index = -1;
        for (int i = 0; i < heapSize; i++) {
            if (elements[i] == key) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            return;
        }

        decreaseKey(index, Integer.MIN_VALUE);
        extractMin();
    }

    public void printHeap() {
        for (int i = 0; i < heapSize; i++) {
            System.out.print(elements[i] + "\t");
        }

        System.out.println();
    }

    public int getMin() {
        if (heapSize == 0) {
            return Integer.MAX_VALUE;
        }

        return elements[0];
    }

    public int extractMin() {
        if (heapSize <= 0) {
            return Integer.MAX_VALUE;
        }

        int min = elements[0];
        elements[0] = elements[heapSize - 1];
        heapSize--;
        minHeapify(0);

        return min;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    private void minHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest = i;

        if (l < heapSize
                && elements[l] < elements[smallest]) {
            smallest = l;
        }

        if (r < heapSize
                && elements[r] < elements[smallest]) {
            smallest = r;
        }

        if (i != smallest) {
            swap(i, smallest);
            minHeapify(smallest);
        }

    }

    private void decreaseKey(int index, int key) {
        elements[index] = key;

        while (index > 0
                && elements[index] < elements[parent(index)]) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    private void swap(int i, int j) {
        int temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
}
