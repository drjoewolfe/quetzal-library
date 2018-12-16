package org.jwolfe.quetzal.library.tree;

import java.util.function.BiFunction;

public class RangeSumSegmentTree extends SegmentTree {
    public RangeSumSegmentTree() {

    }

    public void buildForRangeSum(int[] arr) {
        BiFunction<Integer, Integer, Integer> aggregator = (x, y) -> x + y;
        build(arr, aggregator);
    }

    public int getSum(int indexStart, int indexEnd) {
        BiFunction<Integer, Integer, Integer> aggregator = (x, y) -> x + y;

        return query(indexStart, indexEnd, 0, array.length - 1, 0, aggregator, 0);
    }

    public void updateValue(int index, int newValue) {
        int n = array.length;

        if(index < 0 || index > n) {
            // Invalid input
            return;
        }

        int diff = newValue - array[index];
        array[index] = newValue;

        updateValue(index, diff, 0, n-1, 0);
    }

    private void updateValue(int index, int diff, int segmentStart, int segmentEnd, int segmentIndex) {
        if (index < segmentStart || index > segmentEnd) {
            return;
        }

        tree[segmentIndex] += diff;
        if (segmentStart != segmentEnd) {
            int mid = (segmentStart + segmentEnd) / 2;
            updateValue(index, diff, segmentStart, mid, 2 * segmentIndex + 1);
            updateValue(index, diff, mid + 1, segmentEnd, 2 * segmentIndex + 2);
        }
    }
}
