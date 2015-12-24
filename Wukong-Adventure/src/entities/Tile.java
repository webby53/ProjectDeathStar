package entities;

import Rendering.Sprite;
import states.GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;


public class Tile extends Entity{
	public static ArrayList<Tile> tiles = new ArrayList<Tile>();
	public double x, y;
	

	public Tile(double x, double y, Sprite sprite) {
		super(x, y, sprite);
		this.x = x;
		this.y = y;
		tiles.add(this);
		recTop = new Rectangle((int)this.x + 1, (int)this.y - 1, sprite.getWidth() - 2, 1);
		recBot = new Rectangle((int)this.x + 1, (int)this.y + sprite.getHeight() - 1, sprite.getWidth() - 2, 1);
		recLeft = new Rectangle((int)this.x - 2, (int)this.y, 1, sprite.getHeight() - 2);
		recRight = new Rectangle((int)this.x + sprite.getWidth() + 1, (int)this.y, 1, sprite.getWidth() - 2);
	}

	@Override
	public void tick() {
	}
	
	public void move(int x, int y){
		this.x += x;
		this.y += y;
	}

	public void render(Graphics2D g){
		sprite.render(g, x, y);
		if(GameState.debugging){
			g.setColor(Color.BLUE);
			g.draw(recTop);
			g.setColor(Color.RED);
			g.draw(recBot);
			g.setColor(Color.GREEN);
			g.draw(recLeft);
			g.setColor(Color.ORANGE);
			g.draw(recRight);
		}
	}
}
