package Rendering;

public class SpriteSheet {
	
/* This class is suppose to create and initialize sprite sheets
 * 
 * Based off the skype chat i am guessing we have no sprite sheets right now
 */
	
	
	private Textures texture;
	private int width, height;
	
	public SpriteSheet(Textures texture, int size){
		this(texture, size, size);
	}
	
	public SpriteSheet(Textures texture, int width, int height){
		this.texture = texture;
		this.width = width;
		this.height = height;
	}
	
	//returns the texture
	public Textures getTexture(){
		return texture;
	}
	
	//returns the width
	public int getWidth(){
		return width;
	}
	
	//returns the height
	public int getHeight(){
		return height;
	}
	
	
}