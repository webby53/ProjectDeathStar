package entities;

/**@author Joshua Prpic, Kishon Webb, Simon Yacoub
 * @version 7.4
 * @since 2016-01-18
 */ 

import Rendering.Sprite;
import states.GameState;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Tile extends Entity{
	public static ArrayList<Tile> tiles = new ArrayList<Tile>();
	protected double x, y;
	protected boolean solid;
	
	/**Constructor
	 * 
	 * @param x
	 * @param y
	 * @param sprite
	 */
	public Tile(double x, double y, Sprite sprite, boolean solid) {
		super(x, y, sprite);
		this.solid = solid;
		this.x = x;
		this.y = y;
		tiles.add(this);
		recLeft = new Rectangle((int)this.x, (int)this.y + 5, 1, sprite.getHeight() - 6);
		recRight = new Rectangle((int)this.x + sprite.getWidth(), (int)this.y + 5, 1, sprite.getWidth() - 6);
		recTop = new Rectangle((int)this.x + 4, (int)this.y, sprite.getWidth() - 7, 1);
		recBot = new Rectangle((int)this.x + 4, (int)this.y + sprite.getHeight(), sprite.getWidth() - 7, 1);
	}

	public void tick() {
	}

	/**Renders the hit boxes of the tile it is being called by
	 * 
	 */
	public void render(Graphics2D g){
		tick();
		sprite.render(g, x, y);
		if(GameState.debugging && solid){
			g.setColor(Color.BLUE);
			g.draw(recTop);
			g.setColor(Color.RED);
			g.draw(recBot);
			g.setColor(Color.GREEN);
			g.draw(recLeft);
			g.setColor(Color.ORANGE);
			g.draw(recRight);
		}
	}//render

	public boolean isTouched(Mob player){
		if(isTouchedTop(player) || isTouchedBot(player) || isTouchedLeft(player) || isTouchedRight(player))
			return true;
		else
			return false;
	}

	public boolean isTouchedTop(Mob player){
		if(solid)
			return player.getBounds().intersects(recTop);
		else
			return false;
	}
	public boolean isTouchedBot(Mob player){
		if(solid)
			return player.getBounds().intersects(recBot);
		else
			return false;
	}
	public boolean isTouchedLeft(Mob player){
		if(solid)
			return player.getBounds().intersects(recLeft);
		else
			return false;
	}
	public boolean isTouchedRight(Mob player){
		if(solid)
			return player.getBounds().intersects(recRight);
		else
			return false;
	}

	public void setSolid(boolean solid){
		this.solid = solid;
		tiles.remove(this);
	}
}
