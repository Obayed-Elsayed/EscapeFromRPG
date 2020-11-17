package main.input;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

//Key class
public class Key {
    
    // Creating the keys as simply variables
    public static Key up = new Key();
    public static Key down = new Key();
    public static Key left = new Key();
    public static Key right = new Key();
    public static Key space = new Key();
    public static HashMap<Integer, Key> keyBindings = new HashMap<>();
    public static boolean isAnykeyPressed = false;

    //public static OrderedKeysList orderedKeysPressed = new OrderedKeysList();
    public static ArrayList<Key> orderedKeysPressed = new ArrayList<Key>();

    private Integer Id;
    public boolean isDown;

    /* toggles the keys current state */
    public static void standard_bindings(){
        bind(KeyEvent.VK_W, Key.up);
        bind(KeyEvent.VK_A, Key.left);
        bind(KeyEvent.VK_S, Key.down);
        bind(KeyEvent.VK_D, Key.right);
        bind(KeyEvent.VK_SPACE, Key.space);
    }
    public void toggle() {
        isDown = !isDown;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public Integer getId() {
        return this.Id;
    }

    public static void bind(Integer keyCode, Key key) {
        keyBindings.put(keyCode, key);
    }
}
