package com.programm.onepiece.game.spaceinvaders;

import com.programm.onepiece.game.spaceinvaders.object.GameObject;

public class CollisionUtils {

    public static boolean collides(GameObject o1, GameObject o2) {
        if(o1.x >= o2.x + o2.width) return false;
        if(o1.x + o1.width < o2.x) return false;
        if(o1.y >= o2.y + o2.height) return false;
        if(o1.y + o1.height < o2.y) return false;

        return true;
    }
}
