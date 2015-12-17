package Rendering;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Sprite {

	private BufferedImage image;
	private int width;
	private int height;
	
	//isolates the individual spites
	public Sprite(SpriteSheet spritesheet, int x, int y){
		//calculates where the image that is being pulled comes from on the sprite sheet
		//takes in the initial sprite sheet then creates it into an image then it creates a subimage (isolates the individual sprite into an image)
		this.image = spritesheet.getTexture().getImage().getSubimage(
				//calculates where the specific sprite is on the sheet based on the the position it would be on a number list relative to the size of the sprite sheet
				//the -spritesheet.getWidth() is an offset b/c the array is offset by 1 (he explains that it confuses his viewers that arrays start at 0,0 instead of 1,1 so he is offseting it so it starts at 1,1)
				(x * spritesheet.getWidth()) - spritesheet.getWidth(),
				//same principle as what is happening with the x val but with the y val
				(y * spritesheet.getHeight()) - spritesheet.getHeight(),
				spritesheet.getWidth(), spritesheet.getHeight());
		width = spritesheet.getWidth();
		height = spritesheet.getHeight();
	}
	
	public Sprite(String string, int width, int height) {
		// TODO Auto-generated constructor stub
		Texture tex = new Texture(string);
		image = tex.getImage();
		this.width = width;
		this.height = height;
	}

	//renders the graphics
	public void render(Graphics2D g, double x, double y){
		g.drawImage(image, (int)x, (int)y, null);
	}
	
	public int getHeight(){
		return height;
	}
	public int getWidth(){
		return width;
	}
}
