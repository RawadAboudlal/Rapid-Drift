package com.rawad.rapiddrift.mesh;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

public class Mesh {
	
	private final IntBuffer indices;
	
	private final FloatBuffer data;
	
	private final int vertexCount;
	
	public Mesh(IntBuffer indices, FloatBuffer data, int vertexCount) {
		super();
		
		this.indices = indices;
		
		this.data = data;
		
		this.vertexCount = vertexCount;
		
	}
	
	public Mesh() {
		this(BufferUtils.createIntBuffer(0), BufferUtils.createFloatBuffer(0), 0);
	}
	
	/**
	 * @return the indices
	 */
	public IntBuffer getIndices() {
		return indices;
	}
	
	/**
	 * @return the data
	 */
	public FloatBuffer getData() {
		return data;
	}
	
	/**
	 * @return the vertexCount
	 */
	public int getVertexCount() {
		return vertexCount;
	}
	
	/**
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Mesh clone() {
		return new Mesh(indices, data, vertexCount);
	}
	
}
