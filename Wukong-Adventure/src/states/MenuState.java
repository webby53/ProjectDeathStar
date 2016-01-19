package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
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
import input.MouseInput;
import input.KeyInput;

public class MenuState implements State{

	private ArrayList<Button> buttons;
	private int currentSelection;
	private Texture texture = new Texture("SpriteCell(4x4)");
	private SpriteSheet sheet = new SpriteSheet(texture, 64);
	private Sprite sprite = new Sprite(sheet, 1, 1);
	private Sprite sprite2 = new Sprite(sheet, 3, 1);

	@Override
	public void init() {
		enter();
	}

	@Override
	public void enter() {
		currentSelection = -1;
		buttons = new ArrayList<Button>();
		buttons.add(new Button("PLAY", new Font("Ariel", Font.CENTER_BASELINE, 35), new Font("Ariel", Font.CENTER_BASELINE, 45),
				Color.BLACK, Color.BLUE, 100 + 50));
		buttons.add(new Button("HELP", new Font("Ariel", Font.ITALIC, 35), new Font("Ariel", Font.ITALIC, 45),
				Color.BLACK, Color.RED, 200 + 50));
		buttons.add(new Button("OPTIONS", new Font("Ariel", Font.ITALIC, 35), new Font("Ariel", Font.ITALIC, 45),
				Color.BLACK, Color.RED, 300 + 50));
		buttons.add(new Button("EXIT", new Font("Ariel", Font.CENTER_BASELINE, 35), new Font("Ariel", Font.CENTER_BASELINE, 45),
				Color.BLACK, Color.BLUE, 400 + 50));
	}

	public void tick(StateManager stateManager){
		//key check
		if(KeyInput.wasKeyPressed(KeyEvent.VK_UP) || KeyInput.wasKeyPressed(KeyEvent.VK_W))
			if(currentSelection < 0)
				currentSelection = buttons.size() - 1;
			else
				currentSelection -= 1;
		if(KeyInput.wasKeyPressed(KeyEvent.VK_DOWN) || KeyInput.wasKeyPressed(KeyEvent.VK_S))
			if(currentSelection > buttons.size() - 1)
				currentSelection = 0;
			else
				currentSelection += 1;
		
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
				
		if(isClicked || KeyInput.wasKeyPressed(KeyEvent.VK_SPACE))
			select(stateManager);
	}
	
	public void select(StateManager stateManager){
		switch(currentSelection){
		case 0:	stateManager.setState("Game");
				exit();
		break;
		case 1:	JOptionPane.showMessageDialog(null, "                       Controls: 	 \n Moving left:       'a' or left arrow key "
												+ 				"\n Moving right:    'd' or right arrow key "
												+ 				"\n Jumping:           'w' or up arrow key"
												+ 				"\n Gliding:              's' or down arrow key"
												+ 				"\n Attacking:         Space bar "
												+ 				"\n Debugging:        Backspace"
												+ 				"\n "); Game.INSTANCE.setFocusable(true);
		break;
		case 2: JOptionPane.showMessageDialog(null, "Please Buy the full version!"); Game.INSTANCE.setFocusable(true);
		break;
		case 3: Game.INSTANCE.stop();
		break;
		}
	}

	public void render(Graphics2D g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		DrawString.drawInfo(g);
		DrawString.drawStringCenterV(g, Game.TITLE, Color.GREEN, new Font("Arial",Font.BOLD, 52), 60);

		for(int i = 0; i < buttons.size(); i++){
			if(i == currentSelection)	
				buttons.get(i).setSelected(true);
			else
				buttons.get(i).setSelected(false);
			buttons.get(i).render(g);
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
		buttons.clear();
	}

	@Override
	public String getName() {
		return "Menu";
	}

	//public 

}
