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
	
	public void set(Coordinate newCoordinate) {
		if(newCoordinate == null) {
			return;
		}
		
		this.x = newCoordinate.x;
		this.y = newCoordinate.y;
	}
	
	public void increment(Coordinate deltaCoordinate) {
		if(deltaCoordinate == null) {
			return;
		}
		
		this.x += deltaCoordinate.x;
		this.y += deltaCoordinate.y;
	}
}
