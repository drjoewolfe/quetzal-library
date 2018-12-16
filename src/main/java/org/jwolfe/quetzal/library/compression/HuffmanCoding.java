package org.jwolfe.quetzal.library.compression;

import org.jwolfe.quetzal.library.general.Pair;

import java.util.*;

public class HuffmanCoding {
    class HuffmanNode {
        int frequency;
        char character;

        HuffmanNode left;
        HuffmanNode right;

        public HuffmanNode(int frequency, char character) {
            this.frequency = frequency;
            this.character = character;

            this.left = null;
            this.right = null;
        }
    }

    class HuffmanComparator implements Comparator<HuffmanNode> {
        @Override
        public int compare(HuffmanNode o1, HuffmanNode o2) {
            return o1.frequency - o2.frequency;
        }
    }

    public List<Pair<Character, String>> build(char[] characters, int[] frequencies) {
        int length = characters.length;
        if(length != frequencies.length) {
            return null;
        }

        List<Pair<Character, Integer>> frequencyArray = new ArrayList<>();
        for(int i = 0; i<characters.length; i++) {
            Pair<Character, Integer> pair = new Pair(characters[i], frequencies[i]);
            frequencyArray.add(pair);
        }

        return build(frequencyArray);
    }

    public List<Pair<Character, String>> build(List<Pair<Character, Integer>> frequencyArray) {
        HuffmanNode root = buildHuffmanTree(frequencyArray);
        return collectCodes(root);
    }

    public List<Pair<Character, String>> buildForSortedInput(char[] characters, int[] frequencies) {
        int length = characters.length;
        if(length != frequencies.length) {
            return null;
        }

        List<Pair<Character, Integer>> frequencyArray = new ArrayList<>();
        for(int i = 0; i<characters.length; i++) {
            Pair<Character, Integer> pair = new Pair(characters[i], frequencies[i]);
            frequencyArray.add(pair);
        }

        return buildForSortedInput(frequencyArray);
    }

    public List<Pair<Character, String>> buildForSortedInput(List<Pair<Character, Integer>> frequencyArray) {
        HuffmanNode root = buildHuffmanTreeForSortedInput(frequencyArray);
        return collectCodes(root);
    }

    private HuffmanNode buildHuffmanTree(List<Pair<Character, Integer>> frequencyArray) {
        if(frequencyArray == null
                || frequencyArray.isEmpty()) {
            return null;
        }

        PriorityQueue<HuffmanNode> minHeap = new PriorityQueue<>(new HuffmanComparator());

        for(var item : frequencyArray) {
            HuffmanNode node = new HuffmanNode(item.getSecond(), item.getFirst());
            minHeap.add(node);
        }

        while(minHeap.size() > 1) {
            var node1 = minHeap.poll();
            var node2 = minHeap.poll();

            var internal = new HuffmanNode(
                    node1.frequency + node2.frequency,
                    '-');
            internal.left = node1;
            internal.right = node2;

            minHeap.offer(internal);
        }

        return minHeap.poll();
    }

    private HuffmanNode buildHuffmanTreeForSortedInput(List<Pair<Character, Integer>> frequencyArray) {
        if(frequencyArray == null
                || frequencyArray.isEmpty()) {
            return null;
        }

        Queue<HuffmanNode> queue1 = new LinkedList<>();
        Queue<HuffmanNode> queue2 = new LinkedList<>();

        for(var pair : frequencyArray) {
            queue1.offer(new HuffmanNode(pair.getSecond(), pair.getFirst()));
        }

        while(!queue1.isEmpty()
                || queue2.size() > 1) {
            HuffmanNode node1 = pollMin(queue1, queue2);
            HuffmanNode node2 = pollMin(queue1, queue2);

            var internal = new HuffmanNode(
                    node1.frequency + node2.frequency,
                    '-');
            internal.left = node1;
            internal.right = node2;

            queue2.offer(internal);
        }

        return queue2.poll();
    }

    private List<Pair<Character, String>> collectCodes(HuffmanNode root) {
        if(root == null) {
            return null;
        }

        List<Pair<Character, String>> codes = new LinkedList<>();
        Stack<Integer> pathStack = new Stack<>();
        collectCodes(root, codes, pathStack);

        return codes;
    }

    private void collectCodes(HuffmanNode node, List<Pair<Character, String>> codes, Stack<Integer> pathStack) {
        if(node == null) {
            return;
        }

        if(isLeaf(node)) {
            Pair<Character, String> code = new Pair<>();
            code.setFirst(node.character);
            code.setSecond(getPathFromPathStack(pathStack));

            codes.add(code);
            return;
        }

        // Left traversal
        pathStack.push(0);
        collectCodes(node.left, codes, pathStack);
        pathStack.pop();

        // Right traversal
        pathStack.push(1);
        collectCodes(node.right, codes, pathStack);
        pathStack.pop();
    }

    private boolean isLeaf(HuffmanNode node) {
        return (node.left == null)
                && (node.right == null);
    }

    private String getPathFromPathStack(Stack<Integer> pathStack) {
        StringBuilder path = new StringBuilder();
        var iterator = pathStack.listIterator();
        while (iterator.hasNext())
        {
            path.append(iterator.next());
        }

        return path.toString();
    }

    private HuffmanNode pollMin(Queue<HuffmanNode> queue1, Queue<HuffmanNode> queue2) {
        if(queue1.isEmpty() && queue2.isEmpty()) {
            return null;
        }

        if(queue1.isEmpty()) {
            return queue2.poll();
        }

        if(queue2.isEmpty()) {
            return queue1.poll();
        }

        if(queue1.peek().frequency < queue2.peek().frequency) {
            return queue1.poll();
        }
        else  {
            return queue2.poll();
        }
    }
}
