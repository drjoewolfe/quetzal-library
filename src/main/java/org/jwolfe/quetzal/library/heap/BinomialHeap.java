package org.jwolfe.quetzal.library.heap;

public class BinomialHeap {
    class BinomialHeapNode {
        int data;
        int degree;

        BinomialHeapNode child;
        BinomialHeapNode sibling;
        BinomialHeapNode parent;

        public BinomialHeapNode(int data) {
            this.data = data;
            this.degree = 0;
            this.child = null;
            this.sibling = null;
            this.parent = null;
        }
    }

    BinomialHeapNode head;

    public BinomialHeap() {
        this.head = null;
    }

    public void insert(int[] keys) {
        for(int key : keys) {
            insert(key);
        }
    }

    public void insert(int key) {
        if(key < 0) {
            return;
        }

        var node = new BinomialHeapNode(key);
        if(head == null) {
            head = node;
            return;
        }

        unionNodes(node);
    }

    public void unionNodes(BinomialHeapNode node) {
        merge(node);

        BinomialHeapNode previous = null;
        BinomialHeapNode current = head;
        BinomialHeapNode next = current.sibling;

        while (next != null) {
            if(current.degree != next.degree) {
                // Case 1: degrees not equal. Proceed.
                previous = current;
                current = next;
                next = next.sibling;
            }
            else {
                // Degrees of current & next are equal
                if(next.sibling != null
                        && next.degree == next.sibling.degree) {
                    // Case 2: degrees of current, next & next-next equal. Proceed
                    previous = current;
                    current = next;
                    next = next.sibling;
                }
                else if(current.data <= next.data) {
                    // Case 3: degrees of current & next equal. current's data < next's data
                    current.sibling = next.sibling;
                    next.sibling = current.child;
                    current.child = next;
                    current.degree++;

                    next = current.sibling;
                }
                else {
                    // Case 3: degrees of current & next equal. current's data > next's data
                    if(previous == null) {
                        head = next;
                    }
                    else {
                        previous.sibling = next;
                    }

                    current.sibling = next.child;
                    next.child = current;
                    next.degree++;

                    current = next;
                    next = current.sibling;
                }
            }
        }
    }

    private void merge(BinomialHeapNode node) {
        BinomialHeapNode heap1Runner = head;
        BinomialHeapNode heap1Previous = null;
        BinomialHeapNode heap2Runner = node;

        while(heap1Runner != null
                && heap2Runner != null) {

            if(heap1Runner.degree == heap2Runner.degree) {
                var temp1 = heap1Runner.sibling;
                var temp2 = heap2Runner.sibling;
                mergeInBetween(heap1Runner, heap2Runner, heap1Runner.sibling);
                heap1Previous = heap1Runner;
                heap1Runner = temp1;
                heap2Runner = temp2;
            }
            else if(heap1Runner.degree < heap2Runner.degree) {
                if(heap1Runner.sibling == null
                        || heap1Runner.sibling.degree > heap2Runner.degree) {
                    var temp1 = heap1Runner.sibling;
                    var temp2 = heap2Runner.sibling;
                    mergeInBetween(heap1Runner, heap2Runner, heap1Runner.sibling);
                    heap1Previous = heap1Runner;
                    heap1Runner = temp1;
                    heap2Runner = temp2;
                }
                else {
                    heap1Runner = heap1Runner.sibling;
                }
            }
            else {
                BinomialHeapNode temp1 = heap1Runner.sibling;
                BinomialHeapNode temp2 = heap2Runner.sibling;
                if(heap1Previous != null) {
                    mergeInBetween(heap1Previous, heap2Runner, heap1Runner);
                }
                else {
                    mergeInBetween(heap2Runner, heap1Runner, heap1Runner.sibling);
                    head = heap2Runner;
                }

                heap1Previous = heap1Runner;
                heap1Runner = temp1;
                heap2Runner = temp2;
            }
        }

        if(heap1Runner == null) {
            heap1Runner = head;
            while (heap1Runner.sibling != null) {
                heap1Runner = heap1Runner.sibling;
            }
            heap1Runner.sibling = heap2Runner;
        }
    }

    private void mergeInBetween(BinomialHeapNode firstNode, BinomialHeapNode betweenNode, BinomialHeapNode lastNode) {
        firstNode.sibling = betweenNode;
        betweenNode.sibling = lastNode;
    }

    public int getMin() {
        var minNode = getMinNode();
        if(minNode == null) {
            return -1;
        }

        return minNode.data;
    }

    public BinomialHeapNode getMinNode() {
        if(head == null) {
            return null;
        }

        var runner = head;
        int min = runner.data;
        BinomialHeapNode minNode = runner;
        while(runner.sibling != null) {
            runner = runner.sibling;
            if(min > runner.data) {
                min = runner.data;
                minNode = runner;
            }
        }

        return minNode;
    }

    public int extractMin() {
        if (head == null) {
            return -1;
        }

        BinomialHeapNode previous = null;
        BinomialHeapNode current = head;
        BinomialHeapNode minNode = getMinNode();

        while (current.data != minNode.data) {
            previous = current;
            current = current.sibling;
        }

        if (previous == null) {
            head = current.sibling;
        } else {
            previous.sibling = current.sibling;
        }

        current = current.child;
        BinomialHeapNode fake = current;

        while (current != null) {
            current.parent = null;
            current = current.sibling;
        }

        if ((head == null) && (fake == null)) {

        } else {
            if ((head == null) && (fake != null)) {
                head = reverse(fake, null);
            } else {
                if ((head != null) && (fake == null)) {

                } else {
                    unionNodes(reverse(fake, null));
                }
            }
        }

        return minNode.data;
    }

    public void printHeap() {
        printHeap(head);
        System.out.println();
    }

    private void printHeap(BinomialHeapNode node) {
        while(node != null) {
            System.out.print(node.data + "\t");
            printHeap(node.child);
            node = node.sibling;
        }
    }

    private BinomialHeapNode reverse(BinomialHeapNode node, BinomialHeapNode previousNode) {
        BinomialHeapNode returnNode = null;
        if(node.sibling != null) {
            returnNode = reverse(node.sibling, node);
        }
        else {
            returnNode = node;
        }

        node.sibling = previousNode;
        return returnNode;
    }
}
