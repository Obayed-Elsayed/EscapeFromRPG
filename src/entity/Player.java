package entity;

import levelMaker.LevelManager;
import levelMaker.Tile;
import main.GraphicHandler;
import main.graphics.Sprite;
import main.input.InputManager;

import java.util.ArrayList;
import java.util.Arrays;


public class Player extends Mob {

    private InputManager input;
    public boolean moving = false;
    public int animation_counter;
    public int cycle_counter;

    private int fireRate;


    public Player(InputManager input) {
        this.input = input;
        this.animation_counter = 0;
        this.cycle_counter = 0;
    }

    public Player(int x, int y, InputManager input) {
        this.x = x;
        this.y = y;
        this.input = input;
        this.animation_counter = 0;
        this.cycle_counter = 0;
        fireRate = Projectile.ROF;
    }

    public Player() {

    }

    public void render(GraphicHandler graphic) {
        // TODO animation cycle counter seems to be broken -> investigate please
        if (this.dir == 0) {
            graphic.renderPlayer(this.x, this.y, Sprite.front_player);
        }
        if (this.dir == 2) {
            graphic.renderPlayer(this.x, this.y, Sprite.front_player);
        }
        if (this.dir == 1) {
            if (moving) {
                if (animation_counter % 2 == 0) {
                    graphic.renderPlayer(this.x, this.y, Sprite.player_right_cycle[this.cycle_counter++]);
                    if (this.cycle_counter > 5) this.cycle_counter = 0;
                }
            }
            graphic.renderPlayer(this.x, this.y, Sprite.player_right_cycle[this.cycle_counter]);


        }
        if (this.dir == 3) graphic.renderPlayer(this.x, this.y, Sprite.left_player);
    }

    public void update() {
        if (fireRate>0) {
            fireRate--;
        }
        int xa = 0, ya = 0;
        // TODO test with 5000000 then change to near max INT
        if (animation_counter < 5000000) animation_counter++;
        else animation_counter = 0;

        if (this.input.up) ya -= 5;
        if (this.input.down) ya += 5;
        if (this.input.left) xa -= 5;
        if (this.input.right) xa += 5;

        if(moving) {
            clearLitTiles();
            calculatePlayerLighting();
        }

        if (xa != 0 || ya != 0) {
            moving = true;
            move(xa, ya);
        } else {
            moving = false;
        }
        calculate_shot();
    }

    protected void calculate_shot() {
        if (GraphicHandler.mouseManager.getMouseB() == 1 && fireRate == 0) {
            // Player is always centered with ann offset of height/2 and width/2
            // remove the offset and using simple trig we get the angle
//            System.out.println("Y: "+(GraphicHandler.mouseManager.getMouseY() - GraphicHandler.height/2 - 16));
//            System.out.println("X: "+(GraphicHandler.mouseManager.getMouseX() - GraphicHandler.width/2 - 16));
            double theta = Math.atan2((double) (GraphicHandler.mouseManager.getMouseY() - GraphicHandler.height / 2 - 16),
                    (double) (GraphicHandler.mouseManager.getMouseX() - GraphicHandler.width / 2 - 16));
            shoot(x, y, theta);
        }
    }

    //On Movement
    public void calculatePlayerLighting(){
        // 36 photons
       for(int i =0; i < 37; i++){
           Photon ray = new Photon(x, y, i*Math.PI/36,16.0,320, null, level);
           while(!level.collision(ray.x,ray.y,ray.xCompVector,ray.yCompVector,0) && ray.distance()<ray.range){
               //move onto the block
               ray.x+=ray.xCompVector;
               ray.y+=ray.yCompVector;
               int xpos = (int)ray.x>>LevelManager.tileConverter;
               int ypos = (int)ray.y>>LevelManager.tileConverter;
               if(level.getTiles()[xpos + ypos * level.getMapWidth()] < Tile.lightingMapper){

                   level.getTiles()[xpos + ypos * level.getMapWidth()]+= Tile.lightingMapper;
               }
           }
       }
       for(int i =0; i < 37; i++){
           Photon ray = new Photon(x, y, i*-Math.PI/36,16.0,310, null, level);
           while(!level.collision(ray.x,ray.y,ray.xCompVector,ray.yCompVector,0) && ray.distance()<ray.range){
               //move onto the block
               ray.x+=ray.xCompVector;
               ray.y+=ray.yCompVector;
               int xpos = (int)ray.x>>LevelManager.tileConverter;
               int ypos = (int)ray.y>>LevelManager.tileConverter;
               if(level.getTiles()[xpos + ypos * level.getMapWidth()] < Tile.lightingMapper){

                   level.getTiles()[xpos + ypos * level.getMapWidth()]+= Tile.lightingMapper;
               }
           }
//           int xpos = ((int)ray.x + (int)ray.xCompVector)>>LevelManager.tileConverter;
//           int ypos = ((int)ray.y + (int)ray.yCompVector)>>LevelManager.tileConverter;
//           if(level.getTiles()[xpos + ypos * level.getMapWidth()] < Tile.lightingMapper){
//               level.getTiles()[xpos + ypos * level.getMapWidth()]+= Tile.lightingMapper;
//           }
       }
    }

    public void clearLitTiles(){
        //TODO add a map with the coords of the tiles that were lit up to clear them, instead of the entire map
            for(int i =0; i < level.getTiles().length; i++){
                if(level.getTiles()[i]>= Tile.lightingMapper){
                    level.getTiles()[i]-=Tile.lightingMapper;
                }
            }
    }

    protected void shoot(int x, int y, double dir) {
//               System.out.println(Math.toDegrees(dir));
        Projectile p = new KnifeProj(this.x, this.y, dir, 35, 4000, Sprite.boom, level);
        level.addEntity(p);
        fireRate = Projectile.ROF;

    }

}
