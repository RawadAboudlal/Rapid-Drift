package com.rawad.rapiddrift.renderengine.shader;

import com.rawad.rapiddrift.math.Matrix4f;
import com.rawad.rapiddrift.math.Vector2f;
import com.rawad.rapiddrift.math.Vector3f;
import com.rawad.rapiddrift.mesh.Vertex;

/**
 * @author Rawad
 *
 */
public class StaticModelShader extends ShaderProgram {
	
	private final int model_location;
	private final int view_location;
	private final int projection_location;
	private final int texture_location;
	
	/**
	 * 
	 */
	public StaticModelShader() {
		super("static_entity_shader");
		
		model_location = this.getUniformLocation("model");
		view_location = this.getUniformLocation("view");
		projection_location = this.getUniformLocation("projection");
		texture_location = this.getUniformLocation("modelTexture");
		
	}
	
	/**
	 * @see com.rawad.rapiddrift.renderengine.shader.ShaderProgram#setupVertexAttributes()
	 */
	@Override
	public void setupVertexAttributes() {
		
		int stride = Vertex.SIZE * Float.BYTES;
		
		int location_position = this.getAttributeLocation("position");
		this.enableVertexAttribute(location_position);
		this.pointVertexAttribute(location_position, Vector3f.SIZE, stride, 0);
		
		int location_normal = this.getAttributeLocation("normal");
		this.enableVertexAttribute(location_normal);
		this.pointVertexAttribute(location_normal, Vector3f.SIZE, stride, Vector3f.SIZE * Float.BYTES);
		
		int location_textureCoords = this.getAttributeLocation("textureCoordinates");
		this.enableVertexAttribute(location_textureCoords);
		this.pointVertexAttribute(location_textureCoords, Vector2f.SIZE, stride, (Vector3f.SIZE + Vector3f.SIZE) * Float.BYTES);
		
	}
	
	public void setModelMatrix(Matrix4f model) {
		this.setUniform(model_location, model);
	}
	
	public void setViewMatrix(Matrix4f view) {
		this.setUniform(view_location, view);
	}
	
	public void setProjectionMatrix(Matrix4f projection) {
		this.setUniform(projection_location, projection);
	}
	
	public void setModelTexture(int texturUnit) {
		this.setUniform(texture_location, texturUnit);
	}
	
}
