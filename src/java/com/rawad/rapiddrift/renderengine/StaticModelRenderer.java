package com.rawad.rapiddrift.renderengine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;

import com.rawad.rapiddrift.entity.Entity;
import com.rawad.rapiddrift.entity.component.MeshComponent;
import com.rawad.rapiddrift.entity.component.PerspectiveCameraComponent;
import com.rawad.rapiddrift.entity.component.TextureComponent;
import com.rawad.rapiddrift.entity.component.TransformComponent;
import com.rawad.rapiddrift.math.Matrix4f;
import com.rawad.rapiddrift.mesh.Mesh;
import com.rawad.rapiddrift.renderengine.buffers.IndexBufferObject;
import com.rawad.rapiddrift.renderengine.buffers.VertexBufferObject;
import com.rawad.rapiddrift.renderengine.shader.StaticModelShader;
import com.rawad.rapiddrift.renderengine.texture.Texture;

/**
 * @author Rawad
 *
 */
public class StaticModelRenderer implements IEntityRenderer {
	
	private StaticModelShader staticModelShader = new StaticModelShader();
	
	private VertexArrayObject vao = new VertexArrayObject();
	private VertexBufferObject vbo = new VertexBufferObject();
	private IndexBufferObject ibo = new IndexBufferObject();
	
	private TransformComponent cameraTransform;
	private PerspectiveCameraComponent perspectiveCamera;
	
	/**
	 * @param camera
	 */
	public StaticModelRenderer(Entity camera) {
		super();
		
		this.cameraTransform = (TransformComponent) camera.getComponent(TransformComponent.class);
		this.perspectiveCamera = (PerspectiveCameraComponent) camera.getComponent(PerspectiveCameraComponent.class);
		
	}

	/**
	 * @see com.rawad.rapiddrift.renderengine.IEntityRenderer#render(com.rawad.rapiddrift.entity.Entity[])
	 */
	@Override
	public void render(Entity... entities) {
		
		vao.bind();
		vbo.bind();
		ibo.bind();
		
		staticModelShader.use();
		
		for(Entity e: entities) {
			
			TransformComponent transformComp = (TransformComponent) e.getComponent(TransformComponent.class);
			TextureComponent textureComp = (TextureComponent) e.getComponent(TextureComponent.class);
			MeshComponent meshComp = (MeshComponent) e.getComponent(MeshComponent.class);
			
			Matrix4f modelMatrix = TransformComponent.toMatrix(transformComp);
			
			Texture texture = textureComp.getTexture();
			Mesh mesh = meshComp.getMesh();
			
			staticModelShader.setModelMatrix(modelMatrix);
			staticModelShader.setViewMatrix(PerspectiveCameraComponent.toViewMatrix(cameraTransform));
			staticModelShader.setProjectionMatrix(PerspectiveCameraComponent.toMatrix(perspectiveCamera));
			
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			texture.bind();
			staticModelShader.setModelTexture(0);
			
			ibo.uploadData(mesh.getIndices(), GL15.GL_STATIC_DRAW);
			vbo.uploadData(mesh.getData(), GL15.GL_STATIC_DRAW);
			
			staticModelShader.setupVertexAttributes();
			
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glEnable(GL11.GL_CULL_FACE);
			
			GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
			
		}
		
		ibo.unbind();
		vbo.unbind();
		vao.unbind();
		
		staticModelShader.stopUsing();
		
	}
	
	/**
	 * @see com.rawad.rapiddrift.renderengine.IEntityRenderer#terminate()
	 */
	@Override
	public void terminate() {
		
		ibo.delete();
		vbo.delete();
		vao.delete();
		
		staticModelShader.delete();
		
	}
	
}
