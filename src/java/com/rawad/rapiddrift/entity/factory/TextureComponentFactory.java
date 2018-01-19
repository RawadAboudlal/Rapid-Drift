package com.rawad.rapiddrift.entity.factory;

import java.util.HashMap;

import com.rawad.rapiddrift.entity.component.Component;
import com.rawad.rapiddrift.entity.component.TextureComponent;
import com.rawad.rapiddrift.util.IOUtils;
import com.rawad.rapiddrift.util.Loader;

/**
 * @author Rawad
 *
 */
public class TextureComponentFactory implements ComponentFactory<TextureComponent> {
	
	private static final String TEXTURE_KEY = "texture";
	
	/**
	 * @see com.rawad.rapiddrift.entity.factory.ComponentFactory#createComponent(java.util.HashMap)
	 */
	@Override
	public TextureComponent createComponent(HashMap<String, String> data) {
		
		TextureComponent textureComp = new TextureComponent();
		
		String[] texturePath = data.get(TEXTURE_KEY).split(IOUtils.FILE_SEPARATOR);
		
		textureComp.setTexturePath(texturePath);
		textureComp.setTexture(Loader.loadTexture(data.get(Loader.ENTITY_NAME_KEY), texturePath));
		
		return textureComp;
		
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.factory.ComponentFactory#getStringData(com.rawad.rapiddrift.entity.component.Component)
	 */
	@Override
	public HashMap<String, String> getStringData(HashMap<String, String> data, Component comp) {
		
		TextureComponent textureComp = (TextureComponent) comp;
		
		data.put(TEXTURE_KEY, String.join(IOUtils.FILE_SEPARATOR, textureComp.getTexturePath()));
		
		return data;
		
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.factory.ComponentFactory#getComponentName()
	 */
	@Override
	public String getComponentName() {
		return TextureComponent.class.getCanonicalName();
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.factory.ComponentFactory#matchesComponent(com.rawad.rapiddrift.entity.component.Component)
	 */
	@Override
	public boolean matchesComponent(Component comp) {
		return comp instanceof TextureComponent;
	}
	
}
