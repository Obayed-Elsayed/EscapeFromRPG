package main.graphics;

public class Sprite {
    private int x;
    private int y;
    private SpriteLoader spriteSheet;

    public int SIZE;
    public int xSIZE;
    public int ySIZE;
    public int[] sprite_data;
    public static int zoom=2;
    // Load in sprites here
    // TODO better naming convention for tile sprites

    public static Sprite particle = new Sprite(4,4,0xFFFFFFFF);

    public static Sprite rp_rightidle1 = new Sprite(48, 0, 1, SpriteLoader.mainPlayer);
    public static Sprite rp_rightidle2 = new Sprite(48, 1, 1, SpriteLoader.mainPlayer);
    public static Sprite rp_rightidle3 = new Sprite(48, 2, 1, SpriteLoader.mainPlayer);
    public static Sprite rp_rightidle4 = new Sprite(48, 3, 1, SpriteLoader.mainPlayer);

    public static Sprite player_ridle_cycle[] =

            {
                    rp_rightidle1,
                    rp_rightidle2,
                    rp_rightidle3,
                    rp_rightidle4,
            };

    public static Sprite rp_leftidle1 = new Sprite(48, 0, 2, SpriteLoader.mainPlayer);
    public static Sprite rp_leftidle2 = new Sprite(48, 1, 2, SpriteLoader.mainPlayer);
    public static Sprite rp_leftidle3 = new Sprite(48, 2, 2, SpriteLoader.mainPlayer);
    public static Sprite rp_leftidle4 = new Sprite(48, 3, 2, SpriteLoader.mainPlayer);
    public static Sprite player_lidle_cycle[] =
            {
                    rp_leftidle1,
                    rp_leftidle2,
                    rp_leftidle3,
                    rp_leftidle4,
            };

    public static Sprite rp_rightcycle1 = new Sprite(48, 0, 3, SpriteLoader.mainPlayer);
    public static Sprite rp_rightcycle2 = new Sprite(48, 1, 3, SpriteLoader.mainPlayer);
    public static Sprite rp_rightcycle3 = new Sprite(48, 2, 3, SpriteLoader.mainPlayer);
    public static Sprite rp_rightcycle4 = new Sprite(48, 3, 3, SpriteLoader.mainPlayer);
    public static Sprite rp_rightcycle5 = new Sprite(48, 4, 3, SpriteLoader.mainPlayer);
    public static Sprite rp_rightcycle6 = new Sprite(48, 5, 3, SpriteLoader.mainPlayer);

    public static Sprite player_right_cycle[] =

            {
                    scale(rp_rightcycle1, zoom),
                    scale(rp_rightcycle2, zoom),
                    scale(rp_rightcycle3, zoom),
                    scale(rp_rightcycle4, zoom),
                    scale(rp_rightcycle5, zoom),
                    scale(rp_rightcycle6, zoom)
            };

    public static Sprite rp_leftcycle1 = new Sprite(48, 0, 4, SpriteLoader.mainPlayer);
    public static Sprite rp_leftcycle2 = new Sprite(48, 1, 4, SpriteLoader.mainPlayer);
    public static Sprite rp_leftcycle3 = new Sprite(48, 2, 4, SpriteLoader.mainPlayer);
    public static Sprite rp_leftcycle4 = new Sprite(48, 3, 4, SpriteLoader.mainPlayer);
    public static Sprite rp_leftcycle5 = new Sprite(48, 4, 4, SpriteLoader.mainPlayer);
    public static Sprite rp_leftcycle6 = new Sprite(48, 5, 4, SpriteLoader.mainPlayer);

    public static Sprite player_left_cycle[] =

            {
                    scale(rp_leftcycle1, zoom),
                    scale(rp_leftcycle2, zoom),
                    scale(rp_leftcycle3, zoom),
                    scale(rp_leftcycle4, zoom),
                    scale(rp_leftcycle5, zoom),
                    scale(rp_leftcycle6, zoom)
            };

    public static Sprite rp_shootLeft1 = new Sprite(48, 0, 8, SpriteLoader.mainPlayer);
    public static Sprite rp_shootLeft2 = new Sprite(48, 1, 8, SpriteLoader.mainPlayer);
    public static Sprite rp_shootLeft3 = new Sprite(48, 2, 8, SpriteLoader.mainPlayer);
    public static Sprite rp_shootLeft4 = new Sprite(48, 3, 8, SpriteLoader.mainPlayer);
    public static Sprite rp_shootLeft5 = new Sprite(48, 4, 8, SpriteLoader.mainPlayer);
    public static Sprite rp_shootLeft6 = new Sprite(48, 5, 8, SpriteLoader.mainPlayer);

    public static Sprite player_shootL_cycle[] =

            {
                    scale(rp_shootLeft1, zoom),
                    scale(rp_shootLeft2, zoom),
                    scale(rp_shootLeft3, zoom),
                    scale(rp_shootLeft4, zoom),
                    scale(rp_shootLeft5, zoom),
                    scale(rp_shootLeft6, zoom)
            };

    public static Sprite rp_shootRight1 = new Sprite(48, 0, 7, SpriteLoader.mainPlayer);
    public static Sprite rp_shootRight2 = new Sprite(48, 1, 7, SpriteLoader.mainPlayer);
    public static Sprite rp_shootRight3 = new Sprite(48, 2, 7, SpriteLoader.mainPlayer);
    public static Sprite rp_shootRight4 = new Sprite(48, 3, 7, SpriteLoader.mainPlayer);
    public static Sprite rp_shootRight5 = new Sprite(48, 4, 7, SpriteLoader.mainPlayer);
    public static Sprite rp_shootRight6 = new Sprite(48, 5, 7, SpriteLoader.mainPlayer);

