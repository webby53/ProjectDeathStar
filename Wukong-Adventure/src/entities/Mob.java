package entities;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
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
		if(KeyInput.isKeyDown(KeyEvent.VK_SPACE))
			System.out.println(this.toString());
		move();
		if(dy != 0 || dx != 0){
			dx = 0;
			dy = 0;
		}
	}

	public void move(){
		lastx = x;
		lasty = y;

		x += dx;
		y += dy;
		for(Entity temp: entities){
			if(this.collision(temp.rec)){
				collision = true;
				return;
			}
		}
		if(collision){
			x = lastx;
			y = lasty;
		}
	}

}
