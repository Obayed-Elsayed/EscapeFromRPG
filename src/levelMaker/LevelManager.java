package levelMaker;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import entity.Entity;
import main.GraphicHandler;

// TODO probably change this to abstract class, once I figure out what I am going to do with this
public class LevelManager {

    protected int width;
    protected int height;
    protected int[] tiles;

    public LevelManager(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new int[width * height];
        generateLevel();
    }

    public LevelManager(String path) {
        loadLevel(path);
    }

    protected void generateLevel() {

    }

    private void loadLevel(String path) {

    }

    private void update() {

    }

    private void time() {

    }

    public void render(int xscroll, int yscroll, GraphicHandler graphics) {

    }

}
