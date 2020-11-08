package levelMaker;

import java.util.Random;

public class ProceduralLevel extends LevelManager {

    private static final Random random = new Random();
    public ProceduralLevel(int width, int height) {
        super(width, height);
    }


    protected void generateLevel() {
        generateSeed();

        for (int i = 0; i < 75; i++) {
            for (int y = 1; y < this.height - 1; y++) {
                for (int x = 1; x < this.width - 1; x++) {
                    if(countNeighbours(x, y) >= 3){
                        tiles[x + y * width] = 1;
                    }
                }

            }
        }

        // clean up'
        for (int y = 1; y < this.height - 1; y++) {
                for (int x = 1; x < this.width - 1; x++) {
                    if (countNeighbours(x, y) == 4 && tiles[x + y * width] == 0) {
                        tiles[x + y * width] = 1;
                    }
                    if (countNeighbours(x, y) == 0 && tiles[x + y * width] == 1) {
                        tiles[x + y * width] = 0;
                    }
                }
            }

//        for (int y = 0; y < this.height; y++) {
//            for (int x = 0; x < this.width; x++) {
//                // number from [0:1]
//                System.out.print(tiles[x + y * width]);
//                //tiles[x + y * width] = random.nextInt(3);
//            }
//            System.out.println();
//        }
//        System.out.print("");

    }

    private void generateSeed(){
        //Double density = 0.000;
        for (int y=1; y < this.height - 1; y++){
            for (int x=1; x < this.width - 1; x++){
                Float rand = random.nextFloat();
                if(rand < 0.47){
                    tiles[x + y * width] = 1;
                }
            }
        }
    }

    private int countNeighbours(int x, int y) {
        int count = 0;
        if (tiles[x + ((y + 1) * width)] == 1) count++;
        if (tiles[x + ((y - 1) * width)] == 1) count++;
        if (tiles[(x + 1) + (y * width)] == 1) count++;
        if (tiles[(x - 1) + (y * width)] == 1) count++;
        return count;
    }

}
