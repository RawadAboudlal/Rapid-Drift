package com.rawad.rapiddrift.entity;

import com.rawad.rapiddrift.math.Matrix4f;
import com.rawad.rapiddrift.window.Window;

/**
 * @author Rawad
 *
 */
public class PerspectiveCameraComponent extends Component {
	
	private float fieldOfView = 1;
	
	private float screenWidth = 1;
	private float screenHeight = 1;
	
	private float nearClip = 0;
	private float farClip = 1;
	
	/**
	 * @return the fieldOfView
	 */
	public float getFieldOfView() {
		return fieldOfView;
	}
	
	/**
	 * @param fieldOfView the fieldOfView to set
	 */
	public void setFieldOfView(float fieldOfView) {
		this.fieldOfView = fieldOfView;
	}
	
	/**
	 * @return the screenWidth
	 */
	public float getScreenWidth() {
		return screenWidth;
	}
	
	/**
	 * @param screenWidth the screenWidth to set
	 */
	public void setScreenWidth(float screenWidth) {
		this.screenWidth = screenWidth;
	}
	
	/**
	 * @return the screenHeight
	 */
	public float getScreenHeight() {
		return screenHeight;
	}
	
	/**
	 * @param screenHeight the screenHeight to set
	 */
	public void setScreenHeight(float screenHeight) {
		this.screenHeight = screenHeight;
	}
	
	/**
	 * @return the nearClip
	 */
	public float getNearClip() {
		return nearClip;
	}
	
	/**
	 * @param nearClip the nearClip to set
	 */
	public void setNearClip(float nearClip) {
		this.nearClip = nearClip;
	}
	
	/**
	 * @return the farClip
	 */
	public float getFarClip() {
		return farClip;
	}
	
	/**
	 * @param farClip the farClip to set
	 */
	public void setFarClip(float farClip) {
		this.farClip = farClip;
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.Component#clone()
	 */
	@Override
	public Component clone() {
		
		PerspectiveCameraComponent perspectiveCameraComp = new PerspectiveCameraComponent();
		
		perspectiveCameraComp.setFieldOfView(this.getFieldOfView());
		perspectiveCameraComp.setScreenWidth(this.getScreenWidth());
		perspectiveCameraComp.setScreenHeight(this.getScreenHeight());
		perspectiveCameraComp.setNearClip(this.getNearClip());
		perspectiveCameraComp.setFarClip(this.getFarClip());
		
		return perspectiveCameraComp;
		
	}
	
	public static Matrix4f toViewMatrix(TransformComponent cameraTransform) {
		// The purposee of this method is to negate the camera position. This makes a difference when directly moving camera.
		return Matrix4f.translate(cameraTransform.getPosition().negate()).multiply(Matrix4f.rotate(cameraTransform.getRotation()))
				.multiply(Matrix4f.scale(cameraTransform.getScale()));
	}
	
	public static Matrix4f toMatrix(PerspectiveCameraComponent perspectiveCamera) {
		return Matrix4f.perspective(perspectiveCamera.getFieldOfView(), (float) Window.getWindow().getWidth() / 
				(float) Window.getWindow().getHeight(), perspectiveCamera.getNearClip(), perspectiveCamera.getFarClip());
	}
	
}
