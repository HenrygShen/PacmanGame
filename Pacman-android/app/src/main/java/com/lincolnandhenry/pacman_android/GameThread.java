package com.lincolnandhenry.pacman_android;

import android.graphics.Canvas;
import android.view.SurfaceHolder;


public class GameThread extends Thread {

    private boolean running;
    private GameView gameView;
    private SurfaceHolder surfaceHolder;

    public GameThread(GameView gameView, SurfaceHolder surfaceHolder) {

        this.gameView= gameView;
        this.surfaceHolder= surfaceHolder;
    }

    public void firstDraw(){

        Canvas canvas = null;

        try {
            canvas = this.surfaceHolder.lockCanvas();
            this.gameView.update();
            this.gameView.draw(canvas);
        }
        finally {
            if (canvas != null) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    @Override
    public void run()  {

        Canvas canvas;

        while (this.running) {

            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();

                synchronized (surfaceHolder) {

                    /* Update the screen */
                    this.gameView.update();
                    this.gameView.draw(canvas);

                }
            }
            finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    public void setRunning(boolean running)  {

        this.running= running;
    }
}