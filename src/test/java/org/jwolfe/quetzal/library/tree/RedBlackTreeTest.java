package org.jwolfe.quetzal.library.tree;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.tree.RedBlackTree;

class RedBlackTreeTest {
    @Test
    void insert() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(7);
        tree.insert(6);
        tree.insert(5);
        tree.insert(4);
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);

        tree.visitPreOrder();
        System.out.println();

        tree.visitInOrder();
        System.out.println();

        tree.visitPostOrder();
        System.out.println();

        System.out.println();
        tree.printTree();
        System.out.println();
    }
}