package com.programm.onepiece.game.spaceinvaders;

import com.programm.onepiece.engine.OPKeyboard;
import com.programm.onepiece.engine.OnePieceWindow;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends GameObject {

    private static final float SPEED = 2.5f;

    private final OPKeyboard keyboard;

    private final OnePieceWindow window;


    public Player(float x, float y, OPKeyboard keyboard, OnePieceWindow window) {
        super(x, y, 40, 40, Color.BLUE);
        this.keyboard = keyboard;
        this.window = window;
    }

    @Override
    public void update() {
        if(keyboard.isKeyPressed(KeyEvent.VK_A) && x- SPEED >= 0) {
            x -= SPEED;
        }

        if(keyboard.isKeyPressed(KeyEvent.VK_D) && x + SPEED + width <= window.width()) {
            x += SPEED;
        }
    }
}
