package com.rawad.rapiddrift.window;

import org.lwjgl.glfw.GLFW;

/**
 * @author Rawad
 *
 */
public final class Keyboard {
	
	private static Window window;
	
	private Keyboard() {}
	
	public static void init(Window window) {
		
		Keyboard.window = window;
		
		window.setKeyCallback((id, key, scancode, action, mode) -> {
			
		});
		
	}
	
	public static boolean isKeyDown(int key) {
		return GLFW.glfwGetKey(window.getId(), key) == GLFW.GLFW_PRESS;
	}
	
}
