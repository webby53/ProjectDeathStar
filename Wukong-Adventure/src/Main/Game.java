package Main;

import java.awt.image.BufferStrategy;
import java.awt.*;
import Managers.StateManager;
import Rendering.DrawString;
import input.KeyInput;
import input.MouseInput;
import states.GameState;
import states.MenuState;


@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable{

	public static final String TITLE = "Wukong's Advernture Ver:7.4-Alpha";
	public static final int WIDTH = 896;
	public static final int HEIGHT = WIDTH / 4 * 3;
	public static Game INSTANCE;		
	//this is the target frames per second
	public int target;

	public static String Help = "";
	private boolean running;
	public static int FPS, TPS;

	private BufferStrategy bs;
	private StateManager stateManager;

	public Game(){
		addKeyListener(new KeyInput());
		MouseInput mouse = new MouseInput();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		stateManager = new StateManager();
		stateManager.addState(new MenuState());
		stateManager.addState(new GameState());
		INSTANCE = this;
		DrawString.addInfo("X:" + MouseInput.getX() + " Y:" + MouseInput.getY());
		DrawString.addInfo("FPS:" + Game.FPS + " TPS:" + Game.TPS);
	}

	//makes a new thread
	public void start(){
		Help += "Game Name: " + TITLE + "\nCanvas Width: " + WIDTH + "\nCanvas Height: " + HEIGHT;
		if(running)
			return;
		else{
			running = true;
			new Thread(this, "Main-Thread").start();
		}
	}

	//the tick method
	private void tick(){
		stateManager.tick();
	}

	//this takes all graphics
	//and displays it
	private void render(){
		//This is a buffer. It loads multiple frames
		//into memory so there is a certain amount 
		//loaded before hand instead of only
		//loading one frame at a time.

		bs = getBufferStrategy();

		if(bs == null){
			//3 buffers are better since 2 flicker
			//and 4 may be slow for slower computers
			createBufferStrategy(3);
			return;
		}
		//this makes our buffer supply the graphics
		Graphics g = null;
		Graphics2D g2D = null;
		g = bs.getDrawGraphics();
		g2D = (Graphics2D) g;
		
		//clears game before rendering(THANK GOD)
		g.clearRect(0, 0, Game.WIDTH, Game.HEIGHT);
		stateManager.render(g2D);

		//rending optimization
		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2D.setRenderingHints(rh);
		
		//sets how to render oberjects over other objects
		AlphaComposite ac =  AlphaComposite.getInstance(AlphaComposite.SRC_OVER);
		g2D.setComposite(ac);
		
		bs.show();
		g.dispose();

	}

	//stops the game
	public void stop(){
		if(! running)
			return;
		else{	
			running = false;
			System.exit(0);
		}
	}//stop

	//used to start a thread of our game
	public void run(){
		if(target == 0)
			target = 40;
		requestFocus();
		//this helps with second calculation
		double nanoSecPerTick = 1000000000.0 /target;
		//this is the time we haven't processed
		//so we can tell when to tick
		double unprocessed = 0;
		//
		long lastTime = System.nanoTime();
		//used for fps and tps calculations
		long timer = System.currentTimeMillis();

		//these are the frames and ticks per second
		int fps = 0;
		int tps = 0;
		//Limits frames to when we can tick
		boolean canRender= false;

		while(running){
			//
			nanoSecPerTick = 1000000000.0 /target;
			long now = System.nanoTime();
			//this calculates the time difference
			//from the last loop to current
			unprocessed += (now - lastTime) / nanoSecPerTick;

			lastTime = now;

			//so some time after one second
			//it ticks and increases tps
			//and then resets unprocessed and canRender
			if(unprocessed >= 1){
				tick();
				KeyInput.update();
				MouseInput.update();
				unprocessed--;
				tps++;
				canRender = true;
			}else//this is so the frames are limited by the ticks
				//since we don't render until we tick
				canRender= false;

			if(canRender){
				render();
				fps++;
			}
			try{
				Thread.sleep(4);
			}catch(InterruptedException e){}

			//calculates how much ticks and frames have gone by
			//and outputs them and resets their values
			if(System.currentTimeMillis() - 1000 > timer){
				timer += 1000;
				FPS = fps;
				TPS = tps;
				fps = 0;
				tps = 0;
			}
		}
	}//run

}
