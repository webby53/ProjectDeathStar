package Rendering;

/**@author Joshua Prpic, Kishon Webb, Simon Yacoub
 * @version 7.4
 * @since 2016-01-18
 */

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

import entities.Enemy;
import entities.Mob;
import entities.Player;
import entities.Tile;

public class TileMap {

	private boolean isLoaded;
	public Player player;
	private SpriteSheet sheet;
	private Sprite dirtSprite;
	private Sprite groundSprite;
	private static ArrayList<Mob> entities;
	private static ArrayList<Sprite> sprites;
	private int tiles[][];
	private Tile tilemap[][];

	/**Constructor
	 */
	public TileMap(){
		sprites = new ArrayList<Sprite>();
		entities = new ArrayList<Mob>();
		Texture tex = new Texture("SpriteCell(4x4)");
		sheet = new SpriteSheet(tex, 64);
		dirtSprite = new Sprite(sheet, 1, 1);
		groundSprite = new Sprite(sheet, 2, 1);
	}//constructor

	/**Tick
	 */
	public void tick(){
		player.tick();
		for(int i = 0; i < entities.size(); i++)
			entities.get(i).tick();
	}//tick

	/**This will load levels from a dat file
	 * 
	 * @param fileName
	 */
	public void load(String fileName){		
		//checks if games has been loaded first
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

			//assigns number to a 2d array based of level loaded
			for(int row = 0; row < rows; row++){
				String s = sc.next();
				for(int col = 0; col < cols; col++){
					char num = s.charAt(col);
					tiles[row][col] = Integer.parseInt(Character.toString(num));
				}
			}
			
			//depending on numbers in array, updates
			//2D array of tiles, sprites or entites(Mobs)
			for(int row = 0; row < tiles.length; row++){
				for(int col = 0; col < tiles[row].length; col++){
					switch(tiles[row][col]){
					case 0: 
						break;
					case 1: 
						tilemap[row][col] = new Tile(col * 64, row * 64, dirtSprite);
						break;
					case 2:
						player = new Player(col * 64, row * 64);
						break;
					case 3:
						entities.add(new Enemy(col * 64, row * 64));
					}
				}
			}
		//if a file is loaded clear it and load again;
		}else{
			clear();
			isLoaded = false;	
			load(fileName);
		}
	}//load

	/**Renders the tiles, entities and players that are found on the text file
	 * 
	 * @param g
	 */
	public void render(Graphics2D g){
		if(tilemap != null && tiles != null && entities != null){
			for(int row = 0; row < tilemap.length; row++){
				for(int col = 0; col < tilemap[row].length; col++){
					if(tilemap[row][col] != null){
						tilemap[row][col].render(g);
					}
				}
			}
			for(int i = 0; i < entities.size(); i++)
				entities.get(i).render(g);
			player.render(g);
		}else
			JOptionPane.showMessageDialog(null, "TileMap Error!");
	}//render

	/**Return selected tile
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Tile getTile(int x, int y){
		return tilemap[x][y];
	}//getTile

	/**Sets tile at x and y value
	 * 
	 * @param x
	 * @param y
	 * @param tile
	 */
	public void setTile(int x, int y, Tile tile){
		tilemap[x][y] = tile;
	}

	/**clears loaded level and all other stored values
	 * 
	 */
	public void clear(){
		tiles = null;
		tilemap = null;
		sprites.clear();
		entities.clear();
	}//clear

	/**
	 * 
	 * @return 
	 */
	public ArrayList<Mob> entityList(){
		return entities;
	}//Entities
	
	/**
	 * @param x
	 * @return
	 */
	public Mob entity(int x){
		return entities.get(x);
	}//Entities	


}