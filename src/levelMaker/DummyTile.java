package levelMaker;

import main.GraphicHandler;
import main.graphics.Sprite;

public class DummyTile extends Tile {
    public DummyTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void render(int x, int y, GraphicHandler graphics) {
        graphics.displayMap(x << 5, y << 5, this);
    }
}
