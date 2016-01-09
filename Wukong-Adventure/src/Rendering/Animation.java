package Rendering;

import java.awt.image.BufferedImage;

public class Animation {

	private int x;
	private int y;
	private BufferedImage image;
	
	Animation(int x, int y, BufferedImage image){
		this.image = image;
		this.x = x;
		this.y = y;
	}
	
	public void tick(){}
	public void render(){}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}


	

}
