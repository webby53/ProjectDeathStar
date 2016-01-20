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
	protected double speed = 0.3;
	protected double maxSpeed = 3.5;
	public int level = 0;
	protected static int gliding = 0;
	protected boolean facingRight;
	private Rectangle2D recAttackBox;
	private boolean attacking = false;
	private ArrayList<Sprite> rightAnimate,
	leftAnimate, standRightAnimate, standLeftAnimate,
	jumpRightAnimate, attackRightAnimate,attackLeftAnimate,
	jumpLeftAnimate, flyRightAnimate, flyLeftAnimate;
	private Animation current;
	private Texture charTextures = new Texture("wukong sheet");
	private SpriteSheet charSheet = new SpriteSheet(charTextures, 64);

	/**Constructor with only a sprite
	 * 
	 * @param x
	 * @param y
	 * @param sprite
	 */
	public Player(double x, double y, Sprite sprite) {
		super(x, y, sprite);
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
		standRightAnimate = new ArrayList<Sprite>();
		standRightAnimate.add(new Sprite(charSheet, 1, 6));
		standRightAnimate.add(new Sprite(charSheet, 2, 6));
		standLeftAnimate = new ArrayList<Sprite>();
		standLeftAnimate.add(new Sprite(charSheet, 1, 7));
		standLeftAnimate.add(new Sprite(charSheet, 2, 7));

		//attacking
		attackRightAnimate = new ArrayList<Sprite>();
		attackRightAnimate.add(new Sprite(charSheet, 1, 2));
		attackRightAnimate.add(new Sprite(charSheet, 2, 2));
		attackRightAnimate.add(new Sprite(charSheet, 3, 2));
		attackRightAnimate.add(new Sprite(charSheet, 4, 2));
		attackRightAnimate.add(new Sprite(charSheet, 5, 2));
		attackLeftAnimate = new ArrayList<Sprite>();
		attackLeftAnimate.add(new Sprite(charSheet, 1, 11));
		attackLeftAnimate.add(new Sprite(charSheet, 2, 11));
		attackLeftAnimate.add(new Sprite(charSheet, 3, 11));
		attackLeftAnimate.add(new Sprite(charSheet, 4, 11));
		attackLeftAnimate.add(new Sprite(charSheet, 5, 11));
		//jumping
		jumpLeftAnimate = new ArrayList<Sprite>();
		jumpLeftAnimate.add(new Sprite(charSheet, 1, 10));
		jumpLeftAnimate.add(new Sprite(charSheet, 2, 10));
		jumpLeftAnimate.add(new Sprite(charSheet, 3, 10));
		jumpLeftAnimate.add(new Sprite(charSheet, 4, 10));
		jumpLeftAnimate.add(new Sprite(charSheet, 5, 10));
		jumpRightAnimate = new ArrayList<Sprite>();
		jumpRightAnimate.add(new Sprite(charSheet, 1, 3));
		jumpRightAnimate.add(new Sprite(charSheet, 2, 3));
		jumpRightAnimate.add(new Sprite(charSheet, 3, 3));
		jumpRightAnimate.add(new Sprite(charSheet, 4, 3));
		jumpRightAnimate.add(new Sprite(charSheet, 5, 3));
		//gliding
		flyLeftAnimate = new ArrayList<Sprite>();
		flyRightAnimate = new ArrayList<Sprite>();
		flyLeftAnimate.add(new Sprite(charSheet, 1, 13));
		flyLeftAnimate.add(new Sprite(charSheet, 2, 13));
		flyLeftAnimate.add(new Sprite(charSheet, 3, 13));
		flyRightAnimate.add(new Sprite(charSheet, 1, 12));
		flyRightAnimate.add(new Sprite(charSheet, 2, 12));
		flyRightAnimate.add(new Sprite(charSheet, 3, 12));

		current = new Animation(12, standRightAnimate);
		facingRight = true;
		current.start();
	}

	/**Method that runs every tick reads key input
	 * 
	 */
	public void tick(){
		current.run();
		this.sprite = current.getFrame();
		if(dx == 0 && !attacking && canJump)
			if(facingRight)
				current.setAnimation(standRightAnimate);
			else
				current.setAnimation(standLeftAnimate);
		attacking = false;

		//Corrects the facing of the attack box
		if(facingRight){
			recAttackBox = new Rectangle((int)x, (int)y + 16, this.getHeight() + 55, this.getWidth() - 32);
		}else{
			recAttackBox = new Rectangle((int)x - 55, (int)y + 16, this.getHeight() + 55, this.getWidth() - 32);
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
			if(facingRight)
				current.setAnimation(jumpRightAnimate);
			else
				current.setAnimation(jumpLeftAnimate);
		}
		if(KeyInput.isKeyDown(KeyEvent.VK_S)|| KeyInput.isKeyDown(KeyEvent.VK_DOWN)){
			if(gliding <= 10 + level * 0.8){
				if(gliding <= 10 + level / 4)
					dy = -3;
				else
					dy = 0;
				gliding += 1;
				if(facingRight){
					current.setAnimation(flyRightAnimate);
					dx += 0.1;
				}
				else{
					current.setAnimation(flyLeftAnimate);
					dx -= 0.1;
				}
			}
		}
		if(KeyInput.isKeyDown(KeyEvent.VK_SPACE)){
			if(facingRight)
				current.setAnimation(attackRightAnimate);
			else
				current.setAnimation(attackLeftAnimate);
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
