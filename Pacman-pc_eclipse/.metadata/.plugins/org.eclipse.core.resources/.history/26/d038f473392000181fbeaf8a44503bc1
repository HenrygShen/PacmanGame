package com.henryandlincoln.swimmyfishy;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class Animation {

    private Bitmap[] frames;
    private int frameIndex;
    private boolean isPlaying = false;
    private float frameTime;
    private long lastFrame;

    public Animation(Bitmap[] frames,float animTime){

        this.frames = frames;
        frameIndex = 0;
        frameTime = animTime/frames.length;
        lastFrame = System.currentTimeMillis();

    }

    public boolean isPlaying(){
        return this.isPlaying;
    }

    public void playAnimation(){
        isPlaying = true;
        frameIndex = 0;
        lastFrame = System.currentTimeMillis();
    }

    public void stopAnimation(){
       //isPlaying = false;
    }

    public void update(){
        if (!isPlaying){
            return;
        }
        if  (System.currentTimeMillis() - lastFrame > frameTime * 1000) {
            frameIndex++;
            frameIndex = (frameIndex >= frames.length) ? 0 : frameIndex;
            lastFrame = System.currentTimeMillis();
        }
    }

    public void draw(Canvas canvas, Matrix matrix){
        if (!isPlaying){
            return;
        }
        else {
            canvas.drawBitmap(frames[frameIndex],matrix,null);
        }
    }


}
