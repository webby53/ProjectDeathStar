package entities;

import Rendering.Sprite;

import java.awt.Rectangle;
import java.util.ArrayList;


public class Tile extends Entity{
	public static ArrayList<Tile> tiles = new ArrayList<Tile>();


	public Tile(double x, double y, Sprite sprite) {
		super(x, y, sprite);
		tiles.add(this);
		recTop = new Rectangle((int)x + 1, (int)y - 2, sprite.getWidth() - 2, 1);
		recBot = new Rectangle((int)x + 1, (int)y + sprite.getHeight() - 1, sprite.getWidth() - 2, 1);
		recLeft = new Rectangle((int)x - 2, (int)y, 1, sprite.getHeight() - 2);
		recRight = new Rectangle((int)x + sprite.getWidth() + 1, (int)y, 1, sprite.getWidth() - 2);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
	}
}
