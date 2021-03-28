package main;

import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.awt.AlphaComposite;
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

    Particle p;
    private Sprite tester;

    public GraphicHandler(MainFrame frame, int width, int height) {
        this.frame = frame;
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];

        displayedImage = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        calc_pixels = ((DataBufferInt) displayedImage.getRaster().getDataBuffer()).getData();


        keyboardManager = new InputManager();
        addKeyListener(keyboardManager);
        addMouseListener(mouseManager);
        addMouseMotionListener(mouseManager);

//        testLevel = new ProceduralLevel(64, 64);
        bitLevel = new MapSpawner(128, 128,"src/Resources/sprites/terrain/demomap.png");
        player = new Player(32*6,32*7, keyboardManager);
        player.setLevel(bitLevel);
        tester = new Sprite(200, 300, 0xFFFF4F1F);
        //player.calculatePlayerLighting();
        // player.runPlayerLighting();
    }

    public void tick() {
        keyboardManager.update();
        if (keyboardManager.left) this.map_x_off-=3;
        if (keyboardManager.right) this.map_x_off+=3;
        if (keyboardManager.up) this.map_y_off-=3;
        if (keyboardManager.down) this.map_y_off+=3;
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
        // whereas this offset is the player position centered
        int xScroll = offsetx - this.width/2;
        int yScroll = offsety - this.height/2;

        this.bitLevel.render(xScroll, yScroll, this);
        this.player.render(this);
        //p.render(this);
       // this.renderParticle(1000, 500,tester ,true);
        //this.renderLight(player.x, player.y, 200, 40);
        // this.renderLight2(player.x, player.y, 200, 20);

        Graphics g = this.bs.getDrawGraphics();
        
        g.drawImage(displayedImage, 0, 0, this.width, this.height, null);


        g.dispose();
        bs.show();
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
                this.calc_pixels[xAbsolute + yAbsolute * width] = tile.sprite.sprite_data[x + y * tile.sprite.SIZE];
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
                    this.calc_pixels[xAbsolute + yAbsolute * width] = sprite.sprite_data[x + y * sprite.SIZE];
                }
            }
        }
    }

    public void renderParticle(int xPos, int yPos, Sprite sprite, boolean fixed) {
        if (fixed) {
            xPos -= map_x_off;
            yPos -= map_y_off;
        }

        for (int y = 0; y < sprite.ySIZE; y++) {
            int yAbsolute = y + yPos;
            for (int x = 0; x < sprite.xSIZE; x++) {
                int xAbsolute = x + xPos;
                if(xAbsolute >= width || xAbsolute < 0 || yAbsolute >= height || yAbsolute < 0) continue;
                this.calc_pixels[xAbsolute + yAbsolute *  width] = sprite.sprite_data[x + y * sprite.xSIZE];
            }
        }
    }

    // Try different light approach with double separate line integrals we know how much X and Y movement there is along an arc with that
    public void renderLight(int xPos, int yPos, int radius, int theta) {
        xPos -= map_x_off;
        yPos -= map_y_off;
        int colour_fade = 0x00010101;

        for (int r = radius; r > 0; r--) {
            double thetastepsize = (81.6 / 2.0 * (Math.sin(theta*Math.PI/180.0) * Math.sin((theta*Math.PI/180.0))) * (r / 81.6) * (r / 81.6));
            // System.out.println("R: "+r+" stepsize: "+ +thetastepsize);
            if(r%10==0){
                colour_fade+=0x00010101;
            }
            for (int s = 0; s < (int)thetastepsize; s++) {
                int xAbsolute = (int)(xPos + r * Math.cos(theta/thetastepsize*Math.PI/180.0));
                System.out.println(xAbsolute);
                int yAbsolute = (int)(yPos + r * Math.sin(theta/thetastepsize*Math.PI/180.0));
//                if (xAbsolute < -sprite.SIZE || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) {
//                    break;
//                }
//                if (xAbsolute < 0) {
//                    xAbsolute = 0;
//                }
               this.calc_pixels[xAbsolute + yAbsolute * width] += colour_fade;
            }
        }



    }
    // Try different light approach with double separate line integrals we know how much X and Y movement there is along an arc with that
    public void renderLight2(int xPos, int yPos, int radius, int theta) {
        xPos -= map_x_off;
        yPos -= map_y_off;
        int colour_fade = 0x00010101;
        int xstep = (int) (radius * Math.sin(theta * Math.PI / 180.0));
        int ystep = (int) (-1*radius * (Math.cos(theta * Math.PI / 180.0) - Math.cos(0)));
        System.out.println(xstep + " " + ystep);

       // this.calc_pixels[xAbsolute + yAbsolute * width] += colour_fade;



    }


    public void renderLightTriangle(Integer x1, Integer y1, Integer x2, Integer y2, Integer x3, Integer y3) {

        Integer xpositions[] = new Integer[]{x1, x2, x3};

        Arrays.sort(xpositions, Collections.reverseOrder());

        Integer ypositionsbad[] = new Integer[]{y1, y2, y3};
        ArrayList<Integer> ypositions= new ArrayList();

        // Between right most X and 2nd right most X
        Line line1 = new Line(x1, y1, x2, y2);

        // Between right most X and and left most X
        Line line2 = new Line(x1, y1, x3, y3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (xpositions[i] == ypositionsbad[j]) {
                    ypositions.add(ypositionsbad[j]);
                }
            }
        }

//        for(){
//            for (int x = 0; x < line2.x; x++) {
//                for (int y = 0; y < line2.y; y++) {
//
//                }
//            }
//        }

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
