package org.jwolfe.quetzal.library.tree;

import java.util.function.BiFunction;

public class RangeMinSegmentTree extends SegmentTree {
    public RangeMinSegmentTree() {

    }

    public void buildForRangeMinimum(int[] arr) {
        BiFunction<Integer, Integer, Integer> aggregator = (x, y) -> Math.min(x, y);
        build(arr, aggregator);
    }

    public int getMin(int indexStart, int indexEnd) {
        BiFunction<Integer, Integer, Integer> aggregator = (x, y) -> Math.min(x, y);

        return query(indexStart, indexEnd, 0, array.length - 1, 0, aggregator, Integer.MAX_VALUE);
    }
}
