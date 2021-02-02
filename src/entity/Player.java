package entity;

import main.GraphicHandler;
import main.graphics.Sprite;
import main.input.InputManager;


public class Player extends Mob {

    private InputManager input;
    public boolean moving = false;
    public int animation_counter;
    public int cycle_counter;

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
                    graphic.renderPlayer(this.x, this.y, Sprite.player_scaled_cycle[this.cycle_counter++]);
                    if (this.cycle_counter > 5) this.cycle_counter = 0;
                }
            }
            graphic.renderPlayer(this.x, this.y, Sprite.player_scaled_cycle[this.cycle_counter]);


        }
        if (this.dir == 3) graphic.renderPlayer(this.x, this.y, Sprite.left_player);
    }

    public void update() {
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
    }


}
