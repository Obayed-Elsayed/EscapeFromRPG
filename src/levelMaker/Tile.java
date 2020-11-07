package levelMaker;

import main.GraphicHandler;
import main.graphics.Sprite;

public class Tile {

    public int xCoord, yCoord;
    public Sprite sprite;

    public static Tile voidTileA = new VoidTile(Sprite.voidTile_a);
    public static Tile voidTileB = new VoidTile(Sprite.voidTile_b);
    public static Tile dummyTile = new DummyTile(Sprite.dummyTile);
    public static Tile solidTile = new DummyTile(Sprite.solidTile);


    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, GraphicHandler graphics) {

    }

    public boolean solid() {
        return false;
    }
}
