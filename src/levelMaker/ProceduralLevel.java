package levelMaker;

import java.util.Random;

public class ProceduralLevel extends LevelManager {

    private Random random = new Random();
    public ProceduralLevel(int width, int height) {
        super(width, height);
    }


    protected void generateLevel() {
        for (int y=0; y < this.height; y++){
            for (int x=0; x < this.width; x++){
                // number from [0:1]
                tiles[x + y * width] = random.nextInt(2);
            }
        }
    }
}
