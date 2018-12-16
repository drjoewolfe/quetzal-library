package org.jwolfe.quetzal.library.tree;

import java.util.ArrayList;
import java.util.function.Consumer;

public class BinarySearchTree {
    public BinaryTreeNode getRoot() {
        return root;
    }

    public void setRoot(BinaryTreeNode root) {
        this.root = root;
    }

    BinaryTreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    public void insertList(int... list) {
        for(int key : list) {
            insert(key);
        }
    }

    public void insert(int key) {
        root = insertRecursive(root, key);
    }

    private BinaryTreeNode insertRecursive(BinaryTreeNode node, int key) {
        if (node == null) {
            return new BinaryTreeNode(key);
        }

        if(key <= node.data) {
            node.left = insertRecursive(node.left, key);
            node.left.parent = node;
        }
        else {
            node.right = insertRecursive(node.right, key);
            node.right.parent = node;
        }

        return node;
    }

    public boolean search(int key) {
        return (searchRecursive(root, key) != null);
    }

    private BinaryTreeNode searchRecursive(BinaryTreeNode node, int key) {
        if(node == null)
            return  null;

        if(node.data == key)
            return  node;

        if(node.data > key)
            return searchRecursive(node.left, key);

        return searchRecursive(node.right, key);
    }

    public void delete(int key) {
        root = deleteRecursive(root, key);
    }

    private BinaryTreeNode deleteRecursive(BinaryTreeNode node, int key) {
        if(node == null)
            return  null;

        if(node.data > key) {
            node.left = deleteRecursive(node.left, key);
        }
        else if(node.data < key) {
            node.right = deleteRecursive(node.right, key);
        }
        else {
            if(node.left == null) {
                return  node.right;
            }

            if(node.right == null) {
                return  node.left;
            }

            var successor = getMinNode(node.right);
            node.data = successor.data;
            node.right = deleteRecursive(node.right, successor.data);
        }

        return node;
    }

    private BinaryTreeNode getMinNode(BinaryTreeNode node) {
        var successor = node;
        while (node.left != null) {
            successor = node.left;
            node = node.left;
        }

        return successor;
    }

    public int inOrderSuccessor(int key) {
        // Find head for key
        var keyNode = searchRecursive(root, key);
        if(keyNode == null)
            return  -1;

        // Find successor
        if(keyNode.right != null) {
            var successorNode = getMinNode(keyNode.right);
            return successorNode.data;
        }
        else {
            var parent = keyNode.parent;
            while(parent != null
                    && keyNode == parent.right) {

                keyNode = parent;
                parent = parent.parent;
            }

            if(parent != null)
                return parent.data;
        }

        return -1;
    }

    public int inOrderSuccessorWithoutParentKey(int key) {
        // Find head for key
        var keyNode = searchRecursive(root, key);
        if(keyNode == null)
            return  -1;

        // Find successor
        if(keyNode.right != null) {
            var successorNode = getMinNode(keyNode.right);
            return successorNode.data;
        }

        BinaryTreeNode successorNode = null;
        var node = root;
        while(node != null) {
            if(key < node.data) {
                successorNode = node;
                node = node.left;
            }
            else if(key > node.data) {
                node = node.right;
            }
            else {
                break;
            }
        }

        if(successorNode != null) {
            return successorNode.data;
        }

        return -1;
    }

    public void balanceTree() {
        ArrayList<BinaryTreeNode> inOrderList = new ArrayList<>();

        var visit = new Consumer<BinaryTreeNode>() {
            @Override
            public void accept(BinaryTreeNode node) {
                inOrderList.add(node);
            }
        };
        visitInorder(visit);

        root = constructFromInorder(inOrderList, 0, inOrderList.size() - 1);
    }

    private BinaryTreeNode constructFromInorder(ArrayList<BinaryTreeNode> inOrderList, int start, int end) {
        if(start > end)
            return  null;

        int mid = (start + end) / 2;
        var node = inOrderList.get(mid);
        node.left = constructFromInorder(inOrderList, start, mid - 1);
        node.right = constructFromInorder(inOrderList, mid + 1, end);

        return node;
    }

    public void visitInorder() {
        var visit = new Consumer<BinaryTreeNode>() {
            @Override
            public void accept(BinaryTreeNode node) {
                System.out.print(node.data + "\t");
            }
        };

        visitInorder(root, visit);
    }

    public void visitInorder(Consumer<BinaryTreeNode> visit) {
        visitInorder(root, visit);
    }

    private void visitInorder(BinaryTreeNode node , Consumer<BinaryTreeNode> visit) {
        if(node == null)
            return;

        visitInorder(node.left, visit);
        if(visit != null)
            visit.accept(node);
        visitInorder(node.right, visit);
    }

    public void visitPreorder() {
        visitPreorder(root);
    }

    private void visitPreorder(BinaryTreeNode node) {
        if(node == null)
            return;

        System.out.print(node.data + "\t");
        visitPreorder(node.left);
        visitPreorder(node.right);
    }

    public void visitPostorder() {
        visitPostorder(root);
    }

    private void visitPostorder(BinaryTreeNode node) {
        if(node == null)
            return;

        visitPostorder(node.left);
        visitPostorder(node.right);
        System.out.print(node.data + "\t");
    }
}

