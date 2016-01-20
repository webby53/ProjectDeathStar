package Rendering;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class Animation implements Runnable {

	private ArrayList<Sprite> frames;
	private Thread run;
	private Sprite sprite;
	private boolean running;
	private int current; 
	private long speed;
		

	/**Constructor
	 * @param speed
	 * @param frames
	 */
	public Animation(long speed, ArrayList<Sprite> frames){
		this.frames = frames;
		this.speed = speed * 10;
	}//constructor
	
	/**changes the current frame when current thread time is equal to rendering thread
	 * 
	 */
	public void run(){
		Thread thisThread = Thread.currentThread();
		while(running && run == thisThread){
			current++;
			if(current > frames.size() - 1)
				current = 0;
			if(frames.get(current)!= null)
				sprite = frames.get(current);
			try{
				Thread.sleep(speed);
			}catch(InterruptedException e){}
		}
	}//run
	
	/**Starts the running thread
	 * 
	 */
	public void start(){
		if(!running){
			running = true;
			run = new Thread(this);
			run.start();
		}
	}//start
	
	/**Stops running thread
	 * 
	 */
	public void stop(){
		if(running){
			run = null;
			running = false;
		}
	}//stop

	/**Sets current frame
	 * @param x
	 */
	public void setFrame(int x){
		current = x;
	}
	
	/**Sets frame list (Array list of frames)
	 * @param frames
	 */
	public void setAnimation(ArrayList<Sprite> frames){
		this.frames = frames;
	}
	
	/**Sets speed
	 * @param x
	 */
	public void setSpeed(int x){
		speed = x;
	}
	
	/**Gets current frame
	 * @return sprite
	 */
	public Sprite getFrame(){
		return sprite;
	}//getFrame
	
	/**Adds a frame
	 * @param sprite
	 */
	public void addFrame(Sprite sprite){
		frames.add(sprite);
	}
	/**Returns int value of current frame 
	 * @return current
	 */
	public int getCurrent(){
		return current;
	}
}//Animation
