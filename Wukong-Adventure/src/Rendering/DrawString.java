package Rendering;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class DrawString {

	//draws a string at the specified location with the specified color and font
	public static void drawString(Graphics g, String text, Font f, Color c, int x, int y){
		g.setColor(c);
		g.setFont(f);
		g.drawString(text, x, y);
	}
	//just draws the string at the specifed location
	public static void drawString(Graphics g, String text, int x, int y){
		g.setColor(Color.BLACK);
		g.setFont(Font.getFont("Arial"));
		g.drawString(text, x, y);
	}
}
