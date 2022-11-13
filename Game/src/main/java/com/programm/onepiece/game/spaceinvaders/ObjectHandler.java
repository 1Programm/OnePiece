package com.programm.onepiece.game.spaceinvaders;

import com.programm.onepiece.game.spaceinvaders.object.GameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectHandler implements IUpdatable {

    private final List<GameObject> objects = new ArrayList<>();

    @Override
    public void update() {
        for(int i=0;i<objects.size();i++){
            GameObject obj = objects.get(i);
            if(obj.dead){
                objects.remove(i);
                i--;
                continue;
            }

            obj.update();
        }
    }

    @Override
    public void render(Graphics g) {
        for(int i=0;i<objects.size();i++){
            objects.get(i).render(g);
        }
    }

    public void add(GameObject obj){
        this.objects.add(obj);
    }

}
