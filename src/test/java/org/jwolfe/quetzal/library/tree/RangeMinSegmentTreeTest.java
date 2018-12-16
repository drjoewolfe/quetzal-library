package org.jwolfe.quetzal.library.tree;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RangeMinSegmentTreeTest {
    @Test
    void rangeMinimum() {
        int[] arr;
        int min;

        arr = Utilities.constructArray(1, 3, 2, 7, 9, 11);
        System.out.println(Arrays.toString(arr));

        RangeMinSegmentTree tree = new RangeMinSegmentTree();
        tree.buildForRangeMinimum(arr);
        System.out.println(Arrays.toString(tree.tree));
        tree.printTree();

        min = tree.getMin(1, 3);
        System.out.println(min);
        assertEquals(2, min);
    }
}