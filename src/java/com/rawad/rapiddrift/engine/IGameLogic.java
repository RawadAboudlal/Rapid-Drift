package com.rawad.rapiddrift.engine;

/**
 * @author Rawad
 *
 */
public interface IGameLogic {
	
	public void init(GameEngine gameEngine);
	
	/**
	 * Called once from {@code GameEngine} before game ticks are processed.
	 */
	public void preTick();
	
	public void tick();
	
	/**
	 * Called once from {@code GameEngine} after game ticks are processed.
	 */
	public void postTick();
	
	public void terminate();
	
}
