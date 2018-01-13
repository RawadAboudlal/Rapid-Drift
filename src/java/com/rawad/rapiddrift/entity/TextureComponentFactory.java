package com.rawad.rapiddrift.entity;

import java.util.HashMap;

import com.rawad.rapiddrift.renderengine.texture.TextureLoader;
import com.rawad.rapiddrift.util.Util;

/**
 * @author Rawad
 *
 */
public class TextureComponentFactory implements ComponentFactory<TextureComponent> {
	
	private static final String TEXTURE_KEY = "texture";
	
	/**
	 * @see com.rawad.rapiddrift.entity.ComponentFactory#createComponent(java.util.HashMap)
	 */
	@Override
	public TextureComponent createComponent(HashMap<String, String> data) {
		
		TextureComponent textureComp = new TextureComponent();
		
		String[] texturePath = data.get(TEXTURE_KEY).split(Util.FILE_SEPARATOR);
		
		textureComp.setTexturePath(texturePath);
		textureComp.setTexture(TextureLoader.loadTexture(texturePath));
		
		return textureComp;
		
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.ComponentFactory#getStringData(com.rawad.rapiddrift.entity.Component)
	 */
	@Override
	public HashMap<String, String> getStringData(HashMap<String, String> data, Component comp) {
		
		TextureComponent textureComp = (TextureComponent) comp;
		
		data.put(TEXTURE_KEY, String.join(Util.FILE_SEPARATOR, textureComp.getTexturePath()));
		
		return data;
		
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.ComponentFactory#getComponentName()
	 */
	@Override
	public String getComponentName() {
		return TextureComponent.class.getCanonicalName();
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.ComponentFactory#matchesComponent(com.rawad.rapiddrift.entity.Component)
	 */
	@Override
	public boolean matchesComponent(Component comp) {
		return comp instanceof TextureComponent;
	}
	
}
