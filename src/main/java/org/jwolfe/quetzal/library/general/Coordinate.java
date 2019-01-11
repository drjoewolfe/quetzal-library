package org.jwolfe.quetzal.library.general;

import java.util.Objects;

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
		if (newCoordinate == null) {
			return;
		}

		this.x = newCoordinate.x;
		this.y = newCoordinate.y;
	}

	public void increment(Coordinate deltaCoordinate) {
		if (deltaCoordinate == null) {
			return;
		}

		this.x += deltaCoordinate.x;
		this.y += deltaCoordinate.y;
	}

	public Coordinate buildNew(Coordinate deltaCoordinate) {
		if (deltaCoordinate == null) {
			return null;
		}

		return new Coordinate(this.x + deltaCoordinate.x, this.y + deltaCoordinate.y);
	}

	public boolean isWithinBounds(int top, int left, int bottom, int right) {
		if (x < top || y < left
				|| x > bottom || y > right) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "Coordinate{" +
				"x=" + x +
				", y=" + y +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Coordinate that = (Coordinate) o;
		return x == that.x &&
				y == that.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}
