package entity;

public class Vector2 {

    public double x = 0; 
    public double y = 0;
    
    public Vector2 () {}
    
    public Vector2 (double x, double y) {
        this.set (x, y);
    }
    
    //Set Methods
    public void set (double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public void set (Vector2 v) {
        this.set (v.x, v.y);
    }
    
    
    @Override
    public Vector2 clone () {
        return new Vector2 (this.x, this.y);
    }
    
    public Vector2 add (Vector2 input) {
        this.x += input.x;
        this.y += input.y;
        return this;
    }
    
    public Vector2 subtract (Vector2 input) {
        this.x -= input.x;
        this.y -= input.y;
        return this;
    }
    
    public Vector2 scale (double scale) {
        this.x *= scale;
        this.y *= scale;
        return this;
    }
    
    public double dot (Vector2 input) {
        return ((this.x*input.x)+(this.y*input.y));
    }
    
    public double getLength () {
        return Math.sqrt (Math.pow (this.x, 2)+Math.pow (this.y, 2));
    }
    
    public Vector2 normalize () {
        this.x = (this.x/Math.sqrt (Math.pow (this.x, 2)+Math.pow (this.y, 2)));
        this.y = (this.y/Math.sqrt (Math.pow (this.x, 2)+Math.pow (this.y, 2)));
        return this;
    }

    public boolean isZero(){
        return (int)x==0 && (int)y==0;
    }
    
    @Override
    public String toString () {
        return "Vector2 ("+this.x+", "+this.y+")";
    }
}


