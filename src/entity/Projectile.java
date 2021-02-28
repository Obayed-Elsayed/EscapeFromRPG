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
        if(!level.collision(x, y, xCompVector, yCompVector, LevelManager.tileConverter)){

            this.x +=xCompVector;
            this.y +=yCompVector;
        }else{
            remove();
        }
            if(distance()>range) remove();
    }

    public double distance(){
        return Math.sqrt((xorigin-x)*(xorigin-x) + (yorigin-y)*(yorigin-y));
    }



    public void render(GraphicHandler gh){
        gh.renderPlayer((int)x, (int)y, sprite);

    }
    
    @Override
    public void update(){
            move();
    }


}
