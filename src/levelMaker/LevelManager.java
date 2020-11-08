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

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.dummyTile;
        }
        if (tiles[x + y * width] == 0) {
            return Tile.voidTileA;
        }else if(tiles[x + y * width] == 1){
            return Tile.voidTileB;
        }
        return Tile.solidTile;
    }

    public void render(int xScroll, int yScroll, GraphicHandler graphics) {
        // offSet is the location of the player
        graphics.setOffset(xScroll, yScroll);
        // defining corner pins for rendering a level
        // every 32 or 2^5 is one tile -> tile coords
        int x0 = (xScroll >> 5);
        int x1 = (xScroll + graphics.width +32) >> 5;
        int y0 = (yScroll >> 5);
        int y1 = (yScroll + graphics.height +32) >> 5;

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x ,y ,graphics);
            }
        }
    }

}
