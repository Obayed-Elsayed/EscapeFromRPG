package entity;

/*
 * Main Entity class, any NPC, character or boss is derived
 * from this class.
 */
public class Entity {

    private Integer mass;
    private static Integer entityCount = 0;
    private static Integer IDCount = 0;
    private Integer ID;
    
    protected Vector2 position;
    protected Vector2 velocity;
    protected Vector2 acceleration;

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

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public double getX() {
        return this.position.x;
    }

    public double getY() {
        return this.position.y;
    }

    public Integer getMass() {
        return this.mass;
    }

    public Integer getID() {
        return this.ID;
    }

    public Integer getEntityCount() {
        return entityCount;
    }

}
