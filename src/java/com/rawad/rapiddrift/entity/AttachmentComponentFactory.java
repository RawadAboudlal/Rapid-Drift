package com.rawad.rapiddrift.entity;

import java.util.HashMap;

/**
 * @author Rawad
 *
 */
public class AttachmentComponentFactory implements ComponentFactory<AttachmentComponent> {
	
	/**
	 * @see com.rawad.rapiddrift.entity.ComponentFactory#createComponent(java.util.HashMap)
	 */
	@Override
	public AttachmentComponent createComponent(HashMap<String, String> data) {
		
		AttachmentComponent attachmentComp = new AttachmentComponent();
		
		return attachmentComp;
		
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.ComponentFactory#getComponentName()
	 */
	@Override
	public String getComponentName() {
		return AttachmentComponent.class.getCanonicalName();
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.ComponentFactory#getStringData(java.util.HashMap, com.rawad.rapiddrift.entity.Component)
	 */
	@Override
	public HashMap<String, String> getStringData(HashMap<String, String> data, Component comp) {
		return data;
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.ComponentFactory#matchesComponent(com.rawad.rapiddrift.entity.Component)
	 */
	@Override
	public boolean matchesComponent(Component comp) {
		return comp instanceof AttachmentComponent;
	}
	
}
