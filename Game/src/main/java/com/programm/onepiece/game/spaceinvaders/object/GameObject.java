package com.programm.onepiece.game.spaceinvaders.object;

import com.programm.onepiece.game.spaceinvaders.IUpdatable;

import java.awt.*;

public abstract class GameObject implements IUpdatable {

    protected float x;

    protected float y;

    protected float width;

    protected float height;

    protected final Color color;

    public boolean dead = false;

    public GameObject(float x, float y, float width, float height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int)x, (int)y, (int)width, (int)height);
    }
}
