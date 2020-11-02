package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
//import java.util.Timer;
import javax.imageio.*;
// local imports
import entity.*;
import levelMaker.LevelManager;
import main.graphics.Sprite;
import main.input.InputManager;
import sun.plugin2.gluegen.runtime.BufferFactory;

// TODO: rename this class appropriately like GameManager?
// TODO: Remove useless imports
public class GraphicHandler extends Canvas {

    private java.util.Timer animationTimer = new java.util.Timer();

    public ArrayList<BufferedImage> images;
    public BufferedImage displayedImage;
    private BufferStrategy bs;

    // Rasters = rectangular grid / array of pixels
    private int[] pixels;

    private int[] calc_pixels;
    private int width;
    private int height;

    private MainFrame frame;
    private InputManager inputManager;

    private final int MAP_SIZE = 64;
    public int[] tiles = new int[64 * 64];
    private Random random = new Random();
    public int map_x_off = 0;
    public int map_y_off = 0;

    public GraphicHandler(MainFrame frame, int width, int height) {
        this.frame = frame;
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];

        displayedImage = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        calc_pixels = ((DataBufferInt) displayedImage.getRaster().getDataBuffer()).getData();

        for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
            tiles[i] = random.nextInt(0xffffff);
        }
        inputManager = new InputManager();
        addKeyListener(inputManager);
    }

    public void tick() {
        inputManager.update();
        if (inputManager.left) this.map_x_off--;
        if (inputManager.right) this.map_x_off++;
        if (inputManager.up) this.map_y_off--;
        if (inputManager.down) this.map_y_off++;

    }

    public void render(int offsetx, int offsety) {

        if (this.bs == null) {
            this.createBufferStrategy(3);
            this.bs = this.getBufferStrategy();
            return;
        }

        this.clear();
        this.display(offsetx, offsety);
        for (int i = 0; i < calc_pixels.length; i++) {
            this.calc_pixels[i] = this.pixels[i];
        }

        Graphics g = this.bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, frame.getWidth(), frame.getHeight());

        g.drawImage(displayedImage, 0, 0, this.width, this.height, null);

        g.dispose();
        bs.show();
    }
    // bit wise shift is faster than dividing by 32
    // bit wise & for resetting map rendering
    // Helpful reminder that the difference between y=0 and y=1 is (1* width of screen), because linear array
    public void display(int offsetx, int offsety) {
        for (int y = 0; y < height; y++) {
            //if (y < 0 || y >= height) break;
            int map_y_movement = y + offsety;
            for (int x = 0; x < width; x++) {
                //if (x < 0 || x >= height) break;
                int map_x_movement = x + offsetx;
                int tileIndex = ((map_x_movement >> 5) & (MAP_SIZE - 1)) + ((map_y_movement >> 5) & (MAP_SIZE - 1)) * MAP_SIZE;
                //pixels[x + y * width] = tiles[tileIndex];
                pixels[x + y * width] = Sprite.idle_ghost.sprite_data[(x&31) + (y&31) * Sprite.idle_ghost.SIZE];
            }
        }
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
