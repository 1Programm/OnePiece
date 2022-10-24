package com.programm.onepiece.game.spaceinvaders;

import com.programm.onepiece.engine.OnePieceWindow;

import java.awt.*;

public class Bullet extends GameObject {

    private final float vy;

    private final OnePieceWindow window;

    public Bullet(float x, float y, float vy, OnePieceWindow window) {
        super(x, y, 6, 12, Color.YELLOW);
        this.vy = vy;
        this.window = window;
    }

    @Override
    public void update() {
        if(y < 0 || y >= window.height()) {
            this.dead = true;
        }
        y += vy;
    }
}
