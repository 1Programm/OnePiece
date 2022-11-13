package com.programm.onepiece.game.spaceinvaders;

import com.programm.onepiece.engine.OnePieceWindow;
import com.programm.onepiece.game.spaceinvaders.object.Bullet;
import com.programm.onepiece.game.spaceinvaders.object.Enemy;
import com.programm.onepiece.game.spaceinvaders.object.GameObject;

import java.awt.*;
import java.util.List;

public class SwarmHandler implements IUpdatable {
    private static final int SWARM_OFFSET = 10;

    private static final float SWARM_X_SPEED = 5;

    private static final float SWARM_Y_SPEED = 10;

    private final OnePieceWindow window;
    private final int width;

    private final int height;
    private final GameObject[][] enemies;

    private final ObjectHandler objectHandler;

    private int direction = 1;


    public SwarmHandler(int width, int height, OnePieceWindow window, ObjectHandler objectHandler) {
        this.width = width;
        this.height = height;
        this.window = window;
        enemies = new GameObject[height][width];
        this.objectHandler = objectHandler;
;
    }

    public void init() {
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                enemies[y][x] = new Enemy(x * (Enemy.WIDTH + SWARM_OFFSET), y * (Enemy.HEIGHT + SWARM_OFFSET));
            }
        }
    }

    private boolean leftCollision() {
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                if(!enemies[y][x].dead && enemies[y][x].x < 0)
                    return true;
            }
        }
        return false;
    }

    private boolean rightCollision() {
        for(int x = width - 1; x >= 0; x--) {
            for(int y = 0; y < height; y++) {
                if(!enemies[y][x].dead && (enemies[y][x].x + enemies[y][x].width) >= window.width())
                    return true;
            }
        }
        return false;
    }

    @Override
    public void update() {
        float vx = 0;
        float vy = 0;

        if(direction == 1 && rightCollision()) {
            direction = -1;
            vy = SWARM_Y_SPEED;
        } else if(direction == -1 && leftCollision()) {
            direction = 1;
            vy = SWARM_Y_SPEED;
        }
        vx = direction * SWARM_X_SPEED;

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                GameObject obj = enemies[y][x];
                if(obj.dead)
                    continue;

                for(int i = 0;  i < objectHandler.size(); i++) {
                    GameObject o2 = objectHandler.get(i);
                    if(o2 instanceof Bullet) {
                        Bullet bullet = (Bullet) o2;
                        if(CollisionUtils.collides(obj, bullet)) {
                            obj.dead = true;
                            bullet.dead = true;
                        }
                    }
                }

                obj.x += vx;
                obj.y += vy;
                obj.update();
            }
        }
    }

    @Override
    public void render(Graphics g) {
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                if(enemies[y][x].dead)
                    continue;

                enemies[y][x].render(g);
            }
        }
    }

}
