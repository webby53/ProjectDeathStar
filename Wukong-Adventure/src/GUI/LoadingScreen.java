package GUI;

import java.awt.*;
import javax.swing.*;
import Rendering.Texture;

//this class (should) load recources and show the loading to the user
public class LoadingScreen extends JWindow{

	private BorderLayout borderLayout;
	private JLabel imgLabel;
	private JPanel southPanel;
	private FlowLayout southFlow;
	private JProgressBar progressBar;
	private ImageIcon imageIcon;

	//constructor to intilize parts of loding screen
	//dont really understand as it is using Swing to create
	//graphics
	public LoadingScreen(Texture texture){
		this.imageIcon = new ImageIcon(texture.getImage());
		borderLayout = new BorderLayout();
		imgLabel = new JLabel();
		southPanel = new JPanel();
		southFlow = new FlowLayout();
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		try{
			init();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//this is so we can put all statments that need a tr
	private void init() throws Exception{
		imgLabel.setIcon(imageIcon);
		getContentPane().setLayout(borderLayout);
		southPanel.setLayout(southFlow);
		southPanel.setBackground(Color.BLACK);
		getContentPane().add(imgLabel, BorderLayout.CENTER);
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		southPanel.add(progressBar, null);
		pack();
	}

	//sets the max value for the progress br
	public void setMaxProgress(int max){
		progressBar.setMaximum(max);
	}
	
	//sets the current progress of the progress Bar
	//updated to show precentage
	public void setProgress(final int progress){
		
		// sets percentage to (current progress / max) * 100
		final float percentage = ((float)progress / (float)progressBar.getMaximum()) * 100;
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				progressBar.setValue(progress);
				progressBar.setString("Loading: " + percentage + "%");
			}
		});

	}
}
