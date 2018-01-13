#version 330

uniform sampler2D modelTexture;

in vec2 outTextureCoordinates;

out vec4 fragColor;

void main() {
	
	fragColor = texture2D(modelTexture, outTextureCoordinates);
//	fragColor = vec4(outTextureCoordinates, 1.0, 1.0);
	
}
