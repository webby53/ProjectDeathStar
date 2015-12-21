package entities;

import Rendering.Sprite;

import java.awt.Rectangle;
import java.util.ArrayList;


public class Tile extends Entity{
	public static ArrayList<Tile> tiles = new ArrayList<Tile>();


	public Tile(double x, double y, Sprite sprite) {
		super(x, y, sprite);
		tiles.add(this);
		recTop = new Rectangle((int)x, (int)y - 1, sprite.getWidth() + 2, 1);
		recBot = new Rectangle((int)x, (int)y + sprite.getHeight(), sprite.getWidth() + 2, 1);
		recLeft = new Rectangle((int)x - 3, (int)y, 1, sprite.getHeight());
		recRight = new Rectangle((int)x + sprite.getWidth(), (int)y, 1, sprite.getWidth());
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
	}
}
