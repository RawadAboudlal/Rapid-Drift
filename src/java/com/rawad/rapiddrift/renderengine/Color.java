package com.rawad.rapiddrift.renderengine;

/**
 * @author Rawad
 *
 */
public class Color {
	
	public static final Color WHITE = new Color(1f, 1f, 1f);
	public static final Color BLACK = new Color(0f, 0f, 0f);
	public static final Color RED = new Color(1f, 0f, 0f);
	public static final Color GREEN = new Color(0f, 1f, 0f);
	public static final Color BLUE = new Color(0f, 0f, 1f);
	
	private final float red;
	private final float green;
	private final float blue;
	private final float alpha;
	
	public Color(float red, float green, float blue, float alpha) {
		super();
		
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
		
	}
	
	public Color(float red, float green, float blue) {
		this(red, green, blue, 1f);
	}
	
	/**
	 * @return the red
	 */
	public float getRed() {
		return red;
	}
	
	/**
	 * @return the green
	 */
	public float getGreen() {
		return green;
	}
	
	/**
	 * @return the blue
	 */
	public float getBlue() {
		return blue;
	}
	
	/**
	 * @return the alpha
	 */
	public float getAlpha() {
		return alpha;
	}
	
}
