package com.rawad.rapiddrift.entity;

import java.util.Collection;
import java.util.HashMap;

/**
 * @author Rawad
 *
 */
public final class Entity {
	
	private HashMap<Class<? extends Component>, Component> components = new HashMap<Class<? extends Component>, Component>();
	
	public Entity addComponent(Component comp) {
		
		components.put(comp.getClass(), comp);
		
		return this;
		
	}
	
	public Component getComponent(Class<? extends Component> key) {
		return components.get(key);
	}
	
	public boolean hasComponents(Class<?>... keys) {
		
		for(Class<?> key: keys) {
			if(!components.containsKey(key)) {
				return false;
			}
		}
		
		return true;
		
	}
	
	protected Collection<Component> getComponents() {
		return components.values();
	}
	
	/**
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Entity clone() {
		
		Entity e = new Entity();
		
		for(Component comp: components.values()) {
			e.addComponent(comp.clone());
		}
		
		return e;
		
	}
	
}
