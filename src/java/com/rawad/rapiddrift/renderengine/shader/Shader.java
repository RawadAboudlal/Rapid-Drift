package com.rawad.rapiddrift.renderengine.shader;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryUtil;

/**
 * @author Rawad
 *
 */
public abstract class Shader {
	
	private final int id;
	private final int type;
	
	/**
	 * 
	 * @param type
	 */
	protected Shader(int type) {
		super();
		
		this.type = type;
		
		id = GL20.glCreateShader(type);
		
		if(id == MemoryUtil.NULL) {
			throw new RuntimeException("Could not create shader: " + GL20.glGetShaderInfoLog(id));
		}
		
	}
	
	public boolean load(String name) {
		
		String shaderSource = ShaderLoader.loadShaderSource(this, name);
		
		if(shaderSource == null) {
			Logger.getGlobal().log(Level.WARNING, "Could not load shader source.");
			return false;
		}
		
		GL20.glShaderSource(id, shaderSource);
		GL20.glCompileShader(id);
		
		if(GL20.glGetShaderi(id, GL20.GL_COMPILE_STATUS) == MemoryUtil.NULL) {
			Logger.getGlobal().log(Level.WARNING, "Error compiling shader source: " + GL20.glGetShaderInfoLog(id));
			return false;
		}
		
		return true;
		
	}
	
	public void attach(int program) {
		GL20.glAttachShader(program, id);
	}
	
	public void detach(int program) {
		GL20.glDetachShader(program, id);
	}
	
	public void delete() {
		GL20.glDeleteShader(id);
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	
}
