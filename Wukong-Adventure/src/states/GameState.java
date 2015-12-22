package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Main.Game;
import Managers.StateManager;
import Rendering.DrawString;
import Rendering.Sprite;
import Rendering.SpriteSheet;
import Rendering.Texture;
import entities.Entity;
import entities.Mob;
import entities.Tile;
import input.Button;
import input.MouseInput;

public class GameState implements State{

	private static Texture texture = new Texture("SpriteCell(4x4)");
	private static SpriteSheet sheet = new SpriteSheet(texture, 64);
	private static Sprite sprite = new Sprite(sheet, 1, 1);
	//private Sprite sprite2 = new Sprite(sheet, 3, 1);
	public static Tile tile;
	private Tile tile2;
	private ArrayList<Button> buttons;
	private int currentSelection;
	private ArrayList<Mob> entities = new ArrayList<Mob>();
	private StateManager stateManager;
	private boolean enter = true;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		enter();

	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub
		currentSelection = -1;
		buttons = new ArrayList<Button>();
		buttons.add(new Button("Back",  new Font("Ariel", Font.PLAIN, 25), new Font("Ariel", Font.BOLD, 35),
				Color.BLACK, Color.GREEN, 50));
		buttons.add(new Button("Info",  new Font("Ariel", Font.PLAIN, 25), new Font("Ariel", Font.BOLD, 35),
				Color.BLACK, Color.RED, 90));
		buttons.add(new Button("Exit",  new Font("Ariel", Font.PLAIN, 25), new Font("Ariel", Font.BOLD, 35),
				Color.BLACK, Color.GREEN, 130));
		entities.add(new Mob(Game.WIDTH / 2, Game.HEIGHT / 2, new Sprite("test", 64, 64)));


	}

	@Override
	public void tick(StateManager stateManager) {
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
		case 1: JOptionPane.showMessageDialog(null, Game.Help); Game.INSTANCE.setFocusable(true); 
		break;
		case 2: Game.INSTANCE.stop();
		break;
		}
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		DrawString.drawString(g, "Player[X:" + entities.get(0).getX() + " Y:" +entities.get(0).getY() + "]", new Font("Arial",Font.PLAIN, 13),  Color.BLUE,Game.WIDTH - 128, 11 * 2);

		for(int i = 0; i < buttons.size(); i++){
			if(i == currentSelection)	
				buttons.get(i).setSelected(true);
			else
				buttons.get(i).setSelected(false);
			buttons.get(i).render(g, Game.WIDTH - 110);
		}
		DrawString.drawInfo(g);
		DrawString.drawStringCenterV(g, "Collision Testing", Color.CYAN, new Font("Arial", Font.CENTER_BASELINE, 50), 100);		
		for(int i= 0; i < 14; i++){
			tile = new Tile(64 * i, 500, sprite);
        	tile.render(g);
		}
		entities.get(0).render(g);

		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		buttons.clear();
		this.entities.clear();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Game";
	}


}
