package com.lincolnandhenry.pacman_android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {


    private GameThread gameThread;
    private boolean firstTouch;
    private Paint paint;

    public GameView(Context context) {

        super(context);
        /* Make it so that this view can handle events */
        this.setFocusable(true);
        this.getHolder().addCallback(this);

        /* Stall the game on launch */
        firstTouch = true;

        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(100);
        paint.setStyle(Paint.Style.FILL);

    }

    public void update(){
        //TODO
    }

    @Override
    public void draw(Canvas canvas)  {

        super.draw(canvas);

        canvas.drawText("Same" ,100, 100, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (firstTouch){
                firstTouch = false;
                startThread();
                return true;
            }
            else {
                return true;
            }
        }
        return false;
    }

    private void startThread(){

        gameThread.setRunning(true);
        gameThread.start();
    }

    public void stopThread(){

        if (this.gameThread.isAlive()) {

            this.gameThread.setRunning(false);
            this.gameThread.interrupt();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){

        gameThread = new GameThread(this,holder);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        stopThread();
    }



}