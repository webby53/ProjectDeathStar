package entities;

/**@author Joshua Prpic, Kishon Webb, Simon Yacoub
 * @version 7.4
 * @since 2016-01-18
 */ 
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import Rendering.Animation;
import Rendering.Sprite;

public abstract class Entity {

	protected double x, y;
	protected Sprite sprite;
	protected Animation animate;
	protected boolean dead = false;
	protected Rectangle2D recTop, recBot, recLeft, recRight;
	
	public Entity(){}

	/**Constructor
	 * 
	 * @param x
	 * @param y
	 * @param sprite
	 */
	public Entity(double x, double y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	/**Constructor
	 * 
	 * @param x
	 * @param y
	 */
	public Entity(double x, double y) {
		this.x = x;
		this.y = y;
		sprite = null;
	}
	
	/**Constructor
	 * 
	 * @param x
	 * @param y
	 * @param animate
	 */
	public Entity(double x, double y, Animation animate) {
		this.x = x;
		this.y = y;
		this.animate = animate;
		this.sprite = animate.getFrame();
	}

	/**Basic render method that renders the entity sprites on screen
	 * 
	 * @param g
	 */
	public void render(Graphics2D g){
			this.tick();
			if(sprite != null)
				sprite.render(g, x, y);
	}
	
	/**Checks if an entity is dead and returns it's status
	 * 
	 * @return A boolean that indicates if the calling entity is dead or alive
	 */
	public boolean isDead(){
		return dead;
	}
	
	/**Sets if an entity is dead or alive
	 * 
	 * @param bool
	 */
	public void setDead(boolean bool){
		dead = bool;
	}

	
	public abstract void tick();

	/**Returns the width of the entities sprite in question
	 * 
	 * @return The width of the calling entities sprite
	 */
	public int getWidth(){
		return sprite.getWidth();
	}
	
	/**Returns the height of the entities sprite in question
	 * 
	 * @return The width of the calling entities sprite
	 */
	public int getHeight(){
		return sprite.getHeight();
	}

	/**Returns the x value of the entity
	 * 
	 * @return The x value of the calling entity
	 */
	public int getX(){
		return (int)x;
	}
	
	/**Returns the y value of the entity
	 * 
	 * @return The y value of the calling entity
	 */
	public int getY(){
		return (int)y;
	}
}
