package input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter{

	private static final int NUM_BUTTONS = 5;
	private static final boolean[] buttons = new boolean[NUM_BUTTONS];
	private static final boolean[] lastButtons = new boolean[NUM_BUTTONS];
	private static int x = -1, y = -1;
	private static int lastx = x, lasty = y;
	private static boolean moving;
	
	
	public void mousePressed(MouseEvent e) {
		buttons[e.getButton()] = true;
	}
	public void mouseReleased(MouseEvent e) {
        buttons[e.getButton()] = false;
	}
	public void mouseDragged(MouseEvent e) {
		
	}
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		//add to set moving to false after 5 seconds
		moving = true;
	}
	
	public static boolean isDown(int button){
		return buttons[button];
	}
	public static boolean wasPressed(int button){
		return isDown(button) && !lastButtons[button];
	}
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
