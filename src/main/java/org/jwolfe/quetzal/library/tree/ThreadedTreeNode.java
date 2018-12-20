package org.jwolfe.quetzal.library.tree;

public class ThreadedTreeNode {

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public ThreadedTreeNode getLeft() {
        return left;
    }

    public void setLeft(ThreadedTreeNode left) {
        this.left = left;
    }

    public ThreadedTreeNode getRight() {
        return right;
    }

    public void setRight(ThreadedTreeNode right) {
        this.right = right;
    }

    public ThreadedTreeNode getParent() {
        return parent;
    }

    protected int data;
    protected ThreadedTreeNode left;
    protected ThreadedTreeNode right;
    protected ThreadedTreeNode parent;

    public boolean isThreaded() {
        return isThreaded;
    }

    public void setThreaded(boolean threaded) {
        isThreaded = threaded;
    }

    boolean isThreaded;

    public ThreadedTreeNode() {

    }

    public ThreadedTreeNode(int data) {
        this.data = data;
    }
}
