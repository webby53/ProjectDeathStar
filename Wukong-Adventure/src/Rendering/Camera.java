package Rendering;

import entities.Mob;
import entities.Player;

public class Camera {

	//the x and y of the camera
	private double x, y;
	Player player;
	double startX, startY;
	double disStartX, disStartY;

	public Camera(Mob player){
		this.x = player.getX();
		startX = x + 250;
		//this.y = y;
		//startY = y;
		this.player = (Player)player;
	}

	public void tick(){
		updateCamera();
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
