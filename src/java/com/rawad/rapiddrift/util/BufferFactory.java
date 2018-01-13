package com.rawad.rapiddrift.util;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

import com.rawad.rapiddrift.math.Matrix2f;
import com.rawad.rapiddrift.math.Matrix3f;
import com.rawad.rapiddrift.math.Matrix4f;
import com.rawad.rapiddrift.math.Vector2f;
import com.rawad.rapiddrift.math.Vector3f;
import com.rawad.rapiddrift.math.Vector4f;

/**
 * @author Rawad
 *
 */
public final class BufferFactory {
	
	/**
	 * 
	 * @param mat
	 * @return mat as FloatBuffer
	 */
	public static FloatBuffer createBuffer(Matrix4f mat) {
		
		FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
		
		buffer.put(mat.m00).put(mat.m10).put(mat.m20).put(mat.m30);
		buffer.put(mat.m01).put(mat.m11).put(mat.m21).put(mat.m31);
		buffer.put(mat.m02).put(mat.m12).put(mat.m22).put(mat.m32);
		buffer.put(mat.m03).put(mat.m13).put(mat.m23).put(mat.m33);
		
		buffer.flip();
		
		return buffer;
		
	}
	
	/**
	 * 
	 * @param mat
	 * @return mat as FloatBuffer
	 */
	public static FloatBuffer createBuffer(Matrix3f mat) {
		
		FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
		
		buffer.put(mat.m00).put(mat.m10).put(mat.m20);
		buffer.put(mat.m01).put(mat.m11).put(mat.m21);
		buffer.put(mat.m02).put(mat.m12).put(mat.m22);
		
		buffer.flip();
		
		return buffer;
		
	}
	
	/**
	 * 
	 * @param mat
	 * @return mat as FloatBuffer
	 */
	public static FloatBuffer createBuffer(Matrix2f mat) {
		
		FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
		
		buffer.put(mat.m00).put(mat.m10);
		buffer.put(mat.m01).put(mat.m11);
		
		buffer.flip();
		
		return buffer;
		
	}
	
	/**
	 * 
	 * @param vec
	 * @return vec as FloatBuffer
	 */
	public static FloatBuffer createBuffer(Vector4f vec) {
		
		FloatBuffer buffer = BufferUtils.createFloatBuffer(Vector4f.SIZE);
		
		buffer.put(vec.x).put(vec.y).put(vec.z).put(vec.w);
		buffer.flip();
		
		return buffer;
		
	}
	
	/**
	 * 
	 * @param vec
	 * @return vec as FloatBuffer
	 */
	public static FloatBuffer createBuffer(Vector3f vec) {
		
		FloatBuffer buffer = BufferUtils.createFloatBuffer(Vector3f.SIZE);
		
		buffer.put(vec.x).put(vec.y).put(vec.z);
		buffer.flip();
		
		return buffer;
		
	}
	
	/**
	 * 
	 * @param vec
	 * @return vec as FloatBuffer
	 */
	public static FloatBuffer createBuffer(Vector2f vec) {
		
		FloatBuffer buffer = BufferUtils.createFloatBuffer(Vector2f.SIZE);
		
		buffer.put(vec.x).put(vec.y);
		buffer.flip();
		
		return buffer;
		
	}
	
	/*
	 *  The byte buffer doesnt seem to work even with allocateDirect. Used in classes Texture, Mesh. The way they are done now
	 *  is shallow.
	 */
	public static ByteBuffer clone(ByteBuffer buffer) {
		
		ByteBuffer clone = ByteBuffer.allocateDirect(buffer.capacity());
		
		buffer.rewind();
		clone.put(buffer);
		buffer.rewind();
		clone.flip();
		
		return clone;
		
	}
	
	public static IntBuffer clone(IntBuffer buffer) {
		
		IntBuffer clone = IntBuffer.allocate(buffer.capacity());
		
		buffer.rewind();
		clone.put(buffer);
		buffer.rewind();
		clone.flip();
		
		return clone;
		
	}
	
	public static FloatBuffer clone(FloatBuffer buffer) {
		
		FloatBuffer clone = FloatBuffer.allocate(buffer.capacity());
		
		buffer.rewind();
		clone.put(buffer);
		buffer.rewind();
		clone.flip();
		
		return clone;
		
	}
	
}
