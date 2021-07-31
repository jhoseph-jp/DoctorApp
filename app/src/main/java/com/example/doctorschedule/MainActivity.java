package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIMER = 2000;

    //variable
    ImageView backgroundImage;
    TextView poweredBy;

    //animation
    Animation side_anim, bottom_anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backgroundImage = findViewById(R.id.backgroundImage);
        poweredBy = findViewById(R.id.poweredBy);

        //animation
        side_anim = AnimationUtils.loadAnimation(this,R.anim.side_anim);
        bottom_anim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        backgroundImage.setAnimation(side_anim);
        poweredBy.setAnimation(bottom_anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), OnBoarding.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIMER);
    }
}