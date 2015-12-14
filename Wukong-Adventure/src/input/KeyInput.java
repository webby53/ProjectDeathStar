package input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private static final int NUM_KEYS = 256;
	//creates an list of all possible key options that can be pressed
	private static final boolean[] keys = new boolean[NUM_KEYS];
	private static final boolean[] lastKeys = new boolean[NUM_KEYS];
	
	//when pressed this method check the event to see which key was pressed
	//Then it changes the boolean value at that space in the array that represent the key to true symbolizing that the key was pressed
	public void keyPressed(KeyEvent e){
		keys[e.getKeyCode()] = true;
	}
	
	//when the key is released this method goes back into the array and changed the key that was pressed's value to false representing the key was released
	public void keyReleased(KeyEvent e){
		keys[e.getKeyCode()] = false;
	}
	
	//checks if the key at that value is being pressed
	public static boolean isKeyDown(int key){
		return keys[key];
	}
	
	public static void update(){
		for(int i = 0; i < NUM_KEYS; i++)
			lastKeys[i] = keys[i];
	}
	
	public static boolean wasKeyPressed(int keyCode){
		return isKeyDown(keyCode) && !lastKeys[keyCode];
	}
	
	public static boolean wasReleased(int keyCode){
		return !isKeyDown(keyCode) && lastKeys[keyCode];
	}
	
}