    public static Sprite player_shootR_cycle[] =

            {
                    scale(rp_shootRight1, zoom),
                    scale(rp_shootRight2, zoom),
                    scale(rp_shootRight3, zoom),
                    scale(rp_shootRight4, zoom),
                    scale(rp_shootRight5, zoom),
                    scale(rp_shootRight6, zoom)
            };

    public static Sprite rp_tleftcycle1 = new Sprite(48, 0, 5, SpriteLoader.mainPlayer);
    public static Sprite rp_tleftcycle2 = new Sprite(48, 1, 5, SpriteLoader.mainPlayer);
    public static Sprite rp_tleftcycle3 = new Sprite(48, 2, 5, SpriteLoader.mainPlayer);
    public static Sprite rp_tleftcycle4 = new Sprite(48, 3, 5, SpriteLoader.mainPlayer);
    public static Sprite rp_tleftcycle5 = new Sprite(48, 4, 5, SpriteLoader.mainPlayer);
    public static Sprite rp_tleftcycle6 = new Sprite(48, 5, 5, SpriteLoader.mainPlayer);

    public static Sprite player_tleft_cycle[] =

            {
                    scale(rp_tleftcycle1, zoom),
                    scale(rp_tleftcycle2, zoom),
                    scale(rp_tleftcycle3, zoom),
                    scale(rp_tleftcycle4, zoom),
                    scale(rp_tleftcycle5, zoom),
                    scale(rp_tleftcycle6, zoom)
            };

    public static Sprite rp_trightcycle1 = new Sprite(48, 0, 6, SpriteLoader.mainPlayer);
    public static Sprite rp_trightcycle2 = new Sprite(48, 1, 6, SpriteLoader.mainPlayer);
    public static Sprite rp_trightcycle3 = new Sprite(48, 2, 6, SpriteLoader.mainPlayer);
    public static Sprite rp_trightcycle4 = new Sprite(48, 3, 6, SpriteLoader.mainPlayer);
    public static Sprite rp_trightcycle5 = new Sprite(48, 4, 6, SpriteLoader.mainPlayer);
    public static Sprite rp_trightcycle6 = new Sprite(48, 5, 6, SpriteLoader.mainPlayer);

    public static Sprite player_tright_cycle[] =

            {
                    scale(rp_trightcycle1, zoom),
                    scale(rp_trightcycle2, zoom),
                    scale(rp_trightcycle3, zoom),
                    scale(rp_trightcycle4, zoom),
                    scale(rp_trightcycle5, zoom),
                    scale(rp_trightcycle6, zoom)
            };



    public static Sprite knife1 = new Sprite(32, 0, 0, SpriteLoader.knvies);
    public static Sprite knife2 = new Sprite(32, 1, 0, SpriteLoader.knvies);
    public static Sprite knife3 = new Sprite(32, 2, 0, SpriteLoader.knvies);
    public static Sprite knife4 = new Sprite(32, 3, 0, SpriteLoader.knvies);
    public static Sprite knife5 = new Sprite(32, 4, 0, SpriteLoader.knvies);
    public static Sprite knife6 = new Sprite(32, 5, 0, SpriteLoader.knvies);
    public static Sprite knife7 = new Sprite(32, 6, 0, SpriteLoader.knvies);
    public static Sprite knife8 = new Sprite(32, 7, 0, SpriteLoader.knvies);


    public static Sprite knife_projectile[] =

            {
                    Sprite.scale(knife1, zoom), Sprite.scale(knife2, zoom), Sprite.scale(knife3, zoom),
                    Sprite.scale(knife4, zoom), Sprite.scale(knife5, zoom), Sprite.scale(knife6, zoom),
                    Sprite.scale(knife7, zoom), Sprite.scale(knife8, zoom)
            };

    public static Sprite boom = new Sprite(32, 0, 0, SpriteLoader.projectile);

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

    public Sprite(int xsize, int ysize, SpriteLoader spriteSheet) {
        SIZE = -1;
        xSIZE = xsize;
        ySIZE = ysize;
        this.x = 0;
        this.y = 0;
        this.spriteSheet = spriteSheet;
        sprite_data = new int[xSIZE * ySIZE];
        extractSpriteFromSheetNonSquared();
    }

    public Sprite(int size, int[] data) {
        this.sprite_data = data;
        this.SIZE = size;
    }

    public Sprite(int w, int h, int color) {
        this.xSIZE = w;
        this.ySIZE = h;
        this.SIZE = -1;
        sprite_data = new int[w*h];
        setColour2(color);
    }

    public Sprite() {

    }

    private void setColour(int colour) {
        for (int i = 0; i < SIZE * SIZE; i++) {
            sprite_data[i] = colour;
        }
    }

    private void setColour2(int colour) {
        for (int i = 0; i < xSIZE *ySIZE; i++) {
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

    private void extractSpriteFromSheetNonSquared() {

        for (int y = 0; y < ySIZE; y++) {
            for (int x = 0; x < xSIZE; x++) {
                sprite_data[x + y * xSIZE] = this.spriteSheet.image_data[(x + this.x) + (y + this.y) * this.spriteSheet.xSIZE];
            }
        }
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
        if(scale == 1){
            return new Sprite(sprite.SIZE, sprite.sprite_data);
        }
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

    public static Sprite litSprite(Sprite sprite){
        int data[] = new int[sprite.SIZE * sprite.SIZE];
        for (int i = 0; i < sprite.SIZE * sprite.SIZE; i++) {
            data[i] = sprite.sprite_data[i] + 0x002F2F2F;
        }
        return new Sprite(sprite.SIZE, data);
    }


}
