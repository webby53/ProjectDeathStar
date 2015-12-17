package entities;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import Rendering.Sprite;
import input.KeyInput;

public class Mob extends Entity{

	protected double dx, dy, lastx, lasty;
	private boolean collision;

	public Mob(double x, double y, Sprite sprite) {
		super(x, y, sprite);
	}

	public void tick(){
		if(KeyInput.isKeyDown(KeyEvent.VK_A) || KeyInput.isKeyDown(KeyEvent.VK_LEFT))
			dx = -3;
		if(KeyInput.isKeyDown(KeyEvent.VK_D)|| KeyInput.isKeyDown(KeyEvent.VK_RIGHT))
			dx = 3;
		if(KeyInput.isKeyDown(KeyEvent.VK_W)|| KeyInput.isKeyDown(KeyEvent.VK_UP))
			dy = -3;
		if(KeyInput.isKeyDown(KeyEvent.VK_S)|| KeyInput.isKeyDown(KeyEvent.VK_DOWN))
			dy = 3;
		move();
		if(dy != 0 || dx != 0){
			dx = 0;
			dy = 0;
		}
	}

	public void rebound(Rectangle rec){

		if(this.getBounds().intersects(rec))
			collision = true;
		else
			collision = false;
	}

	public void move(){
		lastx = x;
		lasty = y;
			x += dx;
			y += dy;
	}
}
