package entity;

import levelMaker.LevelManager;
import main.GraphicHandler;

import java.util.Random;

/*
 * Main Entity class, any NPC, character or boss is derived
 * from this class.
 */
public class Entity {

    private Integer mass;
    private static Integer entityCount = 0;
    private static Integer IDCount = 0;
    private Integer ID;
    protected final Random random = new Random();
    protected LevelManager level;

    public int x, y;

    // TODO get rid of the vector based movement for this game
    protected Vector2 position;
    protected Vector2 velocity;
    private boolean removed = false;

    public Entity() {
        entityCount++;
        IDCount++;
        this.ID = IDCount;
        this.position = new Vector2(0, 0);
    }

    public void setMass(Integer mass) {
        this.mass = mass;
    }
    
    public void setPostition (Vector2 input) {
        this.position = input;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public double getX() {
        return this.position.x;
    }

    public double getY() {
        return this.position.y;
    }

    public Integer getID() {
        return this.ID;
    }

    public void update(){

    }

    public void remove(){
        // Remove from level
        removed = true;
    }

}
