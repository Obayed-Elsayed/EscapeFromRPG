package entity;

import levelMaker.LevelManager;
import main.GraphicHandler;
import main.graphics.Sprite;

public abstract class Mob extends Entity {

    protected static Sprite sprite;
    protected int dir = 0;
    protected boolean moving = false;
    private LevelManager level;

    public void move(int Xin, int Yin) {
        // 0 up 1 east 2 south 3 west > compass
        if (Xin > 0) this.dir = 1;
        if (Xin < 0) this.dir = 3;
        if (Yin > 0) this.dir = 2;
        if (Yin < 0) this.dir = 0;
        if(!collision(Xin, Yin)){
            this.x +=Xin;
            this.y +=Yin;
        }
    }

    public void update() {

    }

    public void setLevel(LevelManager level) {
        this.level = level;
    }

    private boolean collision(int xstep, int ystep) {
        //passing pixel number so divide by 32 to get actual tile
        return level.getTile((x+xstep+16)>>5,(y+ystep+16)>>5).solid();
        //return false;
    }

    public void render(GraphicHandler graphic) {

    }
}
