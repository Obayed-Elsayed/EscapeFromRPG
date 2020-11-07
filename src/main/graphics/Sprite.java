package main.graphics;

public class Sprite {
    private int x;
    private int y;
    private SpriteLoader spriteSheet;

    public final int SIZE;
    public int[] sprite_data;

    // Load in sprites here
    // TODO better naming convention for tile sprites
    public static Sprite idle_ghost = new Sprite(32, 0, 0, SpriteLoader.ghost);
    public static Sprite voidTile_a = new Sprite(32, 0, 4, SpriteLoader.basicTerrain);
    public static Sprite voidTile_b = new Sprite(32, 1, 4, SpriteLoader.basicTerrain);
    public static Sprite dummyTile = new Sprite(32, 0x000000);
    public static Sprite solidTile = new Sprite(32, 0x103579);

    public Sprite(int size, int x, int y, SpriteLoader spriteSheet) { ;
        this.SIZE = size;
        // Coordinate of sprite in the sprite sheet
        this.x = x * size;
        this.y = y * size;
        this.spriteSheet = spriteSheet;
        // The size will typically be 32*32 per sprite
        this.sprite_data = new int[SIZE * SIZE];
        extractSpriteFromSheet();
    }

    public Sprite(int size, int colour) {
        SIZE = size;
        sprite_data = new int[SIZE * SIZE];
        setColour(colour);
    }

    private void setColour(int colour) {
        for (int i = 0; i < SIZE * SIZE; i++) {
            sprite_data[i] = colour;
        }
    }

    private void extractSpriteFromSheet(){

        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                sprite_data[x + y * SIZE] = this.spriteSheet.image_data[(x + this.x) + (y + this.y) * this.spriteSheet.xSIZE];
            }
        }
    }

    public static void load_sprites(){

    }


}
