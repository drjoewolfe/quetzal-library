package org.jwolfe.quetzal.library.utilities;

import org.jwolfe.quetzal.library.graph.AdjacencyGraph;
import org.jwolfe.quetzal.library.list.DoublyLinkedListNode;
import org.jwolfe.quetzal.library.list.LinkedListNode;
import org.jwolfe.quetzal.library.list.AuxiliaryLinkedListNode;
import org.jwolfe.quetzal.library.general.Pair;
import org.jwolfe.quetzal.library.list.RightDownLinkedListNode;
import org.jwolfe.quetzal.library.tree.BTNode;
import org.jwolfe.quetzal.library.tree.BinaryTreeNode;
import org.jwolfe.quetzal.library.tree.ConnectableBinaryTreeNode;

import java.util.*;

public class Utilities {
	public static void printLinkedList(LinkedListNode head) {
		while (head != null) {
			System.out.print(head.getData() + " ");
			head = head.getNext();
		}

		System.out.println();
	}

	public static LinkedListNode createLinkedList(int... args) {
		return createLinkedListFromArray(args);
	}

	public static LinkedListNode createLinkedListFromArray(int[] arr) {
		LinkedListNode dummy = new LinkedListNode(0);
		LinkedListNode tail = dummy;

		for (int value : arr) {
			tail.setNext(new LinkedListNode(value));
			tail = tail.getNext();
		}

		return dummy.getNext();
	}
	
	public static DoublyLinkedListNode createDoublyLinkedList(int... args) {
		return createDoublyLinkedListFromArray(args);
	}

	public static DoublyLinkedListNode createDoublyLinkedListFromArray(int[] arr) {
		DoublyLinkedListNode dummy = new DoublyLinkedListNode(0);
		DoublyLinkedListNode tail = dummy;

		for (int value : arr) {
			tail.setNext(new DoublyLinkedListNode(value));
			tail = tail.getNext();
		}

		return dummy.getNext();
	}
	
	public static AuxiliaryLinkedListNode createAuxiliaryLinkedList(int... args) {
		return createAuxiliaryLinkedListFromArray(args);
	}

	public static AuxiliaryLinkedListNode createAuxiliaryLinkedListFromArray(int[] arr) {
		AuxiliaryLinkedListNode dummy = new AuxiliaryLinkedListNode(0);
		AuxiliaryLinkedListNode tail = dummy;

		for (int value : arr) {
			tail.setNext(new AuxiliaryLinkedListNode(value));
			tail = tail.getNext();
		}

		return dummy.getNext();
	}

	public static RightDownLinkedListNode createRightDownLinkedList(int... args) {
		return createRightDownLinkedListFromArray(args);
	}

	public static RightDownLinkedListNode createRightDownLinkedListFromArray(int[] arr) {
		RightDownLinkedListNode dummy = new RightDownLinkedListNode(0);
		RightDownLinkedListNode tail = dummy;

		for (int value : arr) {
			tail.setNext(new RightDownLinkedListNode(value));
			tail = tail.getNext();
		}

		return dummy.getNext();
	}

	public static RightDownLinkedListNode createDownLinkedList(int... args) {
		return createDownLinkedListFromArray(args);
	}

	public static RightDownLinkedListNode createDownLinkedListFromArray(int[] arr) {
		RightDownLinkedListNode dummy = new RightDownLinkedListNode(0);
		RightDownLinkedListNode tail = dummy;

		for (int value : arr) {
			tail.setDown(new RightDownLinkedListNode(value));
			tail = tail.getDown();
		}

		return dummy.getDown();
	}

	public static void attachDownListToRightDownLinkedList(RightDownLinkedListNode head, int pivot, int... args) {
		if (head == null) {
			return;
		}

		while (head.getData() != pivot) {
			head = head.getNext();
		}

		if (head != null) {
			head.setDown(createDownLinkedList(args));
		}
	}

