package com.rawad.rapiddrift.renderengine.shader;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryUtil;

import com.rawad.rapiddrift.math.Matrix2f;
import com.rawad.rapiddrift.math.Matrix3f;
import com.rawad.rapiddrift.math.Matrix4f;
import com.rawad.rapiddrift.math.Vector2f;
import com.rawad.rapiddrift.math.Vector3f;
import com.rawad.rapiddrift.math.Vector4f;
import com.rawad.rapiddrift.util.BufferFactory;

/**
 * @author Rawad
 *
 */
public abstract class ShaderProgram {
	
	private final int id;
	
	private VertexShader vertexShader;
	private GeometryShader geometryShader;
	private FragmentShader fragmentShader;
	
	public ShaderProgram(String name) {
		super();
		
		id = GL20.glCreateProgram();
		
		if(id == MemoryUtil.NULL) {
			throw new RuntimeException("Shader program could not be created: " + GL20.glGetProgramInfoLog(id));
		}
		
		this.vertexShader = this.setupShader(new VertexShader(), name);
		this.geometryShader = this.setupShader(new GeometryShader(), name);
		this.fragmentShader = this.setupShader(new FragmentShader(), name);
		
		this.link();
		
	}
	
	public abstract void setupVertexAttributes();
	
	private <T extends Shader> T setupShader(T shader, String name) {
		
		if(shader.load(name)) {
			shader.attach(id);
		} else {
			shader.delete();
			return null;
		}
		
		return shader;
		
	}
	
	public void link() {
		
		GL20.glLinkProgram(id);
		
		if(GL20.glGetProgrami(id, GL20.GL_LINK_STATUS) == MemoryUtil.NULL) {
			throw new RuntimeException("Could not link shader program: " + GL20.glGetProgramInfoLog(id));
		}
		
		if(vertexShader != null) {
			vertexShader.detach(id);
		}
		
		if(geometryShader != null) {
			geometryShader.detach(id);
		}
		
		if(fragmentShader != null) {
			fragmentShader.detach(id);
		}
		
	}
	
	public void use() {
		GL20.glUseProgram(id);
	}
	
	public void stopUsing() {
		GL20.glUseProgram(0);
	}
	
	public void delete() {
		
		this.stopUsing();
		
		if(vertexShader != null) {
			vertexShader.delete();
		}
		
		if(geometryShader != null) {
			geometryShader.delete();
		}
		
		if(fragmentShader != null) {
			fragmentShader.delete();
		}
		
		if(id != MemoryUtil.NULL) {
			GL20.glDeleteProgram(id);
		}
		
	}
	
	public int getAttributeLocation(CharSequence name) {
		return GL20.glGetAttribLocation(id, name);
	}
	
	/**
	 * Enables a vertex attribute array.
	 * 
	 * @param location
	 */
	public void enableVertexAttribute(int location) {
		GL20.glEnableVertexAttribArray(location);
	}
	
	public void disableVertexAttribute(int location) {
		GL20.glDisableVertexAttribArray(location);
	}
	
	public void pointVertexAttribute(int location, int size, int stride, int offset) {
		GL20.glVertexAttribPointer(location, size, GL11.GL_FLOAT, false, stride, offset);
	}
	
	protected int getUniformLocation(CharSequence name) {
		return GL20.glGetUniformLocation(id, name);
	}
	
	public void setUniform(int location, int value) {
		GL20.glUniform1i(location, value);
	}
	
	public void setUniform(int location, Vector2f value) {
		GL20.glUniform2fv(location, BufferFactory.createBuffer(value));
	}
	
	public void setUniform(int location, Vector3f value) {
		GL20.glUniform3fv(location, BufferFactory.createBuffer(value));
	}
	
	public void setUniform(int location, Vector4f value) {
		GL20.glUniform4fv(location, BufferFactory.createBuffer(value));
	}
	
	public void setUniform(int location, Matrix2f value) {
		GL20.glUniformMatrix2fv(location, false, BufferFactory.createBuffer(value));
	}
	
	public void setUniform(int location, Matrix3f value) {
		GL20.glUniformMatrix3fv(location, false, BufferFactory.createBuffer(value));
	}
	
	public void setUniform(int location, Matrix4f value) {
		GL20.glUniformMatrix4fv(location, false, BufferFactory.createBuffer(value));
	}
	
}
