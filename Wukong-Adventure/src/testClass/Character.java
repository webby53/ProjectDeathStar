package testClass;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.*;

//this class is for the main character of game
//for all intents and purposes he will be refereed to as Wukong
public class Character {

	int x, dx, y; //this is the x coordinate('x')
	//and change in x('dx') and y coordinate('y')
	Image wu;

	//this 
	public Character(){
		ImageIcon i = new ImageIcon("testcharacter.png");
		wu = i.getImage();

		//these are Wukong's starting coordinates
		x = 15;
		y = 20;
	}//Constructor

	//moves the character horizontally
	public void move(){
		x += dx;
	}

	//these return the x and y coordinates since variables are static
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}

	public Image getImage(){
		return wu;
	}

	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
	}
}//Character
