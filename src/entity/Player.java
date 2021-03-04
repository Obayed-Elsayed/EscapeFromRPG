package entity;

import levelMaker.LevelManager;
import levelMaker.Tile;
import main.GraphicHandler;
import main.graphics.Sprite;
import main.input.InputManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Player extends Mob {

    private InputManager input;
    public boolean moving = false;
    public int animation_counter;
    public int cycle_counter;
    public int cycle_idle_counter;


    private int fireRate;

    private static ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

    public Player(InputManager input) {
        this.input = input;
        this.animation_counter = 0;
        this.cycle_counter = 0;
        this.cycle_idle_counter = 0;
    }

    public Player(int x, int y, InputManager input) {
        this.x = x;
        this.y = y;
        this.input = input;
        this.animation_counter = 0;
        this.cycle_counter = 5;
        this.sprite = Sprite.rp_rightidle1;
        fireRate = Projectile.ROF;
    }

    public Player() {

    }

    public void render(GraphicHandler graphic) {
        graphic.renderPlayer(this.x, this.y, this.sprite);
    }

    public void update() {

        // TODO animation cycle counter seems to be broken -> investigate please
        if (animation_counter < 5000000) animation_counter++;
        else animation_counter = 0;

        int animations_per_sec = 10;

        if (this.dir == 0) {
            if (lastSideDir == 1) {
                if (moving) {
                    if (animation_counter % animations_per_sec == 0) {
                        sprite = Sprite.player_tright_cycle[this.cycle_counter++];
                        if (this.cycle_counter > Sprite.player_tright_cycle.length - 1) this.cycle_counter = 0;
                    }
                }
                sprite = Sprite.player_tright_cycle[this.cycle_counter];
            }
            if (lastSideDir == 3) {
                if (moving) {
                    if (animation_counter % animations_per_sec == 0) {
                        sprite = Sprite.player_tleft_cycle[this.cycle_counter++];
                        if (this.cycle_counter > Sprite.player_tleft_cycle.length - 1) this.cycle_counter = 0;

                    }
                }
                sprite = Sprite.player_tleft_cycle[this.cycle_counter];
            }
        }
        if (this.dir == 2) {
            if (lastSideDir == 1) {
                if (moving) {
                    if (animation_counter % animations_per_sec == 0) {
                        sprite = Sprite.player_right_cycle[this.cycle_counter++];
                        if (this.cycle_counter > Sprite.player_right_cycle.length - 1) this.cycle_counter = 0;
                    }
                }
                sprite = Sprite.player_right_cycle[this.cycle_counter];
            }
            if (lastSideDir == 3) {
                if (moving) {
                    if (animation_counter % animations_per_sec == 0) {
                        sprite = Sprite.player_left_cycle[this.cycle_counter++];
                        if (this.cycle_counter > Sprite.player_left_cycle.length - 1) this.cycle_counter = 0;
                    }
                }
                sprite = Sprite.player_left_cycle[this.cycle_counter];
            }
        }
        if (this.dir == 1) {
            if (moving) {
                if (animation_counter % animations_per_sec == 0) {
                    sprite = Sprite.player_right_cycle[this.cycle_counter++];
                    if (this.cycle_counter > Sprite.player_right_cycle.length - 1) this.cycle_counter = 0;
                }
            }
            if (this.cycle_counter > Sprite.player_left_cycle.length - 1) this.cycle_counter = 0;
            sprite = Sprite.player_right_cycle[this.cycle_counter];
        }
        if (this.dir == 3) {
            if (moving) {
                if (animation_counter % animations_per_sec == 0) {
                    sprite = Sprite.player_left_cycle[this.cycle_counter++];
                    if (this.cycle_counter > Sprite.player_left_cycle.length - 1) this.cycle_counter = 0;
                }
            }
            if (this.cycle_counter > Sprite.player_left_cycle.length - 1) this.cycle_counter = 0;
            sprite = Sprite.player_left_cycle[this.cycle_counter];
        }


        if (fireRate>0) {
            fireRate--;
        }
        int xa = 0, ya = 0;
        // TODO test with 5000000 then change to near max INT


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
       for(int i =0; i < 73; i++){
           Photon ray = new Photon(x + sprite.SIZE/2, y+sprite.SIZE/2, i*Math.PI/72,16.0,160, null, level);
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
           if (level.collision(ray.x,ray.y,ray.xCompVector,ray.yCompVector,0)) {
               int xpos = (int) (ray.x + ray.xCompVector) >> LevelManager.tileConverter;
               int ypos = (int) (ray.y + ray.yCompVector) >> LevelManager.tileConverter;
               if (level.getTiles()[xpos + ypos * level.getMapWidth()] < Tile.lightingMapper) {
                   level.getTiles()[xpos + ypos * level.getMapWidth()] += Tile.lightingMapper;
               }
           }
       }
       for(int i =0; i < 73; i++){
           Photon ray = new Photon(x +sprite.SIZE/2, y + sprite.SIZE/2, i*-Math.PI/72,16.0,160, null, level);
           while (!level.collision(ray.x, ray.y, ray.xCompVector, ray.yCompVector, 0) && ray.distance() < ray.range) {
               //move onto the block
               ray.x += ray.xCompVector;
               ray.y += ray.yCompVector;
               int xpos = (int) ray.x >> LevelManager.tileConverter;
               int ypos = (int) ray.y >> LevelManager.tileConverter;
               if (level.getTiles()[xpos + ypos * level.getMapWidth()] < Tile.lightingMapper) {

                   level.getTiles()[xpos + ypos * level.getMapWidth()] += Tile.lightingMapper;
               }
           }
           if (level.collision(ray.x,ray.y,ray.xCompVector,ray.yCompVector,0)) {
               int xpos = (int) (ray.x + ray.xCompVector) >> LevelManager.tileConverter;
               int ypos = (int) (ray.y + ray.yCompVector) >> LevelManager.tileConverter;
               if (level.getTiles()[xpos + ypos * level.getMapWidth()] < Tile.lightingMapper) {
                   level.getTiles()[xpos + ypos * level.getMapWidth()] += Tile.lightingMapper;
               }
           }
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
        Projectile p = new KnifeProj(this.x, this.y, dir, 16, 600, Sprite.boom, level);
        level.addEntity(p);
        fireRate = Projectile.ROF;

    }

}
