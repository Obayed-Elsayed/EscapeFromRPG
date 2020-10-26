package levelMaker;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import entity.Entity;

public class LevelManager {

	public static int WIDTH;
	public static int HEIGHT;
	public ArrayList<Platform> devLevelplatformCollection = new ArrayList<Platform>();

	public static void setScreenSize(int x, int y) {
		WIDTH = x;
		HEIGHT = y;
	}

	public void setupDevloperLevel(Graphics painter) {

		devLevelplatformCollection.add(new Platform(0,HEIGHT/2,WIDTH,50));
		devLevelplatformCollection.add(new Platform(100,HEIGHT/2 -200,200,50));
		
	}
	// consider 
	public void draw( Graphics2D drawTool) {
		
		   
		for(Platform platform: devLevelplatformCollection) {
			drawTool.drawRect(platform.getxPosition(), platform.getyPosition(), platform.getWidth(), platform.getHeight());
		}
		
	}

	public void setupLevelOne() {

	}

}
