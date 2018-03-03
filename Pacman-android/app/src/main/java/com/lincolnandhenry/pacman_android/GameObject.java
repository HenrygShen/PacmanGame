package com.lincolnandhenry.pacman_android;

import android.graphics.Canvas;
import java.util.ArrayList;

public interface GameObject {

    public ArrayList<Rectangle> getHitBox();

    public void update();
    public void draw(Canvas canvas);

    public int getX();
    public int getY();

}