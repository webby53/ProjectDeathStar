package entities;

import Rendering.Sprite;
import states.GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;


public class Tile extends Entity{
	protected static ArrayList<Tile> tiles = new ArrayList<Tile>();
	protected double x, y;
	

	public Tile(double x, double y, Sprite sprite) {
		super(x, y, sprite);
		this.x = x;
		this.y = y;
		tiles.add(this);
		recLeft = new Rectangle((int)this.x, (int)this.y + 5, 1, sprite.getHeight() - 3);
		recRight = new Rectangle((int)this.x + sprite.getWidth(), (int)this.y + 5, 1, sprite.getWidth() - 3);
		recTop = new Rectangle((int)this.x + 4, (int)this.y, sprite.getWidth() - 7, 1);
		recBot = new Rectangle((int)this.x + 4, (int)this.y + sprite.getHeight(), sprite.getWidth() - 7, 1);

	}

	@Override
	public void tick() {
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