	public static void printRightDownLinkedList(RightDownLinkedListNode head) {
		Queue<RightDownLinkedListNode> queue = new LinkedList<>();
		while (head != null) {
			queue.offer(head);
			head = head.getNext();
		}

		int size = queue.size();
		while (!queue.isEmpty()) {
			int nullCount = 0;
			for (int i = 0; i < size; i++) {
				var node = queue.poll();
				if (node != null) {
					System.out.print(node.getData() + "\t");
					queue.offer(node.getDown());
				} else {
					System.out.print("\t");
					queue.offer(null);
					nullCount++;
				}
			}

			System.out.println();
			if (nullCount >= size) {
				break;
			}
		}
	}

	public static LinkedListNode addLoopToLinkedList(LinkedListNode head, int toIndex) {
		var tail = head;

		var loopNode = head;
		int i = 0;

		while (tail.getNext() != null) {
			tail = tail.getNext();
			i++;
			if (i == toIndex) {
				loopNode = tail;
			}
		}

		tail.setNext(loopNode);

		return head;
	}

	public static void printArray(int[] arr) {
		System.out.println(Arrays.toString(arr));
	}

	public static void printArray(int[][] arr) {
		for (int[] subArray : arr) {
			printArray(subArray);
		}
	}

	public static void printArray(boolean[] arr) {
		for(boolean value : arr) {
			System.out.print((value ? "T" : "F") + "\t");
		}
		System.out.println();
	}

	public static void printArray(boolean[][] arr) {
		for (boolean[] subArray : arr) {
			printArray(subArray);
		}
	}

	public static void printPairList(List<Pair<Integer, Integer>> pairs) {
		int counter = 0;
		for (var pair : pairs) {
			if (counter > 0) {
				System.out.print(", ");
			}

			System.out.print("(" + pair.getFirst() + ", " + pair.getSecond() + ")");
			counter++;
		}

		System.out.println();
	}

	public static BinaryTreeNode constructBinaryTree(Integer... args) {
		// Construct from array representation of Binary Tree;
		if (args == null || args.length == 1) {
			return null;
		}

		return constructBinaryTree(args, 0);
	}

	public static BTNode<Character> constructBinaryTree(Character... args) {
		// Construct from array representation of Binary Tree;
		if (args == null || args.length == 1) {
			return null;
		}

		return constructBinaryTree(args, 0);
	}

	private static BinaryTreeNode constructBinaryTree(Integer[] treeArray, int index) {
		if (index >= treeArray.length) {
			return null;
		}

		if (treeArray[index] == null) {
			return null;
		}

		BinaryTreeNode node = new BinaryTreeNode(treeArray[index]);

		int leftChildIndex = 2 * index + 1;
		int rightChildIndex = 2 * index + 2;

		if (leftChildIndex < treeArray.length) {
			var leftChild = constructBinaryTree(treeArray, leftChildIndex);
			node.setLeft(leftChild);
			if (leftChild != null) {
				leftChild.setParent(node);
			}
		}

		if (rightChildIndex < treeArray.length) {
			var rightChild = constructBinaryTree(treeArray, rightChildIndex);
			node.setRight(rightChild);
			if (rightChild != null) {
				rightChild.setParent(node);
			}
		}

		return node;
	}

	private static BTNode<Character> constructBinaryTree(Character[] treeArray, int index) {
		if (index >= treeArray.length) {
			return null;
		}

		if (treeArray[index] == null) {
			return null;
		}

		BTNode<Character> node = new BTNode<Character>(treeArray[index]);

		int leftChildIndex = 2 * index + 1;
		int rightChildIndex = 2 * index + 2;

		if (leftChildIndex < treeArray.length) {
			var leftChild = constructBinaryTree(treeArray, leftChildIndex);
			node.setLeft(leftChild);
			if (leftChild != null) {
				leftChild.setParent(node);
			}
		}

		if (rightChildIndex < treeArray.length) {
			var rightChild = constructBinaryTree(treeArray, rightChildIndex);
			node.setRight(rightChild);
			if (rightChild != null) {
				rightChild.setParent(node);
			}
		}

		return node;
	}

	public static boolean[] constructArray(boolean... args) {
		boolean[] array = new boolean[args.length];
		for (int i = 0; i < args.length; i++) {
			array[i] = args[i];
		}

		return array;
	}

