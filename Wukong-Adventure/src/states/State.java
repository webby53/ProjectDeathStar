package states;

import java.awt.Graphics;

import Managers.StateManager;

public interface State {

	//a type of constructor for states
	//it 'initializes' all the state data
	public void init();
	
	//called when we are entering a state (not needed)
	public void enter();
	
	public void tick(StateManager stateManager);
	
	public void render(Graphics g);
	//a method that is called when a state is being closed
	public void exit();
	//name of the states
	public String getName();
}
