package com.drivex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sujeetkumarmehta on 8/30/15.
 */
public class SplashScreen extends AppCompatActivity {

    ImageView carImageView, logoImageView;

    Animation left2MidAnim, fadeIn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_splash);

        carImageView = (ImageView) findViewById(R.id.imageViewCar);
        logoImageView = (ImageView) findViewById(R.id.imageViewLogo);

   //     left2MidAnim = AnimationUtils.loadAnimation(this, R.anim.anim_left_2_mid);
//        fadeIn = AnimationUtils.loadAnimation(this, R.anim.abc_fade_in);
//
//
//        carImageView.startAnimation(left2MidAnim);
//
//        carImageView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                logoImageView.setVisibility(View.VISIBLE);
//                carImageView.setVisibility(View.GONE);
//                logoImageView.startAnimation(fadeIn);
//            }
//        }, 800);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        }, 2000);


    }
}
