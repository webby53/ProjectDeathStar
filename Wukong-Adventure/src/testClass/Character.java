package testClass;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


//this class is for the main character of game
//for all intents and purposes he will be refereed to as Wukong
public class Character {

	int x, dx, y; //this is the x coordinate('x')
	//and change in x('dx') and y coordinate('y')
	Image wu;

	//this 
	public Character(){
		ImageIcon i = new ImageIcon(this.getClass().getResource("testcharacter.png"));
		wu = i.getImage();

		//these are Wukong's starting coordinates
		x = 15;
		y = 20;
	}//Constructor

	//moves the character horizontally
	public void move(){
		x += dx;
	}

	//these return the x and y coordinates since variables are static like my mixtape
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}

	public Image getImage(){
		return wu;
	}

	//increase the change in dx
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT)
			dx = -2;
		if(key == KeyEvent.VK_RIGHT)
			dx = 2;
	}
	//sets dx back to 0
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT)
			dx = 0;
		if(key == KeyEvent.VK_RIGHT)
			dx = 0;
		
	}
}//Character
