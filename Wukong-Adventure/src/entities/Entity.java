package entities;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import Rendering.Sprite;

public abstract class Entity {

	double x, y;
	public Sprite sprite;
	public Rectangle2D recTop, recBot, recLeft, recRight;
	public boolean solid;//not used
	public Entity(double x, double y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}


	public void render(Graphics2D g){
		this.tick();
		sprite.render(g, x, y);
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
