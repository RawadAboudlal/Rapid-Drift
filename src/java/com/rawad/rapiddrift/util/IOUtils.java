package com.rawad.rapiddrift.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	
	private static final String EXTENSION_SEPARATOR = ".";
	
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
    
	public static String getPath(String... pathParts) {
		return String.join(File.separator, pathParts);
	}
	
	public static String getFilePath(String extension, String... pathParts) {
		return getPath(pathParts) + IOUtils.EXTENSION_SEPARATOR + extension;
	}
	
	public static InputStreamReader openInputStreamReader(String extension, String... pathParts) throws FileNotFoundException {
		return new InputStreamReader(new FileInputStream(IOUtils.getFilePath(extension, pathParts)));
	}
	
	public static FileOutputStream openOutputStream(String extension, String... pathParts) throws FileNotFoundException {
		return new FileOutputStream(IOUtils.getFilePath(extension, pathParts));
	}
	
	/*
	public static URL getResource(String extension, String... pathParts) {
		return Utils.class.getClassLoader().getResource(getFilePath(extension, pathParts));
	}
	
	public static InputStream openFileStream(String extension, String... pathParts) {
		return Utils.class.getClassLoader().getResourceAsStream(getFilePath(extension, pathParts));
	}
	
	public static URL getResource(Class<?> clazz, String extension, String... pathParts) {
		return clazz.getResource(getFilePath(extension, pathParts));
	}
	
	public static InputStream openFileStream(Class<?> clazz, String extension, String... path) {
		return clazz.getResourceAsStream(getFilePath(extension, path));
	}*/
    
}
