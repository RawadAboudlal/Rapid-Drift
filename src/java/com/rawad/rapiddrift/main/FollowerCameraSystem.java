package com.rawad.rapiddrift.main;

import com.rawad.rapiddrift.engine.GameSystem;
import com.rawad.rapiddrift.entity.AttachmentComponent;
import com.rawad.rapiddrift.entity.Entity;
import com.rawad.rapiddrift.entity.PerspectiveCameraComponent;
import com.rawad.rapiddrift.entity.TransformComponent;
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
		
		if(attachedTo == null || !attachedTo.hasComponents(TransformComponent.class)) return;
		
		TransformComponent cameraTransform = (TransformComponent) cameraEntity.getComponent(TransformComponent.class);
		TransformComponent attachedToTransform = (TransformComponent) attachedTo.getComponent(TransformComponent.class);
		
		Vector3f attachedToPosition = attachedToTransform.getPosition();
		
		Quaternionf attachedToRotation = attachedToTransform.getRotation();
		Quaternionf cameraRotation = cameraTransform.getRotation();
		
		Vector3f cameraOrientation = attachedToRotation.toVector3f();
		Vector3f attachedToOrientation = cameraRotation.toVector3f();
		
//		Vector3f newCameraPosition = attachedToPosition.subtract(attachedToOrientation.normalize().scale(DISTANCE_BEHIND));
		
		cameraTransform.setPosition(attachedToPosition.add(new Vector3f(0, 0, DISTANCE_BEHIND)));
		
		cameraTransform.setRotation(attachedToRotation);
		
//		transformComp.setRotation(transformComp.getRotation().multiply(new Quaternionf(new Vector3f(0, 1, 0), 1)));
//		transformComp.setRotation(new Quaternionf(attachedToPosition.subtract(newCameraPosition)));
		
//		if(cameraOrientation.dot(attachedToOrientation) <= MIN_ROTATION_DIFFERENCE) {
//			transformComp.setRotation(attachedToTransform.getRotation());
//		} else {
			
//			Vector3f newCameraOrientation = cameraOrientation.add(attachedToOrientation).divide(2);
			
//			transformComp.setRotation(new Quaternionf(newCameraOrientation));
			
//		}
		
	}
	
}
