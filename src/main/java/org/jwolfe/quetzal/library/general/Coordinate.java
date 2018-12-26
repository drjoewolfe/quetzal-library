package org.jwolfe.quetzal.library.general;

public class Coordinate {

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	int x;

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
