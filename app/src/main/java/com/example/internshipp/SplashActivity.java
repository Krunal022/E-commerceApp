package com.example.internshipp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sp;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView =findViewById(R.id.spash_image);

        sp = getSharedPreferences(ConstantSp.PREF,MODE_PRIVATE);

        AlphaAnimation animation=new AlphaAnimation(0,1);
        animation.setDuration(2000);
        animation.setRepeatCount(2);
        imageView.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    if (sp.getString(ConstantSp.REMEMBER,"").equalsIgnoreCase("")){
                        new CommonMethod(SplashActivity.this,MainActivity.class);
                    }
                    else {
                        new CommonMethod(SplashActivity.this,DashboardActivity.class);
                        }
            }
        },5000);
    }
}






















