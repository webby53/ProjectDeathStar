package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Rendering.DrawString;
import input.MouseInput;
import input.Button;
import input.KeyInput;

public class Menu {

	private final Button[] buttons;
	private int currentSelection;

	public Menu(){
		buttons = new Button[3];

		buttons[0]= new Button("PLAY", new Font("Ariel", Font.PLAIN, 35), new Font("Ariel", Font.BOLD, 45),
				Color.BLACK, Color.BLUE, 150 + 50);
		buttons[1]= new Button("HELP", new Font("Ariel", Font.PLAIN, 35), new Font("Ariel", Font.BOLD, 45),
				Color.BLACK, Color.BLUE, 250 + 50);
		buttons[2]= new Button("EXIT", new Font("Ariel", Font.PLAIN, 35), new Font("Ariel", Font.BOLD, 45),
				Color.BLACK, Color.BLUE, 350 + 50);
	}

	public void tick(){
		
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
			select();
	}
	
	public void select(){
		switch(currentSelection){
		case 0: System.out.println("Play is pressed");
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
	}

	//public 

}
