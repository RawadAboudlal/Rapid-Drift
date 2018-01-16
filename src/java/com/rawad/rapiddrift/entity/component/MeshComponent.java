package com.rawad.rapiddrift.entity.component;

import com.rawad.rapiddrift.mesh.Mesh;

/**
 * @author Rawad
 *
 */
public class MeshComponent extends Component {
	
	private String[] meshPath;
	private Mesh mesh;
	
	/**
	 * @return the meshPath
	 */
	public String[] getMeshPath() {
		return meshPath;
	}
	
	/**
	 * @param meshPath the meshPath to set
	 */
	public void setMeshPath(String[] meshPath) {
		this.meshPath = meshPath;
	}
	
	/**
	 * @return the mesh
	 */
	public Mesh getMesh() {
		return mesh;
	}
	
	/**
	 * @param mesh the mesh to set
	 */
	public void setMesh(Mesh mesh) {
		this.mesh = mesh;
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.component.Component#clone()
	 */
	@Override
	public MeshComponent clone() {
		
		MeshComponent meshComp = new MeshComponent();
		
		meshComp.setMeshPath(this.getMeshPath().clone());
		meshComp.setMesh(this.getMesh().clone());
		
		return meshComp;
		
	}
	
}
