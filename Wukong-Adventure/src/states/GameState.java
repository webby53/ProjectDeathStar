package states;

/**@author Joshua Prpic, Kishon Webb, Simon Yacoub
 * @version 7.5
 * @since 2016-01-19
 */ 

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import GUI.Button;
import Main.Game;
import Managers.StateManager;
import Rendering.*;
import entities.Player;
import entities.Enemy;
import input.MouseInput;


public class GameState implements State{

	public static boolean debugging, options;
	private ArrayList<Button> buttons;
	private int currentSelection;
	private Background bg = new Background("./resources/textures/Background.png", 10);
	private Camera cam;
	private String[] currentlevel;
	private static TileMap tileMap;

	/**Initializes the default variables in the game state
	 * 
	 */
	public void init() {
		bg = new Background("./resources/textures/Background.png", 10);
		bg.setX(0);
		bg.setY(0);
		//background (also should be implemented in tilemapping)	
		bg.setCx(-0.6);
		tileMap = new TileMap("level1");
	}//init
	
	/**Method that runs each time the level is entered, refreshes the level after death
	 * 
	 */
	//runs on entrance
	public void enter() {
		cam = new Camera(tileMap.player);
		cam.setX(tileMap.player.getX() + Game.WIDTH / 2 - 300);

		//sets the buttons in Game
		buttons = new ArrayList<Button>();
		buttons.add(new Button("Back",  new Font("Ariel", Font.PLAIN, 25), new Font("Ariel", Font.BOLD, 35),
				Color.BLACK, Color.ORANGE, 50));
		buttons.add(new Button("Debug",  new Font("Ariel", Font.PLAIN, 25), new Font("Ariel", Font.BOLD, 35),
				Color.BLACK, Color.ORANGE, 90));
		buttons.add(new Button("Exit",  new Font("Ariel", Font.PLAIN, 25), new Font("Ariel", Font.BOLD, 35),
				Color.BLACK, Color.GREEN, 130));

	}//enter

	/**Method that runs each tick, used to run methods that must be constantly checked
	 * 
	 */
	public void tick(StateManager stateManager) {
		//checks if player is dead
		tileMap.tick();

		if(tileMap.player.isOffScreen()){
			JOptionPane.showMessageDialog(null, "You have fallen to your death. You will now be sent back to the menu.");
			Game.INSTANCE.setFocusable(true);
			tileMap.load("level1");
			stateManager.setState("menu");
			return;
		}
		if(tileMap.player.isCollided(tileMap.entityList())){
			JOptionPane.showMessageDialog(null, "An enemy has killed you. You will now be sent back to the menu"); Game.INSTANCE.setFocusable(true);
			tileMap.load("level1");
			stateManager.setState("menu");
			return;
		}
		//checks if a player atacks an enemy
		int enemyNum = tileMap.player.isAttacking(tileMap.entityList());
		if(enemyNum != -1){
			if(tileMap.entity(enemyNum).isDead() == true){
				tileMap.entityList().remove(enemyNum);
			}else
				if(tileMap.entity(enemyNum).getLife() <= 0)
					tileMap.entity(enemyNum).setDead(true);
				else	
					tileMap.entity(enemyNum).takeLife(1);
		}
		//mouse check
		boolean isClicked = false;
		if(options){
			for(int i = 0; i < buttons.size(); i++){
				if(buttons.get(i).intersects(new Rectangle(MouseInput.getX(), MouseInput.getY(), 1, 1))){
					currentSelection = i;
					if(MouseInput.wasPressed(MouseEvent.BUTTON1))
						isClicked = true;
					else
						isClicked = false;
				}
				if(!buttons.get(i).intersects(new Rectangle(MouseInput.getX(), MouseInput.getY(), 1, 1)))
					if(MouseInput.wasReleased(MouseEvent.BUTTON1))
						currentSelection = -1;
			}
		}
		if(isClicked)
			select(stateManager);

		cam.tick();
		if(tileMap.endTile.isTouched(tileMap.player)){
			tileMap.load("level2");	
			enter();
			return;
		}
	}//tick

	/**Determines which button in the debugging menu is clicked
	 * 
	 * @param stateManager
	 */
	public void select(StateManager stateManager){
		switch(currentSelection){
		case 0: stateManager.setState("Menu");
		break;
		case 1:
			if(debugging)
				debugging = false;
			else{
				debugging = true;
			}
			break;
		case 2: Game.INSTANCE.stop();
		break;
		}
	}//select
	
	/**Renders the graphics of the game
	 * 
	 */
	public void render(Graphics2D g) {
		//used to reset graphics

		//background rendering
		bg.draw(g);
		//Text and other
		if(debugging){
			DrawString.drawInfo(g);
			DrawString.drawStringCenterV(g, Game.TITLE, Color.CYAN, new Font("Arial", Font.CENTER_BASELINE, 20), 25);
			DrawString.drawString(g, "Player[X:" + tileMap.player.getX() + " Y:" + tileMap.player.getY() + "]", new Font("Arial",Font.PLAIN, 13),  Color.BLUE,Game.WIDTH - 128, 11 * 2);
		}
		//entites and tiles
		/////////////////////////////////////////////////////////////////
		g.translate(cam.getX(), cam.getY());
		tileMap.render(g);
		g.translate(-cam.getX(), -cam.getY());
		/////////////////////////////////////////////////////////////////
		//button selection
		if(options){
			for(int i = 0; i < buttons.size(); i++){
				if(i == currentSelection)	
					buttons.get(i).setSelected(true);
				else
					buttons.get(i).setSelected(false);
				buttons.get(i).render(g, Game.WIDTH - 110);
			}		
		}

	}//render

	/**Clears the game when killed, or returning to the menu
	 * 
	 */
	public void exit() {
		buttons.clear();
	}//exit

	/**Returns the name of the state when called
	 * 
	 */
	public String getName() {
		return "Game";
	}//getName

}
