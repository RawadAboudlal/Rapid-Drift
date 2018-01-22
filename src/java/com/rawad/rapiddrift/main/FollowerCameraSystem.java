package com.rawad.rapiddrift.main;

import com.rawad.rapiddrift.engine.GameSystem;
import com.rawad.rapiddrift.entity.Entity;
import com.rawad.rapiddrift.entity.component.AttachmentComponent;
import com.rawad.rapiddrift.entity.component.ForwardComponent;
import com.rawad.rapiddrift.entity.component.PerspectiveCameraComponent;
import com.rawad.rapiddrift.entity.component.TransformComponent;
import com.rawad.rapiddrift.math.Quaternionf;
import com.rawad.rapiddrift.math.Vector3f;


/**
 * @author Rawad
 *
 */
public class FollowerCameraSystem extends GameSystem {
	
	private static final float MIN_ROTATION_DIFFERENCE = 0.3f;
	private static final float DISTANCE_BEHIND = 5f;
	
	private static final Class<?>[] COMPATIBLE_COMPONENTS = {
			TransformComponent.class,
			PerspectiveCameraComponent.class,
			AttachmentComponent.class,
			ForwardComponent.class,
	};
	
	protected FollowerCameraSystem() {
		super(COMPATIBLE_COMPONENTS);
	}
	
	/**
	 * @see com.rawad.rapiddrift.engine.GameSystem#tick(com.rawad.rapiddrift.entity.Entity)
	 */
	@Override
	protected void tick(Entity cameraEntity) {
		
		AttachmentComponent attachmentComp = (AttachmentComponent) cameraEntity.getComponent(AttachmentComponent.class);
		Entity attachedTo = attachmentComp.getAttachedTo();
		
		if(attachedTo == null || !attachedTo.hasComponents(TransformComponent.class, ForwardComponent.class)) return;
		
		TransformComponent cameraTransform = (TransformComponent) cameraEntity.getComponent(TransformComponent.class);
		TransformComponent attachedToTransform = (TransformComponent) attachedTo.getComponent(TransformComponent.class);
		
		ForwardComponent cameraForwad = (ForwardComponent) cameraEntity.getComponent(ForwardComponent.class);
		ForwardComponent attachedToForward = (ForwardComponent) attachedTo.getComponent(ForwardComponent.class);
		
		Vector3f attachedToPosition = attachedToTransform.getPosition();
		
		Quaternionf attachedToRotation = attachedToTransform.getRotation();
		Quaternionf cameraRotation = cameraTransform.getRotation();
		
		Vector3f attachedToOrientation = attachedToRotation.rotate(attachedToForward.getForwardDirection());
		
//		cameraTransform.setRotation(cameraRotation.multiply(new Quaternionf(new Vector3f(0, 0, -1), 1)));
		
//		Vector3f newCameraPosition = attachedToPosition.subtract(attachedToOrientation.normalize().scale(DISTANCE_BEHIND));
		
//		cameraTransform.setPosition(attachedToPosition.subtract(new Vector3f(0, 0, -1).scale(DISTANCE_BEHIND)));
		
//		cameraTransform.setRotation(new Quaternionf(new Vector3f(0, -1, 0), 0))/;//cameraRotation.multiply(new Quaternionf(new Vector3f(0, 1, 0), 1)));
		
//		System.out.printf("Camera position: %s, Attached to position: %s\nAttached to rotation axis angle: %s\n", 
//				cameraTransform.getPosition(), attachedToPosition, attachedToRotation.toRotationAxisAngle());
		
	}
	
}
