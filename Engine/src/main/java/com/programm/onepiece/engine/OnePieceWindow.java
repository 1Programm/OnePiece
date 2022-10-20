package com.programm.onepiece.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

public class OnePieceWindow extends WindowAdapter {

    private final JFrame frame;
    private final Canvas canvas;
    private final OnePieceEngine engine;
    private boolean closeRequested;

    public OnePieceWindow(String title, int width, int height, OnePieceEngine engine){
        this.engine = engine;

        this.frame = new JFrame(title);
        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.frame.setResizable(true);
        this.frame.addWindowListener(this);

        Dimension dim = new Dimension(width, height);

        this.canvas = new Canvas();
        this.canvas.setPreferredSize(dim);

        this.frame.add(canvas);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
    }

    public void init(OPKeyboard keyboard, OPMouse mouse) {
        this.canvas.addKeyListener(keyboard);
        this.canvas.addMouseListener(mouse);
        this.canvas.addMouseMotionListener(mouse);
    }

    public void draw(){
        BufferStrategy bs = canvas.getBufferStrategy();

        if(bs == null){
            canvas.createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        engine.render(g);

        g.dispose();
        bs.show();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        this.closeRequested = true;
    }

    public void show(){
        this.frame.setVisible(true);
    }

    public void close(){
        this.frame.dispose();
    }

    public boolean isCloseRequested() {
        return closeRequested;
    }

    public int width() {
        return canvas.getWidth();
    }

    public int height() {
        return canvas.getHeight();
    }

}
