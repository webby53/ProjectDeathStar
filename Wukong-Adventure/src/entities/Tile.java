package entities;

import Rendering.Sprite;
import states.GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;


public class Tile extends Entity{
	public static ArrayList<Tile> tiles = new ArrayList<Tile>();


	public Tile(double x, double y, Sprite sprite) {
		super(x, y, sprite);
		tiles.add(this);
		recTop = new Rectangle((int)x + 1, (int)y - 3, sprite.getWidth() - 2, 1);
		recBot = new Rectangle((int)x + 1, (int)y + sprite.getHeight() - 1, sprite.getWidth() - 2, 1);
		recLeft = new Rectangle((int)x - 2, (int)y, 1, sprite.getHeight() - 2);
		recRight = new Rectangle((int)x + sprite.getWidth() + 1, (int)y, 1, sprite.getWidth() - 2);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
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
