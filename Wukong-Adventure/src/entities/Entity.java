package entities;

import java.awt.Graphics;
import Rendering.Sprite;

public abstract class Entity {

	double x, y;
	private Sprite sprite;
	public Entity(double x, double y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}

	public void render(Graphics g){
		sprite.render(g, x, y);
	}
	
	public abstract void tick();
	
	
}
