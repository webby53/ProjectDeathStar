package Main;

/**@author Joshua Prpic, Kishon Webb, Simon Yacoub
 * @version 7.5
 * @since 2016-01-19
 */ 

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import GUI.LoadingScreenLauncher;

public class Main {
	/**Main method of the game
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		
		//loading screen
		//new LoadingScreenLauncher();
		
		//creates our main frame and instance of Game
		final Game game = new Game();
		
		JFrame frame = new JFrame(Game.TITLE);
		
		frame.add(game);
		frame.setSize(Game.WIDTH, Game.HEIGHT);
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
		
		//puts game frame in center of screen
		frame.setLocationRelativeTo(null);
		//so it can be seen
		frame.setVisible(true);
		game.start();
	}
	
}
