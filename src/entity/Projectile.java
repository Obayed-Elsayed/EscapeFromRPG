package entity;

import levelMaker.LevelManager;
import main.GraphicHandler;
import main.graphics.Sprite;

public class Projectile extends Entity {

    protected double theta;
    protected Sprite sprite;
    protected double missileSpeed;

    protected double xCompVector;
    protected double yCompVector;

    // projectile stats?
    // Dmg
    // Speed
    // ROF
    // size
    // range? TO INFINITY AND BEYOND

    public Projectile(int x, int y, double dir, double speed, Sprite sprite, LevelManager level){

        this.x = x;
        this.y = y;
        theta = dir;
        missileSpeed = speed;
        xCompVector = missileSpeed * Math.cos(theta);
        yCompVector = missileSpeed * Math.sin(theta);
        this.sprite = sprite;
        this.level = level;

    }

    public void move(){
        // TODO check collision
            this.x +=xCompVector;
            this.y +=yCompVector;

    }

    private boolean collision(int xstep, int ystep) {
        //passing pixel number so divide by 32 to get actual tile
        for (int i =0; i< 4; i++) {
            int dbg1= 0;
            int dbg2= 0;
            int xf = ((x + xstep) + dbg1) >> 5;
            int yf = ((y + ystep) + dbg2) >> 5;
            if(level.getTile(xf,yf).solid()) return true;

        }

        return false;

    }

    public void render(GraphicHandler gh){
        gh.renderPlayer(x, y, sprite);

    }
    
    @Override
    public void update(){
            move();
    }


}
