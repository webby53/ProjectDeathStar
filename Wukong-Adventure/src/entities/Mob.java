package entities;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import Rendering.Sprite;
import input.KeyInput;

public class Mob extends Entity{

	protected double dx, dy, lastx, lasty;
	protected boolean collision;
	private double gravity, terminalV;
	private boolean canJump, falling = true;

	public Mob(double x, double y, Sprite sprite) {
		super(x, y, sprite);
		gravity = 0.2;
		terminalV = 7;
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
		move();
		fall();
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, this.getHeight(), this.getWidth());
	}//getBounds

	public void collisionCheck(){
		for(int i = 0; i < Tile.tiles.size(); i++){
			if(getBounds().intersects(Tile.tiles.get(i).recBot) && dy < 0)
				dy = 0;
			if(getBounds().intersects(Tile.tiles.get(i).recTop) && dy > 0){
				dy = 0;
				canJump = true;
				falling = false;
			}else
				falling = true;
			if(getBounds().intersects(Tile.tiles.get(i).recLeft) && dx > 0)
				dx = 0;
			if(getBounds().intersects(Tile.tiles.get(i).recRight) && dx < 0)
				dx = 0;
		}
	}//collisionCheck

	public void move(){
		lastx = x;
		lasty = y;

		collisionCheck();
		x += dx;
		y += dy;
		if(dx < 0)
			dx += 0.15;
		if(dx > 0)
			dx -= 0.15;
	}//move

	private void jump(){
		if(canJump){
			dy = -5;
			canJump = false;
		}else
			System.out.println("Cannot jump anymore");
	}//jump

	private void fall(){
		if(falling){
			dy += gravity;
			if(dy > terminalV)
				dy = terminalV;
		}
	}
}
