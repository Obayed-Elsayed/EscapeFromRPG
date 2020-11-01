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
import sun.plugin2.gluegen.runtime.BufferFactory;

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

    public ArrayList<Entity> entities = new ArrayList<Entity>();
    public String rel_path = "src/Resources/sprites/walking";
    private Player player;
    private LevelManager levelManager;
    private Integer imageCounter;

    private int timer;
    private int counter;

    public MainFrame frame;

    private final int MAP_SIZE = 64;
    public int[] tiles = new int[64 * 64];
    private Random random = new Random();
    public int map_x_off = 0;
    public int map_y_off = 0;

    // Schedueled task manager
    ScheduledExecutorService ses;
    Runnable walkAnimation;

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
    }

    private void load_sprites() {
        ArrayList<String> names = getSpriteNames("Sprite-0001Walking", 7);
        try {
            for (int i = 0; i < 7; i++) {
                images.add(ImageIO.read(new File(rel_path + "/" + names.get(i))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void start_animation_process() {
        // Animate
        //ses.schedule(walkAnimation, 125, TimeUnit.MILLISECONDS);
        ses.scheduleAtFixedRate(walkAnimation, 0, 125, TimeUnit.MILLISECONDS);
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
    public void display(int offsetx, int offsety) {
        for (int y = 0; y < height; y++) {
            //if (y < 0 || y >= height) break;
            int map_y_movement = y;
            for (int x = 0; x < width; x++) {
                //if (x < 0 || x >= height) break;
                int map_x_movement = x + offsetx;
                int tileIndex = ((map_x_movement >> 5) & (MAP_SIZE - 1)) + ((map_y_movement >> 5) & (MAP_SIZE - 1)) * MAP_SIZE;
                pixels[x + y * width] = tiles[tileIndex];
            }
        }
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void tick() {
        this.map_x_off++;
        this.map_y_off++;
    }

    public Player getPlayer() {
        //return this.player;
        return null;
    }

    public ArrayList<String> getSpriteNames(String name, Integer num_images) {
        ArrayList<String> image_names = new ArrayList<>();

        for (int i = 0; i < num_images; i++) {
            image_names.add(name + Integer.toString(i + 1) + ".png");
        }
        return image_names;
    }

}
