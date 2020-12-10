package entity;

import main.GraphicHandler;
import main.input.InputManager;
import main.input.Key;

import java.awt.event.*;
import java.util.HashMap;

public class Player extends Mob {

    private InputManager input;

    public Player(InputManager input) {
        this.input = input;
    }

    public Player(int x, int y, InputManager input) {
        this.x = x;
        this.y = y;
        this.input = input;
    }

    public Player() {

    }

    public void render(GraphicHandler graphic) {

    }

    public void update() {
        int xa = 0, ya = 0;
        if (this.input.up) ya--;
        if (this.input.down) ya++;
        if (this.input.left) xa--;
        if (this.input.right) xa++;

        if (xa != 0 || ya != 0) move(xa, ya);
    }


}
