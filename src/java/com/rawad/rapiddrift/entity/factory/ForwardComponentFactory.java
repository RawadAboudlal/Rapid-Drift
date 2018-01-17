package com.rawad.rapiddrift.entity.factory;

import java.util.HashMap;

import com.rawad.rapiddrift.entity.component.Component;
import com.rawad.rapiddrift.entity.component.ForwardComponent;
import com.rawad.rapiddrift.math.Vector3f;
import com.rawad.rapiddrift.util.Utils;

public class ForwardComponentFactory implements ComponentFactory<ForwardComponent> {
	
	private static final String FORWARD_DIRECTION_KEY = "forward_direction";
	
	private static final String VECTOR_SEPARATOR = ",";
	
	private static final int X_INDEX = 0;
	private static final int Y_INDEX = 1;
	private static final int Z_INDEX = 2;
	
	/**
	 * @see com.rawad.rapiddrift.entity.factory.ComponentFactory#createComponent(java.util.HashMap)
	 */
	@Override
	public ForwardComponent createComponent(HashMap<String, String> data) {
		
		ForwardComponent forwardComp = new ForwardComponent();
		
		String[] forwardDirectionData = data.get(FORWARD_DIRECTION_KEY).split(VECTOR_SEPARATOR);
		
		forwardComp.setForwardDirection(new Vector3f(Utils.parseFloat(forwardDirectionData[X_INDEX]), 
				Utils.parseFloat(forwardDirectionData[Y_INDEX]), Utils.parseFloat(forwardDirectionData[Z_INDEX])));
		
		return forwardComp;
		
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.factory.ComponentFactory#getStringData(java.util.HashMap, com.rawad.rapiddrift.entity.component.Component)
	 */
	@Override
	public HashMap<String, String> getStringData(HashMap<String, String> data, Component comp) {
		
		ForwardComponent forwardComp = (ForwardComponent) comp;
		
		Vector3f forwardDirection = forwardComp.getForwardDirection();
		
		data.put(FORWARD_DIRECTION_KEY, String.join(VECTOR_SEPARATOR, forwardDirection.x + "", 
				forwardDirection.y + "", forwardDirection.z + ""));
		
		return data;
		
	}

	/**
	 * @see com.rawad.rapiddrift.entity.factory.ComponentFactory#getComponentName()
	 */
	@Override
	public String getComponentName() {
		return ForwardComponent.class.getCanonicalName();
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.factory.ComponentFactory#matchesComponent(com.rawad.rapiddrift.entity.component.Component)
	 */
	@Override
	public boolean matchesComponent(Component comp) {
		return comp instanceof ForwardComponent;
	}

}
