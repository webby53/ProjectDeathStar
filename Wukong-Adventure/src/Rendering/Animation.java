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
	
	private Texture charTextures = new Texture("wukong sheet");
	private SpriteSheet charSheet = new SpriteSheet(charTextures, 64);
	
	private Sprite sprite1, sprite2, sprite3, sprite4, sprite5;

	/**Constructor
	 * 
	 * @param speed
	 * @param frames
	 */
	public Animation(long speed, ArrayList<Sprite> frames){
		this.frames = frames;
		this.speed = speed * 10;
	}//constructor
	
	/**
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

	public void start(){
		if(!running){
			running = true;
			run = new Thread(this);
			run.start();
		}
	}//start
	
	public void stop(){
		if(running){
			run = null;
			running = false;
		}
	}//stop

	public void setFrame(int x){
		current = x;
	}
	
	public void setAnimation(ArrayList<Sprite> frames){
		this.frames = frames;
	}
	public void setSpeed(int x){
		speed = x;
	}
	
	public Sprite getFrame(){
		return sprite;
	}//getFrame
	
	public void addFrame(Sprite temp){
		frames.add(temp);
	}
}//Animation
