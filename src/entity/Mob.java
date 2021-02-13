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
        if(!collision(Xin, 0)){
            this.x +=Xin;
        }
        if(!collision(0, Yin)){
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
        for (int i =0; i< 4; i++) {
            int dbg1= i % 2 * 20 + 5;
            int dbg2= i / 2 * 20 + 8;
            int xf = ((x + xstep) + dbg1) >> 5;
            int yf = ((y + ystep) + dbg2) >> 5;
            if(level.getTile(xf,yf).solid()) return true;

        }

       return false;

    }

    public void render(GraphicHandler graphic) {

    }
}
