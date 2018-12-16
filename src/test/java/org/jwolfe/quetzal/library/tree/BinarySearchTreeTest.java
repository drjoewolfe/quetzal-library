package org.jwolfe.quetzal.library.tree;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.tree.BinarySearchTree;

class BinarySearchTreeTest {

    @Test
    void insert() {
        BinarySearchTree tree = new BinarySearchTree();

        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        // print inorder traversal of the BST
        tree.visitInorder();
        System.out.println();
    }

    @Test
    void search() {
        BinarySearchTree tree = new BinarySearchTree();

        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        System.out.println(tree.search(70));
        System.out.println(tree.search(10));
    }

    @Test
    void delete() {
        BinarySearchTree tree = new BinarySearchTree();

        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        tree.visitInorder();
        System.out.println();

        tree.delete(50);
        tree.visitInorder();
        System.out.println();

        tree.delete(30);
        tree.visitInorder();
        System.out.println();

        tree.delete(20);
        tree.visitInorder();
        System.out.println();

        tree.delete(40);
        tree.visitInorder();
        System.out.println();

        tree.delete(70);
        tree.visitInorder();
        System.out.println();

        tree.delete(60);
        tree.visitInorder();
        System.out.println();

        tree.delete(80);
        tree.visitInorder();
        System.out.println();
    }

    @Test
    void balanceTree() {
        BinarySearchTree tree = new BinarySearchTree();

        tree.insert(80);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(50);
        tree.insert(70);
        tree.insert(60);


        System.out.print("Inorder: \t");
        tree.visitInorder();
        System.out.println();
        System.out.print("Preorder: \t");
        tree.visitPreorder();
        System.out.println();
        System.out.print("Postorder: \t");
        tree.visitPostorder();
        System.out.println();

        tree.balanceTree();
        System.out.println();
        System.out.print("Inorder: \t");
        tree.visitInorder();
        System.out.println();
        System.out.print("Preorder: \t");
        tree.visitPreorder();
        System.out.println();
        System.out.print("Postorder: \t");
        tree.visitPostorder();
        System.out.println();
    }

    @Test
    void inOrderSuccessor() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(80);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(50);
        tree.insert(70);
        tree.insert(60);

        System.out.print("Inorder: \t");
        tree.visitInorder();
        System.out.println();

        System.out.println("Inorder successor of 20 is " + tree.inOrderSuccessor(10));
        System.out.println("Inorder successor of 20 is " + tree.inOrderSuccessor(20));
        System.out.println("Inorder successor of 30 is " + tree.inOrderSuccessor(30));
        System.out.println("Inorder successor of 40 is " + tree.inOrderSuccessor(40));
        System.out.println("Inorder successor of 50 is " + tree.inOrderSuccessor(50));
        System.out.println("Inorder successor of 60 is " + tree.inOrderSuccessor(60));
        System.out.println("Inorder successor of 70 is " + tree.inOrderSuccessor(70));
        System.out.println("Inorder successor of 80 is " + tree.inOrderSuccessor(80));
    }

    @Test
    void inOrderSuccessorWithoutParentKey() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(80);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(50);
        tree.insert(70);
        tree.insert(60);

        System.out.print("Inorder: \t");
        tree.visitInorder();
        System.out.println();

        System.out.println("Inorder successor of 20 is " + tree.inOrderSuccessorWithoutParentKey(10));
        System.out.println("Inorder successor of 20 is " + tree.inOrderSuccessorWithoutParentKey(20));
        System.out.println("Inorder successor of 30 is " + tree.inOrderSuccessorWithoutParentKey(30));
        System.out.println("Inorder successor of 40 is " + tree.inOrderSuccessorWithoutParentKey(40));
        System.out.println("Inorder successor of 50 is " + tree.inOrderSuccessorWithoutParentKey(50));
        System.out.println("Inorder successor of 60 is " + tree.inOrderSuccessorWithoutParentKey(60));
        System.out.println("Inorder successor of 70 is " + tree.inOrderSuccessorWithoutParentKey(70));
        System.out.println("Inorder successor of 80 is " + tree.inOrderSuccessorWithoutParentKey(80));
    }
}