package com.rawad.rapiddrift.entity;

import java.util.HashMap;
import java.util.Set;

/**
 * @author Rawad
 *
 */
public final class EntityBlueprintManager {
	
	private static final HashMap<String, Entity> blueprints = new HashMap<String, Entity>();
	
	public static Entity getBlueprint(String key) {
		return blueprints.get(key);
	}
	
	public static Entity getBlueprint(Object key) {
		return EntityBlueprintManager.getBlueprint(key.toString());
	}
	
	public static void addBlueprint(String key, Entity blueprint) {
		blueprints.put(key, blueprint);
	}
	
	public static Entity createEntity(String key) {
		return EntityBlueprintManager.getBlueprint(key).clone();
	}
	
	public static void addBlueprint(Object key, Entity blueprint) {
		EntityBlueprintManager.addBlueprint(key.toString(),  blueprint);
	}
	
	public static Entity createEntity(Object key) {
		return EntityBlueprintManager.getBlueprint(key).clone();
	}
	
	public static Set<String> getEntityKeys() {
		return blueprints.keySet();
	}
	
}
