package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import Main.Game;
import Managers.StateManager;
import Rendering.DrawString;
import Rendering.Sprite;
import Rendering.SpriteSheet;
import Rendering.Texture;
import input.MouseInput;
import input.Button;
import input.KeyInput;

public class MenuState implements State{

	private Button[] buttons;
	private int currentSelection;
	private Texture texture = new Texture("SpriteCell(4x4)");
	private SpriteSheet sheet = new SpriteSheet(texture, 64);
	private Sprite sprite = new Sprite(sheet, 1, 1);
	private Sprite sprite2 = new Sprite(sheet, 3, 1);

	@Override
	public void init() {
		buttons = new Button[3];

		buttons[0]= new Button("PLAY", new Font("Ariel", Font.PLAIN, 35), new Font("Ariel", Font.BOLD, 45),
				Color.BLACK, Color.BLUE, 150 + 50);
		buttons[1]= new Button("HELP", new Font("Ariel", Font.PLAIN, 35), new Font("Ariel", Font.BOLD, 45),
				Color.BLACK, Color.BLUE, 250 + 50);
		buttons[2]= new Button("EXIT", new Font("Ariel", Font.PLAIN, 35), new Font("Ariel", Font.BOLD, 45),
				Color.BLACK, Color.BLUE, 350 + 50);
	}

	@Override
	public void enter() {
	}

	public void tick(StateManager stateManager){
		//key check
		if(KeyInput.wasKeyPressed(KeyEvent.VK_UP) || KeyInput.wasKeyPressed(KeyEvent.VK_W))
			if(currentSelection < 0)
				currentSelection = buttons.length - 1;
			else
				currentSelection -= 1;
		if(KeyInput.wasKeyPressed(KeyEvent.VK_DOWN) || KeyInput.wasKeyPressed(KeyEvent.VK_S))
			if(currentSelection > buttons.length - 1)
				currentSelection = 0;
			else
				currentSelection += 1;
		
		//mouse check
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
				
		if(isClicked || KeyInput.wasKeyPressed(KeyEvent.VK_SPACE))
			select(stateManager);
	}
	
	public void select(StateManager stateManager){
		switch(currentSelection){
		case 0: System.out.println("Play is pressed");
				stateManager.setState("Game");
				exit();
		break;
		case 1:System.out.println("Help is pressed");
		break;
		case 2: Game.INSTANCE.stop();
		break;
		}
	}

	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		DrawString.drawString(g, "X:" + MouseInput.getX() + " Y:" + MouseInput.getY(), new Font("Arial",Font.PLAIN, 13),  Color.BLUE,Game.WIDTH - 105, 11);
		DrawString.drawStringCenterV(g, Game.TITLE, Color.GREEN, new Font("Arial",Font.BOLD, 52), 60);

		for(int i = 0; i < buttons.length; i++){
			if(i == currentSelection)	
				buttons[i].setSelected(true);
			else
				buttons[i].setSelected(false);
			buttons[i].render(g);
		}
		for(int i= 0; i < 14; i++){
        	sprite.render(g, 64 * i, 500);
        	sprite2.render(g, 64 * i, 564);
        	sprite2.render(g, 64 * i, 628);
        }
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
	}

	@Override
	public String getName() {
		return "Menu";
	}

	//public 

}
