package com.rawad.rapiddrift.entity;

import com.rawad.rapiddrift.math.Matrix4f;
import com.rawad.rapiddrift.math.Quaternionf;
import com.rawad.rapiddrift.math.Vector3f;

/**
 * @author Rawad
 *
 */
public class TransformComponent extends Component {
	
	private Vector3f position = new Vector3f();
	private Vector3f scale = new Vector3f(1f, 1f, 1f);
	private Quaternionf rotation = new Quaternionf();
	
	/**
	 * @return the position
	 */
	public Vector3f getPosition() {
		return position;
	}
	
	/**
	 * @param position the position to set
	 */
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	
	/**
	 * @return the scale
	 */
	public Vector3f getScale() {
		return scale;
	}
	
	/**
	 * @param scale the scale to set
	 */
	public void setScale(Vector3f scale) {
		this.scale = scale;
	}
	
	/**
	 * @return the rotation
	 */
	public Quaternionf getRotation() {
		return rotation;
	}
	
	/**
	 * @param rotation the rotation to set
	 */
	public void setRotation(Quaternionf rotation) {
		this.rotation = rotation;
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.Component#clone()
	 */
	@Override
	public Component clone() {
		
		TransformComponent transformComp = new TransformComponent();
		
		transformComp.setPosition(this.position.clone());
		transformComp.setScale(this.scale.clone());
		transformComp.setRotation(this.rotation.clone());
		
		return transformComp;
		
	}
	
	public static Matrix4f toMatrix(TransformComponent comp) {
		return Matrix4f.translate(comp.getPosition()).multiply(Matrix4f.rotate(comp.getRotation())).multiply(
				Matrix4f.scale(comp.getScale()));
	}
	
}
