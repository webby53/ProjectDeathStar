package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import states.GameState;
import Rendering.Sprite;
import input.KeyInput;
import input.MouseInput;

public class Player extends Mob{

	protected static boolean canJump;
	protected double speed = 0.5;
	protected double maxSpeed = 3;
	protected boolean facingRight;
	private Rectangle2D recAttackBox;
	private boolean attacking = false;

	public Player(double x, double y, Sprite sprite) {
		super(x, y, sprite);
		recAttackBox = new Rectangle((int)x, (int)y + 16, this.getHeight() + 22, this.getWidth() - 32);
	}

	public void tick(){
		//Corrects the facing of the attack box
		if(facingRight){
			recAttackBox = new Rectangle((int)x, (int)y + 16, this.getHeight() + 32, this.getWidth() - 32);
		}else{
			recAttackBox = new Rectangle((int)x - 32, (int)y + 16, this.getHeight() + 32, this.getWidth() - 32);
		}
		
		//key inputs
		if(KeyInput.isKeyDown(KeyEvent.VK_A) || KeyInput.isKeyDown(KeyEvent.VK_LEFT)){
			dx += -speed;
			if(dx < -maxSpeed) dx = -maxSpeed;
			facingRight = false;
		}
		if(KeyInput.isKeyDown(KeyEvent.VK_D)|| KeyInput.isKeyDown(KeyEvent.VK_RIGHT)){
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
			attacking = true;
		}
		super.tick();	
	}

	//jump for player
	public void jump(){
		if(canJump){
			x += dx;
			dy = -10;
			canJump = false;
			falling = true;
		}
	}//jump
	
	
	public Rectangle2D showAttack(){
		return recAttackBox;
	}
	
	public double getHorSpeed(){
		return dx;
	}
	
	public boolean isFacingRight(){
		return facingRight;
	}
	
	public void render(Graphics2D g){
		super.render(g);
		if(attacking)
			g.setColor(Color.BLUE);
		else
			g.setColor(Color.RED);
		if(GameState.debugging){
			g.draw(recAttackBox);
		}
	}
	
	public boolean isAttacking(ArrayList<Enemy> b){
		boolean attack = false;
		
		for(int i = 0; i < b.size(); i++){
			if(recAttackBox.intersects(b.get(i).getBounds()) && attacking){
				attack = true;
			}
		}
		
		return attack;
	}
	
}
