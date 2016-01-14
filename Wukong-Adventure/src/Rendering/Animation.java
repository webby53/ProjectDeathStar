package Rendering;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class Animation {

	private ArrayList<Sprite> frames;

	public Sprite sprite;

	private volatile boolean running = false;
	private long previousTime, speed;
	private int frameAtPause, currentFrame;
	private Texture charTextures = new Texture("wukong sheet");
	private SpriteSheet charSheet = new SpriteSheet(charTextures, 64);
	private Sprite sprite1 = new Sprite(charSheet, 1, 1);
	private Sprite sprite2 = new Sprite(charSheet, 1, 2);
	private Sprite sprite3 = new Sprite(charSheet, 1, 3);


	public Animation(){
		frames = new ArrayList<Sprite>();
		frames.add(sprite1);
		frames.add(sprite2);
		frames.add(sprite3);
		speed = 100;
	}

	public void setSpeed(long speed){
		this.speed = speed;
	}
	public void update(){
		while(running){
			if(speed - previousTime >= speed){
				//update the animation

				try{
					sprite = frames.get(currentFrame);
				}catch(IndexOutOfBoundsException e){
					currentFrame = 0;
					sprite = frames.get(currentFrame);
				}
				currentFrame++;
				previousTime = speed;
			}
		}
	}

	public void play(){
		running = true;
		previousTime = 0;
		frameAtPause = 0;
		currentFrame = 0;

	}
	public void stop(){
		running = false;
		previousTime = 0;
		frameAtPause = 0;
		currentFrame = 0;

	}
	public void pause(){
		frameAtPause = currentFrame;
		running = false;

	}
	public void resume(){
		currentFrame = frameAtPause;
		running = true;
	}

	public Sprite getFrame(){
		return sprite;
		
	}
}
