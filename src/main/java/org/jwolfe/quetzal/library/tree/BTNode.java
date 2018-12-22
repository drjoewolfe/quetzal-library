package org.jwolfe.quetzal.library.tree;

public class BTNode<T> {
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BTNode<T> getLeft() {
        return left;
    }

    public void setLeft(BTNode<T> left) {
        this.left = left;
    }

    public BTNode<T> getRight() {
        return right;
    }

    public void setRight(BTNode<T> right) {
        this.right = right;
    }

    public BTNode<T> getParent() {
        return parent;
    }
    
    public void setParent(BTNode<T> parent) {
        this.parent = parent;
    }

    protected T data;
    protected BTNode<T> left;
    protected BTNode<T> right;
    protected BTNode<T> parent;

    protected BTNode() {

    }

    public BTNode(T data) {
        this.data = data;
        left = null;
        right = null;
    }
}
