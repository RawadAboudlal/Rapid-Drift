package com.rawad.rapiddrift.engine;

import java.util.ArrayList;

import com.rawad.rapiddrift.entity.Entity;

/**
 * @author Rawad
 *
 */
public abstract class GameSystem {
	
	private Class<?>[] compatibleComponents;
	
	protected ArrayList<Entity> compatibleEntities;
	
	/**
	 * 
	 * @param compatibleComponents
	 */
	protected GameSystem(Class<?>[] compatibleComponents) {
		super();
		
		this.compatibleComponents = compatibleComponents;
		
		this.compatibleEntities = new ArrayList<Entity>();
		
	}
	
	public final void preTick(Entity... entities) {
		
		compatibleEntities.clear();
		
		for(Entity e: entities) {
			if(e.hasComponents(compatibleComponents)) {
				compatibleEntities.add(e);
			}
		}
		
	}
	
	public final void tick() {
		
		for(Entity e: compatibleEntities) {
			this.tick(e);
		}
		
	}
	
	protected abstract void tick(Entity e);
	
	/**
	 * @return the compatibleComponents
	 */
	public Class<?>[] getCompatibleComponents() {
		return compatibleComponents;
	}
	
}
