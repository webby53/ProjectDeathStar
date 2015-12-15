package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import Rendering.DrawString;
import input.MouseInput;
import input.KeyInput;

public class Menu {

	private static final String[] SELECTIONS = {"Play", "Help", "Exit"};
	private static int currentSelection = 0;
	//KeyInput key = new 

	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		DrawString.drawString(g, "X:" + MouseInput.getX() + " Y:" + MouseInput.getY(), new Font("Arial",Font.PLAIN, 13),  Color.BLUE,Game.WIDTH - 105, 11);
		DrawString.drawStringCenterV(g, Game.TITLE, Color.GREEN, new Font("Arial",Font.BOLD, 30), 30);
	
		for(int i = 0; i < SELECTIONS.length; i++)
			DrawString.drawStringCenterV(g, SELECTIONS[i], Color.BLACK, new Font("Arial",Font.BOLD, 26), 100 * (i + 1) + 50);
		
		//if
	}
	
	//public 
	
}
