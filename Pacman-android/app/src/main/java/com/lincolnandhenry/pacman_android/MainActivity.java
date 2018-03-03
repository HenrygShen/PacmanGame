package com.lincolnandhenry.pacman_android;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends Activity {

    private static final String PREFS_NAME = "Settings";
    private long lastClickTime = 0;
    private String highScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /* Remove status,title bar and lock screen to portrait */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        configurePlayButton();


    }


    private void configurePlayButton(){

        Button playBtn = (Button) findViewById(R.id.play);

        playBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


                /* Start the game */
                Intent i  = new Intent(MainActivity.this,GameActivity.class);
                startActivity(i);
            }
        });
    }



}