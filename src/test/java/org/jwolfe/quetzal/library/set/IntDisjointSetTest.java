package org.jwolfe.quetzal.library.set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntDisjointSetTest {
    @Test
    void IntDisjointSetTest() {
        IntDisjointSet set = new IntDisjointSet(9);

        for (int i = 0; i < 9; i++) {
           assertEquals(i, set.find(i));
        }

        set.union(3, 4);
        assertEquals(3, set.find(3));
        assertEquals(3, set.find(4));

        set.union(1, 2);
        assertEquals(1, set.find(1));
        assertEquals(1, set.find(2));

        set.union(5, 6);
        assertEquals(5, set.find(5));
        assertEquals(5, set.find(6));

        set.union(6, 8);
        assertEquals(5, set.find(5));
        assertEquals(5, set.find(6));
        assertEquals(5, set.find(8));

        set.union(2, 8);
        assertEquals(1, set.find(1));
        assertEquals(1, set.find(2));
        assertEquals(1, set.find(5));
        assertEquals(1, set.find(6));
        assertEquals(1, set.find(8));

        assertEquals(3, set.find(3));
        assertEquals(3, set.find(4));

        assertEquals(7, set.find(7));
    }
}