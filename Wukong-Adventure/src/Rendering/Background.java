package Rendering;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import Main.Game;
import javax.imageio.ImageIO;

public class Background {
	
	private BufferedImage image;
	private double x;
	private int y;
	private double move;
	private double cx;
	
	public Background(String s, double move){
		this.move = move;
		try {
			image = ImageIO.read(new File(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x * move;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setCx(double dx){
		this.cx = dx;
	}//updates the location of background
	public void update(){
		x += cx;
	}
	
	//draws the background
	public void draw(Graphics2D g){
		g.drawImage(image, (int)x, y, null);
		if(x < 0){
			g.drawImage(image, (int)x + Game.WIDTH, y, null);
		}
		if(x > 0){
			g.drawImage(image, (int)x - Game.WIDTH, y, null);
		}	
	}
	
	
	
}
