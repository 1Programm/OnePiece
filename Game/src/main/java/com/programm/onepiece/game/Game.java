package com.programm.onepiece.game;

import com.programm.onepiece.engine.OnePieceEngine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Game extends OnePieceEngine {

    private float testX = 100, testY = 100;

    public Game(String title, int width, int height, int fps, int logLevel) {
        super(title, width, height, fps, logLevel);
    }

    @Override
    protected void init() {
        mouse.onMousePressed(this::onMousePressed);
        keyboard.onKeyPressed(this::onKeyboardPressed);
    }

    protected void onMousePressed(int button){
        logger.info("Mouse pressed at: (" + mouse.x() + ", " + mouse.y() + ")");

        //Left Click
        if(button == MouseEvent.BUTTON1) {
            testX = mouse.x();
            testY = mouse.y();
        }
    }

    protected void onKeyboardPressed(int key) {
        logger.info(("Key pressed : (" + key + ")"));

        if(key == KeyEvent.VK_W)
            testY -= 20;
        if(key == KeyEvent.VK_S)
            testY += 20;
        if(key == KeyEvent.VK_A)
            testX -= 20;
        if(key == KeyEvent.VK_D)
            testX += 20;
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
        g.setColor(Color.BLUE);
        g.drawRect((int)testX, (int)testY, 100, 100);
    }
}
