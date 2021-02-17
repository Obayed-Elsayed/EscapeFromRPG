package main;

import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Random;
//import java.util.Timer;
// local imports
import entity.*;
import levelMaker.LevelManager;
import levelMaker.MapSpawner;
import levelMaker.Tile;
import main.graphics.Sprite;
import main.input.InputManager;
import main.input.MouseInputManager;

// TODO: rename this class appropriately like GameManager?
// TODO: Remove useless imports
public class GraphicHandler extends Canvas {

    private java.util.Timer animationTimer = new java.util.Timer();

    public ArrayList<BufferedImage> images;
    public BufferedImage displayedImage;
    public Player player;

    // Rasters = rectangular grid / array of pixels
    private BufferStrategy bs;
    private int[] pixels;
    private int[] calc_pixels;
    private MainFrame frame;
    private InputManager keyboardManager;
    public static MouseInputManager mouseManager = new MouseInputManager();
    private LevelManager testLevel;
    private LevelManager bitLevel;
    private Random random = new Random();
//    private final int MAP_SIZE = 64;

    public static int width;
    public static int height;
    public int[] tiles = new int[64 * 64];
    public int map_x_off = 0;
    public int map_y_off = 0;

    public GraphicHandler(MainFrame frame, int width, int height) {
        this.frame = frame;
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];

        displayedImage = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        calc_pixels = ((DataBufferInt) displayedImage.getRaster().getDataBuffer()).getData();


        keyboardManager = new InputManager();
//        mouseManager = new MouseInputManager();
        addKeyListener(keyboardManager);
        addMouseListener(mouseManager);
        addMouseMotionListener(mouseManager);

//        testLevel = new ProceduralLevel(64, 64);
        bitLevel = new MapSpawner(128, 128,"src/Resources/sprites/terrain/map2.png");
        player = new Player(32*6,32*7, keyboardManager);
        // give player collision info/ level
        player.setLevel(bitLevel);
    }

    public void tick() {
        keyboardManager.update();
        if (keyboardManager.left) this.map_x_off-=5;
        if (keyboardManager.right) this.map_x_off+=5;
        if (keyboardManager.up) this.map_y_off-=5;
        if (keyboardManager.down) this.map_y_off+=5;
        player.update();
        bitLevel.update();

    }

    public void render(int offsetx, int offsety) {

        if (this.bs == null) {
            this.createBufferStrategy(3);
            this.bs = this.getBufferStrategy();
            return;
        }

        this.clear();
        // whereas this offset is the player position
        int xScroll = offsetx - this.width/2;
        int yScroll = offsety - this.height/2;

        this.bitLevel.render(xScroll, yScroll, this);
        this.player.render(this);
        for (int i = 0; i < calc_pixels.length; i++) {
            this.calc_pixels[i] = this.pixels[i];
        }
//        Arrays.stream(this.calc_pixels).forEach(pixel-> pixel = this.pi);
//        this.calc_pixels= this.pixels.clone();

        Graphics g = this.bs.getDrawGraphics();
        g.drawImage(displayedImage, 0, 0, this.width, this.height, null);

        g.dispose();
        bs.show();
    }

    // bit wise shift is faster than dividing by 32
    // bit wise & for resetting map rendering
    // Helpful reminder that the difference between y=0 and y=1 is (1* width of screen), because linear array
    public void display(int offsetx, int offsety) {
        for (int y = 0; y < height; y++) {
            int yPixel = y + offsety;
            if (yPixel < 0 || yPixel >= height) continue;
            for (int x = 0; x < width; x++) {
                int xPixel = x + offsetx;
                if (xPixel < 0 || xPixel >= width) continue;
                //int tileIndex = ((xPixel >> 5) & (MAP_SIZE - 1)) + ((yPixel >> 5) & (MAP_SIZE - 1)) * MAP_SIZE;
                //pixels[x + y * width] = tiles[tileIndex];
                pixels[(xPixel) + (yPixel) * width] = Sprite.idle_player_01.sprite_data[(x & 31) + (y & 31) * Sprite.idle_player_01.SIZE];
            }
        }
    }

    public void displaySprite(int xPos, int yPos, Tile tile) {
        xPos -= map_x_off;
        yPos -= map_y_off;
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int yAbsolute = y + yPos;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xAbsolute = x + xPos;
                if (xAbsolute < -tile.sprite.SIZE || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) {
                    break;
                }
                if (xAbsolute < 0) {
                    xAbsolute = 0;
                }
                pixels[xAbsolute + yAbsolute * width] = tile.sprite.sprite_data[x + y * tile.sprite.SIZE];
            }
        }
    }

    public void renderPlayer(int xPos, int yPos, Sprite sprite) {
        xPos -= map_x_off;
        yPos -= map_y_off;
        for (int y = 0; y < sprite.SIZE; y++) {
            int yAbsolute = y + yPos;
            for (int x = 0; x < sprite.SIZE; x++) {
                int xAbsolute = x + xPos;
                if (xAbsolute < -sprite.SIZE || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) {
                    break;
                }
                if (xAbsolute < 0) {
                    xAbsolute = 0;
                }
                int colour = sprite.sprite_data[x + y * sprite.SIZE];
                // Render everything in sprite except background!
                if (colour != 0xFFFF00FF){
                    pixels[xAbsolute + yAbsolute * width] = sprite.sprite_data[x + y * sprite.SIZE];
                }
            }
        }
    }

    public void setOffset(int xOff, int yOff) {
        map_x_off = xOff;
        map_y_off = yOff;
    }


    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public Player getPlayer() {
        //return this.player;
        return null;
    }

}
