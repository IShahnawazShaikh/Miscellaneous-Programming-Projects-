package com.example.oasisNav;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo=findViewById(R.id.logo);
        Animation myanim= AnimationUtils.loadAnimation(this,R.anim.animation);
        logo.startAnimation(myanim);
        Thread mythread=new Thread(){
            @Override
            public void run() {
                try{
                    sleep(3000);
                    Intent intent=new Intent(getApplicationContext(), com.example.oasisNav.MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch (InterruptedException ae){ae.printStackTrace();}
            }
        };
        mythread.start();


    }
}
