package entity;

import levelMaker.LevelManager;
import main.GraphicHandler;
import main.graphics.Sprite;

public class KnifeProj extends Projectile {

    public static int ROF = 20;
    private int animation_counter;
    private int cycle_counter;

    public KnifeProj(int x, int y, double dir, double speed, double range, Sprite sprite, LevelManager level) {
        super(x, y, dir, speed, range, sprite, level);
        animation_counter = 0;
        cycle_counter = 0;
    }

    @Override
    public void render(GraphicHandler gh) {

            if (animation_counter % 4 == 0) {
                gh.renderPlayer((int) x, (int) y, Sprite.knife_projectile[cycle_counter++]);
                if (cycle_counter > 5) cycle_counter = 0;
            }

        gh.renderPlayer((int) x, (int) y, Sprite.knife_projectile[cycle_counter]);

    }

    @Override
    public void update() {
        if (animation_counter < 5000000) animation_counter++;
        else animation_counter = 0;
        move();
    }

}
