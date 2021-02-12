package levelMaker;

import main.GraphicHandler;
import main.graphics.Sprite;
import main.graphics.SpriteLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class MapSpawner extends LevelManager {

    public int bit_map[];
    public HashMap<Integer,Integer> assets;

    public MapSpawner(int width, int height, String path) {
        super(width, height, path);
    }


    @Override
    public void loadLevel(String path) {
        bit_map = new int[width * height];
        try {
            //BufferedImage image = ImageIO.read(SpriteLoader.class.getResource(path));
            BufferedImage image = ImageIO.read(new File(path));
            image.getRGB(0,0,image.getWidth(),image.getHeight(),bit_map,0,image.getWidth());
        } catch (IOException e) {
            System.out.println("\n Failed to load assets \n");
            e.printStackTrace();
        }
        create_assets();
    }

    protected void create_assets(){
        assets = new HashMap<>();

        int colors[] = Arrays.stream(bit_map).distinct().toArray();
        // assets.put(colors[i], i);
        for(int i =0;  i < colors.length; i++){
            Tile.map_tiles.add(new GenericTile(new Sprite(32, colors[i])));
            if(colors[i] == 0xFF595652){
                Tile.map_tiles.get(i).setSolid(true);
            }
            assets.put(colors[i], i);
        }
        System.out.println();

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                tiles[x + y * width] = assets.get(bit_map[x + y * width]);
                System.out.print(tiles[x + y * width]);
            }
//            System.out.println();
        }
//        System.out.println();

    }

    protected void load_map(){

    }

    @Override
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.dummyTile;
        }
        try{

            //System.out.println(tiles[x + y * width]);
            return Tile.map_tiles.get(tiles[x + y * width]);
        }
        catch(Exception e){
           // System.out.println("OH NO");
            return Tile.dummyTile;
        }

    }
}
