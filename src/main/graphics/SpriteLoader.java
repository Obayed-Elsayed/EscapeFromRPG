package main.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// TODO sprite sheets shouldn't only be squares, but rectangles instead
public class SpriteLoader {
    //    private ArrayList<Entity> entities = new ArrayList<Entity>();
    //    private String rel_path = "src/Resources/sprites/walking";
    public int[] image_data;

    private String path;
    public final int xSIZE;
    public final int ySIZE;

    public static SpriteLoader ghost = new SpriteLoader("src/Resources/sprites/ghost/spooky.png", 128, 128);
    public static SpriteLoader basicTerrain = new SpriteLoader("src/Resources/sprites/terrain/Terrain.png", 128, 160);

    public SpriteLoader(String path, int xSize, int ySize) {
        this.path = path;
        this.xSIZE = xSize;
        this.ySIZE = ySize;
        image_data = new int[xSIZE * ySIZE];
        loadSpriteSheet();
    }

    private void loadSpriteSheet() {
        try {
            //BufferedImage image = ImageIO.read(SpriteLoader.class.getResource(path));
            BufferedImage image = ImageIO.read(new File(path));
            // scans horizontally, so scan size = width
            image.getRGB(0,0,image.getWidth(),image.getHeight(),image_data,0,image.getWidth());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
