import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferStrategy;

import java.io.Serial;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game extends Canvas implements Runnable{

    @Serial
    private static final long serialVersionUID = -7969492678833627878L;

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;
    private static final Logger logger = Logger.getLogger(Game.class.getName());

    private Random r;

    public Game(){
        new Window(WIDTH, HEIGHT, "Let's build a game", this);
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();  //put the current thread on wait until it is dead
            running = false;
        }catch (Exception e){
            logger.log(Level.SEVERE, "Exception caught", e); // Outputs the message, full stack trace, and exception details
        }
    }

    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >=1)
            {
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println("FPS: "+ frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){

    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        //Background Colour
        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new Game();
    }
}