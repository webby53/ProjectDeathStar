package states;

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

	public static boolean debugging;
	private ArrayList<Button> buttons;
	private int currentSelection;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private Background bg = new Background("./resources/textures/Background.png", 10);
	private Camera cam;
	private TileMap tileMap;

	public void init() {
		enter();
	}

	//runs on entrance
	public void enter() {
		//sets the buttons in Game
		buttons = new ArrayList<Button>();
		buttons.add(new Button("Back",  new Font("Ariel", Font.PLAIN, 25), new Font("Ariel", Font.BOLD, 35),
				Color.BLACK, Color.GREEN, 50));
		buttons.add(new Button("Debug",  new Font("Ariel", Font.PLAIN, 25), new Font("Ariel", Font.BOLD, 35),
				Color.BLACK, Color.GREEN, 90));
		buttons.add(new Button("Exit",  new Font("Ariel", Font.PLAIN, 25), new Font("Ariel", Font.BOLD, 35),
				Color.BLACK, Color.GREEN, 130));
		buttons.add(new Button("Refresh",  new Font("Ariel", Font.PLAIN, 25), new Font("Ariel", Font.BOLD, 35),
				Color.BLACK, Color.GREEN, 170));
		
		//adds entites (remove soon)
		enemies.add(new Enemy(Game.WIDTH / 3, Game.HEIGHT / 2, new Sprite("test", 64, 64)));
		tileMap = new TileMap();
		tileMap.load("level1");
		cam = new Camera(0, 0, tileMap.entity(0));
		//background (also should be implemented in tilemapping)
		bg.setX(0);
		bg.setY(0);	
		bg.setDx(-0.6);
		cam.setX(0);
		cam.setY(0);
	}//enter

	public void tick(StateManager stateManager) {
		//checks if player is dead
		if(((Player)tileMap.entity(0)).isDead()){
			JOptionPane.showMessageDialog(null, "You have fallen to your death. You will now be sent back to the menu.");
			Game.INSTANCE.setFocusable(true);
			tileMap.load("level1");
			stateManager.setState("menu");
		}
		if(tileMap.entity(0).isCollided(enemies)){
			JOptionPane.showMessageDialog(null, "An enemy has killed you. You will now be sent back to the menu"); Game.INSTANCE.setFocusable(true);
			tileMap.load("level1");
			stateManager.setState("menu");
		}
		//mouse check
		boolean isClicked = false;
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
		if(isClicked)
			select(stateManager);
		
		cam.tick();
	}//tick

	//determines which button is currently selected
	public void select(StateManager stateManager){
		switch(currentSelection){
		case 0: stateManager.setState("Menu"); exit();
		break;
		case 1:
			if(debugging)
				debugging = false;
			else
				debugging = true;
		break;
		case 2: Game.INSTANCE.stop();
		break;
		case 3: 
				cam.updateCamera();
		}
	}

	//renders graphics
	public void render(Graphics2D g) {
		//background rendering
		bg.draw(g);
		bg.update();
		//Text and other
		DrawString.drawInfo(g);
		DrawString.drawStringCenterV(g, "Alpha", Color.CYAN, new Font("Arial", Font.CENTER_BASELINE, 50), 100);
		DrawString.drawString(g, "Player[X:" + tileMap.entityList().get(0).getX() + " Y:" + tileMap.entityList().get(0).getY() + "]", new Font("Arial",Font.PLAIN, 13),  Color.BLUE,Game.WIDTH - 128, 11 * 2);

		//entites and tiles
		/////////////////////////////////////////////////////////////////
		g.translate(cam.getX(), cam.getY());
		tileMap.render(g);
		//enemies.get(0).render(g);
		g.translate(-cam.getX(), -cam.getY());
		/////////////////////////////////////////////////////////////////
		//button selection
		for(int i = 0; i < buttons.size(); i++){
			if(i == currentSelection)	
				buttons.get(i).setSelected(true);
			else
				buttons.get(i).setSelected(false);
			buttons.get(i).render(g, Game.WIDTH - 110);
		}		
			
	}//render

	public void exit() {
		buttons.clear();
		this.enemies.clear();
	}//exit

	public String getName() {
		return "Game";
	}//getName


}
