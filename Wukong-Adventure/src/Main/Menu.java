package Main;

import java.awt.Color;
import java.awt.Graphics;

import Rendering.DrawString;
import input.MouseInput;

public class Menu {

	private static final String[] SELECTIONS = {Game.TITLE, "Play", "Help", "Exit"};
	private static int currentSelection;	

	public void render(Graphics g){
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		DrawString.drawString(g, "X:" + MouseInput.getX() + " Y:" + MouseInput.getY(), 
				Game.WIDTH / 2, Game.HEIGHT / 2);
	}
}
