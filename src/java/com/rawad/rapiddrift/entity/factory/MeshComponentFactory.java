package com.rawad.rapiddrift.entity.factory;

import java.util.HashMap;

import com.rawad.rapiddrift.entity.component.Component;
import com.rawad.rapiddrift.entity.component.MeshComponent;
import com.rawad.rapiddrift.util.IOUtils;
import com.rawad.rapiddrift.util.Loader;

/**
 * @author Rawad
 *
 */
public class MeshComponentFactory implements ComponentFactory<MeshComponent> {
	
	private static final String MESH_KEY = "mesh";
	
	/**
	 * @see com.rawad.rapiddrift.entity.factory.ComponentFactory#createComponent(java.util.HashMap)
	 */
	@Override
	public MeshComponent createComponent(HashMap<String, String> data) {
		
		MeshComponent meshComp = new MeshComponent();
		
		String[] meshPath = data.get(MESH_KEY).split(IOUtils.FILE_SEPARATOR);
		
		meshComp.setMeshPath(meshPath);
		meshComp.setMesh(Loader.loadMesh(data.get(Loader.ENTITY_NAME_KEY), meshPath));
		
		return meshComp;
		
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.factory.ComponentFactory#getStringData(java.util.HashMap, com.rawad.rapiddrift.entity.component.Component)
	 */
	@Override
	public HashMap<String, String> getStringData(HashMap<String, String> data, Component comp) {
		
		MeshComponent meshComp = (MeshComponent) comp;
		
		data.put(MESH_KEY, String.join(IOUtils.FILE_SEPARATOR, meshComp.getMeshPath()));
		
		return data;
		
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.factory.ComponentFactory#getComponentName()
	 */
	@Override
	public String getComponentName() {
		return MeshComponent.class.getCanonicalName();
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.factory.ComponentFactory#matchesComponent(com.rawad.rapiddrift.entity.component.Component)
	 */
	@Override
	public boolean matchesComponent(Component comp) {
		return comp instanceof MeshComponent;
	}
	
}
