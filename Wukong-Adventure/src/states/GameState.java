package states;

import java.awt.Color;
import java.awt.Graphics;

import Main.Game;
import Managers.StateManager;
import Rendering.Sprite;
import Rendering.SpriteSheet;
import Rendering.Texture;

public class GameState implements State{
	
	private Texture texture = new Texture("SpriteCell(4x4)");
	private SpriteSheet sheet = new SpriteSheet(texture, 64);
	private Sprite sprite = new Sprite(sheet, 1, 1);
	private Sprite sprite2 = new Sprite(sheet, 3, 1);
	private boolean enter = true;
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
		
		
	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick(StateManager stateManager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		if(enter){
			g.clearRect(0, 0, Game.WIDTH, Game.HEIGHT);
			enter = false;
		}
		// TODO Auto-generated method stub
		//g.setColor(Color.BLUE);
		//g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
//		for(int i= 0; i < 14; i++){
//        	sprite.render(g, 64 * i, 500);
//        	sprite2.render(g, 64 * i, 564);
//        	sprite2.render(g, 64 * i, 628);
//        }
		
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
