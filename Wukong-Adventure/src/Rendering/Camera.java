package Rendering;

import Main.Game;
import entities.Mob;
import entities.Player;

public class Camera {

	//the x and y of the camera
	private double x, y;
	Player player;
	double maxRight, maxLeft;
	double startX, startY;
	double disStartX, disStartY;

	public Camera(Mob player){
		this.x = 0;
		startX = x + 400;
		this.y = y;
		startY = y;
		this.player = (Player)player;
		maxRight = player.getX() + 400;
		maxLeft = player.getX() - 400;
	}

	public void tick(){
		updateCamera();
//		if(player.getX() > maxRight){
//			maxRight = player.getX();
//		}
//		if(player.getX() < maxLeft){
//			maxLeft = player.getX();
//		}
			}
	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void updateCamera(){
		disStartX = startX - player.getX();
		maxRight = player.getX() + 400;
		maxLeft = player.getX() - 400;
		if(x > disStartX){
			if(x-disStartX  > 10)
				x += -player.getHorSpeed() - 10;
			else
				x += -player.getHorSpeed() - 1.5;
			if(x-disStartX  < 2)
				x = disStartX;
		}
		if(x < disStartX){
			if(x+disStartX  > 10)
				x += -player.getHorSpeed() + 10;
			else
				x += -player.getHorSpeed() + 1.5;
			if(x+disStartX  > 1.5)
				x = disStartX;
		}
	}//updateCamera

}
