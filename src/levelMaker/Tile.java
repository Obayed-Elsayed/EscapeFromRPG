package levelMaker;

import main.GraphicHandler;
import main.graphics.Sprite;

public class Tile {

    public int xCoord, yCoord;
    public Sprite sprite;

    public static Tile voidTile = new VoidTile(Sprite.voidTile_a);


    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, GraphicHandler graphics) {

    }

    public boolean solid() {
        return false;
    }
}
