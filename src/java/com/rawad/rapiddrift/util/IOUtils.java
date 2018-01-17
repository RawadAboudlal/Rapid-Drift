package com.rawad.rapiddrift.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.lwjgl.BufferUtils;

/**
 * @author Rawad
 *
 */
public final class IOUtils {
	
	private static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) {
		
		ByteBuffer newBuffer = BufferUtils.createByteBuffer(newCapacity);
        
		buffer.flip();
        newBuffer.put(buffer);
        
        return newBuffer;
        
	}
	
	/**
     * Reads the specified resource and returns the raw data as a ByteBuffer.
     *
     * @param resource   the resource to read
     * @param bufferSize the initial buffer size
     *
     * @return the resource data
     *
     * @throws IOException if an IO error occurs
     */
    public static ByteBuffer ioResourceToByteBuffer(Class<?> clazz, String resource, int bufferSize) throws IOException {
		
		ByteBuffer buffer;
		
		Path path = Paths.get(resource);
		
		if(Files.isReadable(path)) {
		
			try (SeekableByteChannel fc = Files.newByteChannel(path)) {
				
				buffer = BufferUtils.createByteBuffer((int) fc.size() + 1);
				
				while (fc.read(buffer) != -1);
				
			}
			
		} else {
			
			try (	InputStream source = clazz.getResourceAsStream(resource);
					ReadableByteChannel rbc = Channels.newChannel(source)) {
				
				buffer = BufferUtils.createByteBuffer(bufferSize);
				
				while (true) {
					
					int bytes = rbc.read(buffer);
					
					if (bytes == -1) {
						break;
					}
					
					if (buffer.remaining() == 0) {
						buffer = IOUtils.resizeBuffer(buffer, buffer.capacity() * 3 / 2);// 50%
					}
					
				}
				
			}
		}
		
		buffer.flip();
		
		return buffer.slice();
		
    }
    
	/*
	 * This class might be deprecated now. This piece of code is the one that needed it the most, was in TextureLoader.
	 * Fixed by moving the res source directory into src and adding a java source directory, also in src.
	try {
		
		ByteBuffer textureBuffer = IOUtil.ioResourceToByteBuffer(TextureLoader.class, 
				Util.getPath(TEXTURE_EXTENSION, name), Byte.SIZE);
		
		ByteBuffer texture = STBImage.stbi_load_from_memory(textureBuffer, w, h, comp, STBImage.STBI_rgb_alpha);
		
		if(texture == null) {
			throw new RuntimeException(String.format("Failed to load texture file %s.%s%s", name, Util.NL, 
					STBImage.stbi_failure_reason()));
		}
		
		return new Texture(w.get(), h.get(), texture);
		
	} catch(IOException ex) {
		ex.printStackTrace();
	}
	*/
	
}
