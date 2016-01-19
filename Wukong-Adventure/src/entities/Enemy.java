package entities;

/**@author Joshua Prpic, Kishon Webb, Simon Yacoub
 * @version 7.4
 * @since 2016-01-18
 */

import java.awt.Rectangle;
import java.util.ArrayList;

import Rendering.Animation;
import Rendering.Sprite;
import Rendering.SpriteSheet;
import Rendering.Texture;

public class Enemy extends Mob{

	private double speed = 2;
	private double dx = 0.25;
	private Animation animate;
	private Texture charTextures = new Texture("wukong sheet");
	private SpriteSheet charSheet = new SpriteSheet(charTextures, 64);
	
	/**Constructor
	 * 
	 * @param x
	 * @param y
	 * @param sprite
	 */
	public Enemy(double x, double y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	/**Constructor
	 * 
	 * @param x
	 * @param y
	 */
	public Enemy(double x, double y) {
		super(x, y);
		
		ArrayList<Sprite> frames = new ArrayList<Sprite>();
		frames.add(new Sprite(charSheet, 1, 5));
		frames.add(new Sprite(charSheet, 2, 5));
		frames.add(new Sprite(charSheet, 3, 5));
		frames.add(new Sprite(charSheet, 4, 5));
		
		animate = new Animation(20, frames);
		animate.start();
		this.sprite = animate.getFrame();

	}
	
	/**Tick
	 * 
	 */
	public void tick(){
		//if(!dead){
		animate.run();
		this.sprite = animate.getFrame();
		super.tick();
		//}
	}
	
	/**Creates the rectangle which is the hit box all enemies
	 */
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, this.getHeight(), this.getWidth());
	}//getBounds
	
	/**Checks if a tile is intersecting with a enemy entity and based on that sets the movement speed, direction of movement and prevents them from passing through tiles
	 * 
	 */
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

	/**Moves the enemy sprites according to thier movement speed when called
	 * 
	 */
	public void move(){
		collisionCheck();
		x -= dx;
		y += dy;
		collisionCheck();
	}//move
	
	
	
}
