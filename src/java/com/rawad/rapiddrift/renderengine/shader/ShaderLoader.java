package com.rawad.rapiddrift.renderengine.shader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL32;

import com.rawad.rapiddrift.util.IOUtils;
import com.rawad.rapiddrift.util.Utils;

/**
 * @author Rawad
 *
 */
public class ShaderLoader {
	
	private static final String VERTEX_SHADER_EXTENSION = "vert.glsl";
	private static final String GEOMETRY_SHADER_EXTENSION = "geom.glsl";
	private static final String FRAGMENT_SHADER_EXTENSION = "frag.glsl";
	
	public static String loadShaderSource(Shader shader, String name) {
		
		StringBuilder source = new StringBuilder();
		
		try (BufferedReader reader = new BufferedReader(IOUtils.openInputStreamReader(shader.getClass(), name, ShaderLoader
				.getExtensionByType(shader.getType())))) {
			
			String line = null;
			
			while((line = reader.readLine()) != null) {
				source.append(line).append(Utils.NL);
			}
			
		} catch (FileNotFoundException ex) {
			Logger.getGlobal().log(Level.WARNING, String.format("Shader file called \"%s\" was not found", name), ex);
			return null;
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} catch(NullPointerException ex) {
			return null;
		}
		
		return source.toString();
		
	}
	
	private static String getExtensionByType(int type) {
		
		switch(type) {
		
		case GL20.GL_VERTEX_SHADER:
			return VERTEX_SHADER_EXTENSION;
		case GL32.GL_GEOMETRY_SHADER:
			return GEOMETRY_SHADER_EXTENSION;
		case GL20.GL_FRAGMENT_SHADER:
			return FRAGMENT_SHADER_EXTENSION;
		
		}
		
		return "";
		
	}
	
}
