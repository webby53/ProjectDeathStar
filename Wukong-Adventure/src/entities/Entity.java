package entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import Rendering.Sprite;

public abstract class Entity {

	double x, y;
	protected Sprite sprite;
	public Entity(double x, double y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}

	public void render(Graphics2D g){
		sprite.render(g, x, y);
	}
	
	public abstract void tick();
	
	public Rectangle getBounds(){
		Rectangle rec = new Rectangle((int)x, (int)y, sprite.getWidth(), sprite.getHeight());
		return rec;
	}
	
	public int getWidth(){
		return sprite.getWidth();
	}
	public int getHeight(){
		return sprite.getHeight();
	}
	
}
