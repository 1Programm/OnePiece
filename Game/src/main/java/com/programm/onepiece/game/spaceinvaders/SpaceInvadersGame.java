package com.programm.onepiece.game.spaceinvaders;

import com.programm.onepiece.engine.OnePieceEngine;
import com.programm.onepiece.engine.loggin.ILogger;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class SpaceInvadersGame extends OnePieceEngine {

    public static void main(String[] args) {
        new SpaceInvadersGame("Space Invaders", 600, 500, 30, ILogger.LEVEL_INFO).start();
    }

    private final List<GameObject> objects = new ArrayList<>();

    private Player player;

    public SpaceInvadersGame(String title, int width, int height, int fps, int logLevel) {
        super(title, width, height, fps, logLevel);
    }

    @Override
    protected void init() {
        player = new Player(window.width() / 2f, window.height() - 100, keyboard, window);
        objects.add(player);
    }

    @Override
    protected void update() {
        if(keyboard.isKeyPressed(KeyEvent.VK_SPACE)) {
            Bullet bullet = new Bullet(player.x, player.y, -4, window);
            objects.add(bullet);
        }


        for (int i = 0; i < objects.size(); i++) {
            if(objects.get(i).dead) {
                objects.remove(i);
                i--;
                continue;
            }
            objects.get(i).update();
        }

    }

    @Override
    protected void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, window.width(), window.height());

        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).render(g);
        }
    }
}
