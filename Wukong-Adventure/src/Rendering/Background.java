package Rendering;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import Main.Game;
import javax.imageio.ImageIO;

public class Background {
	
	private BufferedImage image;
	private int x;
	private int y;
	
	public Background(int x, int y){
		this.x = x;
		this.y = y;
		
		try {
			image = ImageIO.read(new File("./resources/textures/Background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g){
		g.drawImage(image, x, y, null);
		if(x < 0){
			g.drawImage(image, x + Game.returnWidth(), y, null);
		}
		if(x > 0){
			g.drawImage(image, x - Game.returnWidth(), y, null);
		}
		
		
	}
	
}
