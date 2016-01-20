package Rendering;

import entities.Mob;
import entities.Player;

public class Camera {

	//the x and y of the camera
	private double x, y;
	private Player player;
	private double startX, startY;
	private double disStartX, disStartY;

	/**Constructor
	 * @param player
	 */
	public Camera(Mob player){
		this.x = player.getX();
		startX = x + 250;
		//this.y = y;
		//startY = y;
		this.player = (Player)player;
	}//camera

	/**tick
	 * 
	 */
	public void tick(){
		updateCamera();
	}//tick
	
	/**Gets x value
	 * @return x
	 */
	public double getX() {
		return x;
	}//getX

	/**Sets x value
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}//setX

	/**Gets y value
	 * @return y
	 */
	public double getY() {
		return y;
	}

	/**Sets y value
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**Updates camera values around player
	 * 
	 */
	public void updateCamera(){
		disStartX = startX - player.getX();

		if(x > disStartX + 120){
			x += -player.getHorSpeed() - 1.8;
			if(x-disStartX  < 1.8)
				x = disStartX;
			if(x-disStartX  > 120)
				x -= 3.6;
		}
		if(x < disStartX - 120){
			x += -player.getHorSpeed() + 1.8;
			if(x+disStartX  > 1.8)
				x = disStartX;
			if(x+disStartX  < 120)
				x += 3.6;
		}
	}//updateCamera

}
