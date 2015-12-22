package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import Rendering.Sprite;

public abstract class Entity {

	double x, y;
	protected Sprite sprite;
	protected Rectangle2D recTop, recBot, recLeft, recRight;
	protected boolean solid;//not used
	public static double camx;
	
	public Entity(double x, double y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}


	public void render(Graphics2D g){
		this.tick();
		sprite.render(g, x, y);
		g.translate(0 + camx, 0);
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
