package com.rawad.rapiddrift.renderengine.texture;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;

import com.rawad.rapiddrift.util.IOUtils;

/**
 * @author Rawad
 *
 */
public final class TextureLoader {
	
	private static final String TEXTURE_EXTENSION = "png";
	
	public static Texture loadTexture(String... pathParts) {
		
		IntBuffer w = BufferUtils.createIntBuffer(1);
		IntBuffer h = BufferUtils.createIntBuffer(1);
		IntBuffer comp = BufferUtils.createIntBuffer(1);
		ByteBuffer textureBuffer = BufferUtils.createByteBuffer(1);
		
		STBImage.stbi_set_flip_vertically_on_load(true);
		
		String path = IOUtils.getFilePath(TEXTURE_EXTENSION, pathParts);
		
		textureBuffer = STBImage.stbi_load(path, w, h, comp, STBImage.STBI_rgb_alpha);
		
		if(textureBuffer == null) {
			throw new RuntimeException(String.format("Could not load texture at path: %s", path));
		}
		
		return new Texture(w.get(), h.get(), textureBuffer);
		
	}
	
}
