package entity;

import levelMaker.LevelManager;
import main.GraphicHandler;
import main.graphics.Sprite;
import main.input.InputManager;
import main.input.MouseInputManager;


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

    protected void shoot(int x, int y, double dir) {
//               System.out.println(Math.toDegrees(dir));
        Projectile p = new KnifeProj(this.x, this.y, dir, 10, 4000, Sprite.boom, level);
        level.addEntity(p);
        fireRate = Projectile.ROF;

    }

}
