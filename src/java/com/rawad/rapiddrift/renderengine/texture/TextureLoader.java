package com.rawad.rapiddrift.renderengine.texture;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.file.Paths;

import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;

import com.rawad.rapiddrift.util.Utils;

/**
 * @author Rawad
 *
 */
public final class TextureLoader {
	
	private static final String TEXTURE_EXTENSION = "png";
	
	public static Texture loadTexture(String... pathParts) {
		return TextureLoader.loadFromUrl(Utils.getResource(TEXTURE_EXTENSION, pathParts));
	}
	
	public static Texture loadTexture(Class<?> relativeTo, String... pathparts) {
		return TextureLoader.loadFromUrl(Utils.getResource(relativeTo, TEXTURE_EXTENSION, pathparts));
	}
	
	private static Texture loadFromUrl(URL url) {
		
		IntBuffer w = BufferUtils.createIntBuffer(1);
		IntBuffer h = BufferUtils.createIntBuffer(1);
		IntBuffer comp = BufferUtils.createIntBuffer(1);
		ByteBuffer textureBuffer = BufferUtils.createByteBuffer(1);
		
		STBImage.stbi_set_flip_vertically_on_load(true);
		
		try {
			
			String path = Paths.get(url.toURI()).toFile().getAbsolutePath();
			
			textureBuffer = STBImage.stbi_load(path, w, h, comp, STBImage.STBI_rgb_alpha);
			
			if(textureBuffer == null) {
				throw new RuntimeException(String.format("Could not load texture at path: %s", path));
			}
			
		} catch(URISyntaxException ex) {
			ex.printStackTrace();
		}
		
		return new Texture(w.get(), h.get(), textureBuffer);
		
	}
	
}
