package com.rawad.rapiddrift.entity.factory;

import java.util.HashMap;

import com.rawad.rapiddrift.entity.component.Component;
import com.rawad.rapiddrift.entity.component.PerspectiveCameraComponent;
import com.rawad.rapiddrift.util.Utils;

/**
 * @author Rawad
 *
 */
public class PerspectiveCameraComponentFactory implements ComponentFactory<PerspectiveCameraComponent> {
	
	private static final String FIELD_OF_VIEW_KEY = "field_of_view";
	private static final String NEAR_CLIP_KEY = "near_clip";
	private static final String FAR_CLIP_KEY = "far_clip";
	
	/**
	 * @see com.rawad.rapiddrift.entity.factory.ComponentFactory#createComponent(java.util.HashMap)
	 */
	@Override
	public PerspectiveCameraComponent createComponent(HashMap<String, String> data) {
		
		PerspectiveCameraComponent perspectiveCameraComp = new PerspectiveCameraComponent();
		
		perspectiveCameraComp.setFieldOfView(Utils.parseFloat(data.get(FIELD_OF_VIEW_KEY)));
		perspectiveCameraComp.setNearClip(Utils.parseFloat(data.get(NEAR_CLIP_KEY)));
		perspectiveCameraComp.setFarClip(Utils.parseFloat(data.get(FAR_CLIP_KEY)));
		
		return perspectiveCameraComp;
		
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.factory.ComponentFactory#getStringData(java.util.HashMap, com.rawad.rapiddrift.entity.component.Component)
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
	 * @see com.rawad.rapiddrift.entity.factory.ComponentFactory#getComponentName()
	 */
	@Override
	public String getComponentName() {
		return PerspectiveCameraComponent.class.getCanonicalName();
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.factory.ComponentFactory#matchesComponent(com.rawad.rapiddrift.entity.component.Component)
	 */
	@Override
	public boolean matchesComponent(Component comp) {
		return comp instanceof PerspectiveCameraComponent;
	}
	
}