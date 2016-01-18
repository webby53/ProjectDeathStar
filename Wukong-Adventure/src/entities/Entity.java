package entities;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import Rendering.Animation;
import Rendering.Sprite;

public abstract class Entity {

	protected double x, y;
	protected Sprite sprite;
	protected Animation animate;
	//protected boolean dead = false;
	protected Rectangle2D recTop, recBot, recLeft, recRight;

	public Entity(double x, double y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	public Entity(double x, double y) {
		this.x = x;
		this.y = y;
		sprite = null;
	}
	public Entity(double x, double y, Animation animate) {
		this.x = x;
		this.y = y;
		this.animate = animate;
		this.sprite = animate.getFrame();
	}

	public void render(Graphics2D g){
		//if(!dead){
			this.tick();
			if(sprite != null)
				sprite.render(g, x, y);
		//}
	}
	public boolean isDead(){
		return true;
	}

	public abstract void tick();

	public int getWidth(){
		return sprite.getWidth();
	}
	public int getHeight(){
		return sprite.getHeight();
	}

	public int getX(){
		return (int)x;
	}
	public int getY(){
		return (int)y;
	}
}
