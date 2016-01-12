package Rendering;

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
	private ArrayList<Tile> tiles;
	private ArrayList<Entity> entities;
	
	public TileMap(String name){
		this.name = name;
		entities = new ArrayList<Entity>();
		tiles = new ArrayList<Tile>();
	}
	
	public void tick(){
		for(int i = 0; i < entities.size(); i++)
			entities.get(i).tick();
		for(int i = 0; i < tiles.size(); i++)
			tiles.get(i).tick();
	}
	
	//this will load levels from a text file
	//number corespond to tiles
	public void load(String fileName){
		File file = new File("./resources/levels/" +	fileName + ".txt");
		
		try{
			Scanner sc = new Scanner(file);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		System.out.println("Loaded tile map: " + fileName);
	}//load
	
}
