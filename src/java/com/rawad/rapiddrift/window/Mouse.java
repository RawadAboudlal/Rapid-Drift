package com.rawad.rapiddrift.window;

import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

/**
 * @author Rawad
 *
 */
public final class Mouse {
	
	private static Window window = Window.getWindow();
	
	private static DoubleBuffer mouseX;
	private static DoubleBuffer mouseY;
	
	private Mouse() {}
	
	public static void init(Window window) {
		
		Mouse.window = window;
		
		mouseX = BufferUtils.createDoubleBuffer(1);
		mouseY = BufferUtils.createDoubleBuffer(1);
		
		window.setSizeCallback((callbackWindowId, newWidth, newHeight) -> {
			
			window.setWidth(newWidth);
			window.setHeight(newHeight);
			
			GL11.glViewport(0, 0, newWidth, newHeight);
			
		});
		
	}
	
	public static void update() {
		GLFW.glfwGetCursorPos(window.getId(), mouseX, mouseY);
	}
	
	public boolean isLeftMousePressed() {
		return this.getMouseButton(GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS;
	}
	
	public boolean isRightMousePressed() {
		return this.getMouseButton(GLFW.GLFW_MOUSE_BUTTON_RIGHT) == GLFW.GLFW_PRESS;
	}
	
	public boolean isMiddleMousePressed() {
		return this.getMouseButton(GLFW.GLFW_MOUSE_BUTTON_MIDDLE) == GLFW.GLFW_PRESS;
	}
	
	public int getMouseButton(int button) {
		return GLFW.glfwGetMouseButton(window.getId(), button);
	}
	
	public double getMouseX() {
		return mouseX.get(0);
	}
	
	public double getMouseY() {
		return mouseY.get(0);
	}
	
	
}
