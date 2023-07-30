package com.example.retrofit_loginapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Splash_Screen extends AppCompatActivity {
    ImageButton splash;
    Runnable runnable;
    int login;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        splash = findViewById(R.id.flashimage);

        sharedPreferences = getSharedPreferences("MyDownload",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        login = sharedPreferences.getInt("login",0);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        splash.setAnimation(animation);
        Log.d("RRR", "onCreate: "+animation);

        runnable = new Runnable() {
            @Override
            public void run() {
                if(login==0)
                {
                    Intent intent = new Intent(Splash_Screen.this, Login_Activity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Intent intent = new Intent(Splash_Screen.this, Main_Page.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(runnable,5000);
    }
}