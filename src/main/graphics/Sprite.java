package main.graphics;

public class Sprite {
    private int x;
    private int y;
    private SpriteLoader spriteSheet;

    public int SIZE;
    public int[] sprite_data;

    // Load in sprites here
    // TODO better naming convention for tile sprites
    public static Sprite right_player1 = new Sprite(32, 0, 0, SpriteLoader.player_dummy2);
    public static Sprite right_player2 = new Sprite(32, 1, 0, SpriteLoader.player_dummy2);
    public static Sprite right_player3 = new Sprite(32, 2, 0, SpriteLoader.player_dummy2);
    public static Sprite right_player4 = new Sprite(32, 3, 0, SpriteLoader.player_dummy2);
    public static Sprite right_player5 = new Sprite(32, 4, 0, SpriteLoader.player_dummy2);
    public static Sprite right_player6 = new Sprite(32, 0, 1, SpriteLoader.player_dummy2);

    public static Sprite scaled_player1 = Sprite.scale(right_player1,3);
    public static Sprite scaled_player2 = Sprite.scale(right_player2,3);
    public static Sprite scaled_player3 = Sprite.scale(right_player3,3);
    public static Sprite scaled_player4 = Sprite.scale(right_player4,3);
    public static Sprite scaled_player5 = Sprite.scale(right_player5,3);
    public static Sprite scaled_player6 = Sprite.scale(right_player6,3);

    public static Sprite player_right_cycle[] =

            {
                    right_player1, right_player2, right_player3, right_player4, right_player4, right_player5, right_player6
            };

    public static Sprite player_scaled_cycle[] =

            {
                    scaled_player1, scaled_player2, scaled_player3, scaled_player4, scaled_player4, scaled_player5, scaled_player6
            };

    public static Sprite front_player = new Sprite(32, 1, 3, SpriteLoader.player_dummy2);
    public static Sprite left_player = new Sprite(right_player1.SIZE, Sprite.flip_sprite(right_player1.SIZE, right_player1));
    public static Sprite idle_player_01 = new Sprite(32, 0, 0, SpriteLoader.ghost);

    // Map assets
    public static Sprite floor = new Sprite(32, 0, 0, SpriteLoader.mapTerrain);
    public static Sprite leftSideWall = new Sprite(32, 1, 4, SpriteLoader.mapTerrain);
    public static Sprite rightSideWall = new Sprite(32, 1, 2, SpriteLoader.mapTerrain);
    public static Sprite upperWall = new Sprite(32, 1, 1, SpriteLoader.mapTerrain);
    public static Sprite upperWall2 = new Sprite(32, 2, 0, SpriteLoader.mapTerrain);
    public static Sprite lowerWall = new Sprite(32, 1, 3, SpriteLoader.mapTerrain);
    public static Sprite tlcorner = new Sprite(32, 1, 5, SpriteLoader.mapTerrain);
    public static Sprite trcorner = new Sprite(32, 2, 5, SpriteLoader.mapTerrain);
    public static Sprite blcorner = new Sprite(32, 1, 6, SpriteLoader.mapTerrain);
    public static Sprite brcorner = new Sprite(32, 2, 6, SpriteLoader.mapTerrain);

    public static Sprite voidTile_a = new Sprite(32, 0, 4, SpriteLoader.basicTerrain);
    public static Sprite voidTile_b = new Sprite(32, 1, 4, SpriteLoader.basicTerrain);
    public static Sprite dummyTile = new Sprite(32, 0x000000);
    public static Sprite solidTile = new Sprite(32, 0x103579);

    public Sprite(int size, int x, int y, SpriteLoader spriteSheet) {

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

    public Sprite(int size, int[] data) {
        this.sprite_data = data;
        this.SIZE = size;
    }

    public Sprite() {

    }

    private void setColour(int colour) {
        for (int i = 0; i < SIZE * SIZE; i++) {
            sprite_data[i] = colour;
        }
    }

    public static void copy(Sprite original, Sprite copy) {
        copy.SIZE = original.SIZE;
        copy.sprite_data = original.sprite_data;
    }

    private void extractSpriteFromSheet() {

        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                sprite_data[x + y * SIZE] = this.spriteSheet.image_data[(x + this.x) + (y + this.y) * this.spriteSheet.xSIZE];
            }
        }
    }

    public static boolean verifyAssetsHaveLoaded() {
        return idle_player_01 != null && dummyTile != null && solidTile != null && voidTile_a != null && voidTile_b != null;
    }

    public static void load_sprites() {

    }

    public static int[] flip_sprite(int sprite_size, Sprite sprite) {

        int[] flipped_sprite_data = new int[sprite_size * sprite_size];

        //sprite size = 32
        for (int i = 0; i < sprite_size; i++) {
            for (int j = sprite_size - 1; j >= 0; j--) {
                flipped_sprite_data[(sprite_size - (j + 1)) + (i * sprite_size)] = sprite.sprite_data[j + (i * sprite_size)];
            }
        }
        return flipped_sprite_data;


    }
    public static Sprite scale(Sprite sprite, int scale) {
        int scaled_size = scale * sprite.SIZE;
        int[] scaled_data = new int[scaled_size * scaled_size];

        for (int y_og = 0; y_og < sprite.SIZE; y_og++) {
            for (int x_og = 0; x_og < sprite.SIZE; x_og++) {
                for(int j = 0; j < scale ; j++){
                    for(int i = 0; i < scale ; i++){
//                        int debug1 =(x_og * scale +i);
//                        int debug2 =((y_og * scaled_size * scale  +j*scaled_size));
                        scaled_data[(x_og * scale +i) + (y_og * scaled_size * scale  +j*scaled_size)] = sprite.sprite_data[x_og + y_og * sprite.SIZE];
                    }
                }
            }
        }
        return new Sprite(scaled_size, scaled_data);

    }


}
