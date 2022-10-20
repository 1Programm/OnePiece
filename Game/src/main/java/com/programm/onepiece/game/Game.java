package com.programm.onepiece.game;

import com.programm.onepiece.engine.OnePieceEngine;

import java.awt.*;

public class Game extends OnePieceEngine {

    public Game(String title, int width, int height, int fps, int logLevel) {
        super(title, width, height, fps, logLevel);
    }

    @Override
    protected void init() {
        mouse.onMousePressed(this::onMousePressed);
    }

    protected void onMousePressed(int button){
        logger.info("Mouse pressed at: (" + mouse.x() + ", " + mouse.y() + ")");
    }

    @Override
    protected void update() {

    }

    @Override
    protected void render(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(100, 100, 100, 100);
    }
}
