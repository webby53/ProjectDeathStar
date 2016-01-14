package SimonStuff;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Main extends JFrame {
	BufferedImage sprite;
	public Main(){
	setSize(800,600);
	setVisible(true);
	setResizable(false);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocationRelativeTo(null);
	init();
	}//main
	
	private void init(){
	
	}
	public void paint(Graphics g){
		
	}
	
	public static void main(String [] args){
		Main main = new Main(); 
	}
	

}
