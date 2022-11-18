package com.example.credit__book.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.credit__book.R;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 4000;
    private Animation topAnim, bottomAnim;
    private ImageView splashImg;
    private TextView splashText1, splashText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        splashImg = findViewById(R.id.splashImg);
        splashText1 = findViewById(R.id.splashText1);
        splashText2 = findViewById(R.id.splashText2);

        splashImg.setAnimation(topAnim);
        splashText1.setAnimation(bottomAnim);
        splashText2.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);

    }

}