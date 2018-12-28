package org.jwolfe.quetzal.library.general;

public class IntPair extends Pair<Integer, Integer> {
	public int getA() {
		return this.getFirst();
	}
	
	public int getB() {
		return this.getSecond();
	}
	
	public IntPair(int a, int b) {
		this.first = a;
		this.second = b;
	}
}
