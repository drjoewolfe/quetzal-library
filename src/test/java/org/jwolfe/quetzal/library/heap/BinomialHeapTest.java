package org.jwolfe.quetzal.library.heap;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.heap.BinomialHeap;

import static org.junit.jupiter.api.Assertions.*;

class BinomialHeapTest {

    @Test
    void insert() {
        BinomialHeap heap;
        int min;

        heap = new BinomialHeap();
        heap.insert(10);
        heap.printHeap();
        heap.insert(20);
        heap.printHeap();
        heap.insert(30);
        heap.printHeap();
        heap.insert(40);
        heap.printHeap();
        heap.insert(50);
        heap.printHeap();
        heap.insert(60);
        heap.printHeap();
        heap.insert(70);
        heap.printHeap();
        heap.insert(80);
        heap.printHeap();
        heap.insert(90);
        heap.printHeap();
        heap.insert(100);
        heap.printHeap();

        heap = new BinomialHeap();
        heap.insert(new int[]{10, 20, 30});
        heap.printHeap();
        min = heap.getMin();
        assertEquals(10, min);
        min = heap.extractMin();
        assertEquals(10, min);
        heap.printHeap();
        min = heap.extractMin();
        assertEquals(20, min);
        heap.printHeap();


        heap = new BinomialHeap();
        heap.insert(new int[]{12, 7, 25, 15, 28, 33, 41, 18, 3, 27, 6, 8, 29, 10, 44, 30, 23, 22, 48, 31, 17, 45, 32, 24, 50, 55});
        heap.printHeap();
        min = heap.getMin();
        assertEquals(3, min);
        min = heap.extractMin();
        assertEquals(3, min);
        heap.printHeap();
        min = heap.extractMin();
        assertEquals(6, min);
        heap.printHeap();
    }
}