package com.example.internshipp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sp;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView =findViewById(R.id.spash_image);

        sp = getSharedPreferences(ConstantSp.PREF,MODE_PRIVATE);

//        AlphaAnimation animation=new AlphaAnimation(0,1);
//        animation.setDuration(2000);
//        animation.setRepeatCount(2);
//        imageView.startAnimation(animation);

        Glide.with(SplashActivity.this).asGif().load("https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/fa919395-0b28-46d7-b800-27faaedfddef/db0ad1g-459d25ef-162d-4372-9678-abe64af4aec9.gif?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwic3ViIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsImF1ZCI6WyJ1cm46c2VydmljZTpmaWxlLmRvd25sb2FkIl0sIm9iaiI6W1t7InBhdGgiOiIvZi9mYTkxOTM5NS0wYjI4LTQ2ZDctYjgwMC0yN2ZhYWVkZmRkZWYvZGIwYWQxZy00NTlkMjVlZi0xNjJkLTQzNzItOTY3OC1hYmU2NGFmNGFlYzkuZ2lmIn1dXX0.4mzqxHaAPDlPCWEtkP3H5PASnSSdhvpFj6Csh8-Jel0").into(imageView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    if (sp.getString(ConstantSp.REMEMBER,"").equalsIgnoreCase("")){
                        new CommonMethod(SplashActivity.this,LoginWithOtpActivity.class);
                    }
                    else {
                        new CommonMethod(SplashActivity.this,DashboardActivity.class);
                        }
            }
        },4000);
    }
}






















