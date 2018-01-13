package com.rawad.rapiddrift.renderengine.texture;

import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class Texture {
	
	private final int id;
	
	private final int width;
	private final int height;
	
	private final ByteBuffer data;
	
	public Texture(int width, int height, ByteBuffer data) {
		super();
		
		id = GL11.glGenTextures();
		
		this.width = width;
		this.height = height;
		
		this.data = data;
		
		this.bind();
		
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		// Or, generate a mip map instead.
		
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, width, height, 0, GL11.GL_RGBA, 
				GL11.GL_UNSIGNED_BYTE, data);
		
	}
	
	public Texture() {
		this(0, 0, BufferUtils.createByteBuffer(0));
	}
	
	public void bind() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
	}
	
	public void delete() {
		GL11.glDeleteTextures(id);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getId() {
		return id;
	}
	
	/**
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Texture clone() {
		return new Texture(width, height, data);
	}
	
}
