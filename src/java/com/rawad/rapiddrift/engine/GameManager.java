package com.rawad.rapiddrift.engine;


/**
 * @author Rawad
 *
 */
public final class GameManager {
	
	private static GameEngine gameEngine;
	
	private GameManager() {}
	
	public static void runGame(IGameLogic gameLogic) {
		
		gameEngine = new GameEngine(gameLogic);
		
		gameEngine.init();
		
		gameEngine.gameLoop();
		
		gameEngine.terminate();
		
	}
	
	/**
	 * @return the gameEngine
	 */
	public static GameEngine getGameEngine() {
		return gameEngine;
	}
	
}