	public static int[] constructArray(int... args) {
		int[] array = new int[args.length];
		for (int i = 0; i < args.length; i++) {
			array[i] = args[i];
		}

		return array;
	}

	public static boolean[] constructBinaryArray(int... args) {
		boolean[] array = new boolean[args.length];
		for (int i = 0; i < args.length; i++) {
			array[i] = args[i] != 0;
		}

		return array;
	}

	public static char[] constructArray(char... args) {
		char[] array = new char[args.length];
		for (int i = 0; i < args.length; i++) {
			array[i] = args[i];
		}

		return array;
	}

	public static int max(int x, int y, int z) {
		return Math.max(Math.max(x, y), z);
	}

	public static int max(int a, int b, int c, int d) {
		return Math.max(max(a, b, c), d);
	}

	public static int min(int x, int y, int z) {
		return Math.min(Math.min(x, y), z);
	}

	public static void swap(int[] items, int i, int j) {
		int temp = items[i];
		items[i] = items[j];
		items[j] = temp;
	}

	public static <T> void swap(List<T> list, int i, int j) {
		T temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}

	public static String swap(String str, int i, int j) {
		char[] ch = str.toCharArray();
		char temp = ch[i];
		ch[i] = ch[j];
		ch[j] = temp;

		return new String(ch);
	}

	public static <T> void printList(List<T> list) {
		for (T i : list) {
			System.out.print(i + " ");
		}

		System.out.println();
	}
	
	public static <T> void printListByLines(List<T> list) {
		for (T i : list) {
			System.out.println(i + " ");
		}

		System.out.println();
	}
	
	public static <T> void printSet(Set<T> list) {
		for (T i : list) {
			System.out.print(i + " ");
		}

		System.out.println();
	}
	
	public static <T> void printSetByLines(Set<T> list) {
		for (T i : list) {
			System.out.print(i + " ");
		}

		System.out.println();
	}

	public static <T> void printSetListByLines(List<Set<T>> list) {
		for(Set<T> set : list) {
			printSetByLines(set);
		}

		System.out.println();
	}

	public static Stack<Integer> createStack(int... args) {
		Stack<Integer> stack = new Stack<>();

		for (int i : args) {
			stack.push(i);
		}

		return stack;
	}

	public static void copy(List<Integer> dest, List<Integer> source, int sourceStart, int sourceEnd) {
		if (dest == null || source == null) {
			return;
		}

		for (int i = sourceStart; i <= sourceEnd; i++) {
			dest.add(source.get(i));
		}
	}

	public static AdjacencyGraph constructUndirectedGraphFromAdjacencyArray(int[][] arr) {
		int vertexCount = arr.length;
		AdjacencyGraph graph = new AdjacencyGraph(vertexCount);

		for (int u = 0; u < vertexCount; u++) {
			for (int v = 0; v < vertexCount; v++) {
				if (arr[u][v] != 0) {
					graph.addUndirectedEdge(u, v);
				}
			}
		}

		return graph;
	}

	public static int[][] constructAdjacencyArrayFromUndirectedGraph(AdjacencyGraph graph) {
		int vertexCount = graph.getVertexCount();
		int[][] arr = new int[vertexCount][vertexCount];
		for (int u = 0; u < vertexCount; u++) {
			Arrays.fill(arr[u], 0);

			for (var v : graph.getAdjacencyList()[u]) {
				arr[u][v] = 1;
			}
		}

		return arr;
	}

	public static int[] getLeftToRightTraversal(BinaryTreeNode head) {
		BinaryTreeNode current = head;
		List<Integer> traversal = new LinkedList<>();
		while (current != null) {
			traversal.add(current.getData());
			current = current.getRight();
		}

		return traversal.stream().mapToInt(i -> i).toArray();
	}

	public static int[] getRightToLeftTraversal(BinaryTreeNode head) {
		BinaryTreeNode current = head;
		while (current.getRight() != null) {
			current = current.getRight();
		}

		List<Integer> traversal = new LinkedList<>();
		while (current != null) {
			traversal.add(current.getData());
			current = current.getLeft();
		}

		return traversal.stream().mapToInt(i -> i).toArray();
	}

