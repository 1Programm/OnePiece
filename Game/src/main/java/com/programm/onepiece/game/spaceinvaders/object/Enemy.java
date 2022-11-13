package com.programm.onepiece.game.spaceinvaders.object;

import com.programm.onepiece.engine.OnePieceWindow;

import java.awt.*;

public class Enemy extends GameObject {

    public static final float WIDTH = 30, HEIGHT = 30;
    private static final float SPEED = 5;

    private final OnePieceWindow window;

    private float vx, vy;

    public Enemy(float x, float y, OnePieceWindow window) {
        super(x, y, WIDTH, HEIGHT, Color.RED);
        this.window = window;
        vx = SPEED;
    }

    @Override
    public void update() {
        if(x + vx < 0){
            vx = SPEED;
            y += 10;
        }
        else if(x + width + vx >= window.width()){
            vx = -SPEED;
            y += 10;
        }

        x += vx;
        y += vy;
    }
}
