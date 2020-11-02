package main.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteLoader {
    //    private ArrayList<Entity> entities = new ArrayList<Entity>();
    //    private String rel_path = "src/Resources/sprites/walking";
    public int[] image_data;

    private String path;
    public final int SIZE;

    public static SpriteLoader ghost = new SpriteLoader("src/Resources/sprites/ghost/spooky.png", 128);

    public SpriteLoader(String path, int size) {
        this.path = path;
        this.SIZE = size;
        image_data = new int[SIZE * SIZE];
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
