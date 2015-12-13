package Rendering;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import Managers.TextureManager;

public class Textures{

	private final static Map<String, TextureManager> textmap = new HashMap<String, TextureManager>();
	private String fileName;
	//creatures a texture manager for a texture
	private TextureManager manager;
	
	//so creates a texture 
	public Textures(String fileName){
		this.fileName = fileName;
		TextureManager oldTexture = textmap.get(fileName);
		if(oldTexture != null){
			manager = oldTexture;
			manager.addReference();
		}else{
			try {
				System.out.println("Printing file: " + fileName);
				manager = new TextureManager(ImageIO.read(new File("./resources/textures/" + fileName + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void finalize() throws Throwable{
		if(manager.removeReference() && !fileName.isEmpty()) textmap.remove(fileName);
		super.finalize();
	}	
	
	//this will draw the texture at the specified x and y
	 public void render(Graphics g, double x, double y){
		 g.drawImage(manager.getImage(), (int)x, (int)y, null);
	 }
}
