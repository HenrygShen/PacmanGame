package com.lincolnandhenry.pacman_android;


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends Activity{

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
         /* Remove status,title bar and lock screen to portrait */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        gameView = new GameView(this);


        setContentView(gameView);

    }


    @Override
    protected void onPause() {
        super.onPause();
        gameView.stopThread();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        gameView.stopThread();
    }



}