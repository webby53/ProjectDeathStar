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

	public Camera(double x, double y, Mob player){
		this.x = x + 400;
		startX = x + 400;
		this.y = y;
		startY = y;
		this.player = (Player)player;
		maxRight = player.getX() + 120;
		maxLeft = player.getX() - 220;
	}

	public void tick(){

		if(player.getX() > maxRight){
			updateCamera();
			maxRight = player.getX();
			maxLeft = player.getX() - 400;
		}
		if(player.getX() < maxLeft){
			updateCamera();
			maxLeft = player.getX();
			maxRight = player.getX() + 400;
		}
		
		disStartX = startX - player.getX();
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
		maxRight = player.getX() + 120;
		maxLeft = player.getX() - 220;
		x = disStartX;
	}

}
