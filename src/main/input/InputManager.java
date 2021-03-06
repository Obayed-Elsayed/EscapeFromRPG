package main.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class Handles all inputs.
 * This will also replace the Player class's abillity handle Keyboard input
 */
public class InputManager implements KeyListener {

    private boolean keys[] = new boolean [256];
    public boolean up, down, left, right;

    public void update(){
        up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
