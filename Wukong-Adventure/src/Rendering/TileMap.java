package Rendering;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import entities.Entity;
import entities.Player;
import entities.Tile;

public class TileMap {

	private String name;
	private int width, height;
	private Player player;
	//private ArrayList<Tile> tiles;
	private ArrayList<Entity> entities;
	private SpriteSheet sheet;
	private Sprite sprite;
	private int tiles[][];

	public TileMap(String name){
		this.name = name;
		Texture tex = new Texture("SpriteCell(4x4)");
		sheet = new SpriteSheet(tex, 64);
		sprite = new Sprite(sheet, 1, 1);
		entities = new ArrayList<Entity>();
		entities.add(player);
	}

	public void tick(){
		for(int i = 0; i < entities.size(); i++)
			entities.get(i).tick();
		//for(int i = 0; i < tiles.size(); i++)
		//tiles.get(i).tick();
	}

	//this will load levels from a text file
	//number correspond to tiles
	public void load(String fileName){
		File file = new File("./resources/levels/" + fileName + ".dat");
		Scanner sc = null;
		try{
			sc = new Scanner(file);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}

		int rows = sc.nextInt();
		int cols = sc.nextInt();

		tiles = new int[rows][cols];

		for(int row = 0; row < rows; row++){
			String s = sc.next();
			for(int col = 0; col < cols; col++){
				tiles[row][col] = s.charAt(col);
			}
		}
	}//load

	public void render(Graphics2D g){

		for(int row = 0; row < tiles.length; row++){
			for(int col = 0; col < tiles[row].length; col++){

				switch(tiles[row][col]){
				case 0: 
				break;
				case 1: 
					Tile test = new Tile(row * 64, col * 64, sprite);
					test.render(g);
				break;
				}
			}
		}
	}//render
}
