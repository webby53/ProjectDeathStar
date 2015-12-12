package version1;

import java.awt.Canvas;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

	public static final String TITLE = "Wukongs Advernture Ver 1.0";
	public static final int WIDTH = 896;
	public static final int HEIGHT = WIDTH / 4 * 3;
	
	//makes a new thread
	public void start(){
		new Thread(this, "Main-Thread").start();
	}
	
	
	//stops the game
	private void stop(){
		System.exit(0);
	}//stop

	//used to start a thread of our game
	public void run(){
		System.out.println("The Game has Started!");
	}//run
	
	public static void main(String[] args){
		//creates our main frame and instance of Game
		final Game game = new Game();
		JFrame frame =new JFrame(TITLE);
		
		frame.add(game);
		frame.setSize(WIDTH, HEIGHT);
		//so they can't change window size
		frame.setResizable(false);
		//so we can get key inputs
		frame.setFocusable(true);
		frame.addWindowListener(new WindowAdapter(){
			
			public void windowClosing(WindowEvent e){
				System.err.println("The Game is now Closing...");
				game.stop();
			}
		});
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.requestFocus();	
		game.start();
	}
	
}
