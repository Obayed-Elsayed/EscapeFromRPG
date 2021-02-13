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

    public static SpriteLoader ghost = new SpriteLoader("/Resources/sprites/ghost/spooky.png", 128, 128);
    public static SpriteLoader player_dummy2 = new SpriteLoader("/Resources/sprites/playerDummy/player_sheet2.png", 160, 128);
    public static SpriteLoader basicTerrain = new SpriteLoader("/Resources/sprites/terrain/Terrain.png", 128, 160);
    public static SpriteLoader mapTerrain = new SpriteLoader("/Resources/sprites/terrain/tileset.png", 256, 256);

    public SpriteLoader(String path, int xSize, int ySize) {
        this.path = path;
        this.xSIZE = xSize;
        this.ySIZE = ySize;
        image_data = new int[xSIZE * ySIZE];
        loadSpriteSheet();
    }

    private void loadSpriteSheet() {
        try {
            BufferedImage image = ImageIO.read(SpriteLoader.class.getResource(path));
            image.getRGB(0,0,image.getWidth(),image.getHeight(),image_data,0,image.getWidth());
        } catch (IOException e) {
            System.out.println("\n Failed to load assets \n");
            e.printStackTrace();
        }
    }

    public static boolean verifyAssetsHaveLoaded() {
        return ghost != null && basicTerrain != null;
    }



}
