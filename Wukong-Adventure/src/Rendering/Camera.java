package Rendering;

import Main.Game;
import entities.Mob;
import entities.Player;

public class Camera {

	//the x and y of the camera
	private double x, y;
	Player player;
	int maxRight, maxLeft;

	public Camera(double x, double y, Mob player){
		this.x = x;
		this.y = y;
		this.player = (Player)player;
		maxRight = player.getX() + 120;
		maxLeft = player.getX() - 220;
	}

	public void tick(){

		if(player.getX() > maxRight){
			x -= player.getHorSpeed() + 1.4;
			maxRight = player.getX();
			maxLeft = player.getX() - 220;
		}
		if(player.getX() < maxLeft){
			x -= player.getHorSpeed()-1.4;
			maxLeft = player.getX();
			maxRight = player.getX() + 120;
		}

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

}
