package org.jwolfe.quetzal.library.tree;

import java.util.function.BiFunction;

public abstract class SegmentTree {
    protected int[] tree;
    protected int[] array;

    public SegmentTree() {

    }

    public void build(int[] arr, BiFunction<Integer, Integer, Integer> aggregator) {
        int n = arr.length;
        int depth = (int) Math.ceil(Math.log(n)) + 1;
        int size =  2* (int) Math.pow(2, depth) - 1;

        array = arr;
        tree = new int[size];
        build(arr, 0, n-1, 0, aggregator);
    }

    private int build(int[] arr, int segmentStart, int segmentEnd, int segmentIndex, BiFunction<Integer, Integer, Integer> aggregator) {
        if (segmentStart == segmentEnd) {
            int value = arr[segmentStart];
            tree[segmentIndex] = value;
            return value;
        }

        int mid = (segmentStart + segmentEnd) / 2;
        tree[segmentIndex] = aggregator.apply(
                build(arr, segmentStart, mid, 2 * segmentIndex + 1, aggregator),
                build(arr, mid + 1, segmentEnd, 2 * segmentIndex + 2, aggregator)
        );

        return tree[segmentIndex];
    }

    public int query(int indexStart, int indexEnd, int segmentStart, int segmentEnd, int segmentIndex, BiFunction<Integer, Integer, Integer> aggregator, int outRangeValue) {
        // Range completely covers segment
        if(indexStart <= segmentStart && segmentEnd <= indexEnd) {
            return tree[segmentIndex];
        }

        // Range is outside segment
        if(indexStart > segmentEnd || indexEnd < segmentStart) {
            return outRangeValue;
        }

        // Range partially covers segment
        int mid = (segmentStart + segmentEnd) / 2;
        return aggregator.apply(query(indexStart, indexEnd, segmentStart, mid, 2 * segmentIndex + 1, aggregator, outRangeValue),
                query(indexStart, indexEnd, mid + 1, segmentEnd, 2 * segmentIndex + 2, aggregator, outRangeValue));
    }

    public void printTree() {
        int height = (int) log2(tree.length) + 1;

        for (int i = 1; i <= tree.length; i++) {
            int x = tree[i-1];
            int level = log2(i) + 1;
            int spaces = (height - level + 1) * 2;

            for(int j = 0; j < spaces; j++) {
                System.out.print("\t");
            }

            System.out.print(x);
            if((int)Math.pow(2, level) - 1 == i) {
                System.out.println();
            }
        }
    }

    private int log2(int x) {
        return (int)(Math.log(x) / Math.log(2));
    }
}
