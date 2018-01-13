package com.rawad.rapiddrift.window;

import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFWMouseButtonCallbackI;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowCloseCallbackI;
import org.lwjgl.glfw.GLFWWindowSizeCallbackI;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLCapabilities;
import org.lwjgl.system.MemoryUtil;

public final class Window {
	
	private static Window window;
	
	private final long id;
	
	private CharSequence title;
	
	private int prefWidth;
	private int prefHeight;
	
	private int width;
	private int height;
	
	private boolean vsync;
	private boolean fullscreen;
	
	private GLFWWindowCloseCallbackI closeCallback;
	private GLFWKeyCallbackI keyCallback;
	private GLFWMouseButtonCallbackI mouseCallback;
	private GLFWWindowSizeCallbackI sizeCallback;
	
	private Window(long id, CharSequence title, int prefWidth, int prefHeight, boolean vsync) {
		super();
		
		this.id = id;
		
		this.prefWidth = prefWidth;
		this.prefHeight = prefHeight;
		
		this.width = prefWidth;
		this.height = prefHeight;
		
		this.setTitle(title);
		this.setVSync(vsync);
		
	}
	
	public GLFWWindowCloseCallbackI setCloseCallback(GLFWWindowCloseCallbackI callback) {
		
		this.closeCallback = callback;
		
		return GLFW.glfwSetWindowCloseCallback(id, callback);
		
	}
	
	public GLFWKeyCallbackI setKeyCallback(GLFWKeyCallbackI callback) {
		
		this.keyCallback = callback;
		
		return GLFW.glfwSetKeyCallback(id, callback);
		
	}
	
	public GLFWMouseButtonCallbackI setMouseCallback(GLFWMouseButtonCallbackI callback) {
		
		this.mouseCallback = callback;
		
		return GLFW.glfwSetMouseButtonCallback(id, callback);
		
	}
	
	public GLFWWindowSizeCallbackI setSizeCallback(GLFWWindowSizeCallbackI callback) {
		
		this.sizeCallback = callback;
		
		return GLFW.glfwSetWindowSizeCallback(id, callback);
		
	}
	
	public void setTitle(CharSequence title) {
		this.title = title;
		GLFW.glfwSetWindowTitle(id, title);
	}
	
	public void update() {
		
		if(vsync) {
			GLFW.glfwSwapInterval(1);
		} else {
			GLFW.glfwSwapInterval(0);
		}
		
		GLFW.glfwSwapBuffers(id);
		GLFW.glfwPollEvents();
		
	}
	
	/**
	 * Destory window and release callbacks.
	 */
	public void destroy() {
		
		Callbacks.glfwFreeCallbacks(id);
		GLFW.glfwDestroyWindow(id);
		
		GLFW.glfwTerminate();
		GLFW.glfwSetErrorCallback(null).free();
		
	}
	
	/**
	 * @return the fullscreen
	 */
	public boolean isFullscreen() {
		return fullscreen;
	}
	
	/**
	 * 
	 * @param vsync
	 */
	public void setVSync(boolean vsync) {
		
		this.vsync = vsync;
		
	}
	
	/**
	 * 
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * 
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * 
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * 
	 * @return the vsync
	 */
	public boolean isVSyncEnabled() {
		return vsync;
	}
	
	
	/**
	 * @return the prefWidth
	 */
	public int getPrefWidth() {
		return prefWidth;
	}

	
	/**
	 * @param prefWidth the prefWidth to set
	 */
	public void setPrefWidth(int prefWidth) {
		this.prefWidth = prefWidth;
	}

	
	/**
	 * @return the prefHeight
	 */
	public int getPrefHeight() {
		return prefHeight;
	}

	
	/**
	 * @param prefHeight the prefHeight to set
	 */
	public void setPrefHeight(int prefHeight) {
		this.prefHeight = prefHeight;
	}

	
	/**
	 * @return the title
	 */
	public CharSequence getTitle() {
		return title;
	}
	
	/**
	 * @return the keyCallback
	 */
	public GLFWKeyCallbackI getKeyCallback() {
		return keyCallback;
	}
	
	/**
	 * @return the closeCallback
	 */
	public GLFWWindowCloseCallbackI getCloseCallback() {
		return closeCallback;
	}
	
	/**
	 * @return the mouseCallback
	 */
	public GLFWMouseButtonCallbackI getMouseCallback() {
		return mouseCallback;
	}
	
