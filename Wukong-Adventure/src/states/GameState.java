package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import Main.Game;
import Managers.StateManager;
import Rendering.Sprite;
import Rendering.SpriteSheet;
import Rendering.Texture;
import entities.Tile;
import input.Button;
import input.MouseInput;

public class GameState implements State{

	private Texture texture = new Texture("SpriteCell(4x4)");
	private SpriteSheet sheet = new SpriteSheet(texture, 64);
	private Sprite sprite = new Sprite(sheet, 1, 1);
	private Sprite sprite2 = new Sprite(sheet, 3, 1);
	private Tile tile = new Tile(100, 100, sprite);
	private Button buttons[];
	private int currentSelection;
	private StateManager stateManager;
	private boolean enter = true;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		currentSelection = 0;
		buttons = new Button[2];
		buttons[0] = new Button("Back",  new Font("Ariel", Font.PLAIN, 35), new Font("Ariel", Font.BOLD, 45),
				Color.BLACK, Color.GREEN, 200);
		buttons[1] = new Button("Exit",  new Font("Ariel", Font.PLAIN, 35), new Font("Ariel", Font.BOLD, 45),
				Color.BLACK, Color.GREEN, 300);



	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tick(StateManager stateManager) {
		// TODO Auto-generated method stub
		boolean isClicked = false;
		for(int i = 0; i < buttons.length; i++){
			if(buttons[i].intersects(new Rectangle(MouseInput.getX(), MouseInput.getY(), 1, 1))){
				currentSelection = i;
				if(MouseInput.wasPressed(MouseEvent.BUTTON1))
					isClicked = true;
				else
					isClicked = false;
			}
		}

		if(isClicked)
			select(stateManager);
	}
	
	public void select(StateManager stateManager){
		switch(currentSelection){
		case 0: System.out.println("Play is pressed");
				stateManager.setState("Menu");
				exit();
		break;
		case 1: Game.INSTANCE.stop();
		break;
		}
	}

	@Override
	public void render(Graphics2D g) {
		if(enter){
			g.clearRect(0, 0, Game.WIDTH, Game.HEIGHT);
			enter = false;
		}
		// TODO Auto-generated method stub
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
//		for(int i= 0; i < 14; i++){
//			sprite.render(g, 64 * i, 500);
//			sprite2.render(g, 64 * i, 564);
//			sprite2.render(g, 64 * i, 628);
//		}

		for(int i = 0; i < buttons.length; i++){
			if(i == currentSelection)	
				buttons[i].setSelected(true);
			else
				buttons[i].setSelected(false);
			buttons[i].render(g);
		}
		tile.render(g);
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Game";
	}


}
