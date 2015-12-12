package Rendering;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Textures {

	private final static Map<String, Textures> textmap = new HashMap<String, Textures>();
	
	//stores images in memory
	private BufferedImage image;
	
	public Textures(String filename){
		try{
			image = ImageIO.read(new File("./resources/textures/" + filename + ".png");
			textmap.put(filename, this);
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
}
