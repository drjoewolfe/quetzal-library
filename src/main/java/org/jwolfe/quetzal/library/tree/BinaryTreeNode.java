package org.jwolfe.quetzal.library.tree;

public class BinaryTreeNode {
    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    public BinaryTreeNode getParent() {
        return parent;
    }
    
    public void setParent(BinaryTreeNode parent) {
        this.parent = parent;
    }

    protected int data;
    protected BinaryTreeNode left;
    protected BinaryTreeNode right;
    protected BinaryTreeNode parent;

    protected BinaryTreeNode() {

    }

    public BinaryTreeNode(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}
