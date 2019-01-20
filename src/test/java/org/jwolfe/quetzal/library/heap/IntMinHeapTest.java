package org.jwolfe.quetzal.library.heap;

import org.junit.jupiter.api.Test;

class IntMinHeapTest {

    @Test
    void insert() {
        IntMinHeap heap = new IntMinHeap(11);

        heap.insert(new int[] {3, 2, 1, 15, 5, 4, 45});
        heap.printHeap();
    }

    @Test
    void delete() {
        IntMinHeap heap = new IntMinHeap(11);

        heap.insert(new int[] {3, 2, 1, 15, 5, 4, 45});
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
        IntMinHeap heap = new IntMinHeap(11);

        heap.insert(new int[] {3, 2, 1, 15, 5, 4, 45});
        heap.printHeap();

        int min = heap.extractMin();
        while(min != Integer.MAX_VALUE) {
            System.out.print(min + "\t");
            min = heap.extractMin();
        }

        System.out.println();
    }
}