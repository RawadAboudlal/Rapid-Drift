package com.rawad.rapiddrift.main;

import com.rawad.rapiddrift.entity.Entity;
import com.rawad.rapiddrift.entity.EntityBlueprintManager;
import com.rawad.rapiddrift.entity.EntityLocator;
import com.rawad.rapiddrift.entity.component.AttachmentComponent;
import com.rawad.rapiddrift.entity.component.ForwardComponent;
import com.rawad.rapiddrift.entity.component.MeshComponent;
import com.rawad.rapiddrift.entity.component.PerspectiveCameraComponent;
import com.rawad.rapiddrift.entity.component.TextureComponent;
import com.rawad.rapiddrift.entity.component.TransformComponent;
import com.rawad.rapiddrift.math.Quaternionf;
import com.rawad.rapiddrift.math.Vector3f;
import com.rawad.rapiddrift.util.Loader;

/**
 * @author Rawad
 *
 */
public class EntityBlueprintMaker {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Entity face = new Entity();
		
		TransformComponent faceTransform = new TransformComponent();
		TextureComponent faceTexture = new TextureComponent();
		MeshComponent faceMesh = new MeshComponent();
		ForwardComponent faceForward = new ForwardComponent();
		
		faceTransform.setPosition(new Vector3f(0, 0, 5));
		faceTransform.setRotation(new Quaternionf(new Vector3f(0, 0, -1), 0));
		
		String[] faceTexturePath = {"face"};
		faceTexture.setTexturePath(faceTexturePath);
		
		String[] faceMeshPath = {"face"};
		faceMesh.setMeshPath(faceMeshPath);
		
		faceForward.setForwardDirection(new Vector3f(0, 0, -1));
		
		face.addComponent(faceTransform).addComponent(faceTexture).addComponent(faceMesh).addComponent(faceForward);
		
		Entity camera = new Entity();
		
		TransformComponent cameraTransform = new TransformComponent();
		PerspectiveCameraComponent perspectiveCameraComp = new PerspectiveCameraComponent();
		AttachmentComponent attachmentComp = new AttachmentComponent();
		ForwardComponent cameraForward = new ForwardComponent();
		
		cameraTransform.setRotation(new Quaternionf(new Vector3f(0, 0, -1), 0));
		
		perspectiveCameraComp.setNearClip(0.1f);
		perspectiveCameraComp.setFarClip(100f);
		perspectiveCameraComp.setFieldOfView(90);
		
		cameraForward.setForwardDirection(new Vector3f(0, 0, -1));
		
		camera.addComponent(cameraTransform).addComponent(perspectiveCameraComp).addComponent(attachmentComp)
		.addComponent(cameraForward);
		
		Entity ring = new Entity();
		
		TransformComponent ringTransform = new TransformComponent();
		MeshComponent ringMesh = new MeshComponent();
		TextureComponent ringTexture = new TextureComponent();
		
		ringTransform.setPosition(new Vector3f(0, 5, -5));
		
		String[] ringMeshPath = {"torus"};
		ringMesh.setMeshPath(ringMeshPath);
		
		String[] ringTexturePath = {"unknown"};
		ringTexture.setTexturePath(ringTexturePath);
		
		ring.addComponent(ringTransform).addComponent(ringMesh).addComponent(ringTexture);
		
		EntityBlueprintManager.addBlueprint(EntityLocator.CAMERA, camera);
		EntityBlueprintManager.addBlueprint(EntityLocator.FACE, face);
		EntityBlueprintManager.addBlueprint(EntityLocator.RING, ring);
		
		// Need to ensure these folder and the entity folder are all already present on file system.
		Loader.saveEntities();
		
	}
	
}
