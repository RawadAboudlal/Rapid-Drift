package com.rawad.rapiddrift.mesh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.BufferUtils;

import com.rawad.rapiddrift.math.Vector2f;
import com.rawad.rapiddrift.math.Vector3f;
import com.rawad.rapiddrift.util.BufferFactory;
import com.rawad.rapiddrift.util.Util;

/**
 * @author Rawad
 *
 */
public class ObjFileLoader {
	
	private static final String OBJ_FILE_EXTENSION = "obj";
	
	private static final String REGEX = " ";
	
	private static final String REGEX_FACE_DATA = "/";
	
	private static final String ID_COMMENT = "#";
	private static final String ID_VERTEX = "v";	
	private static final String ID_TEXTURE = "vt";
	private static final String ID_NORMAL = "vn";
	private static final String ID_FACE = "f";
	
	private static final int INDEX_X = 0;
	private static final int INDEX_Y = 1;
	private static final int INDEX_Z = 2;
	
	private static final int INDEX_POSITION = 0;
	private static final int INDEX_TEXTURE_COORDS = 1;
	private static final int INDEX_NORMAL = 2;
	
	private static Vector3f parseVector3f(String lineData) {
		
		String[] vertexCoords = lineData.split(REGEX);
		
		float x = 0f;
		float y = 0f;
		float z = 0f;
		
		if(vertexCoords.length >= Vector3f.SIZE) {
			x = Util.parseFloat(vertexCoords[INDEX_X]);
			y = Util.parseFloat(vertexCoords[INDEX_Y]);
			z = Util.parseFloat(vertexCoords[INDEX_Z]);
		}
		
		return new Vector3f(x, y, z);
		
	}
	
	private static Vector2f parseVector2f(String lineData) {
		
		String[] textureCoords = lineData.split(REGEX);
		
		float x = 0;
		float y = 0;
		
		if(textureCoords.length >= Vector2f.SIZE) {
			x = Util.parseFloat(textureCoords[INDEX_X]);
			y = Util.parseFloat(textureCoords[INDEX_Y]);
		}
		
		return new Vector2f(x, y);
		
	}
	
	private static int parseVertexIndices(String lineData, ArrayList<Integer> positionsIndices, 
			ArrayList<Integer> textureCoordsIndices, ArrayList<Integer> normalsIndices) {
		
		String[] faces = lineData.split(REGEX);
		
		int vertexCount = 0;
		
		for(int i = 0; i < faces.length; i++) {
			
			String[] indices = faces[i].split(REGEX_FACE_DATA);
			
			positionsIndices.add(Util.parseInt(indices[INDEX_POSITION]) - 1);
			textureCoordsIndices.add(Util.parseInt(indices[INDEX_TEXTURE_COORDS]) - 1);
			normalsIndices.add(Util.parseInt(indices[INDEX_NORMAL]) - 1);
			
			vertexCount++;
			
		}
		
		return vertexCount;
		
	}
	
	private static Mesh createMesh(ArrayList<Vector3f> positions, ArrayList<Vector3f> normals, 
			ArrayList<Vector2f> textureCoords, ArrayList<Integer> positionsIndices, ArrayList<Integer> normalsIndices, 
			ArrayList<Integer> textureCoordsIndices, int vertexCount) {
		
		ArrayList<Vertex> vertices = new ArrayList<Vertex>(vertexCount);
		ArrayList<Integer> indices = new ArrayList<Integer>();// Final indices for IBO.
		
		int uniqueVertexCount = 0;
		
		for(int i = 0; i < vertexCount; i++) {
			
			int positionIndex = positionsIndices.get(i);
			int normalIndex = normalsIndices.get(i);
			int textureCoordIndex = textureCoordsIndices.get(i);
			
			Vertex vertex = new Vertex(positionIndex, normalIndex, textureCoordIndex);
			
			if(!vertices.contains(vertex)) {
				vertices.add(vertex);
				uniqueVertexCount++;
			}
			
			int vertexIndex = vertices.indexOf(vertex);
			
			indices.add(vertexIndex);
			
		}
		
		IntBuffer indexBuffer = BufferUtils.createIntBuffer(vertexCount);
		
		for(int i: indices) {
			indexBuffer.put(i);
		}
		
		indexBuffer.flip();
		
		FloatBuffer data = BufferUtils.createFloatBuffer(uniqueVertexCount * Vertex.SIZE);
		
		for(Vertex vertex: vertices) {
			
			int positionIndex = vertex.getPosition();
			int normalIndex = vertex.getNormal();
			int textureCoordIndex = vertex.getTextureCoord();
			
			Vector3f position = positions.get(positionIndex);
			Vector3f normal = normals.get(normalIndex);
			Vector2f textureCoord = textureCoords.get(textureCoordIndex);
			
			data.put(BufferFactory.createBuffer(position)).put(BufferFactory.createBuffer(normal))
			.put(BufferFactory.createBuffer(textureCoord));
			
		}
		
		data.flip();
		
		return new Mesh(indexBuffer, data, vertexCount);
		
	}
	
	public static Mesh loadMesh(String... pathParts) {
		return ObjFileLoader.loadFromInputStream(Util.openFileStream(OBJ_FILE_EXTENSION, pathParts));
	}
	
	public static Mesh loadMesh(Class<?> relativeTo, String... pathParts) {
		return ObjFileLoader.loadFromInputStream(Util.openFileStream(relativeTo, OBJ_FILE_EXTENSION, pathParts));
	}
	
	private static Mesh loadFromInputStream(InputStream inputStream) {
		
		ArrayList<Vector3f> positions = new ArrayList<Vector3f>();
		ArrayList<Vector3f> normals = new ArrayList<Vector3f>();
		ArrayList<Vector2f> textureCoords = new ArrayList<Vector2f>();
		
		ArrayList<Integer> positionsIndices = new ArrayList<Integer>();
		ArrayList<Integer> normalsIndices = new ArrayList<Integer>();
		ArrayList<Integer> textureCoordsIndices = new ArrayList<Integer>();
		
		int vertexCount = 0;
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			
			String line = null;
			
			while((line = reader.readLine()) != null) {
				
				int firstRegexIndex = line.indexOf(REGEX);
				
				String id = line.substring(0, firstRegexIndex);
				
				String lineData = line.substring(firstRegexIndex + REGEX.length());
				
				switch(id) {
				
				case ID_COMMENT:
					break;
				
				case ID_VERTEX:
					positions.add(parseVector3f(lineData));
					break;
					
				case ID_TEXTURE:
					textureCoords.add(parseVector2f(lineData));
					break;
					
				case ID_NORMAL:
					normals.add(parseVector3f(lineData));
					break;
					
				case ID_FACE:
					vertexCount += ObjFileLoader.parseVertexIndices(lineData, positionsIndices, textureCoordsIndices, 
							normalsIndices);
					break;
					
				default:
					Logger.getGlobal().log(Level.INFO, String.format("The line id %s did not match any known line id. "
							+ "Line Data: %s", id, lineData));
					break;
				
				}
				
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return ObjFileLoader.createMesh(positions, normals, textureCoords, positionsIndices, normalsIndices, 
				textureCoordsIndices, vertexCount);
		
	}
	
}
