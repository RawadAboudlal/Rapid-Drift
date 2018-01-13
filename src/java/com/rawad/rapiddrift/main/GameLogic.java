package com.rawad.rapiddrift.main;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallbackI;

import com.rawad.rapiddrift.engine.GameEngine;
import com.rawad.rapiddrift.engine.GameSystem;
import com.rawad.rapiddrift.engine.IGameLogic;
import com.rawad.rapiddrift.entity.AttachmentComponent;
import com.rawad.rapiddrift.entity.Entity;
import com.rawad.rapiddrift.entity.EntityBlueprintManager;
import com.rawad.rapiddrift.entity.EntityLocator;
import com.rawad.rapiddrift.entity.TextureComponent;
import com.rawad.rapiddrift.entity.TransformComponent;
import com.rawad.rapiddrift.math.Quaternionf;
import com.rawad.rapiddrift.math.Vector3f;
import com.rawad.rapiddrift.renderengine.IRenderer;
import com.rawad.rapiddrift.renderengine.MasterRenderer;
import com.rawad.rapiddrift.renderengine.StaticModelRenderer;
import com.rawad.rapiddrift.util.Loader;
import com.rawad.rapiddrift.window.Keyboard;
import com.rawad.rapiddrift.window.Mouse;
import com.rawad.rapiddrift.window.Window;

/**
 * 
 * 
 * @author Rawad
 *
 */
public class GameLogic implements IGameLogic, IRenderer {
	
	private static final String GAME_TITLE = "Super Fast";
	
	/** Frequency at which the game logic is updated. */
	private static final double TARGET_UPDATE_RATE = 30;
	
	private static final int WINDOW_WIDTH = 640;
	private static final int WINDOW_HEIGHT = 480;
	
	private GameSystem[] gameSystems;
	
	private Window window;
	
	private MasterRenderer masterRenderer;
	
	private StaticModelRenderer staticModelRenderer;
	
	private Entity camera;
	
	private Entity face;
	private TransformComponent transformComp;
	private TextureComponent textureComp;
	
	/**
	 * @see com.rawad.rapiddrift.engine.IGameLogic#init()
	 */
	@Override
	public void init(GameEngine gameEngine) {
		
		Logger.getGlobal().setLevel(Level.WARNING);
		
		window = Window.createWindow(GAME_TITLE, WINDOW_WIDTH, WINDOW_HEIGHT, true, false);
		
		window.setKeyCallback(new GLFWKeyCallbackI() {
			
			/**
			 * @see org.lwjgl.glfw.GLFWKeyCallbackI#invoke(long, int, int, int, int)
			 */
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				if(key == GLFW.GLFW_KEY_F11 && action == GLFW.GLFW_RELEASE) {
					GameLogic.this.window.toggleFullscreen();
				}
			}
			
		});
		
		window.setCloseCallback((window) -> {
			
			if(!gameEngine.isStopRequested()) {
				gameEngine.requestStop();
				GameLogic.this.window.destroy();
			}
			
		});
		
		Loader.loadEntities();
		
		camera = EntityBlueprintManager.createEntity(EntityLocator.CAMERA);
		
		face = EntityBlueprintManager.createEntity(EntityLocator.FACE);
		
		transformComp = (TransformComponent) face.getComponent(TransformComponent.class);
		textureComp = (TextureComponent) face.getComponent(TextureComponent.class);
		
		((AttachmentComponent) camera.getComponent(AttachmentComponent.class)).setAttachedTo(face);
		
		masterRenderer = new MasterRenderer(this);
		
		gameEngine.setSecondsPerUpdate(1d / TARGET_UPDATE_RATE);
		
		staticModelRenderer = new StaticModelRenderer(camera);
		
		gameSystems = new GameSystem[] {
			new FollowerCameraSystem(),	
		};
		
	}
	
	/**
	 * @see com.rawad.rapiddrift.engine.IGameLogic#preTick()
	 */
	@Override
	public void preTick() {
		// This should be for input handling.
		
		Mouse.update();
		
		for(GameSystem gameSystem: gameSystems) {
			gameSystem.preTick(camera, face);
		}
		
	}
	
	/**
	 * @see com.rawad.rapiddrift.engine.IGameLogic#tick()
	 */
	@Override
	public void tick() {
		
		float x = 0;
		float z = 0;
		
		if(Keyboard.isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
			x = 1;
		} else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT)) {
			x = -1;
		}
		
		if(Keyboard.isKeyDown(GLFW.GLFW_KEY_UP)) {
			z = -1;
		} else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_DOWN)) {
			z = 1;
		}
		
		if(x != 0 || z != 0) {
			transformComp.setPosition(transformComp.getPosition().add(new Vector3f(x, 0, z)));
		}
		
		float rotY = 0;
		
		if(Keyboard.isKeyDown(GLFW.GLFW_KEY_D)) {
			rotY = 5;
		} else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_A)) {
			rotY = -5;
		}
		
		transformComp.setRotation(transformComp.getRotation().multiply(new Quaternionf(new Vector3f(0, 1, 0), rotY)));
		
		for(GameSystem gameSystem: gameSystems) {
			gameSystem.tick();
		}
		
	}
	
	/**
	 * @see com.rawad.rapiddrift.engine.IGameLogic#postTick()
	 */
	@Override
	public void postTick() {
		
		masterRenderer.render();
		
		window.update();
		
	}
	
	/**
	 * @see com.rawad.rapiddrift.renderengine.IRenderer#render()
	 */
	@Override
	public void render() {
		
		staticModelRenderer.render(face);
		
	}
	
	/**
	 * @see com.rawad.rapiddrift.engine.IGameLogic#terminate()
	 */
	@Override
	public void terminate() {
		
		textureComp.getTexture().delete();
		
		staticModelRenderer.terminate();
		
	}
	
}