	/**
	 * @return the sizeCallback
	 */
	public GLFWWindowSizeCallbackI getSizeCallback() {
		return sizeCallback;
	}
	
	public void makeFullscreen() {
		this.fullscreen = true;
		
		long monitor = GLFW.glfwGetPrimaryMonitor();
		
		GLFWVidMode vidMode = GLFW.glfwGetVideoMode(monitor);
		
		int monitorWidth = vidMode.width();
		int monitorHeight = vidMode.height();
		
		GLFW.glfwSetWindowMonitor(id, monitor, 0, 0, monitorWidth, monitorHeight, vidMode.refreshRate());
		
	}
	
	public void makeWindowed() {
		this.fullscreen = false;
		
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_TRUE);
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
		
		long monitor = GLFW.glfwGetPrimaryMonitor();
		
		GLFWVidMode vidMode = GLFW.glfwGetVideoMode(monitor);
		
		int monitorWidth = vidMode.width();
		int monitorHeight = vidMode.height();
		
		GLFW.glfwSetWindowMonitor(id, MemoryUtil.NULL, 0, 0, prefWidth, prefHeight, vidMode.refreshRate());
		
		GLFW.glfwSetWindowPos(id, (monitorWidth - prefWidth) / 2, (monitorHeight - prefHeight) / 2);
		
	}
	
	public void toggleFullscreen() {
		
		if(fullscreen) {
			this.makeWindowed();
		} else {
			this.makeFullscreen();
		}
		
	}
	
	private static void initWindowHints() {
		
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
		
		long tempWindow = GLFW.glfwCreateWindow(1, 1, "", MemoryUtil.NULL, MemoryUtil.NULL);
		GLFW.glfwMakeContextCurrent(tempWindow);
		GLCapabilities glCaps = GL.createCapabilities();
		GLFW.glfwDestroyWindow(tempWindow);
		
		GLFW.glfwDefaultWindowHints();
		
		if(glCaps.OpenGL32) {
			
			GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
			GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);
			GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
			GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GLFW.GLFW_TRUE);
			
		} else {
			throw new RuntimeException("OpenGL 3.2 is not suppported; try updating graphics driver.");
		}
		
	}
	
	private static void setWindowedHints() {
		
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_TRUE);
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
		
	}
	
	private static void setFullscreenHints(GLFWVidMode vidMode) {
		
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_TRUE);
		GLFW.glfwWindowHint(GLFW.GLFW_RED_BITS, vidMode.redBits());
		GLFW.glfwWindowHint(GLFW.GLFW_GREEN_BITS, vidMode.greenBits());
		GLFW.glfwWindowHint(GLFW.GLFW_BLUE_BITS, vidMode.blueBits());
		GLFW.glfwWindowHint(GLFW.GLFW_REFRESH_RATE, vidMode.refreshRate());
		
	}
	
	public static Window createWindow(CharSequence title, int width, int height, boolean vsync, boolean fullscreen) {
		
		GLFWErrorCallback.createPrint(System.err).set();
		
		if(!GLFW.glfwInit()) {
			throw new RuntimeException("Could not initialize GLFW.");
		}
		
		Window.initWindowHints();
		
		long monitor = GLFW.glfwGetPrimaryMonitor();
		
		GLFWVidMode vidMode = GLFW.glfwGetVideoMode(monitor);
		
		int monitorWidth = vidMode.width();
		int monitorHeight = vidMode.height();
		
		long id;
		
		if(fullscreen) {
			
			id = GLFW.glfwCreateWindow(vidMode.width(), vidMode.height(), title, monitor, MemoryUtil.NULL);
			
			Window.setFullscreenHints(vidMode);
			
			// Does not work only wheen starting in fullscreen mode. Needs to be manually focused.
			GLFW.glfwFocusWindow(id);
			
		} else {
			
			id = GLFW.glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL);
			
			Window.setWindowedHints();
			
			GLFW.glfwSetWindowPos(id, (monitorWidth - width) / 2, (monitorHeight - height) / 2);
			
		}
		
		if(id == MemoryUtil.NULL) {
			GLFW.glfwTerminate();
			throw new RuntimeException("Failed to create GLFW window.");
		}
		
		GLFW.glfwMakeContextCurrent(id);
		GL.createCapabilities();
		
		window = new Window(id, title, width, height, vsync);
		
		Mouse.init(window);
		Keyboard.init(window);
		
		return window;
		
	}
	
	/**
	 * @return the window
	 */
	public static Window getWindow() {
		return window;
	}
	
}
