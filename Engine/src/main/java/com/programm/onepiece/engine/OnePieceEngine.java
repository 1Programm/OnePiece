package com.programm.onepiece.engine;

import com.programm.onepiece.engine.loggin.ConsoleLogger;
import com.programm.onepiece.engine.loggin.ILogger;

import java.awt.*;

public abstract class OnePieceEngine {

    private final double fps;
    protected final OnePieceWindow window;
    protected final ILogger logger;

    protected final OPKeyboard keyboard;
    protected final OPMouse mouse;

    private boolean running;
    private boolean printFps;

    public OnePieceEngine(String title, int width, int height, int fps, int logLevel) {
        this.fps = fps;
        this.window = new OnePieceWindow(title, width, height, this);
        this.logger = new ConsoleLogger(logLevel);

        this.keyboard = new OPKeyboard(logger);
        this.mouse = new OPMouse(logger);

        window.init(keyboard, mouse);
    }

    public void start(){
        if(running) return;
        running = true;

        logger.info("Starting Engine!");

        window.show();
        new Thread(this::run).start();
    }

    public void stop(){
        if(!running) return;

        logger.info("Stopping Engine!");
        running = false;
    }

    protected abstract void init();
    protected abstract void update();
    protected abstract void render(Graphics g);

    private void run(){
        init();

        long lastTime = System.nanoTime();
        double ns = fps / 1000000000;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) * ns;
            lastTime = now;

            boolean updated = false;

            while(delta >= 1){
                update();
                if(!running) return;
                if(printFps) updates++;
                delta--;

                updated = true;
            }

            if(updated){
                window.draw();
            }

            if(printFps) {
                frames++;

                if(System.currentTimeMillis() - timer > 1000){
                    timer += 1000;
                    logger.debug("UPDATES: " + frames + " - FPS: " + updates);
                    frames = 0;
                    updates = 0;
                }
            }
        }

        window.close();
    }

    public OnePieceEngine printDebug(boolean debug){
        this.printFps = debug;
        return this;
    }

}
