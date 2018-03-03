package com.lincolnandhenry.pacman_android;

import android.graphics.Canvas;
import java.util.ArrayList;

public class Ghost implements GameObject {

    ArrayList<Rectangle> hitBoxes;
    private int x;
    private int y;
    private int ghostType;

    public Ghost(int x,int y,int ghostType){

        this.x = x;
        this.y = y;
        this.ghostType = ghostType;

    }

    @Override
    public ArrayList<Rectangle> getHitBox(){

        return hitBoxes;
    }

    @Override
    public void update() {

    }

    public void draw(Canvas canvas) {

    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

}
