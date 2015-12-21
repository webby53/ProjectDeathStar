package entities;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import Rendering.Sprite;
import input.KeyInput;

public class Mob extends Entity{

	protected double dx, dy, lastx, lasty;
	protected boolean collision;

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
			//System.out.println("Top-X:" + recLeft.getX() + " Y:" + recLeft.getY());
			for(Entity ent: Tile.tiles)
				System.out.println(ent);
		move();
		if(dy != 0 || dx != 0){
			dx = 0;
			dy = 0;
		}
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, this.getHeight(), this.getWidth());
	}

	public void collisionCheck(){
		for(int i = 0; i < Tile.tiles.size(); i++){
			if(getBounds().intersects(Tile.tiles.get(i).recBot) && dy < 0)
				dy = 0;
			if(getBounds().intersects(Tile.tiles.get(i).recTop) && dy > 0)
				dy = 0;
			if(getBounds().intersects(Tile.tiles.get(i).recLeft) && dx > 0)
				dx = 0;
			if(getBounds().intersects(Tile.tiles.get(i).recRight) && dx < 0)
				dx = 0;
		}
	}

	public void move(){
		lastx = x;
		lasty = y;

		collisionCheck();
		x += dx;
		y += dy;

	}

}
