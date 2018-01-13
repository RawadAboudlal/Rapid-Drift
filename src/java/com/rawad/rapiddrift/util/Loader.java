package com.rawad.rapiddrift.util;

import com.rawad.rapiddrift.entity.AttachmentComponentFactory;
import com.rawad.rapiddrift.entity.ComponentFactory;
import com.rawad.rapiddrift.entity.EntityBlueprintLoader;
import com.rawad.rapiddrift.entity.EntityBlueprintManager;
import com.rawad.rapiddrift.entity.EntityLocator;
import com.rawad.rapiddrift.entity.MeshComponentFactory;
import com.rawad.rapiddrift.entity.PerspectiveCameraComponentFactory;
import com.rawad.rapiddrift.entity.TextureComponentFactory;
import com.rawad.rapiddrift.entity.TransformComponentFactory;

/**
 * @author Rawad
 *
 */
public final class Loader {
	
	private static final String ENTITY_FOLDER = "entity";
	
	private static final String ENTITY_BLUEPRINT_FILE_NAME = "blueprint";
	
	private static final EntityBlueprintLoader entityBlueprintLoader = new EntityBlueprintLoader(new ComponentFactory<?>[] {
		new TransformComponentFactory(),
		new MeshComponentFactory(),
		new TextureComponentFactory(),
		new PerspectiveCameraComponentFactory(),
		new AttachmentComponentFactory(),
	});
	
	public static void loadEntities() {
		
		for(EntityLocator entityLocator: EntityLocator.values()) {
			EntityBlueprintManager.addBlueprint(entityLocator, entityBlueprintLoader.loadEntityBlueprint(
					ENTITY_FOLDER, entityLocator.getName(), ENTITY_BLUEPRINT_FILE_NAME));
		}
		
	}
	
	public static void saveEntities(String... basePath) {
		
		String[] path = new String[basePath.length + 3];// + 3 for ENTITY_FOLDER, key, ENTITY_BLUEPRINT_FILE_NAME
		
		for(int i = 0; i < basePath.length; i++) {
			path[i] = basePath[i];
		}
		
		int i = basePath.length;
		
		path[i++] = ENTITY_FOLDER;
		
		final int keyLocation = i++;
		
		path[i++] = ENTITY_BLUEPRINT_FILE_NAME;
		
		for(String key: EntityBlueprintManager.getEntityKeys()) {
			
			path[keyLocation] = key;
			
			// Don't create an entity, just get the blueprint. Causes errors with meshes and textures aren't being saved.
			entityBlueprintLoader.saveEntityBlueprint(EntityBlueprintManager.getBlueprint(key), path);
			
		}
		
	}
	
}
