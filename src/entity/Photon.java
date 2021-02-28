package entity;

import levelMaker.LevelManager;
import main.graphics.Sprite;

public class Photon extends Projectile{

    public Photon(int x, int y, double dir, double speed, double range, Sprite sprite, LevelManager level) {
        super(x, y, dir, speed, range, sprite, level);
    }


}
