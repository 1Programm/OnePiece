package com.programm.onepiece.game.spaceinvaders.object;

import com.programm.onepiece.engine.OnePieceWindow;

import java.awt.*;

public class Enemy extends GameObject {

    public static final float WIDTH = 30, HEIGHT = 30;


    public Enemy(float x, float y) {
        super(x, y, WIDTH, HEIGHT, Color.RED);
    }

    @Override
    public void update() {

    }
}
