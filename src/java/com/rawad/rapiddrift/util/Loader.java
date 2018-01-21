package com.rawad.rapiddrift.util;

import java.io.File;
import java.util.HashMap;

import com.rawad.rapiddrift.entity.EntityBlueprintLoader;
import com.rawad.rapiddrift.entity.EntityBlueprintManager;
import com.rawad.rapiddrift.entity.factory.AttachmentComponentFactory;
import com.rawad.rapiddrift.entity.factory.ComponentFactory;
import com.rawad.rapiddrift.entity.factory.ForwardComponentFactory;
import com.rawad.rapiddrift.entity.factory.MeshComponentFactory;
import com.rawad.rapiddrift.entity.factory.PerspectiveCameraComponentFactory;
import com.rawad.rapiddrift.entity.factory.TextureComponentFactory;
import com.rawad.rapiddrift.entity.factory.TransformComponentFactory;
import com.rawad.rapiddrift.mesh.Mesh;
import com.rawad.rapiddrift.mesh.ObjFileLoader;
import com.rawad.rapiddrift.renderengine.texture.Texture;
import com.rawad.rapiddrift.renderengine.texture.TextureLoader;

/**
 * @author Rawad
 *
 */
public final class Loader {
	
	public static final String ENTITY_NAME_KEY = "entity_name";
	
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
		new ForwardComponentFactory(),
	});
	
	public static void loadEntities() {
		
		File entitiesFolder = new File(IOUtils.getPath(ENTITY_BLUEPRINT_PATH));
		
		HashMap<String, String> data = new HashMap<String, String>();
		
		for(File entityFolder: entitiesFolder.listFiles()) {
			
			String entityName = entityFolder.getName();
			
			data.put(ENTITY_NAME_KEY, entityName);
			
			EntityBlueprintManager.addBlueprint(entityName, entityBlueprintLoader.loadEntityBlueprint(data,
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
	
	public static Mesh loadMesh(String entityName, String... meshPath) {
		return ObjFileLoader.loadMesh(IOUtils.mergePaths(IOUtils.mergePaths(ENTITY_BLUEPRINT_PATH, entityName), meshPath));
	}
	
	public static Texture loadTexture(String entityName, String... texturePath) {
		return TextureLoader.loadTexture(IOUtils.mergePaths(IOUtils.mergePaths(ENTITY_BLUEPRINT_PATH, entityName), texturePath));
	}
	
}
