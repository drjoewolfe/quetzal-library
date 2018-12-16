package org.jwolfe.quetzal.library.tree;

import java.util.function.Consumer;

public class RedBlackTree {
    protected RedBlackNode root;

    public void insert(int key) {
        if(root == null) {
            root = new RedBlackNode(key, RedBlackNode.Color.Black);
            return;
        }

        var node = new RedBlackNode(key, RedBlackNode.Color.Red);
        var runner = root;
        while(true) {
            if (runner.data > key) {
                if(runner.left == null) {
                    node.parent = runner;
                    runner.left = node;
                    break;
                }
                else {
                    runner = runner.left;
                }
            } else {
                if(runner.right == null) {
                    node.parent = runner;
                    runner.right = node;
                    break;
                }
                else {
                    runner = runner.right;
                }
            }
        }

        fixRedBlackTreee(node);
    }

    private void fixRedBlackTreee(RedBlackNode node) {
        while(node.parent != null
                && node.parent.color == RedBlackNode.Color.Red) {
            RedBlackNode parent = node.parent;
            RedBlackNode grandParent = node.parent.parent;
            RedBlackNode uncle = null;

            if(parent == grandParent.left) {
                // Parent is left child
                uncle = grandParent.right;

                if(uncle != null
                        && uncle.color == RedBlackNode.Color.Red) {
                    // Swap colors of uncle / parent with grandparent & continue with grandparent
                    uncle.color = RedBlackNode.Color.Black;
                    parent.color = RedBlackNode.Color.Black;
                    grandParent.color = RedBlackNode.Color.Red;

                    node = grandParent;
                    continue;
                }
                else {
                    if(node == parent.right) {
                        // Left - Right Case
                        node = node.parent;
                        leftRotate(node);
                    }

                    // Left - Left Case
                    parent.color = RedBlackNode.Color.Black;
                    grandParent.color = RedBlackNode.Color.Red;
                    rightRotate(grandParent);
                }
            }
            else {
                // Parent is right child
                uncle = grandParent.left;

                if(uncle != null
                        && uncle.color == RedBlackNode.Color.Red) {
                    // Swap colors of uncle / parent with grandparent & continue with grandparent
                    uncle.color = RedBlackNode.Color.Black;
                    parent.color = RedBlackNode.Color.Black;
                    grandParent.color = RedBlackNode.Color.Red;

                    node = grandParent;
                    continue;
                }
                else {
                    if(node == parent.left) {
                        // Right - Left Case
                        node = node.parent;
                        rightRotate(parent);
                    }

                    // Right - Right Case
                    parent.color = RedBlackNode.Color.Black;
                    grandParent.color = RedBlackNode.Color.Red;
                    leftRotate(grandParent);
                }
            }

        }

        root.color = RedBlackNode.Color.Black;
    }

    private RedBlackNode leftRotate(RedBlackNode node) {
        var x = node;
        var y = x.right;
        var t2 = y.left;
        var z = x.parent;

        y.left = x;
        x.right = t2;

        y.parent = z;
        x.parent = y;
        if (t2 != null) {
            t2.parent = x;
        }

        if(z == null) {
            // update head;
            root = y;
        }
        else {
            if(z.left == x) {
                z.left = y;
            }
            else {
                z.right = y;
            }
        }

        return y;
    }

    private RedBlackNode rightRotate(RedBlackNode node) {
        var y = node;
        var x = y.left;
        var t2 = x.right;
        var z = y.parent;

        x.right = y;
        y.left = t2;

        x.parent = z;
        y.parent = x;
        if (t2 != null) {
            t2.parent = y;
        }

        if(z == null) {
            // update head;
            root = x;
        }
        else {
            if(z.left == y) {
                z.left = x;
            }
            else {
                z.right = x;
            }
        }

        return x;
    }

    public void visitPreOrder() {
        visitPreOrder(root);
    }

    public void visitPreOrder(Consumer<RedBlackNode> visit) {
        visitPreOrder(root, visit);
    }

    private void visitPreOrder(RedBlackNode node) {
        var visit = getGenericVisitRoutine();
        visitPreOrder(node, visit);
    }

    private void visitPreOrder(RedBlackNode node, Consumer<RedBlackNode> visit) {
        if(node == null) {
            return;
        }

        if(visit != null) {
            visit.accept(node);
        }

        visitPreOrder(node.left, visit);
        visitPreOrder(node.right, visit);
    }

    public void visitInOrder() {
        visitInOrder(root);
    }

    public void visitInOrder(Consumer<RedBlackNode> visit) {
        visitInOrder(root, visit);
    }

    private void visitInOrder(RedBlackNode node) {
        var visit = getGenericVisitRoutine();
        visitInOrder(node, visit);
    }

    private void visitInOrder(RedBlackNode node, Consumer<RedBlackNode> visit) {
        if(node == null) {
            return;
        }

        visitInOrder(node.left, visit);

        if(visit != null) {
            visit.accept(node);
        }

        visitInOrder(node.right, visit);
    }

    public void visitPostOrder() {
        visitPostOrder(root);
    }

    public void visitPostOrder(Consumer<RedBlackNode> visit) {
        visitPostOrder(root, visit);
    }

    private void visitPostOrder(RedBlackNode node) {
        var visit = getGenericVisitRoutine();
        visitPostOrder(node, visit);
    }

    private void visitPostOrder(RedBlackNode node, Consumer<RedBlackNode> visit) {
        if(node == null) {
            return;
        }

        visitPostOrder(node.left, visit);
        visitPostOrder(node.right, visit);

        if(visit != null) {
            visit.accept(node);
        }
    }

    public void printTree() {
        printTree(root);
    }

    private void printTree(RedBlackNode node) {
        var visit = getGenericSpacerVisitRoutine();
        printTree(node, visit, 0);
    }

    private void printTree(RedBlackNode node, Consumer<RedBlackNodeWithSpacer> visit, int spacer) {
        // Reverse in-order traversal

        if(node == null) {
            return;
        }

        spacer++;

        printTree(node.right, visit, spacer);
        if(visit != null) {
            visit.accept(new RedBlackNodeWithSpacer(node, spacer));
        }
        printTree(node.left, visit, spacer);

    }

    private Consumer<RedBlackNode> getGenericVisitRoutine() {
        var visit = new Consumer<RedBlackNode>() {
            @Override
            public void accept(RedBlackNode node) {
                System.out.print(node.data + "(" + node.color + ")" + "\t");
            }
        };

        return visit;
    }

    private Consumer<RedBlackNodeWithSpacer> getGenericSpacerVisitRoutine() {
        var visit = new Consumer<RedBlackNodeWithSpacer>() {
            @Override
            public void accept(RedBlackNodeWithSpacer node) {
                for(int i = 1; i<node.spacer; i++) {
                    System.out.print("\t");
                }
                System.out.print(node.node.data + "(" + node.node.color + ")" + "\t");
                System.out.println();
            }
        };

        return visit;
    }

    class RedBlackNodeWithSpacer {
        RedBlackNode node;
        int spacer;

        public RedBlackNodeWithSpacer(RedBlackNode node, int spacer) {
            this.node = node;
            this.spacer = spacer;
        }
    }
}
