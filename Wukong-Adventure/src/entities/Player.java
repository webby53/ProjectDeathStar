package entities;

import java.awt.event.KeyEvent;

import Rendering.Sprite;
import input.KeyInput;

public class Player extends Mob{

	public static boolean canJump;
	public double speed = 0.5;
	public double maxSpeed = 7;

	public Player(double x, double y, Sprite sprite) {
		super(x, y, sprite);
		// TODO Auto-generated constructor stub
	}

	public void tick(){
		if(KeyInput.isKeyDown(KeyEvent.VK_A) || KeyInput.isKeyDown(KeyEvent.VK_LEFT)){
			dx += -speed;
			if(dx < -maxSpeed) dx = -maxSpeed;
		}
		if(KeyInput.isKeyDown(KeyEvent.VK_D)|| KeyInput.isKeyDown(KeyEvent.VK_RIGHT)){
			dx += speed;
			if(dx > maxSpeed) dx = maxSpeed;

		}
		if(KeyInput.isKeyDown(KeyEvent.VK_W)|| KeyInput.isKeyDown(KeyEvent.VK_UP)){
			jump();
		}

		if(KeyInput.isKeyDown(KeyEvent.VK_S)|| KeyInput.isKeyDown(KeyEvent.VK_DOWN)){
			dy = 0;
		}
		if(KeyInput.isKeyDown(KeyEvent.VK_E))
			for(int i = 0; i < Tile.tiles.size(); i++)
				System.out.println(Tile.tiles.get(i).x + " and " + Tile.tiles.get(i).y);
		super.tick();	
	}

	public void jump(){
		if(canJump){
			dy = -15;
			canJump = false;
		}

	}//jump
}
