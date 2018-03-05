package com.henryandlincoln.swimmyfishy;

import android.graphics.Canvas;
import android.graphics.Matrix;

public class AnimationManager {

    private Animation animations;
    private int animationIndex;

    public AnimationManager(Animation animations){

        this.animations = animations;
    }

    public void playAction(int index){

        if (!animations.isPlaying()){
            animations.playAnimation();
        }
        else {
            animations.stopAnimation();
        }
        animationIndex = index;
    }

    public void draw(Canvas canvas,Matrix matrix){

        if (animations.isPlaying()){
            animations.draw(canvas,matrix);
        }
    }

    public void update(){

        if (animations.isPlaying()){
            animations.update();
        }
    }

}
