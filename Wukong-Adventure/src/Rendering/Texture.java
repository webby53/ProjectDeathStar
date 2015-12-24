package Rendering;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
/*	This class ensures only one use of textures
 * 	auto removes unused textures
 */
public class Texture{

	private final static Map<String, BufferedImage> textmap = new HashMap<String, BufferedImage>();
	private String fileName;
	//stores a texture
	private BufferedImage image;
	
	//so creates a texture 
	public Texture(String fileName){
		this.fileName = fileName;
		BufferedImage oldTexture = textmap.get(this.fileName);
		if(oldTexture != null){
			image = oldTexture;
		}else{
			try {
				image = ImageIO.read(new File("./resources/textures/" + this.fileName + ".png"));
				textmap.put(this.fileName,image);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//this will draw the texture at the specified x and y
	 public void render(Graphics g, double x, double y){
		 g.drawImage(image, (int)x, (int)y, null);
	 }
	 
	 public BufferedImage getImage(){
		 return image;
	 }
	 
}