	public static <T> Set<T> constructSet(T... items) {
		Set<T> set = new HashSet<>();
		for (var item : items) {
			set.add(item);
		}

		return set;
	}

	public static int[] extractArray(int[] arr, int start, int i) {
		int[] result = new int[i - start + 1];
		for (int j = start; j <= i; j++) {
			result[j - start] = arr[j];
		}

		return result;
	}

	public static <T> Queue<T> constructQueue(T... items) {
		Queue<T> queue = new LinkedList<>();
		for (var item : items) {
			queue.offer(item);
		}

		return queue;
	}

	public static int max(int[] arr, int start, int end) {
		int max = Integer.MIN_VALUE;
		for (int i = start; i <= end; i++) {
			max = Math.max(max, arr[i]);
		}

		return max;
	}

	public static int maxIndex(int[] arr, int start, int end) {
		int max = Integer.MIN_VALUE;
		int maxIndex = -1;
		for (int i = start; i <= end; i++) {
			if (max < arr[i]) {
				max = arr[i];
				maxIndex = i;
			}
		}

		return maxIndex;
	}

	public static <T> ConnectableBinaryTreeNode<T> constructConnectableBinaryTree(T... args) {
		if (args == null || args.length == 1) {
			return null;
		}

		return constructConnectableBinaryTree(args, 0);
	}

	public static <T> ConnectableBinaryTreeNode<T> constructConnectableBinaryTree(T[] treeArray, int index) {
		if (index >= treeArray.length) {
			return null;
		}

		if (treeArray[index] == null) {
			return null;
		}

		ConnectableBinaryTreeNode<T> node = new ConnectableBinaryTreeNode<T>(treeArray[index]);
		int leftChildIndex = 2 * index + 1;
		int rightChildIndex = 2 * index + 2;

		if (leftChildIndex < treeArray.length) {
			var leftChild = constructConnectableBinaryTree(treeArray, leftChildIndex);
			node.setLeft(leftChild);
		}

		if (rightChildIndex < treeArray.length) {
			var rightChild = constructConnectableBinaryTree(treeArray, rightChildIndex);
			node.setRight(rightChild);
		}

		return node;
	}

	public static <T> ArrayList<T> createArrayList(T... args) {
		ArrayList<T> list = new ArrayList<>();
		for (T item : args) {
			list.add(item);
		}
		
		return list;
	}

	public static int getMaxValue(int[][] arr) {
		int max = Integer.MIN_VALUE;
		for (int[] subArray : arr) {
			max = Math.max(max, getMaxValue(subArray));
		}		
		
		return max;
	}
	
	public static int getMaxValue(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int value : arr) {
			max = Math.max(max, value);
		}		
		
		return max;
	}

    public static void fillArray(char[][] arr, char x) {
		for(var row : arr) {
			Arrays.fill(row, x);
		}
    }
    
	public static void fillArray(boolean[][] arr, boolean x) {
		for(var row : arr) {
			Arrays.fill(row, x);
		}
	}

    public static <T> List<T> constructList(T... args) {
		List<T> list = new ArrayList<>();
		for(T item : args) {
			list.add(item);
		}

		return list;
    }

	public static <T> Set<T> getSetDifference(Set<T> set1, Set<T> set2) {
		if(set1 == null || set2 == null) {
			return  null;
		}

		Set<T> difference = new HashSet<>();
		for(var item : set1) {
			if(!set2.contains(item)) {
				difference.add(item);
			}
		}

		return difference;
	}

	public static Set<Integer> getSetDifference(int[] set1, Set<Integer> set2) {
		if(set1 == null || set2 == null) {
			return  null;
		}

		Set<Integer> difference = new HashSet<>();
		for(var item : set1) {
			if(!set2.contains(item)) {
				difference.add(item);
			}
		}

		return difference;
	}

	public static String getTextFromResourceFile(String fileName) {
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		Scanner scanner = new Scanner(loader.getResourceAsStream(fileName), "UTF-8");
		return scanner.useDelimiter("\\A").next();
	}
}
