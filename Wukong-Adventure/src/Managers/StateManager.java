package Managers;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import states.State;

public class StateManager {

	//map that stores a string value (a name) with an instance 
	private Map<String, State> map;
	private State currentState;

	/**Constructor
	 * 
	 */
	public StateManager(){
		map = new HashMap<String, State>();
	}

	/**Add a new state to the hashMap containing all of the states
	 * 
	 * @param state
	 */
	public void addState(State state){
		map.put(state.getName().toUpperCase(), state);
		state.init();
		if(currentState == null){
			state.enter();
			currentState = state;
		}
	}
	
	/**
	 * 
	 * @param name
	 */
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
	
	/**Renders the current state
	 * 
	 * @param g
	 */
	public void render(Graphics2D g){
		currentState.render(g);
	}
	
	/**Ticks the game, updates the screen and any methods or events in tick
	 * 
	 */
	public void tick(){
		currentState.tick(this);
	}
}
