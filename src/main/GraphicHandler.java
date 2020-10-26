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
    private Integer fpsCounter = 0;
    public ArrayList<BufferedImage> images;
    public BufferedImage displayedImage;
    // Rasters = rectangular grid / array of pixels
    private int [] pixels;

    public ArrayList<Entity> entities = new ArrayList<Entity> ();
    public String rel_path = "src/Resources/sprites/walking";
    private Player player;
    private LevelManager levelManager;
    private Integer imageCounter;

    public MainFrame frame;

    // Schedueled task manager
    ScheduledExecutorService ses;
    Runnable walkAnimation;
    public GraphicHandler(MainFrame frame) {
        this.player = new Player();
        this.player.setPostition(new Vector2(300, 50  ));
        this.levelManager = new LevelManager();
        this.images = new ArrayList<BufferedImage>();
        load_sprites();
        this.displayedImage = images.get(0);
        this.imageCounter =0;
        ses = Executors.newScheduledThreadPool(1);
        this.frame = frame;
        walkAnimation = () -> displayedImage = images.get(imageCounter++);
        displayedImage = new BufferedImage(frame.getWidth(),frame.getHeight(),BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt)displayedImage.getRaster().getDataBuffer()).getData();
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
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,frame.getWidth(),frame.getHeight());

//        this.setBackground(Color.WHITE);
//        Graphics2D drawTool = (Graphics2D) g;
//        if(imageCounter >=6){
//            imageCounter=0;
//        }
        // Draw player
//        drawTool.drawImage(displayedImage,(int) player.getX(),(int) player.getY(),65,65, null);
//        drawTool.drawString(Integer.toString(this.fpsCounter),LevelManager.WIDTH-50,10);

//        levelManager.setupDevloperLevel(g);
//        levelManager.draw(drawTool);

        g.dispose();
        bs.show();
    }

    public void tick(){
        this.player.update(3);
    }

    public void clear(){
        for(int pixel : pixels){
            pixel=0;
        }
    }
    public Player getPlayer() {
        return this.player;
    }

    public ArrayList<String> getSpriteNames(String name, Integer num_images) {
        ArrayList<String> image_names = new ArrayList<>();

        for (int i = 0; i < num_images; i++) {
            image_names.add(name + Integer.toString(i + 1) + ".png");
        }
        return image_names;
    }

}
