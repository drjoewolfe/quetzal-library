package org.jwolfe.quetzal.library.heap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinHeapTest {
    @Test
    void insert() {
        MinHeap<Integer> heap = new MinHeap<>(11);

        heap.insert(new Integer[]{3, 2, 1, 15, 5, 4, 45});
        heap.printHeap();
    }

    @Test
    void delete() {
        MinHeap<Integer> heap = new MinHeap<>(11);

        heap.insert(new Integer[]{3, 2, 1, 15, 5, 4, 45});
        heap.printHeap();

        heap.delete(1);
        heap.printHeap();

        heap.delete(45);
        heap.printHeap();

        heap.delete(4);
        heap.printHeap();
    }

    @Test
    void extractMin() {
        MinHeap<Integer> heap = new MinHeap<>(11);

        heap.insert(new Integer[]{3, 2, 1, 15, 5, 4, 45});
        heap.printHeap();

        Integer min = heap.extractMin();
        while (min != null) {
            System.out.print(min + "\t");
            min = heap.extractMin();
        }

        System.out.println();
    }
}