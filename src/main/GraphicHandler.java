package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
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

public class GraphicHandler extends JPanel {

    private java.util.Timer animationTimer = new java.util.Timer();

    public ArrayList<BufferedImage> images;
    public BufferedImage displayedImage;

    // Rasters = rectangular grid / array of pixels
    private int [] pixels;

    private int [] calc_pixels;
    private int width;
    private int height;

    public ArrayList<Entity> entities = new ArrayList<Entity> ();
    public String rel_path = "src/Resources/sprites/walking";
    private Player player;
    private LevelManager levelManager;
    private Integer imageCounter;

    private int timer;
    private int counter;

    public MainFrame frame;

    // Schedueled task manager
    ScheduledExecutorService ses;
    Runnable walkAnimation;
    public GraphicHandler(MainFrame frame, int width, int height) {
        this.frame = frame;
        this.width = width;
        this.height = height;
        this.pixels = new int[width*height];

        displayedImage = new BufferedImage(this.width,this.height,BufferedImage.TYPE_INT_RGB);
        calc_pixels = ((DataBufferInt)displayedImage.getRaster().getDataBuffer()).getData();
    }
    private void load_sprites(){
         ArrayList<String> names = getSpriteNames("Sprite-0001Walking",7);
        try {
            for(int i =0; i < 7; i++){
                images.add(ImageIO.read(new File(rel_path+"/"+names.get(i))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void start_animation_process(){
        // Animate
        //ses.schedule(walkAnimation, 125, TimeUnit.MILLISECONDS);
        ses.scheduleAtFixedRate(walkAnimation, 0,125, TimeUnit.MILLISECONDS);
    }

    public void render() {
        BufferStrategy bs = frame.getBufferStrategy();
        if (bs == null) {
            frame.createBufferStrategy(3);
            return;
        }
        counter++;
        if(counter %100 == 0){
            timer++;
        }
        this.clear();
        this.display();
        for(int i =0; i <calc_pixels.length; i++){
            this.calc_pixels[i] = this.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,frame.getWidth(),frame.getHeight());

        g.drawImage(displayedImage,0,0,this.width,this.height, null);

        g.dispose();
        bs.show();
    }

    public void display(){
        for (int y=0; y <height; y++) {
            for (int x=0; x <width; x++) {
                pixels[300+timer*width] = 0xff00ff;
            }
        }
    }

    public void clear() {
        for(int i=0; i < pixels.length; i++){
            pixels[i]=0;
        }
    }
    public void tick(){
       // this.player.update(3);
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
