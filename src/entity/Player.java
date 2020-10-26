package entity;
import java.util.logging.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

public class Player extends Entity implements KeyListener {

	public static boolean TEMP_NAME[] = new boolean[256];
	public HashMap<Integer, Key> keyBindings = new HashMap<>();
	private Integer jumps;
	Vector2 friction;
	public Player() {
		// Bind keys for player
		bind(KeyEvent.VK_W, Key.up);
		bind(KeyEvent.VK_A, Key.left);
		bind(KeyEvent.VK_S, Key.down);
		bind(KeyEvent.VK_D, Key.right);
		bind(KeyEvent.VK_SPACE, Key.space);
		this.acceleration = new Vector2();
		this.position = new Vector2();
		this.velocity = new Vector2();
		this.friction = new Vector2();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Key.isAnykeyPressed = true;
		TEMP_NAME[e.getKeyCode()] = true;
		if (keyBindings.containsKey(e.getKeyCode())) {
			keyBindings.get(e.getKeyCode()).isDown = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (keyBindings.containsKey(e.getKeyCode())) {

			keyBindings.get(e.getKeyCode()).isDown = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Main update function used with the renderer Timer
	 */
	public void update(Integer step) {

		// Gravity
		if(this.getPosition().y <215){

			this.acceleration.y = 0.5;
			this.velocity.y+= this.acceleration.y;

		}else{
			this.jumps=0;
			this.acceleration.y = 0;
			this.velocity.y = 0;
		}
		 // Friction
		if(this.velocity.x!=0){
			this.friction.x = -1*(this.velocity.x/Math.abs(this.velocity.x));
			this.friction.scale(0.125);
			System.out.println(this.friction.toString());
			this.velocity.x+= this.friction.x;
		}

		if (Key.isAnykeyPressed) {
			if (Key.up.isDown) {
				//this.getPosition().add(new Vector2(0, -step));
			}
			if (Key.left.isDown && this.velocity.x > -5) {
				this.acceleration.x = -0.25;
				this.velocity.x += this.acceleration.x;
			}
			if (Key.down.isDown) {
				//this.getPosition().add(new Vector2(0, step));
			}
			if (Key.right.isDown && this.velocity.x< 5) {
				this.acceleration.x = 0.25;
				this.velocity.x += this.acceleration.x;
			}
			if (Key.space.isDown && this.jumps<1) {
				this.jumps ++;
				this.velocity.y =-12;
			}
		}
		System.out.println(Double.toString(this.velocity.x) +" Accel: "+ Double.toString(this.acceleration.x));

		this.position.add(velocity);

	}

	public void bind(Integer keyCode, Key key) {
		keyBindings.put(keyCode, key);
	}

	public void releaseAll() {
		keyBindings.values().forEach(key -> {
			key.isDown = false;
		});
	}

}
