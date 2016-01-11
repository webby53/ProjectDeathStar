package Rendering;

import java.util.ArrayList;

import entities.Entity;
import entities.Player;
import entities.Tile;

public class TileMap {

	private String name;
	private int width, height;
	private Player player;
	private ArrayList<Tile> tiles;
	private ArrayList<Entity> entities;
	
	TileMap(String name){
		entities = new ArrayList<Entity>();
		tiles = new ArrayList<Tile>();
	}
	
	public void tick(){
		for(int i = 0; i < entities.size(); i++)
			entities.get(i).tick();
		for(int i = 0; i < tiles.size(); i++)
			tiles.get(i).tick();
	}
	
}
