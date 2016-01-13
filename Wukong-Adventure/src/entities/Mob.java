package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import Rendering.Sprite;
import states.GameState;
import java.util.*;

public abstract class Mob extends Entity{

	protected double dx, dy;
	protected boolean collision;
	protected double gravity = 0.5;
	protected double terminalV = 15;
	protected double friction = 0.2;
	protected boolean falling = true;

	public Mob(double x, double y, Sprite sprite) {
		super(x, y, sprite);
	}

	public void tick(){
		friction();
		fall();
		move();
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, this.getHeight(), this.getWidth());
	}//getBounds

	public void collisionCheck(){
		for(int i = 0; i < Tile.tiles.size(); i++){
//			if(getBounds().intersects(Tile.tiles.get(i).recTop) && (getBounds().intersects(Tile.tiles.get(i).recLeft)) || 
//					getBounds().intersects(Tile.tiles.get(i).recTop) && (getBounds().intersects(Tile.tiles.get(i).recRight))){
//				x += dx;
//				dy = 0;
//			}
			if(getBounds().intersects(Tile.tiles.get(i).recLeft) && dx > 0){
				dx = 0;
				x -= 0.7;
			}
			if(getBounds().intersects(Tile.tiles.get(i).recRight) && dx < 0){
				dx = 0;// - Tile.tiles.get(i).recRight.getX();
				x += 0.7;
			}
			if(getBounds().intersects(Tile.tiles.get(i).recBot) && dy < 0)
				dy = 0;
			if(getBounds().intersects(Tile.tiles.get(i).recTop) && dy > 0){
				dy = 0;
				x += dx;
				y = Tile.tiles.get(i).recTop.getY() - 64;
				Player.canJump = true;
				falling = false;
			}
		}
	}//collisionCheck

	public void move(){
		collisionCheck();
		x += dx;
		y += dy;
		collisionCheck();
	}//move

	//slows down when moving
	public void friction(){
		if(dx < 0)
			if(dx > -friction)
				dx = 0;
			else	
				dx += friction;
		if(dx > 0)
			if(dx < friction)
				dx = 0;
			else	
				dx -= friction;
	}

	public void jump(){
		dy = -4;
	}//jump

	public void fall(){
		if(falling){
			dy += gravity;
			if(dy > terminalV)
				dy = terminalV;
		}
	}

	public void render(Graphics2D g){
		super.render(g);
		g.setColor(Color.MAGENTA);
		if(GameState.debugging)
			g.draw(getBounds());
	}
	
	public boolean isDead(){
		boolean death = false;
		
		if(y > 800){
			death = true;
		}
		
		return death;
	}
	
	public boolean isCollided(ArrayList<Enemy> b){
		boolean collision = false;
		 
		for(int i = 0; i < b.size(); i++){
			if(this.getBounds().intersects(b.get(i).getBounds())){
				collision = true;
			}
		}
		
		return collision;
	}
}
