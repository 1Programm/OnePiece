package com.programm.onepiece.game.spaceinvaders.object;

import com.programm.onepiece.engine.OPKeyboard;
import com.programm.onepiece.engine.OnePieceWindow;
import com.programm.onepiece.game.spaceinvaders.ObjectHandler;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends GameObject {

    private static final float SPEED = 2.5f;

    private static final int SHOOTING_SPEED = 30;

    private final OPKeyboard keyboard;

    private final OnePieceWindow window;

    private final ObjectHandler objects;

    private int coolDownTimer;


    public Player(float x, float y, OPKeyboard keyboard, OnePieceWindow window, ObjectHandler objects) {
        super(x, y, 40, 40, Color.BLUE);
        this.keyboard = keyboard;
        this.window = window;
        this.objects = objects;
    }

    @Override
    public void update() {
        if(coolDownTimer > 0) {
            coolDownTimer--;
        } else {
            if(keyboard.isKeyPressed(KeyEvent.VK_SPACE)) {
                coolDownTimer = SHOOTING_SPEED;
                Bullet bullet = new Bullet(x, y, -4, window);
                objects.add(bullet);
            }
        }

        if(keyboard.isKeyPressed(KeyEvent.VK_A) && x- SPEED >= 0) {
            x -= SPEED;
        }

        if(keyboard.isKeyPressed(KeyEvent.VK_D) && x + SPEED + width <= window.width()) {
            x += SPEED;
        }
    }
}
