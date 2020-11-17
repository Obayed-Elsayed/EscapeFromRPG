package main;
import java.util.logging.Logger;

public class Runner implements Runnable {

    private boolean active = false;
    private Thread main;

    private boolean running;
    private MainFrame mainFrame;
    // public static final Logger logger = Logger.getLogger(Runner.class.getName());
    public Runner(){
         this.mainFrame = new MainFrame(960);
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
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
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
            this.mainFrame.canvas.render(this.mainFrame.canvas.map_x_off, this.mainFrame.canvas.map_y_off);
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
        Runner game = new Runner();
        game.start();
    }

}
