package entities;

/**@author Joshua Prpic, Kishon Webb, Simon Yacoub
 * @version 7.4
 * @since 2016-01-18
 */ 

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import Main.Game;
import states.GameState;
import Rendering.Animation;
import Rendering.Sprite;
import Rendering.SpriteSheet;
import Rendering.Texture;
import input.KeyInput;

public class Player extends Mob{

	protected static boolean canJump;
	protected double speed = 0.35;
	protected double maxSpeed = 3.5;
	protected boolean facingRight;
	private Rectangle2D recAttackBox;
	private boolean attacking = false;
	private ArrayList<Sprite> rightAnimate,
	leftAnimate, standAnimate,
	jumpAnimate, attackAnimate;
	private Animation current;
	private Texture charTextures = new Texture("wukong sheet");
	private SpriteSheet charSheet = new SpriteSheet(charTextures, 64);

	/**Constructor with a default attack rectangle and only a sprite
	 * 
	 * @param x
	 * @param y
	 * @param sprite
	 */
	public Player(double x, double y, Sprite sprite) {
		super(x, y, sprite);
		recAttackBox = new Rectangle((int)x, (int)y + 16, this.getHeight() + 22, this.getWidth() - 32);
	}

	/**Constructor with a no default attack rectangle and animations
	 * 
	 * @param x
	 * @param y
	 * @param animate
	 */
	public Player(double x, double y, Animation animate) {
		super(x,y,animate);
	}

	/**Constructor with default animations
	 * 
	 * @param x
	 * @param y
	 */
	public Player(double x, double y){
		super(x,y);

		//These are animations for differnt actions
		//right
		rightAnimate = new ArrayList<Sprite>();
		rightAnimate.add(new Sprite(charSheet, 1, 1));
		rightAnimate.add(new Sprite(charSheet, 2, 1));
		rightAnimate.add(new Sprite(charSheet, 3, 1));
		rightAnimate.add(new Sprite(charSheet, 4, 1));
		rightAnimate.add(new Sprite(charSheet, 5, 1));
		//left
		leftAnimate = new ArrayList<Sprite>();
		leftAnimate.add(new Sprite(charSheet, 1, 4));
		leftAnimate.add(new Sprite(charSheet, 2, 4));
		leftAnimate.add(new Sprite(charSheet, 3, 4));
		leftAnimate.add(new Sprite(charSheet, 4, 4));
		leftAnimate.add(new Sprite(charSheet, 5, 4));
		//standing
		standAnimate = new ArrayList<Sprite>();
		standAnimate.add(new Sprite(charSheet, 1, 5));
		standAnimate.add(new Sprite(charSheet, 2, 5));
		standAnimate.add(new Sprite(charSheet, 3, 5));
		standAnimate.add(new Sprite(charSheet, 4, 5));
		//attacking
		attackAnimate = new ArrayList<Sprite>();
		attackAnimate.add(new Sprite(charSheet, 1, 2));
		attackAnimate.add(new Sprite(charSheet, 2, 2));
		attackAnimate.add(new Sprite(charSheet, 3, 2));
		attackAnimate.add(new Sprite(charSheet, 4, 2));
		attackAnimate.add(new Sprite(charSheet, 4, 2));
		attackAnimate.add(new Sprite(charSheet, 1, 2));
		attackAnimate.add(new Sprite(charSheet, 2, 2));
		attackAnimate.add(new Sprite(charSheet, 3, 2));
		attackAnimate.add(new Sprite(charSheet, 4, 2));
		attackAnimate.add(new Sprite(charSheet, 4, 2));

		current = new Animation(9, standAnimate);
		current.start();
	}

