package Rendering;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import entities.Mob;
import entities.Player;
import entities.Tile;

public class TileMap {

	private Texture charTextures = new Texture("wukong sheet");
	private SpriteSheet charSheet = new SpriteSheet(charTextures, 64);
	private Sprite charSprite = new Sprite(charSheet, 1, 1);
	private static ArrayList<Mob> entities = new ArrayList<Mob>();
	private SpriteSheet sheet;
	private Sprite dirtSprite;
	private Sprite playerSprite;
	private int tiles[][];
	private Tile tilemap[][];
	//checks to makes sure another level is loaded
	private boolean isLoaded = false;

	public TileMap(){
		Texture tex = new Texture("SpriteCell(4x4)");
		sheet = new SpriteSheet(tex, 64);
		dirtSprite = new Sprite(sheet, 1, 1);
		
	}

	public void tick(){
		for(int i = 0; i < entities.size(); i++)
			entities.get(i).tick();
	}

	//this will load levels from a text file
	//number correspond to tiles
	public void load(String fileName){
		if(!isLoaded){
			File file = new File("./resources/levels/" + fileName + ".dat");
			Scanner sc = null;
			try{
				sc = new Scanner(file);
			}catch(FileNotFoundException e){
				e.printStackTrace();
			}
			isLoaded = true;
			int rows = sc.nextInt();
			int cols = sc.nextInt();

			tiles = new int[rows][cols];
			tilemap = new Tile[rows][cols];

			for(int row = 0; row < rows; row++){
				String s = sc.next();
				for(int col = 0; col < cols; col++){
					char num = s.charAt(col);
					tiles[row][col] = Integer.parseInt(Character.toString(num));
				}
			}
			for(int row = 0; row < tiles.length; row++){
				for(int col = 0; col < tiles[row].length; col++){

					switch(tiles[row][col]){
					case 0: 
						break;
					case 1: 
						tilemap[row][col] = new Tile(col * 64, row * 64, dirtSprite);
						break;
					case 2:
						entities.add(new Player(col * 64, row * 64, charSprite));
					}
				}
			}
		}else{
			clear();
			isLoaded = false;	
			load(fileName);
		}
	}//load

	//renders tilemap
	public void render(Graphics2D g){
		for(int row = 0; row < tilemap.length; row++){
			for(int col = 0; col < tilemap[row].length; col++){
				if(tilemap[row][col] != null){
					tilemap[row][col].render(g);
				}
			}
		}
		//for(int i = 0; i < entities.size(); i++)
		entities.get(0).render(g);
	}//render

	//return selected tile
	public Tile getTile(int x, int y){
		return tilemap[x][y];
	}//getTile

	public void setTile(int x, int y, Tile tile){
		tilemap[x][y] = tile;
	}

	//clears tiles
	public void clear(){
		tiles = null;
		tilemap = null;
		entities.clear();
	}//clear

	public ArrayList<Mob> entityList(){
		return entities;
	}//Entities
	public Mob entity(int x){
		return entities.get(x);
	}//Entities	


}