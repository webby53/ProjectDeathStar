package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import GUI.Button;
import Main.Game;
import Managers.StateManager;
import Rendering.DrawString;
import Rendering.Sprite;
import Rendering.SpriteSheet;
import Rendering.Texture;
import Rendering.Background;
import entities.Mob;
import entities.Player;
import entities.Tile;
import entities.Enemy;
import input.MouseInput;


public class GameState implements State{

	private static Texture texture = new Texture("SpriteCell(4x4)");
	private static SpriteSheet sheet = new SpriteSheet(texture, 64);
	private static Sprite sprite = new Sprite(sheet, 1, 1);
	private Sprite sprite2 = new Sprite(sheet, 3, 1);
	private static Texture charTextures = new Texture("wukong sheet");
	private static SpriteSheet charSheet = new SpriteSheet(charTextures, 64);
	private static Sprite charSprite = new Sprite(charSheet, 1, 1);
	public static Tile tile;
	public static boolean debugging;
	private ArrayList<Button> buttons;
	private int currentSelection;
	private ArrayList<Mob> entities = new ArrayList<Mob>();
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private Background bg = new Background("./resources/textures/Background.png", 10);

	public void init() {
		enter();
	}

	public void enter() {
		currentSelection = -1;
		buttons = new ArrayList<Button>();
		buttons.add(new Button("Back",  new Font("Ariel", Font.PLAIN, 25), new Font("Ariel", Font.BOLD, 35),
				Color.BLACK, Color.GREEN, 50));
		buttons.add(new Button("Debug",  new Font("Ariel", Font.PLAIN, 25), new Font("Ariel", Font.BOLD, 35),
				Color.BLACK, Color.RED, 90));
		buttons.add(new Button("Exit",  new Font("Ariel", Font.PLAIN, 25), new Font("Ariel", Font.BOLD, 35),
				Color.BLACK, Color.GREEN, 130));
		entities.add(new Player(Game.WIDTH / 2, Game.HEIGHT / 2, charSprite));
		enemies.add(new Enemy(Game.WIDTH / 3, Game.HEIGHT / 2, new Sprite("test", 64, 64)));

		bg.setX(0);
		bg.setY(0);	
		bg.setDx(0.3);

	}

	public void tick(StateManager stateManager) {
		
		if(entities.get(0).isDead()){
			JOptionPane.showMessageDialog(null, "You have fallen to your death. You will now be sent back to the menu."); Game.INSTANCE.setFocusable(true);
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
		
		
	}

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
		}
	}

	public void render(Graphics2D g) {
		//background rendering
		bg.draw(g);
		bg.update();
		//player coordinates
		DrawString.drawString(g, "Player[X:" + entities.get(0).getX() + " Y:" +entities.get(0).getY() + "]", new Font("Arial",Font.PLAIN, 13),  Color.BLUE,Game.WIDTH - 128, 11 * 2);

		//rendering tiles and entities
		for(int i= 0; i < 14; i++){
			tile = new Tile(64 * i, 500, sprite);
			tile.render(g);
		}
		for(int i= 1; i < 4; i++){
			tile = new Tile(64, 500 - 64 * i, sprite2);
			tile.render(g);
		}
		entities.get(0).render(g);	
		enemies.get(0).render(g);
		//button selection
		for(int i = 0; i < buttons.size(); i++){
			if(i == currentSelection)	
				buttons.get(i).setSelected(true);
			else
				buttons.get(i).setSelected(false);
			buttons.get(i).render(g, Game.WIDTH - 110);
		}
		
		//Text and other
		DrawString.drawInfo(g);
		DrawString.drawStringCenterV(g, "Alpha", Color.CYAN, new Font("Arial", Font.CENTER_BASELINE, 50), 100);	
		
		//ignore
		tile = new Tile(200, 100, sprite);
		tile.render(g);		
			
	}//render

	public void exit() {
		buttons.clear();
		this.entities.clear();
	}//exit

	public String getName() {
		return "Game";
	}//getName


}
