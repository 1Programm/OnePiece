package com.programm.onepiece.game.spaceinvaders;

import com.programm.onepiece.engine.OnePieceEngine;
import com.programm.onepiece.engine.loggin.ILogger;
import com.programm.onepiece.game.spaceinvaders.object.Enemy;
import com.programm.onepiece.game.spaceinvaders.object.Player;

import java.awt.*;

public class SpaceInvadersGame extends OnePieceEngine {

    public static void main(String[] args) {
        new SpaceInvadersGame("Space Invaders", 600, 500, 30, ILogger.LEVEL_INFO).start();
    }

    private final ObjectHandler objects = new ObjectHandler();

    private SwarmHandler swarmHandler;

    private Player player;

    public SpaceInvadersGame(String title, int width, int height, int fps, int logLevel) {
        super(title, width, height, fps, logLevel);
    }

    @Override
    protected void init() {
        player = new Player(window.width() / 2f, window.height() - 100, keyboard, window, objects);
        objects.add(player);
        swarmHandler = new SwarmHandler(5, 3, window, objects);
        swarmHandler.init();
    }

    @Override
    protected void update() {
        objects.update();
        swarmHandler.update();
    }

    @Override
    protected void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, window.width(), window.height());

        objects.render(g);
        swarmHandler.render(g);
    }
}
