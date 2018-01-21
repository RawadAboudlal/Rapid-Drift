package com.rawad.rapiddrift.entity;


/**
 * @author Rawad
 *
 */
public enum EntityLocator {
	
	CAMERA("camera"),
	FACE("face"),
	RING("ring");
	
	private final String name;
	
	/**
	 * @param name
	 */
	private EntityLocator(String name) {
		this.name = name;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.getName();
	}
	
}
