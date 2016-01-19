package GUI;

/**@author Joshua Prpic, Kishon Webb, Simon Yacoub
 * @version 7.5
 * @since 2016-01-19
 */ 

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Main.Game;
import Rendering.DrawString;

@SuppressWarnings("serial")
public class Button extends Rectangle{

	//fonts and colors for a selected and non selected button
	//all buttons are rectangles that have a font(so we can set size)
	//and a text in the middle
	private Font currentFont, selectedFont;
	private Color currentColor, selectedColor;
	private String text;
	private int texty;//y value of where to draw string since text strings are drawn in lower left rather than top left
	private boolean isSelected;
	
	/**Constructor default
	 * 
	 * @param text
	 * @param currentFont
	 * @param selectedFont
	 * @param currentColor
	 * @param selectedColor
	 * @param texty
	 */
	public Button(String text, Font currentFont, Font selectedFont, Color currentColor, Color selectedColor,
			int texty) {
		super();
		this.text = text;
		this.currentFont = currentFont;
		this.selectedFont = selectedFont;
		this.currentColor = currentColor;
		this.selectedColor = selectedColor;
		this.texty = texty;
	}
	
	/**Constructor with adjustable placement
	 * 
	 * @param text
	 * @param currentFont
	 * @param selectedFont
	 * @param currentColor
	 * @param selectedColor
	 * @param texty
	 * @param x
	 */
	public Button(String text, Font currentFont, Font selectedFont, Color currentColor, Color selectedColor,
			int texty, int x) {
		super();
		this.text = text;
		this.currentFont = currentFont;
		this.selectedFont = selectedFont;
		this.currentColor = currentColor;
		this.selectedColor = selectedColor;
		this.texty = texty;
	}
	
	/**This method is to set if a button is being selected
	 * 
	 * @param selected
	 */
	public void setSelected(boolean selected){
		isSelected = selected;
	}
	/**Button rendering
	 * 
	 * @param g
	 */
	public void render(Graphics2D g){
		//draws the text first and bold if they are selected (and changes color)
		if(isSelected)
			DrawString.drawStringCenterV(g, text, selectedColor, selectedFont, texty);
		else
			DrawString.drawStringCenterV(g, text, currentColor, currentFont, texty);
        //this is so we get the right font metrics sizes for the font being drawn
		FontMetrics fm = g.getFontMetrics();
		//this is similar to how we draw centered text in the DrawStirng class
		this.x = (Game.WIDTH - fm.stringWidth(text)) / 2;
		//since strings are drawn from bottom we subtract height of font from the y
		this.y = texty - fm.getHeight();
		
		//these are for rectangle dimensions we can then use a intersection check to tell if someone is clicking inside
		this.height = fm.getHeight();
		this.width = fm.stringWidth(text);
		//draws the text first and bold if they are selected (and changes color)
	}
	
	/**Renders the button at a specified x coordinate
	 * 
	 * @param g
	 * @param x
	 */
	public void render(Graphics2D g, int x){
		//draws the text first and bold if they are selected (and changes color)
		this.x = x;
		if(isSelected)
			DrawString.drawString(g, text, selectedFont, selectedColor, x, texty);
		else
			DrawString.drawString(g, text, currentFont, currentColor, x, texty);
        //this is so we get the right font metrics sizes for the font being drawn
		FontMetrics fm = g.getFontMetrics();
		//this is similar to how we draw centered text in the DrawStirng class
		//since strings are drawn from bottom we subtract height of font from the y
		this.y = texty - fm.getHeight();
		
		//these are for rectangle dimensions we can then use a intersection check to tell if someone is clicking inside
		this.height = fm.getHeight();
		this.width = fm.stringWidth(text);
		//draws the text first and bold if they are selected (and changes color)
	}
}
