package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import Rendering.Sprite;
import states.GameState;

public abstract class Mob extends Entity{

	public double dx, dy;
	protected boolean collision;
	protected double gravity = 0.2;
	protected double terminalV = 7;
	private boolean falling = true;

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
			if(getBounds().intersects(Tile.tiles.get(i).recTop) && (getBounds().intersects(Tile.tiles.get(i).recLeft)) || 
					getBounds().intersects(Tile.tiles.get(i).recTop) && (getBounds().intersects(Tile.tiles.get(i).recRight)) || 
					getBounds().intersects(Tile.tiles.get(i).recRight) && (getBounds().intersects(Tile.tiles.get(i).recLeft)))
				y -= 1;
			if(getBounds().intersects(Tile.tiles.get(i).recBot) && dy < 0)
				dy = 0;
			if(getBounds().intersects(Tile.tiles.get(i).recTop) && dy > 0){
				dy=0;
				x += dx;
				Player.canJump = true;
				Player.numJumps = 2;
				Player.canDoubleJump = false;
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
		collisionCheck();
		x += dx;
		y += dy;
		collisionCheck();
	}//move

	public void friction(){
		if(dx < 0)
			if(dx > -0.15)
				dx = 0;
			else	
				dx += 0.15;
		if(dx > 0)
			if(dx < 0.15)
				dx = 0;
			else	
				dx -= 0.15;
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
}
