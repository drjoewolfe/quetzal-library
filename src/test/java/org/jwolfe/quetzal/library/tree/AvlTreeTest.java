package org.jwolfe.quetzal.library.tree;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.tree.AvlTree;
import static org.junit.jupiter.api.Assertions.*;

class AvlTreeTest {

    @Test
    void insert() {
        AvlTree tree = new AvlTree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        tree.visitPreOrder();
    }

    @Test
    void search() {
        AvlTree tree = new AvlTree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        assertEquals(true, tree.search(10));
        assertEquals(true, tree.search(40));
        assertEquals(true, tree.search(25));
        assertEquals(false, tree.search(15));
    }

    @Test
    void getFloor() {
        AvlTree tree = new AvlTree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        assertEquals(10, tree.getFloor(20));
        assertEquals(20, tree.getFloor(25));
        assertEquals(50, tree.getFloor(60));
        assertEquals(Integer.MAX_VALUE, tree.getFloor(9));
    }
}