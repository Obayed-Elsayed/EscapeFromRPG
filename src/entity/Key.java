package entity;

import java.util.ArrayList;

//Key class
public class Key {
    
    // Creating the keys as simply variables
    public static Key up = new Key();
    public static Key down = new Key();
    public static Key left = new Key();
    public static Key right = new Key();
    public static Key space = new Key();
    public static boolean isAnykeyPressed = false;
    //public static OrderedKeysList orderedKeysPressed = new OrderedKeysList();
    public static ArrayList<Key> orderedKeysPressed = new ArrayList<Key>();

    private Integer Id;
    public boolean isDown;

    /* toggles the keys current state */
    public void toggle() {
        isDown = !isDown;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public Integer getId() {
        return this.Id;
    }

}
