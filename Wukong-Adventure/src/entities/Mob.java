package entities;

/**@author Joshua Prpic, Kishon Webb, Simon Yacoub
 * @version 7.4
 * @since 2016-01-18
 */ 

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Rendering.Animation;
import Rendering.Sprite;
import states.GameState;
import java.util.*;
import entities.Player;

public abstract class Mob extends Entity{

	protected double dx, dy;
	protected boolean collision;
	protected double gravity = 0.45;
	protected double terminalV = 13;
	protected double friction = 0.15;
	private int life = 4;
	protected boolean falling = true;

	/**Constructor with only sprites
	 * 
	 * @param x
	 * @param y
	 * @param sprite
	 */
	public Mob(double x, double y, Sprite sprite) {
		super(x, y, sprite);
	}//constructor with sprite
	
	/**Constructor with only animations
	 * 
	 * @param x
	 * @param y
	 * @param animate
	 */
	public Mob(double x, double y, Animation animate) {
		super(x, y, animate);
	}//constructor with animations
	
	/**Constructor with neither animations and sprites
	 * 
	 * @param x
	 * @param y
	 */
	public Mob(double x, double y) {
		super(x, y);
	}//constructor with neither
<<<<<<< HEAD

=======
	
	/**Ticks
	 * 
	 */
>>>>>>> 73442b4... UPDATE Javadoc the classes within the entities package
	public void tick(){
		friction();
		fall();
		move();
	}//tick

	/**returns entire boundary of Entity (not sides)
	 * 
	 * @return rectangle
	 */
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, this.getHeight(), this.getWidth());
	}//getBounds

	/**checks all tiles if player is colliding with them
	 * 
	 */
	public void collisionCheck(){
		for(int i = 0; i < Tile.tiles.size(); i++){
				if(Tile.tiles.get(i).isTouchedLeft(this) && dx > 0){
					dx = 0;
					x -= 0.7;
				}
				if(Tile.tiles.get(i).isTouchedRight(this) && dx < 0){
					dx = 0;
					x += 0.7;
				}
				if(Tile.tiles.get(i).isTouchedBot(this) && dy < 0)
					dy = 0;
				if(Tile.tiles.get(i).isTouchedTop(this) && dy > 0){
					dy = 0;
					x += dx;
					y = Tile.tiles.get(i).recTop.getY() - 64;
					Player.canJump = true;
					falling = false;
				}else
					falling = true;
			}
	}//collisionCheck

	/**updates x and y and calls collision check
	 * Allow movement and checks if the mob is colliding with any tiles
	 * 
	 */
	public void move(){
		collisionCheck();
		x += dx;
		y += dy;
		collisionCheck();
	}//move

	/**Slows down movement
	 * 
	 */
	public void friction(){
		if(falling){
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
	}//friction

	/**Allows the player to jump
	 * 
	 */
	public void jump(){
		dy = -4;
	}//jump

	/**Makes it so that the player will fall when not touching the top of any tile
	 */
	public void fall(){
		if(falling){
			dy += gravity;
			if(dy > terminalV)
				dy = terminalV;
		}
	}//fall

	/**Renders the mob entities and can show the mob's hitboxes if it is in debug mode
	 * 
	 * @param g
	 */
	public void render(Graphics2D g){
		//	if(!dead){
		super.render(g);
		g.setColor(Color.MAGENTA);
		if(GameState.debugging){
			g.draw(getBounds());
		}
		if(animate != null)
			this.sprite = animate.getFrame();
		//	}
	}//render
<<<<<<< HEAD

=======
	
	/**Sends a boolean that indicates if the player is on screen
	 * 
	 * @return A boolean that indicates is the player is off of the screen
	 */
>>>>>>> 73442b4... UPDATE Javadoc the classes within the entities package
	//checks if player is dead (off screen)
	public boolean isOffScreen(){
		boolean fell = false;
		if(y > 800){
			fell = true;
		}
		//dead = true;
		return fell;
	}
<<<<<<< HEAD

	//this checks for collision with other enemies
=======
	
	/**Checks if a mob is colliding with any other mob
	 * 
	 * @param b
	 * @return A boolean that indicates if the calling object is intersecting with any other mob
	 */
>>>>>>> 73442b4... UPDATE Javadoc the classes within the entities package
	public boolean isCollided(ArrayList<Mob> b){
		boolean collision = false;

		for(int i = 0; i < b.size(); i++){
			if(this.getBounds().intersects(b.get(i).getBounds())){
				collision = true;
			}
		}
		return collision;
	}//isCollided
<<<<<<< HEAD

	public void setAnimation(Animation animate){
		this.animate = animate;
	}

=======
	
	/**Displays and runs the animations
	 * 
	 * @param animate
	 */
	public void setAnimation(Animation animate){
		this.animate = animate;
	}
	
	/**Checks the amount of lives of the calling mob and returns that number
	 * 
	 * @return The amount of lives left on the calling object
	 */
>>>>>>> 73442b4... UPDATE Javadoc the classes within the entities package
	public int getLife() {
		return life;
	}//getLife

	/**Modifies the amount of lives left on a mob based on the inital amount and the amount being taken away
	 * 
	 * @param life
	 */
	public void takeLife(int life) {
		this.life -= life;
	}//takeLife

}//Mob
