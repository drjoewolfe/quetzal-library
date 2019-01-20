package org.jwolfe.quetzal.library.heap;

import java.util.ArrayList;
import java.util.List;

public class MinHeap<T extends Comparable> {
    int capacity;
    int heapSize;
    List<T> elements;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.heapSize = 0;
        this.elements = new ArrayList<>(capacity);
    }

    public void insert(T[] keys) {
        if (keys == null) {
            return;
        }

        for (T key : keys) {
            insert(key);
        }
    }

    public void insert(T key) {
        if (key == null) {
            return;
        }

        if (heapSize == capacity) {
            return;
        }

        heapSize++;

        int index = heapSize - 1;
        elements.add(key);
        while (index > 0
                && elements.get(index).compareTo(elements.get(parent(index))) < 0) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    public void delete(T key) {
        if (key == null) {
            return;
        }

        // Find key
        int index = -1;
        for (int i = 0; i < heapSize; i++) {
            if (elements.get(i) == key) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            return;
        }

        if (index == heapSize - 1) {
            elements.remove(heapSize - 1);
            heapSize--;
        } else {
            elements.set(index, elements.get(heapSize - 1));

            if (elements.get(index).compareTo(elements.get(parent(index))) < 0) {
                // Fix up
                while (index > 0
                        && elements.get(index).compareTo(elements.get(parent(index))) < 0) {
                    swap(index, parent(index));
                    index = parent(index);
                }
            } else {
                // Fix down
                minHeapify(index);
            }
        }
    }

    public void printHeap() {
        for (int i = 0; i < heapSize; i++) {
            System.out.print(elements.get(i) + "\t");
        }

        System.out.println();
    }

    public T getMin() {
        if (heapSize == 0) {
            return null;
        }

        return elements.get(0);
    }

    public T extractMin() {
        if (heapSize <= 0) {
            return null;
        }

        T min = elements.get(0);
        elements.set(0, elements.get(heapSize - 1));
        elements.remove(heapSize - 1);
        heapSize--;
        minHeapify(0);

        return min;
    }

    private void minHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest = i;

        if (l < heapSize
                && elements.get(l).compareTo(elements.get(smallest)) < 0) {
            smallest = l;
        }

        if (r < heapSize
                && elements.get(r).compareTo(elements.get(smallest)) < 0) {
            smallest = r;
        }

        if (i != smallest) {
            swap(i, smallest);
            minHeapify(smallest);
        }
    }

    private void decreaseKey(int index, T key) {
        elements.set(index, key);

        while (index > 0
                && elements.get(index).compareTo(elements.get(parent(index))) < 0) {
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
        T temp = elements.get(i);
        elements.set(i, elements.get(j));
        elements.set(j, temp);
    }
}