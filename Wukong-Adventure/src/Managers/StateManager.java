package Managers;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.Map;

import Main.Game;
import states.GameState;
import states.State;

public class StateManager {

	private Map<String, State> map;
	private State currentState;

	public StateManager(){
		map = new HashMap<String, State>();
	}

	public void addState(State state){
		map.put(state.getName().toUpperCase(), state);
		state.init();
		if(currentState == null){
			state.enter();
			currentState = state;
		}
	}
	
	public void setState(String name){
		State state = map.get(name.toUpperCase());
		if(state==null){
			System.err.println("State <" + name + "> doesn't exist!");
			return;
		}
		currentState.exit();
		state.enter();
		currentState = state;
	}
	
	public void render(Graphics g){
		currentState.render(g);
	}
	public void tick(){
		currentState.tick(this);
	}
}