	/**Method that runs every tick reads key input
	 * 
	 */
	public void tick(){
		current.run();
		this.sprite = current.getFrame();
		if(dx == 0 && !attacking && canJump)
			current.setAnimation(standAnimate);
		attacking = false;

		//Corrects the facing of the attack box
		if(facingRight){
			recAttackBox = new Rectangle((int)x, (int)y + 16, this.getHeight() + 45, this.getWidth() - 32);
		}else{
			recAttackBox = new Rectangle((int)x - 45, (int)y + 16, this.getHeight() + 32, this.getWidth() - 32);
		}

		//key inputs
		if(KeyInput.isKeyDown(KeyEvent.VK_A) || KeyInput.isKeyDown(KeyEvent.VK_LEFT)){
			current.setAnimation(leftAnimate);
			dx += -speed;
			if(dx < -maxSpeed) dx = -maxSpeed;
			facingRight = false;
		}
		if(KeyInput.isKeyDown(KeyEvent.VK_D)|| KeyInput.isKeyDown(KeyEvent.VK_RIGHT)){
			current.setAnimation(rightAnimate);
			dx += speed;
			if(dx > maxSpeed) dx = maxSpeed;
			facingRight = true;

		}
		if(KeyInput.isKeyDown(KeyEvent.VK_W)|| KeyInput.isKeyDown(KeyEvent.VK_UP)){
			jump();
		}
		if(KeyInput.isKeyDown(KeyEvent.VK_S)|| KeyInput.isKeyDown(KeyEvent.VK_DOWN)){
			dy = 0;
		}
		if(KeyInput.isKeyDown(KeyEvent.VK_SPACE)){
			current.setAnimation(attackAnimate);
			attacking = true;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//////////Extra options//////////
		if(KeyInput.isKeyDown(KeyEvent.VK_1)){
			Game.INSTANCE.target = 40;
		}
		if(KeyInput.isKeyDown(KeyEvent.VK_2)){
			Game.INSTANCE.target = 50;
		}
		if(KeyInput.isKeyDown(KeyEvent.VK_3)){
			Game.INSTANCE.target = 60;
		}
		if(KeyInput.isKeyDown(KeyEvent.VK_Q)){
			falling = false;
			dy = 0;
		}
		if(KeyInput.isKeyDown(KeyEvent.VK_BACK_SPACE)){
			if(GameState.options){
				GameState.options = false;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else{
				GameState.options = true;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		///////////////////////////////////
		super.tick();	
	}

	/**Jump method for the player(has adjusted jump height)
	 * 
	 */
	public void jump(){
		if(canJump){
			collisionCheck();
			x += dx;
			dy = -11.5;
			canJump = false;
			falling = true;
		}
	}//jump


	/**Returns the attack rectangle to the calling method
	 * 
	 * @return The a rectangle that represents the attack area of the player
	 */
	public Rectangle2D showAttack(){
		return recAttackBox;
	}

	/**A method that returns the player instances horizontal movement speed to calling statement
	 * 
	 * @return The horizontal movement speed
	 */
	public double getHorSpeed(){
		return dx;
	}

	/**Checks and returns the direction the player is facing
	 * 
	 * @return A boolean representing the direction of the player being left or right
	 */
	public boolean isFacingRight(){
		return facingRight;
	}//isFacingRight


	/**Renders the player objects and the players hit box and attack hit box if debugging is enabled
	 * @param g
	 */
	public void render(Graphics2D g){
		super.render(g);
		if(attacking)
			g.setColor(Color.BLUE);
		else
			g.setColor(Color.RED);
		if(GameState.debugging){
			g.draw(recAttackBox);
		}
	}//render

	/**Checks if when the player is clicking the attack key, is any mob intersecting with the attack box
	 * 
	 * @param b
	 * @return The place on the array of the enemy that is intersecting with the attack box is otherwise it returns -1 to represent that nothing was hit
	 */
	public int isAttacking(ArrayList<Mob> b){
		int enemy = -1;
		for(int i = 0; i < b.size(); i++){
			if(recAttackBox.intersects(b.get(i).getBounds()) && attacking){
				enemy = i;
			}
		}
		return enemy;
	}//isAttacking

}
