package com.rawad.rapiddrift.entity.component;

import com.rawad.rapiddrift.renderengine.texture.Texture;

/**
 * @author Rawad
 *
 */
public class TextureComponent extends Component {
	
	private String[] texturePath;
	private Texture texture;
	
	/**
	 * @return the texturePath
	 */
	public String[] getTexturePath() {
		return texturePath;
	}
	
	/**
	 * @param texturePath the texturePath to set
	 */
	public void setTexturePath(String[] texturePath) {
		this.texturePath = texturePath;
	}
	
	/**
	 * @return the texture
	 */
	public Texture getTexture() {
		return texture;
	}
	
	/**
	 * @param texture the texture to set
	 */
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.component.Component#clone()
	 */
	@Override
	public TextureComponent clone() {
		
		TextureComponent textureComp = new TextureComponent();
		
		textureComp.setTexturePath(this.getTexturePath().clone());
		textureComp.setTexture(this.getTexture().clone());
		
		return textureComp;
		
	}
	
}
