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
import entities.Mob;
import entities.Tile;
import input.Button;
import input.MouseInput;

public class GameState implements State{

	private Texture texture = new Texture("SpriteCell(4x4)");
	private SpriteSheet sheet = new SpriteSheet(texture, 64);
	private Sprite sprite = new Sprite(sheet, 1, 1);
	//private Sprite sprite2 = new Sprite(sheet, 3, 1);
	private Tile tile = new Tile(100, 100, sprite);
	private ArrayList<Button> buttons;
	private int currentSelection;
	private ArrayList<Mob> entites;
	private StateManager stateManager;
	private boolean enter = true;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		enter();
		entites = new ArrayList<Mob>();
		entites.add(new Mob(Game.WIDTH / 2, Game.HEIGHT / 2, new Sprite("test")));

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
		entites.get(0).tick();
	}

	public void select(StateManager stateManager){
		switch(currentSelection){
		case 0: stateManager.setState("Menu"); exit();
		break;
		case 1: JOptionPane.showMessageDialog(null, Game.INFO); Game.INSTANCE.setFocusable(true); 
		try{
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
		
		for(int i = 0; i < buttons.size(); i++){
			if(i == currentSelection)	
				buttons.get(i).setSelected(true);
			else
				buttons.get(i).setSelected(false);
			buttons.get(i).render(g, Game.WIDTH - 110);
		}
		DrawString.drawInfo(g);
		DrawString.drawStringCenterV(g, "Collision Testing", Color.CYAN, new Font("Arial", Font.CENTER_BASELINE, 50), 100);		
		tile.render(g);
		entites.get(0).render(g);

		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		buttons.clear();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Game";
	}


}
