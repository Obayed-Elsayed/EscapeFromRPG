package entity;

import main.GraphicHandler;
import main.graphics.Sprite;

public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected int dir = 0;
    protected boolean moving = false;

    public void move(int Xin, int Yin) {
        // 0 up 1 east 2 south 3 west > compass
        if (Xin > 0) this.dir = 1;
        if (Xin < 0) this.dir = 3;
        if (Xin > 0) this.dir = 2;
        if (Xin < 0) this.dir = 0;
        if(!collision()){
            this.x +=Xin;
            this.y +=Yin;
        }
    }

    public void update() {

    }

    private boolean collision() {
        return false;
    }

    public void render(GraphicHandler graphic) {

    }
}
