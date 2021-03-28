package entity;

import main.GraphicHandler;
import main.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Particle extends Entity {
    public List<Particle> particleList = new ArrayList<Particle>();

    Sprite sprite;

    private double xpos, ypos, xa, ya, yAccel, yVelocity;
    private int time;
    private int life;
    public Particle(int x, int y, int life) {
        this.x = x;
        this.y = y;
        this.xpos = x;
        this.ypos = y;
        this.life = life + random.nextInt(5);
        this.sprite = Sprite.particle;

        this.yVelocity = 1.5;
        this.yAccel = 0.3;

        this.xa = random.nextGaussian();
        // this.ya = Math.abs(random.nextGaussian());
        this.ya = random.nextGaussian();
    }

    public Particle(int x, int y, int life, int amount) {
        this(x, y, life);

        for (int i = 0; i < amount; i++) {
            particleList.add(new Particle(x, y, life));
        }
    }

    @Override
    public void render(GraphicHandler graphic) {
        for(Particle p: particleList){
            graphic.renderParticle((int) p.xpos, (int) p.ypos + (int) p.yVelocity, p.sprite, true);
        }
    }

    @Override
    public void update(){
//        for(Particle p: particleList){
//            if(p.time++ > p.life){
//                Particle
//            }
//            p.xpos+=p.xa;
//            p.ypos+=p.ya;
//        }
        for (int i = 0; i < particleList.size(); i++) {
            Particle p = particleList.get(i);
            p.yAccel+=0.1;
            p.yVelocity +=p.yAccel;
            if (p.time++ > p.life) {
                particleList.remove(i);
            }
            p.xpos += p.xa;
            p.ypos += p.ya;
        }

    }
}
