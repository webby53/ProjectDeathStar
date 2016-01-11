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
			for(int j = 0; j <= 8000000;j++){
				int k = j * i;
			}
			load.setProgress(i);
		}
		load.setVisible(false);
	}
	
}
