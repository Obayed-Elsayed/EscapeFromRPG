package levelMaker;

import main.GraphicHandler;
import main.graphics.Sprite;

public class GenericTile extends  Tile {

    public GenericTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void render(int x, int y, GraphicHandler graphics) {

        graphics.displaySprite(x << 5, y << 5, this);
    }
}
