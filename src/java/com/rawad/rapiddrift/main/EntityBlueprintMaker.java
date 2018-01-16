package com.rawad.rapiddrift.main;

import java.io.File;

import com.rawad.rapiddrift.entity.Entity;
import com.rawad.rapiddrift.entity.EntityBlueprintManager;
import com.rawad.rapiddrift.entity.EntityLocator;
import com.rawad.rapiddrift.entity.component.AttachmentComponent;
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
		
		faceTransform.setPosition(new Vector3f(0, 0, -5));
		faceTransform.setRotation(new Quaternionf(new Vector3f(0, 0, -1), 0));
		
		String[] faceTexturePath = {"entity", "face", "face"};
		
		faceTexture.setTexturePath(faceTexturePath);
		
		String[] faceMeshPath = {"entity", "face", "face"};
		
		faceMesh.setMeshPath(faceMeshPath);
		
		face.addComponent(faceTransform).addComponent(faceTexture).addComponent(faceMesh);
		
		Entity camera = new Entity();
		
		TransformComponent cameraTransform = new TransformComponent();
		PerspectiveCameraComponent perspectiveCameraComp = new PerspectiveCameraComponent();
		AttachmentComponent attachmentComp = new AttachmentComponent();
		
		cameraTransform.setRotation(new Quaternionf(0, 0, -1, 0));
		
		perspectiveCameraComp.setNearClip(0.1f);
		perspectiveCameraComp.setFarClip(100f);
		perspectiveCameraComp.setFieldOfView(90);
		
		camera.addComponent(cameraTransform).addComponent(perspectiveCameraComp).addComponent(attachmentComp);
		
		EntityBlueprintManager.addBlueprint(EntityLocator.CAMERA, camera);
		EntityBlueprintManager.addBlueprint(EntityLocator.FACE, face);
		
		// Need to ensure these folder and the entity folder are all already present on file system.
		Loader.saveEntities();
		
//		File entityFolder = new File("src/res/entity/");
//		
//		for(File file: entityFolder.listFiles()) {
//			System.out.println(file.getPath());
//		}
		
		
	}
	
}
