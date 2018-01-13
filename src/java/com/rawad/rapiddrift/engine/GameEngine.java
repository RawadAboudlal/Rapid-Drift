package com.rawad.rapiddrift.engine;

import org.lwjgl.glfw.GLFW;

/**
 * @author Rawad
 *
 */
public final class GameEngine {
	
	private IGameLogic gameLogic;
	
	private double secondsPerUpdate = 1;
	
	private boolean stopRequested = false;
	
	/**
	 * @param gameLogic
	 */
	protected GameEngine(IGameLogic gameLogic) {
		super();
		
		this.gameLogic = gameLogic;
		
	}
	
	public void init() {
		
		gameLogic.init(this);
		
	}
	
	public void gameLoop() {
		
		double previousTime = GLFW.glfwGetTime();
		double accumulatedTime = 0d;
		
		while(!stopRequested) {
			
			double currentTime = GLFW.glfwGetTime();
			double elapsedTime = currentTime - previousTime;
			
			previousTime = currentTime;
			
			accumulatedTime += elapsedTime;

			gameLogic.preTick();
			
			while(accumulatedTime >= secondsPerUpdate) {
				
				gameLogic.tick();
				
				accumulatedTime -= secondsPerUpdate;
				
			}
			
			gameLogic.postTick();
			
		}
		
	}
	
	public void terminate() {
		
		gameLogic.terminate();
		
	}
	
	public void requestStop() {
		this.stopRequested = true;
	}
	
	/**
	 * @return the gameLogic
	 */
	public IGameLogic getGameLogic() {
		return gameLogic;
	}
	
	/**
	 * @return the stopRequested
	 */
	public boolean isStopRequested() {
		return stopRequested;
	}
	
	/**
	 * @return the secondsPerUpdate
	 */
	public double getSecondsPerUpdate() {
		return secondsPerUpdate;
	}
	
	/**
	 * @param secondsPerUpdate the secondsPerUpdate to set
	 */
	public void setSecondsPerUpdate(double secondsPerUpdate) {
		this.secondsPerUpdate = secondsPerUpdate;
	}
	
}
