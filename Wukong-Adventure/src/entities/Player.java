package entities;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Rendering.Sprite;
import input.KeyInput;
import input.MouseInput;

public class Player extends Mob{

	protected static boolean canJump;
	protected double speed = 0.5;
	protected double maxSpeed = 3;
	protected boolean facingRight;

	public Player(double x, double y, Sprite sprite) {
		super(x, y, sprite);
	}

	public void tick(){
		//key inputs
		if(KeyInput.isKeyDown(KeyEvent.VK_A) || KeyInput.isKeyDown(KeyEvent.VK_LEFT)){
			dx += -speed;
			if(dx < -maxSpeed) dx = -maxSpeed;
			facingRight = false;
		}
		if(KeyInput.isKeyDown(KeyEvent.VK_D)|| KeyInput.isKeyDown(KeyEvent.VK_RIGHT)){
			dx += speed;
			if(dx > maxSpeed) dx = maxSpeed;
			facingRight = true;

		}
		if(KeyInput.isKeyDown(KeyEvent.VK_W)|| KeyInput.isKeyDown(KeyEvent.VK_UP)){
			jump();
		}

		if(KeyInput.isKeyDown(KeyEvent.VK_S)|| KeyInput.isKeyDown(KeyEvent.VK_DOWN)){
			dy = 0;
		}
		super.tick();	
	}

	//jump for player
	public void jump(){
		if(canJump){
			x += dx;
			dy = -10;
			canJump = false;
			falling = true;
		}
	}//jump
	
	public double getHorSpeed(){
		return dx;
	}
	
	public boolean isFacingRight(){
		return facingRight;
	}
	
	
}
