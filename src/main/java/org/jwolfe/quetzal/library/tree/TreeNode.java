package org.jwolfe.quetzal.library.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    protected T data;


    public TreeNode<T> getParent() {
        return parent;
    }

    protected TreeNode<T> parent;


    public List<TreeNode<T>> getChildren() {
        return children;
    }

    protected List<TreeNode<T>> children;

    protected TreeNode() {
        children = new ArrayList<>();
    }

    public TreeNode(T data) {
        this();
        this.data = data;
    }

    public void addChild(TreeNode<T> child) {
        if(!this.children.contains(child)) {
            this.children.add(child);
            child.parent = this;
        }
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "data=" + data +
                '}';
    }
}
