package com.rawad.rapiddrift.entity;

import java.util.HashMap;

import com.rawad.rapiddrift.util.Util;

/**
 * @author Rawad
 *
 */
public class PerspectiveCameraComponentFactory implements ComponentFactory<PerspectiveCameraComponent> {
	
	private static final String FIELD_OF_VIEW_KEY = "field_of_view";
	private static final String NEAR_CLIP_KEY = "near_clip";
	private static final String FAR_CLIP_KEY = "far_clip";
	
	/**
	 * @see com.rawad.rapiddrift.entity.ComponentFactory#createComponent(java.util.HashMap)
	 */
	@Override
	public PerspectiveCameraComponent createComponent(HashMap<String, String> data) {
		
		PerspectiveCameraComponent perspectiveCameraComp = new PerspectiveCameraComponent();
		
		perspectiveCameraComp.setFieldOfView(Util.parseFloat(data.get(FIELD_OF_VIEW_KEY)));
		perspectiveCameraComp.setNearClip(Util.parseFloat(data.get(NEAR_CLIP_KEY)));
		perspectiveCameraComp.setFarClip(Util.parseFloat(data.get(FAR_CLIP_KEY)));
		
		return perspectiveCameraComp;
		
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.ComponentFactory#getStringData(java.util.HashMap, com.rawad.rapiddrift.entity.Component)
	 */
	@Override
	public HashMap<String, String> getStringData(HashMap<String, String> data, Component comp) {
		
		PerspectiveCameraComponent perspectiveCameraComp = (PerspectiveCameraComponent) comp;
		
		data.put(FIELD_OF_VIEW_KEY, String.valueOf(perspectiveCameraComp.getFieldOfView()));
		data.put(NEAR_CLIP_KEY, String.valueOf(perspectiveCameraComp.getNearClip()));
		data.put(FAR_CLIP_KEY, String.valueOf(perspectiveCameraComp.getFarClip()));
		
		return data;
		
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.ComponentFactory#getComponentName()
	 */
	@Override
	public String getComponentName() {
		return PerspectiveCameraComponent.class.getCanonicalName();
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.ComponentFactory#matchesComponent(com.rawad.rapiddrift.entity.Component)
	 */
	@Override
	public boolean matchesComponent(Component comp) {
		return comp instanceof PerspectiveCameraComponent;
	}
	
}
