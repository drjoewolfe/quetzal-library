package org.jwolfe.quetzal.library.tree;

public class ConnectableBinaryTreeNode<T> {

	public ConnectableBinaryTreeNode<T> getLeft() {
		return left;
	}

	public void setLeft(ConnectableBinaryTreeNode<T> left) {
		this.left = left;
	}

	ConnectableBinaryTreeNode<T> left;

	public ConnectableBinaryTreeNode<T> getRight() {
		return right;
	}

	public void setRight(ConnectableBinaryTreeNode<T> right) {
		this.right = right;
	}

	ConnectableBinaryTreeNode<T> right;

	public ConnectableBinaryTreeNode<T> getConnection() {
		return connection;
	}

	public void setConnection(ConnectableBinaryTreeNode<T> connection) {
		this.connection = connection;
	}

	ConnectableBinaryTreeNode<T> connection;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	T data;

	public ConnectableBinaryTreeNode(T data) {
		this.data = data;
	}
}
