package org.jwolfe.quetzal.library.stack;

public class TwoStacks {
    int[] arr;
    int size;

    int top1;
    int top2;

    public TwoStacks(int size) {
        this.size = size;
        this.arr = new int[size];
        this.top1 = -1;
        this.top2 = size;
    }

    public boolean push1(int value) {
        if(top1 >= top2 - 1) {
            return false;
        }

        arr[++top1] = value;
        return true;
    }

    public boolean push2(int value) {
        if(top1 >= top2 - 1) {
            return false;
        }

        arr[--top2] = value;
        return false;
    }

    public int pop1() {
        if(top1 == -1) {
            return -1;
        }

        return arr[top1--];
    }

    public int pop2() {
        if(top2 == size) {
            return -1;
        }

        return arr[top2++];
    }
}
