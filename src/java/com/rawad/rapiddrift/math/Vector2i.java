package com.rawad.rapiddrift.math;


/**
 * @author Rawad
 *
 */
public class Vector2i {
	
	public final int x;
	public final int y;
	
	/**
	 * @param x
	 * @param y
	 */
	public Vector2i(int x, int y) {
		super();
		
		this.x = x;
		this.y = y;
		
	}
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Vector2i(%s, %s)", x, y);
	}
	
}
