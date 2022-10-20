package com.programm.onepiece.game;

import com.programm.onepiece.engine.OnePieceEngine;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Game extends OnePieceEngine {

    private float testX = 100, testY = 100;

    public Game(String title, int width, int height, int fps, int logLevel) {
        super(title, width, height, fps, logLevel);
    }

    @Override
    protected void init() {
        mouse.onMousePressed(this::onMousePressed);
    }

    protected void onMousePressed(int button){
        logger.info("Mouse pressed at: (" + mouse.x() + ", " + mouse.y() + ")");

        //Left Click
        if(button == MouseEvent.BUTTON1) {
            testX = mouse.x();
            testY = mouse.y();
        }
    }

    @Override
    protected void update() {

    }

    @Override
    protected void render(Graphics g) {
        //Fill Screen White
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, window.width(), window.height());

        //Draw Test Rectangle
        g.setColor(Color.RED);
        g.drawRect((int)testX, (int)testY, 100, 100);
    }
}
