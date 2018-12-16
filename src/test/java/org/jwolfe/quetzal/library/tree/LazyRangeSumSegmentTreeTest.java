package org.jwolfe.quetzal.library.tree;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.utilities.Utilities;

import static org.junit.jupiter.api.Assertions.*;

class LazyRangeSumSegmentTreeTest {

    @Test
    void lazyRangeSum() {
        int[] arr = Utilities.constructArray(1, 3, 5, 7, 9, 11);
        LazyRangeSumSegmentTree tree = new LazyRangeSumSegmentTree();
        tree.build(arr);

        int sum;
        sum = tree.getSum(1, 3);
        assertEquals(15, sum);

        tree.updateRange(1, 5, 10);
        sum = tree.getSum(1, 3);
        assertEquals(45, sum);

        tree.updateRange(1, 5, 5);
        tree.updateRange(1, 5, 2);
        sum = tree.getSum(1, 3);
        assertEquals(66, sum);
    }

}