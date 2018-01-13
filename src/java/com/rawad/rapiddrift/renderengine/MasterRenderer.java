package com.rawad.rapiddrift.renderengine;

import org.lwjgl.opengl.GL11;

/**
 * @author Rawad
 *
 */
public class MasterRenderer {
	
	private IRenderer renderer;
	
	private Color clearColor;
	
	public MasterRenderer(IRenderer renderer) {
		super();
		
		this.renderer = renderer;
		
		this.clearColor = new Color(0.3f, 0.3f, 0.3f);
		
	}
	
	public void render() {
		
		this.clear();
		
		this.renderer.render();
		
	}
	
	public void clear() {
		
		GL11.glClearColor(clearColor.getRed(), clearColor.getGreen(), clearColor.getBlue(), clearColor.getAlpha());
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
	}
	
	/**
	 * @return the clearColor
	 */
	public Color getClearColor() {
		return clearColor;
	}
	
	/**
	 * @param clearColor the clearColor to set
	 */
	public void setClearColor(Color clearColor) {
		this.clearColor = clearColor;
	}
	
}
