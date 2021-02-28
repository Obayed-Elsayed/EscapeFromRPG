package levelMaker;

import java.util.ArrayList;
import java.util.List;

import entity.Entity;
import main.GraphicHandler;

// TODO probably change this to abstract class, once I figure out what I am going to do with this
public class LevelManager {

    protected int mapWidth;
    protected int mapHeight;
    protected int[] tiles;

    protected List<Entity> entities = new ArrayList<Entity>();
    // typical tile size
    public static int tileConverter = binlog(Tile.dummyTile.sprite.SIZE);

    public LevelManager(int width, int height) {
        this.mapWidth = width;
        this.mapHeight = height;
        tiles = new int[width * height];
        generateLevel();
    }

    public LevelManager(int width, int height, String path) {
        this.mapWidth = width;
        this.mapHeight = height;
        tiles = new int[width * height];
        loadLevel(path);
    }

    protected void generateLevel() {

    }

    public void loadLevel(String path) {

    }

    public void addEntity(Entity e) {
        entities.add(e);
    }


    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
            if (entities.get(i).removed) {
                entities.remove(i);
            }
        }
    }

    private void time() {

    }

    public boolean collision(double xPos, double yPos, double xstep, double ystep, double size) {
        //passing pixel number so divide by 32 to get actual tile
        for (int i = 0; i < 4; i++) {

            int xf = (int) ((xPos + xstep) + i % 2 * size) >> tileConverter;
            int yf = (int) ((yPos + ystep) + i / 2 * size) >> tileConverter;
            if (this.getTile(xf, yf).solid()) return true;

        }

        return false;

    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= mapWidth || y >= mapHeight) {
            return Tile.dummyTile;
        }
        if (tiles[x + y * mapWidth] == 0) {
            return Tile.voidTileA;
        } else if (tiles[x + y * mapWidth] == 1) {
            return Tile.solidTile;
        }
        return Tile.dummyTile;
    }

    public void render(int xScroll, int yScroll, GraphicHandler graphics) {
        // offSet is the location of the player
        graphics.setOffset(xScroll, yScroll);
        // defining corner pins for rendering a level
        // every 32 or 2^5 is one tile -> tile coords
        int x0 = (xScroll >> 5);
        int x1 = (xScroll + graphics.width + 32) >> 5;
        int y0 = (yScroll >> 5);
        int y1 = (yScroll + graphics.height + 32) >> 5;

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, graphics);
            }
        }
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(graphics);
        }
    }

    public int[] getTiles() {
        return tiles;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public static int binlog(int bits ) // returns 0 for bits=0
    {
        int log = 0;
        if( ( bits & 0xffff0000 ) != 0 ) { bits >>>= 16; log = 16; }
        if( bits >= 256 ) { bits >>>= 8; log += 8; }
        if( bits >= 16  ) { bits >>>= 4; log += 4; }
        if( bits >= 4   ) { bits >>>= 2; log += 2; }
        return log + ( bits >>> 1 );
    }


}
