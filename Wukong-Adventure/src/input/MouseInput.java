package input;

/**@author Joshua Prpic, Kishon Webb, Simon Yacoub
 * @version 7.5
 * @since 2016-01-19
 */ 

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter{

	private static final int NUM_BUTTONS = 5;
	private static final boolean[] buttons = new boolean[NUM_BUTTONS];
	private static final boolean[] lastButtons = new boolean[NUM_BUTTONS];
	private static int x = -1, y = -1;
	private static int lastx = x, lasty = y;
	private static boolean moving;
	
	/**Registers if a mouse event occurred and record the event
	 * @param e
	 */
	public void mousePressed(MouseEvent e) {
		buttons[e.getButton()] = true;
	}
	/**Registers when the mouse pressed event ends and recored the end of the event
	 * @param e
	 */
	public void mouseReleased(MouseEvent e) {
        buttons[e.getButton()] = false;
	}
	/**Empty
	 * 
	 */
	public void mouseDragged(MouseEvent e) {
		
	}
	/**Registers the location of the mouse when it moves
	 * @param e
	 */
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		//add to set moving to false after 5 seconds
		moving = true;
	}
	
	/**Checks if the button in question is being pressed
	 * 
	 * @param button
	 * @return
	 */
	public static boolean isDown(int button){
		return buttons[button];
	}
	/**Checks if the button in question was pressed at some point
	 * 
	 * @param button
	 * @return Boolean that represents if the button in question was released at some point
	 */
	public static boolean wasPressed(int button){
		return isDown(button) && !lastButtons[button];
	}
	/**Checks if the button in question was released at some point
	 * 
	 * @param button
	 * @return Boolean that represents if the button was released
	 */
	public static boolean wasReleased(int button){
		return !isDown(button) && lastButtons[button];
	}
	
	public static int getX() {
		return x;
	}
	public static int getY() {
		return y;
	}
	public static boolean isMoving(){
		return moving;
	}
	public static int getLastx() {
		return lastx;
	}
	public static int getLasty() {
		return lasty;
	}
	
	public static void update(){
		for(int i = 0; i < NUM_BUTTONS; i++)
			lastButtons[i] = buttons[i];
		
		if(y == lasty && x == lastx)
			moving = false;
		lasty = y;
		lastx = x;
	}
}
