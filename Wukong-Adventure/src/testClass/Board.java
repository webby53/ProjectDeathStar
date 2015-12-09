package testClass;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.*;

import javax.swing.*;

//a kind of panel that has all objects (like Wukong) on it
public class Board extends JPanel implements ActionListener{

	Character wukong;
	Image img; //contains background image
	Timer time; //timer to update the frames(so we SEE something happen)
	
	public Board(){
		wukong = new Character();
		setFocusable(true); //allows charcter to move on board
		ImageIcon i = new ImageIcon("testcharacter.png"); // gets image
		img = i.getImage();
		time = new Timer(5, this);
		time.start();
	}
	
	public void actionPreformed(ActionEvent e){
		repaint();
	}
	
	public void paint(Graphics g){
		super.paint(g);
			Graphics2D g2d = (Graphics2D) g;
	}
	
	//this is the action listener class
	//the key adaptor implementation determines which key was pressed
	private class AL extends KeyAdapter{
		
		//these methods are self explanatory
		public void keyReleased(KeyEvent e){
			
		}
		
		public void keyPressed(KeyEvent e){
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
