package com.rawad.rapiddrift.entity;

import java.util.HashMap;

/**
 * @author Rawad
 *
 */
public interface ComponentFactory<T extends Component> {
	
	public abstract T createComponent(HashMap<String, String> data);
	
	/**
	 * This should return the Canonical name of the specific Component class, {@code T}. This is to ensure compatibility with
	 * modding and adding other Components with the same name.
	 * *
	 * @return
	 */
	public abstract String getComponentName();
	
	public abstract HashMap<String, String> getStringData(HashMap<String, String> data, Component comp);
	
	public abstract boolean matchesComponent(Component comp);
	
}
