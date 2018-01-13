package com.rawad.rapiddrift.entity;

import java.util.HashMap;

import com.rawad.rapiddrift.mesh.ObjFileLoader;
import com.rawad.rapiddrift.util.Util;

/**
 * @author Rawad
 *
 */
public class MeshComponentFactory implements ComponentFactory<MeshComponent> {
	
	private static final String MESH_KEY = "mesh";
	
	/**
	 * @see com.rawad.rapiddrift.entity.ComponentFactory#createComponent(java.util.HashMap)
	 */
	@Override
	public MeshComponent createComponent(HashMap<String, String> data) {
		
		MeshComponent meshComp = new MeshComponent();
		
		String[] meshPath = data.get(MESH_KEY).split(Util.FILE_SEPARATOR);
		
		meshComp.setMeshPath(meshPath);
		meshComp.setMesh(ObjFileLoader.loadMesh(meshPath));
		
		return meshComp;
		
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.ComponentFactory#getStringData(java.util.HashMap, com.rawad.rapiddrift.entity.Component)
	 */
	@Override
	public HashMap<String, String> getStringData(HashMap<String, String> data, Component comp) {
		
		MeshComponent meshComp = (MeshComponent) comp;
		
		data.put(MESH_KEY, String.join(Util.FILE_SEPARATOR, meshComp.getMeshPath()));
		
		return data;
		
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.ComponentFactory#getComponentName()
	 */
	@Override
	public String getComponentName() {
		return MeshComponent.class.getCanonicalName();
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.ComponentFactory#matchesComponent(com.rawad.rapiddrift.entity.Component)
	 */
	@Override
	public boolean matchesComponent(Component comp) {
		return comp instanceof MeshComponent;
	}
	
}
