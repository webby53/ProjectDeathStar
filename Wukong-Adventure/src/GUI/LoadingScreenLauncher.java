package GUI;

import Rendering.Texture;

public class LoadingScreenLauncher {

	LoadingScreen load;

	public LoadingScreenLauncher(){
		load = new LoadingScreen(new Texture("sky"));
		load.setLocationRelativeTo(null);
		load.setMaxProgress(100);
		load.setVisible(true);

		for(int i =0; i <= 100; i++){
			for(int j = 0; j <= 9990000;j++){
				for(int k = 0; k <= 9000;k++){
					int l = j * i * k;//random operation to take time
				}
			} 
			load.setProgress(i);
		}
		load.setVisible(false);
	}
	
}
