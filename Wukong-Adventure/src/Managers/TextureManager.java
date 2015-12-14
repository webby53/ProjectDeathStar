package Managers;

import java.awt.image.BufferedImage;

//as the name implies it manages textures
public class TextureManager extends ResourceManager{

	private BufferedImage image;
	
	//when you create a manager you import an image
	public TextureManager(BufferedImage image){
		this.image = image;
	}
	
	public BufferedImage getImage(){
		return image;
	}
}
