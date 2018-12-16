package org.jwolfe.quetzal.library.tree;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RangeSumSegmentTreeTest {
    @Test
    void rangeSum() {
        int[] arr;
        int sum;

        arr = Utilities.constructArray(1, 3, 5, 7, 9, 11);
        System.out.println(Arrays.toString(arr));

        RangeSumSegmentTree tree = new RangeSumSegmentTree();
        tree.buildForRangeSum(arr);
        System.out.println(Arrays.toString(tree.tree));
        tree.printTree();

        sum = tree.getSum(1, 3);
        System.out.println(sum);
        assertEquals(15, sum);

        tree.updateValue(1, 10);
        System.out.println(Arrays.toString(tree.tree));
        tree.printTree();

        sum = tree.getSum(1, 3);
        System.out.println(sum);
        assertEquals(22, sum);
    }
}