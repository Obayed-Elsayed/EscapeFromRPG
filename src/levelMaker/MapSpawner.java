package levelMaker;

import main.graphics.Sprite;

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
        bit_map = new int[mapWidth * mapHeight];
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

            if (colors[i] == 0xFFf200ff) { // purple top wall

                Tile.map_tiles.add(new GenericTile(Sprite.upperWall));
                Tile.map_tiles.get(i).setSolid(true);
            } else if (colors[i] == 0xFFb14863) { // right red
                Tile.map_tiles.add(new GenericTile(Sprite.rightSideWall));
                Tile.map_tiles.get(i).setSolid(true);
            }
            else if (colors[i] == 0xFF9ccc47) { // bottom lime
                Tile.map_tiles.add(new GenericTile(Sprite.lowerWall));
                Tile.map_tiles.get(i).setSolid(true);
            }
            else if (colors[i] == 0xFF2e2e2e) { // top wall 2
                Tile.map_tiles.add(new GenericTile(Sprite.upperWall2));
                Tile.map_tiles.get(i).setSolid(true);
            }
            else if (colors[i] == 0xFF313a91) { // left blue
                Tile.map_tiles.add(new GenericTile(Sprite.leftSideWall));
                Tile.map_tiles.get(i).setSolid(true);
            }
            else if (colors[i] == 0xFFf2ff00) { // top left corner
                Tile.map_tiles.add(new GenericTile(Sprite.tlcorner));
                Tile.map_tiles.get(i).setSolid(true);
            }
            else if (colors[i] == 0xFFff0000) { // top right corner
                Tile.map_tiles.add(new GenericTile(Sprite.trcorner));
                Tile.map_tiles.get(i).setSolid(true);
            }
            else if (colors[i] == 0xFF1bff00) { // bot left corner
                Tile.map_tiles.add(new GenericTile(Sprite.blcorner));
                Tile.map_tiles.get(i).setSolid(true);
            }
            else if (colors[i] == 0xFFffa100) { // bot right corner
                Tile.map_tiles.add(new GenericTile(Sprite.brcorner));
                Tile.map_tiles.get(i).setSolid(true);
            }
            else if (colors[i] == 0xFF9badb7 ) { // flooring
                Tile.map_tiles.add(new GenericTile(Sprite.floor));
            }
            else{

                Tile.map_tiles.add(new GenericTile(new Sprite(32, colors[i])));
            }
            assets.put(colors[i], i);
        }
        Tile.lightingMapper =Tile.map_tiles.size();
        Tile.tile_assets =  new Tile[Tile.lightingMapper];
        Tile.tile_assets =  Tile.map_tiles.toArray(Tile.tile_assets);
        for(Tile t: Tile.tile_assets){
            // add the lit up version of this tile
            Tile.map_tiles.add(new GenericTile(Sprite.litSprite(t.sprite)));
            Tile.map_tiles.get(Tile.map_tiles.size()-1).setSolid(t.solid());
        }

        // change it back to an array again
        Tile.tile_assets =  new Tile[Tile.map_tiles.size()];
        Tile.tile_assets =  Tile.map_tiles.toArray(Tile.tile_assets);

        for(int y = 0; y < mapHeight; y++){
            for(int x = 0; x < mapWidth; x++){
                tiles[x + y * mapWidth] = assets.get(bit_map[x + y * mapWidth]);
//               System.out.print(tiles[x + y * width]);
            }
//            System.out.println();
        }
//        System.out.println();

    }

    protected void load_map(){

    }

    @Override
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= mapWidth || y >= mapHeight) {
            return Tile.dummyTile;
        }
        try{

            return Tile.tile_assets[tiles[x + y * mapWidth]];
        }
        catch(Exception e){
            return Tile.dummyTile;
        }

    }
}
