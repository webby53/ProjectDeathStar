package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import Rendering.Sprite;

public abstract class Entity {

	double x, y;
	protected Sprite sprite;
	public static ArrayList<Entity> entities = new ArrayList<Entity>();
	protected Rectangle rec;
	
	public Entity(double x, double y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		rec = new Rectangle((int)x, (int)y, sprite.getWidth(), sprite.getHeight());
		entities.add(this);
	}

	@Override
	public String toString() {
		return "Entity [x=" + x + ", y=" + y + ", sprite=" + sprite + ", rec=" + rec + "]";
	}

	public void render(Graphics2D g){
		sprite.render(g, x, y);
		g.setColor(Color.YELLOW);
		rec.setLocation((int)x, (int)y);
		g.draw(rec);
	}
	
	public abstract void tick();
	
	public boolean collision(Rectangle rec){
		return this.rec.intersects(rec);
	}
	
	public int getWidth(){
		return sprite.getWidth();
	}
	public int getHeight(){
		return sprite.getHeight();
	}
	
}
