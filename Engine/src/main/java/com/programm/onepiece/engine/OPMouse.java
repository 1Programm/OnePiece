package com.programm.onepiece.engine;

import com.programm.onepiece.engine.loggin.ILogger;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class OPMouse extends MouseAdapter  {

    private final ILogger logger;
    private float x, y;
    private boolean left, mid, right;

    private final List<Consumer<Integer>> mousePressedListeners = new ArrayList<>();
    private final List<Consumer<Integer>> mouseReleasedListeners = new ArrayList<>();
    private final List<Runnable> mouseMovedListeners = new ArrayList<>();
    private final List<Runnable> mouseDraggedListeners = new ArrayList<>();

    public OPMouse(ILogger logger) {
        this.logger = logger;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int btn = e.getButton();
        setButton(btn, true);

        notify(mousePressedListeners, btn);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int btn = e.getButton();
        setButton(btn, false);

        notify(mouseReleasedListeners, btn);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();

        notify(mouseDraggedListeners);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();

        notify(mouseMovedListeners);
    }

    private void notify(List<Runnable> list){
        for(int i=0;i<list.size();i++){
            list.get(i).run();
        }
    }

    private void notify(List<Consumer<Integer>> list, int button){
        for(int i=0;i<list.size();i++){
            list.get(i).accept(button);
        }
    }

    private void setButton(int btn, boolean state){
        if(btn == 1){
            left = state;
        }
        else if(btn == 2){
            mid = state;
        }
        else if(btn == 3){
            right = state;
        }
        else {
            logger.error("Unknown mouse button: [" + btn + "]!");
        }
    }

    public float x() {
        return x;
    }

    public float y() {
        return y;
    }

    public boolean leftPressed() {
        return left;
    }

    public boolean midPressed() {
        return mid;
    }

    public boolean rightPressed() {
        return right;
    }

    public void onMousePressed(Consumer<Integer> callback) {
        this.mousePressedListeners.add(callback);
    }

    public void onMouseReleased(Consumer<Integer> callback) {
        this.mouseReleasedListeners.add(callback);
    }

    public void onMouseMoved(Runnable callback) {
        this.mouseMovedListeners.add(callback);
    }

    public void onMouseDragged(Runnable callback) {
        this.mouseDraggedListeners.add(callback);
    }
}
