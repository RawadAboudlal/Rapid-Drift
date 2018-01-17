package com.rawad.rapiddrift.util;

import java.io.File;

import com.rawad.rapiddrift.entity.EntityBlueprintLoader;
import com.rawad.rapiddrift.entity.EntityBlueprintManager;
import com.rawad.rapiddrift.entity.factory.AttachmentComponentFactory;
import com.rawad.rapiddrift.entity.factory.ComponentFactory;
import com.rawad.rapiddrift.entity.factory.MeshComponentFactory;
import com.rawad.rapiddrift.entity.factory.PerspectiveCameraComponentFactory;
import com.rawad.rapiddrift.entity.factory.TextureComponentFactory;
import com.rawad.rapiddrift.entity.factory.TransformComponentFactory;

/**
 * @author Rawad
 *
 */
public final class Loader {
	
	// TODO: Use this instead of other stuff. EntityBlueprintMaker has way of getting all directories in entity package.
	private static final String[] ENTITY_BLUEPRINT_PATH = {
			"src",
			"res",
			"entity"
	};
	
	private static final String ENTITY_BLUEPRINT_FILE_NAME = "blueprint";
	
	private static final EntityBlueprintLoader entityBlueprintLoader = new EntityBlueprintLoader(new ComponentFactory<?>[] {
		new TransformComponentFactory(),
		new MeshComponentFactory(),
		new TextureComponentFactory(),
		new PerspectiveCameraComponentFactory(),
		new AttachmentComponentFactory(),
	});
	
	public static void loadEntities() {
		
		File entitiesFolder = new File(Utils.getPath(ENTITY_BLUEPRINT_PATH));
		
		for(File entityFolder: entitiesFolder.listFiles()) {
			EntityBlueprintManager.addBlueprint(entityFolder.getName(), entityBlueprintLoader.loadEntityBlueprint(
					entityFolder.getPath(), ENTITY_BLUEPRINT_FILE_NAME));
		}
		
	}
	
	public static void saveEntities() {
		
		String[] path = new String[ENTITY_BLUEPRINT_PATH.length + 2];// + 2 because of key and ENTITY_BLUEPRINT_FILE_NAME
		
		for(int i = 0; i < ENTITY_BLUEPRINT_PATH.length; i++) {
			path[i] = ENTITY_BLUEPRINT_PATH[i];
		}
		
		int i = ENTITY_BLUEPRINT_PATH.length;
		
		final int keyLocation = i++;
		
		path[i++] = ENTITY_BLUEPRINT_FILE_NAME;
		
		for(String key: EntityBlueprintManager.getEntityKeys()) {
			
			path[keyLocation] = key;
			
			// Don't create an entity, just get the blueprint. Causes errors with meshes and textures aren't being saved.
			entityBlueprintLoader.saveEntityBlueprint(EntityBlueprintManager.getBlueprint(key), path);
			
		}
		
	}
	
}
