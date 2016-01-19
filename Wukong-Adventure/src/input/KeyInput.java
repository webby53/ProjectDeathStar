package input;

/**@author Joshua Prpic, Kishon Webb, Simon Yacoub
 * @version 7.5
 * @since 2016-01-19
 */ 

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private static final int NUM_KEYS = 256;
	//creates an list of all possible key options that can be pressed
	private static final boolean[] KEYS = new boolean[NUM_KEYS];
	private static final boolean[] LASTKEYS = new boolean[NUM_KEYS];
	
	/**Checks the event to see which key was pressed and record it on an array
	 * 
	 */
	//when pressed this method check the event to see which key was pressed
	//Then it changes the boolean value at that space in the array that represent the key to true symbolizing that the key was pressed
	public void keyPressed(KeyEvent e){
		KEYS[e.getKeyCode()] = true;
	}
	
	/**When the key is released it records the release of the key in the array of keys
	 * 
	 */
	//when the key is released this method goes back into the array and changed the key that was pressed's value to false representing the key was released
	public void keyReleased(KeyEvent e){
		KEYS[e.getKeyCode()] = false;
	}
	
	/**Checks if a key at a specified value is being pressed
	 * 
	 * @param key
	 * @return Boolean that represents if a specified key has been pressed
	 */
	public static boolean isKeyDown(int key){
		return KEYS[key];
	}
	
	/**Sets the values the array LASTKEYS to the value of KEYS
	 * 
	 */
	public static void update(){
		for(int i = 0; i < NUM_KEYS; i++)
			LASTKEYS[i] = KEYS[i];
	}
	
	/**Checks if the key in question has been pressed
	 * 
	 * @param keyCode
	 * @return Boolean representing if the key in question was pressed previously
	 */
	public static boolean wasKeyPressed(int keyCode){
		return isKeyDown(keyCode) && !LASTKEYS[keyCode];
	}
	
	/**Checks the key in question has been released
	 * 
	 * @param keyCode
	 * @return Boolean representing if the key in question was released
	 */
	//Same is pressed but with released
	public static boolean wasReleased(int keyCode){
		return !isKeyDown(keyCode) && LASTKEYS[keyCode];
	}
	
}
