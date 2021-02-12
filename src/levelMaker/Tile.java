package levelMaker;

import main.GraphicHandler;
import main.graphics.Sprite;

import java.util.ArrayList;

public class Tile {

    public int xCoord, yCoord;
    public Sprite sprite;
    private boolean solid = false;

    public static Tile voidTileA = new VoidTile(Sprite.voidTile_a);
    public static Tile voidTileB = new VoidTile(Sprite.voidTile_b);
    public static Tile dummyTile = new DummyTile(Sprite.dummyTile);
    public static Tile solidTile = new DummyTile(Sprite.solidTile);

    public static ArrayList<Tile> map_tiles = new ArrayList<Tile>();
    public static Tile [] tile_assets;

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, GraphicHandler graphics) {

    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    public boolean solid() {
        return solid;
    }
}
