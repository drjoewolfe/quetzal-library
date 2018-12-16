package org.jwolfe.quetzal.library.tree;

public class AvlTree {
    BinaryTreeNode root;

    public void insert(int key) {
        this.root = insert(root, key);
    }

    private BinaryTreeNode insert(BinaryTreeNode node, int key) {
        if(node == null) {
            return new BinaryTreeNode(key);
        }

        if(node.data > key) {
            node.left = insert(node.left, key);
        }
        else if(node.data < key) {
            node.right = insert(node.right, key);
        }
        else {
            // Duplicates not allowed
            return node;
        }

        int balance = getBalance(node);

        if(balance > 1) {
            // Left

            if(node.left.data > key) {
                // Left - Left
                return rightRotate(node);
            }
            else {
                // Left - Right
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }

        }
        else if(balance < -1) {
            // Right

            if(node.right.data < key) {
                // Right - Right
                return leftRotate(node);
            }
            else {
                // Right - Left
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }

        return node;
    }

    private BinaryTreeNode leftRotate(BinaryTreeNode x) {
        var y = x.right;
        var T1 = y.left;

        y.left = x;
        x.right = T1;

        return y;
    }

    private BinaryTreeNode rightRotate(BinaryTreeNode y) {
        var x = y.left;
        var T2 = x.right;

        x.right = y;
        y.left = T2;

        return x;
    }

    public boolean search(int key) {
        return search(root, key);
    }

    private boolean search(BinaryTreeNode node, int key) {
        if(node == null) {
            return false;
        }

        if(node.data == key) {
            return true;
        }
        else if(node.data >= key) {
            return search(node.left, key);
        }
        else {
            return search(node.right, key);
        }
    }

    public int getFloor(int key) {
        return getFloor(root, key);
    }

    private int getFloor(BinaryTreeNode node, int key) {
        if(node == null) {
            return Integer.MAX_VALUE;
        }

        if(node.data >= key) {
            return getFloor(node.getLeft(), key);
        }

        int rightFloor = getFloor(node.getRight(), key);
        return rightFloor < key ? rightFloor : node.data;
    }

    public void visitPreOrder() {
        visitPreOrder(root);
    }

    private void visitPreOrder(BinaryTreeNode node) {
        if(node == null)
            return;

        System.out.print(node.getData() + " ");
        visitPreOrder(node.getLeft());
        visitPreOrder(node.getRight());
    }

    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(BinaryTreeNode node) {
        if(node == null) {
            return 0;
        }

        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    public int getBalance() {
        return getBalance(root);
    }

    private int getBalance(BinaryTreeNode node) {
        if(node == null) {
            return 0;
        }

        return getHeight(node.left) - getHeight(node.right);
    }
}

