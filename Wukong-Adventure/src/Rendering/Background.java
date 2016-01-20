package Rendering;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import Main.Game;
import javax.imageio.ImageIO;
/**@author Joshua Prpic, Kishon Webb, Simon Yacoub
 *
 */
public class Background {
	
	private BufferedImage image;
	private double x;
	private int y;
	private double move;
	private double cx;
	
	/**Background Constructor 
	 * 
	 */
	public Background(String s, double move){
		this.move = move;
		try {
			image = ImageIO.read(new File(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**returns the value of x
	 * 
	 * @return x
	 */
	public double getX() {
		return x;
	}

	/**Sets the value of x
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x * move;
	}

	/**returns the value of y
	 * 
	 * @return y
	 */
	public double getY() {
		return y;
	}

	/**sets the value of y
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**sets the value of cx
	 * 
	 * @param dx
	 */
	public void setCx(double dx){
		this.cx = dx;
	}//updates the location of background
	
	
	
	/**draws the background
	 * 
	 * @param g
	 */
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
