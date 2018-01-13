package com.rawad.rapiddrift.renderengine;

import com.rawad.rapiddrift.entity.Entity;

/**
 * @author Rawad
 *
 */
public interface IEntityRenderer {
	
	public void render(Entity... entities);
	
	public void terminate();
	
}
