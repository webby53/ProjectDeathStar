package testClass;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.*;	
import javax.swing.*;

//a kind of panel that has all objects (like Wukong) on it
public class Board extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Character wukong;
	Image img; //contains background image
	Timer time; //timer to update the frames(so we SEE something happen)
	
	public Board(){
		wukong = new Character();
		addKeyListener(new AL());
		setFocusable(true); //allows character to move on board
		ImageIcon i = new ImageIcon(this.getClass().getResource("sky.png")); // gets image
		img = i.getImage();
		time = new Timer(5, this);
		time.start();
	}
	
	public void actionPerformed(ActionEvent e){
		wukong.move();
		repaint();
	}
	
	public void paint(Graphics g){
		super.paint(g);
			Graphics2D g2d = (Graphics2D) g;
			
			g2d.drawImage(img, nx2, 0, null);
			g2d.drawImage(wukong.getImage(), 472, wukong.getY(), null);
	}
	
	//this is the action listener class
	//the key adaptor implementation determines which key was pressed
	private class AL extends KeyAdapter{
		
		//these methods are self explanatory
		public void keyReleased(KeyEvent e){
			wukong.keyReleased(e);
		}
		
		public void keyPressed(KeyEvent e){
			wukong.keyPressed(e);
		}
	}

}
