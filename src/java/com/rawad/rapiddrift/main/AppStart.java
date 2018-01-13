package com.rawad.rapiddrift.main;

import com.rawad.rapiddrift.engine.GameManager;

/**
 * @author Rawad
 *
 */
public class AppStart {
	
	private static GameLogic gameLogic;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		gameLogic = new GameLogic();
		
		GameManager.runGame(gameLogic);
		
	}
	
}
