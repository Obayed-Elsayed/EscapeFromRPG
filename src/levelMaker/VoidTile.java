package levelMaker;

import main.GraphicHandler;
import main.graphics.Sprite;

public class VoidTile extends Tile {

    public VoidTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void render(int x, int y, GraphicHandler graphics) {
        graphics.displaySprite(x << 5, y << 5, this);
    }
}
