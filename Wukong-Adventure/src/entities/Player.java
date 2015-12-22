package entities;

import java.awt.event.KeyEvent;

import Rendering.Sprite;
import input.KeyInput;
import states.GameState;

public class Player extends Mob{
	
	public Player(double x, double y, Sprite sprite) {
		super(x, y, sprite);
		// TODO Auto-generated constructor stub
	}

	public void tick(){
		if(KeyInput.isKeyDown(KeyEvent.VK_A) || KeyInput.isKeyDown(KeyEvent.VK_LEFT)){
			dx += -0.3;
			if(dx < -3) dx = -3;
		}
		if(KeyInput.isKeyDown(KeyEvent.VK_D)|| KeyInput.isKeyDown(KeyEvent.VK_RIGHT)){
			dx += 0.3;
			if(dx > 3) dx = 3;
		}
		if(KeyInput.isKeyDown(KeyEvent.VK_W)|| KeyInput.isKeyDown(KeyEvent.VK_UP))
			jump();
		if(KeyInput.isKeyDown(KeyEvent.VK_S)|| KeyInput.isKeyDown(KeyEvent.VK_DOWN))
			dy = 4;
		if(KeyInput.isKeyDown(KeyEvent.VK_Q))
			camx -=0.5;
		if(KeyInput.isKeyDown(KeyEvent.VK_E))
			camx += 0.5;
		System.out.println("Cam change = " + camx);
		super.tick();	
	}
		
}
