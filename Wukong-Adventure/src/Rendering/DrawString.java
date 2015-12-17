package Rendering;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import Main.Game;
import input.MouseInput;

public class DrawString {

	//draws a string at the specified location with the specified color and font
	public static void drawString(Graphics g, String text, Font f, Color c, int x, int y){
		g.setColor(c);
		g.setFont(f);
		g.drawString(text, x, y);
	}
	
	public static void drawString(Graphics g, String text,Color c, Font f){
		g.setColor(c);
		g.setFont(f);
		FontMetrics fm = g.getFontMetrics(f);
		int y = (Game.HEIGHT - fm.getHeight()) / 2;
		int x = (Game.WIDTH - fm.stringWidth(text)) / 2;
		g.drawString(text, x, y);
	}
	
	public static void drawStringCenterH(Graphics g, String text,Color c, Font f, int x){
		g.setColor(c);
		g.setFont(f);
		FontMetrics fm = g.getFontMetrics(f);
		int y = (Game.HEIGHT - fm.getHeight()) / 2;
		g.drawString(text, x, y);
	}
	
	public static void drawStringCenterV(Graphics g, String text, Color c, Font f, int y){
		FontMetrics fm = g.getFontMetrics(f);
		g.setColor(c);
		g.setFont(f);
		int x = (Game.WIDTH - fm.stringWidth(text)) / 2;
		g.drawString(text, x, y);
	}
	
	public static void drawInfo(Graphics g){
		DrawString.drawString(g, "X:" + MouseInput.getX() + " Y:" + MouseInput.getY(), new Font("Arial",Font.PLAIN, 13),  Color.BLUE,Game.WIDTH - 105, 11);
		DrawString.drawString(g, "FPS:" + Game.FPS + " TPS:" + Game.TPS, new Font("Arial",Font.PLAIN, 13),  Color.BLUE,10, 11);
	}
}
