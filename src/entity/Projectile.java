package entity;

import levelMaker.LevelManager;
import main.GraphicHandler;
import main.graphics.Sprite;

public class Projectile extends Entity {

    protected double theta;
    protected Sprite sprite;
    protected double missileSpeed;

    protected double range;
    protected double xCompVector;
    protected double yCompVector;

    // reduced rounding error when moving using doubles
    protected double x, y;
    protected int xorigin,yorigin;
    // TODO change appropriately with more projectile types
    public static int ROF = 20;
    // projectile stats?
    // Dmg
    // Speed
    // ROF
    // size
    // range? TO INFINITY AND BEYOND

    public Projectile(int x, int y, double dir, double speed, double range, Sprite sprite, LevelManager level){
        this.x = xorigin = x;
        this.y = yorigin = y;
        this.range = range;
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
            if(distance()>range) remove();
    }

    private double distance(){
        return Math.sqrt((xorigin-x)*(xorigin-x) + (yorigin-y)*(yorigin-y));
    }

//    private boolean collision(int xstep, int ystep) {
//        //passing pixel number so divide by 32 to get actual tile
//        for (int i =0; i< 4; i++) {
//            int dbg1= 0;d
//            int dbg2= 0;
//            int xf = ((x + xstep) + dbg1) >> 5;
//            int yf = ((y + ystep) + dbg2) >> 5;
//            if(level.getTile(xf,yf).solid()) return true;
//
//        }
//
//        return false;
//
//    }

    public void render(GraphicHandler gh){
        gh.renderPlayer((int)x, (int)y, sprite);

    }
    
    @Override
    public void update(){
            move();
    }


}
