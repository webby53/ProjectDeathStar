package testClass;

import javax.swing.JFrame;

//the class frame which is the game outline
public class Frame extends TryingGraphics {
	
	public static void main(String [] args){
	
		//creates a new frame
		JFrame frame = new JFrame("Wukong's Adventure");
		
		frame.add(new Board());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(450,150);  //this value should be the size of the background
					             //THIS IS FOR YOU SIMON	
		frame.setVisible(true);
		
		
	}//main	
}//Frame
