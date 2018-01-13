package com.rawad.rapiddrift.entity;

import java.util.HashMap;

import com.rawad.rapiddrift.math.Quaternionf;
import com.rawad.rapiddrift.math.Vector3f;
import com.rawad.rapiddrift.util.Util;

/**
 * @author Rawad
 *
 */
public class TransformComponentFactory implements ComponentFactory<TransformComponent> {
	
	private static final String POSITION_KEY = "position";
	private static final String SCALE_KEY = "scale";
	private static final String ROTATION_KEY = "rotation";
	
	private static final String VECTOR_SEPARATOR = ",";
	
	private static final int X_INDEX = 0;
	private static final int Y_INDEX = 1;
	private static final int Z_INDEX = 2;
	private static final int W_INDEX = 3;
	
	/**
	 * @see com.rawad.rapiddrift.entity.ComponentFactory#createComponent(java.util.HashMap)
	 */
	@Override
	public TransformComponent createComponent(HashMap<String, String> data) {
		
		TransformComponent transformComp = new TransformComponent();
		
		String[] positionData = data.get(POSITION_KEY).split(VECTOR_SEPARATOR);
		
		transformComp.setPosition(new Vector3f(Util.parseFloat(positionData[X_INDEX]), 
				Util.parseFloat(positionData[Y_INDEX]), Util.parseFloat(positionData[Z_INDEX])));
		
		String[] scaleData = data.get(SCALE_KEY).split(VECTOR_SEPARATOR);
		
		transformComp.setScale(new Vector3f(Util.parseFloat(scaleData[X_INDEX]), 
				Util.parseFloat(scaleData[Y_INDEX]), Util.parseFloat(scaleData[Z_INDEX])));
		
		String[] rotationData = data.get(ROTATION_KEY).split(VECTOR_SEPARATOR);
		
		transformComp.setRotation(new Quaternionf(Util.parseFloat(rotationData[X_INDEX]), 
				Util.parseFloat(rotationData[Y_INDEX]), Util.parseFloat(rotationData[Z_INDEX]), 
				Util.parseFloat(rotationData[W_INDEX])));
		
		return transformComp;
		
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.ComponentFactory#getStringData(java.util.HashMap, com.rawad.rapiddrift.entity.Component)
	 */
	@Override
	public HashMap<String, String> getStringData(HashMap<String, String> data, Component comp) {
		
		TransformComponent transformComp = (TransformComponent) comp;
		
		Vector3f position = transformComp.getPosition();
		data.put(POSITION_KEY, String.join(VECTOR_SEPARATOR, position.x + "", position.y + "", position.z + ""));
		
		Vector3f scale = transformComp.getScale();
		data.put(SCALE_KEY, String.join(VECTOR_SEPARATOR, scale.x + "", scale.y + "", scale.z + ""));
		
		Quaternionf rotation = transformComp.getRotation();
		data.put(ROTATION_KEY, String.join(VECTOR_SEPARATOR, rotation.x + "", rotation.y + "", rotation.z + "", 
				rotation.w + ""));
		
		return data;
		
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.ComponentFactory#getComponentName()
	 */
	@Override
	public String getComponentName() {
		return TransformComponent.class.getCanonicalName();
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.ComponentFactory#matchesComponent(com.rawad.rapiddrift.entity.Component)
	 */
	@Override
	public boolean matchesComponent(Component comp) {
		return comp instanceof TransformComponent;
	}
	
}
