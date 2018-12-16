package org.jwolfe.quetzal.library.tree;

import java.util.Arrays;

public class LazyRangeSumSegmentTree {
    int[] tree;
    int[] lazy;
    int[] array;

    public LazyRangeSumSegmentTree() {

    }

    public void build(int[] arr) {
        if(arr == null) {
            return;
        }

        int n = arr.length;
        int depth = (int) Math.ceil(Math.log(n)) + 1;
        int size = 2 * (int) Math.pow(2, depth) + 1;

        array = arr;
        tree = new int[size];
        lazy = new int[size];
        Arrays.fill(lazy, 0);

        build(arr, 0, n - 1, 0);
    }

    private void build(int[] arr, int segmentStart, int segmentEnd, int segmentIndex) {
        if(segmentStart > segmentEnd) {
            return;
        }

        if(segmentStart == segmentEnd) {
            tree[segmentIndex] = arr[segmentStart];
        }
        else {
            int mid = (segmentStart + segmentEnd) / 2;
            int leftChildSegmentIndex =2* segmentIndex + 1;
            int rightChildSegmentIndex = 2* segmentIndex + 2;

            build(arr, segmentStart, mid, leftChildSegmentIndex);
            build(arr, mid+1, segmentEnd, rightChildSegmentIndex);

            tree[segmentIndex] = tree[leftChildSegmentIndex] + tree[rightChildSegmentIndex];
        }
    }

    public int getSum(int queryStart, int queryEnd) {
        if(queryStart < 0
                || queryEnd > array.length - 1) {
            return -1;
        }

        return getSum(queryStart, queryEnd, 0, array.length - 1, 0);
    }

    private int getSum(int queryStart, int queryEnd, int segmentStart, int segmentEnd, int segmentIndex) {
        int leftChildSegmentIndex = 2 * segmentIndex + 1;
        int rightChildSegmentIndex = 2 * segmentIndex + 2;

        if(lazy[segmentIndex] > 0) {
            // Apply pending changes
            tree[segmentIndex] += (segmentEnd - segmentStart + 1) * lazy[segmentIndex];
            if(segmentStart != segmentEnd) {
                lazy[leftChildSegmentIndex] += lazy[segmentIndex];
                lazy[rightChildSegmentIndex] += lazy[segmentIndex];
            }

            lazy[segmentIndex] = 0;
        }

        if(segmentStart > segmentEnd) {
            return 0;
        }

        if (queryStart <= segmentStart
                && queryEnd >= segmentEnd) {
            return tree[segmentIndex];
        } else if (queryStart > segmentEnd
                || queryEnd < segmentStart) {
            return 0;
        } else {
            int mid = (segmentStart + segmentEnd) / 2;
            return getSum(queryStart, queryEnd, segmentStart, mid, leftChildSegmentIndex)
                    + getSum(queryStart, queryEnd, mid + 1, segmentEnd, rightChildSegmentIndex);
        }
    }

    public void updateRange(int rangeStart, int rangeEnd, int diff) {
        updateRange(rangeStart, rangeEnd, diff, 0, array.length - 1, 0);
    }

    public void updateRange(int rangeStart, int rangeEnd, int diff, int segmentStart, int segmentEnd, int segmentIndex) {
        int leftChildSegmentIndex = 2 * segmentIndex + 1;
        int rightChildSegmentIndex = 2 * segmentIndex + 2;

        if(lazy[segmentIndex] > 0) {
            // Apply pending changes
            tree[segmentIndex] = (segmentEnd - segmentStart + 1) * lazy[segmentIndex];
            if(segmentStart != segmentEnd) {
                lazy[leftChildSegmentIndex] += lazy[segmentIndex];
                lazy[rightChildSegmentIndex] += lazy[segmentIndex];
            }

            lazy[segmentIndex] = 0;
        }

        if (rangeStart > segmentEnd
                || rangeEnd < segmentStart) {
            // Out of range
            return;
        }

        if(rangeStart >= segmentStart
                && segmentEnd <= rangeEnd) {
            // Completely in range
            tree[segmentIndex] = (segmentEnd - segmentStart + 1) * diff;
            if(segmentStart != segmentEnd) {
                lazy[leftChildSegmentIndex] += diff;
                lazy[rightChildSegmentIndex] += diff;
            }

            return;
        }

        // Not completely in range
        int mid = (segmentStart + segmentEnd) / 2;
        updateRange(rangeStart, rangeEnd, diff, segmentStart, mid, leftChildSegmentIndex);
        updateRange(rangeStart, rangeEnd, diff, mid + 1, segmentEnd, rightChildSegmentIndex);
        tree[segmentIndex] = tree[leftChildSegmentIndex] + tree[rightChildSegmentIndex];
    }
}

