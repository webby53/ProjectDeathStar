package entities;

import java.awt.Rectangle;

import Rendering.Sprite;

public class Enemy extends Mob{

	private double speed = 2;
	private double dx = 0.25;
	
	public Enemy(double x, double y, Sprite sprite) {
		super(x, y, sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void tick(){
		move();
		super.tick();
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, this.getHeight(), this.getWidth());
	}//getBounds
	
	
	public void collisionCheck(){
		for(int i = 0; i < Tile.tiles.size(); i++){
			if(getBounds().intersects(Tile.tiles.get(i).recLeft) && dx > 0){
				dx = 0;
				x -= 0.7;
				dx = -0.25 * speed;
			}
			if(getBounds().intersects(Tile.tiles.get(i).recRight) && dx < 0){
				dx = 0;
				x += 0.7;
				dx = 0.25 * speed;// - Tile.tiles.get(i).recRight.getX();
			}
			
			if(getBounds().intersects(Tile.tiles.get(i).recBot) && dy < 0)
				dy = 0;
			if(getBounds().intersects(Tile.tiles.get(i).recTop) && dy > 0){
				dy = 0;
				y = Tile.tiles.get(i).recTop.getY() - 65;
				x += dx;
				falling = false;
			}else
				falling = true;
			
		}
	}
	
	public void move(){
		collisionCheck();
		x -= dx;
		y += dy;
		collisionCheck();
	}//move
	
	
	
}
