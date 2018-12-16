package org.jwolfe.quetzal.library.tree;

public class RedBlackNode  {
    enum Color {Red, Black}

    protected Color color;

    protected int data;

    protected RedBlackNode left;

    protected RedBlackNode right;

    protected RedBlackNode parent;

    public RedBlackNode(int data) {
        this.data = data;
    }

    public RedBlackNode(int data, Color color) {
        this.data = data;
        this.color = color;
    }
}