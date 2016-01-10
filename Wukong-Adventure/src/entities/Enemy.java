package entities;

import Rendering.Sprite;

public class Enemy extends Mob{

	private double speed = 2;
	private double dx = 1;
	
	public Enemy(double x, double y, Sprite sprite) {
		super(x, y, sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void tick(){
		x -= dx;
		super.tick();
	}
	
	
	public void collisionCheck(){
		for(int i = 0; i < Tile.tiles.size(); i++){
			if(getBounds().intersects(Tile.tiles.get(i).recLeft) && dx > 0){
				dx = -1;
				x -= 0.7;
			}
			if(getBounds().intersects(Tile.tiles.get(i).recRight) && dx < 0){
				dx = 1;// - Tile.tiles.get(i).recRight.getX();
				x += 0.7;
			}
			
			if(getBounds().intersects(Tile.tiles.get(i).recBot) && dy < 0)
				dy = 0;
			if(getBounds().intersects(Tile.tiles.get(i).recTop) && dy > 0){
				dy = 0;
				y = Tile.tiles.get(i).recTop.getY() - 64;
				x += dx;
				Player.canJump = true;
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
