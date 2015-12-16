package Main;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import Rendering.Textures;
import input.KeyInput;
import input.MouseInput;
import Rendering.Sprite;
import Rendering.SpriteSheet;


public class Game extends Canvas implements Runnable{

	public static final String TITLE = "Wukongs Advernture Ver 1.52";
	public static final int WIDTH = 896;
	public static final int HEIGHT = WIDTH / 4 * 3;
	public static Game INSTANCE;
	//boolean to test if game is running
	private boolean running;
	private Textures texture, guy;
	private SpriteSheet sheet;
	private Sprite sprite;
	private Sprite sprite2;
	private double sX = 200, sY = 200;
	private Menu menu;
	
	public Game(){
		texture = new Textures("test");
		guy = new Textures("testcharacter");
		sheet = new SpriteSheet(new Textures("SpriteCell(4x4)"), 64);
		sprite = new Sprite(sheet, 1, 1);
		sprite2 = new Sprite(sheet, 3, 1);
		addKeyListener(new KeyInput());
		MouseInput mouse = new MouseInput();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		menu = new Menu();
		INSTANCE = this;

	}
	
	//makes a new thread
	public void start(){
		if(running)
			return;
		else{
			running = true;
			new Thread(this, "Main-Thread").start();
		}
	}
	
	//the tick method
	private void tick(){
		menu.tick();
		
	}
	
	//this takes all graphics
	//and displays it
	private void render(){
		//This is a buffer. It loads multiple frames
		//into memory so there is a certain amount 
		//loaded before hand instead of only
		//loading one frame at a time.
		BufferStrategy bs = getBufferStrategy();
		
		if(bs == null){
			//3 buffers are better since 2 flicker
			//and 4 may be slow for slower computers
			createBufferStrategy(3);
			return;
		}
		
		//this makes our buffer supply the graphics
		Graphics g = bs.getDrawGraphics();
		
		//////\\\\\\
		menu.render(g);
        for(int i= 0; i < 14; i++){
        	sprite.render(g, 64 * i, 500);
        	sprite2.render(g, 64 * i, 564);
        	sprite2.render(g, 64 * i, 628);
        }
		g.dispose();//disposes last graphics
        
		//////\\\\\\
		
		bs.show();
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
		requestFocus();
		//this is the target frames per second
		double target = 60.0;
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
			long now =System.nanoTime();
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
			
			//sleeps for 1 millisecond (delay)
			try{
				Thread.sleep(1);	
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
			if(canRender){
				render();
				fps++;
			}
			
			//calculates how much ticks and frames have gone by
			//and outputs them and resets their values
			if(System.currentTimeMillis() - 1000 > timer){
				timer += 1000;
				System.out.printf("FPS: %d | TPS: %d\n", fps, tps);
				fps = 0;
				tps = 0;
			}
		}

		System.out.println("The Game has Started!");
	}//run
	
	public static void main(String[] args){
		//creates our main frame and instance of Game
		final Game game = new Game();
		JFrame frame =new JFrame(TITLE);
		
		frame.add(game);
		frame.setSize(WIDTH, HEIGHT);
		//so they can't change window size
		frame.setResizable(false);
		//so we can get key inputs
		frame.setFocusable(true);
		frame.addWindowListener(new WindowAdapter(){
			
			public void windowClosing(WindowEvent e){
				System.err.println("The Game is now Closing...");
				game.stop();
			}
		});
		
		//puts game frame in center of screen
		frame.setLocationRelativeTo(null);
		//so it can be seen
		frame.setVisible(true);
		game.start();
	}
	
}
