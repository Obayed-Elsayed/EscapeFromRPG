package main;
import main.graphics.Sprite;
import main.graphics.SpriteLoader;

import java.util.logging.Logger;

public class Runner implements Runnable {

    private boolean active = false;
    private Thread main;

    private boolean running;
    private MainFrame mainFrame;
    // public static final Logger logger = Logger.getLogger(Runner.class.getName());
    public Runner(){
         this.mainFrame = new MainFrame(1920);
    }

    public synchronized void start() {
        running = true;
        main = new Thread (this, "[Display]");
        main.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            // Joins threads or stops them
            main.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        // wait for assets to load
        while(true){
            if(SpriteLoader.verifyAssetsHaveLoaded() && Sprite.verifyAssetsHaveLoaded()) break;
            System.out.println("Not yet loaded");
        }
        System.out.println("Reach");

        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        // 1e9/ 60
        double ns = 1000000000.0/ 60.0;
        double delta = 0;
        int frames = 0;
        int ticks = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            while (delta >= 1){
                this.mainFrame.canvas.tick();
                delta--;
                ticks++;
            }
            this.mainFrame.canvas.render(this.mainFrame.canvas.player.x, this.mainFrame.canvas.player.y);
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer+=1000;
                this.mainFrame.setTitle(Integer.toString(frames));
                ticks = 0;
                frames = 0;
            }
        }
    }

    public static void main(String[] args) {

        Runnable r = new Runnable() {
            public void run() {
                Runner game = new Runner();
                game.start();
            }
        };

        // Invoke gui threads and interactive threads with EDT, the magical event dispatch thread
        javax.swing.SwingUtilities.invokeLater(r);


    }

}
