package com.programm.onepiece.game;

import com.programm.onepiece.engine.loggin.ILogger;

public class Main {

    public static void main(String[] args) {
        new Game("One Piece!", 600, 500, 30, ILogger.LEVEL_TRACE)
                .printDebug(false)
                .start();
    }

}
