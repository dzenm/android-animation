package com.din.animationdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button alphaAnimaition, rotateAnimation, scaleAnimation, translateAnimation;
    private ImageView imageView;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alphaAnimaition = findViewById(R.id.alpha_animation);
        rotateAnimation = findViewById(R.id.rotate_animation);
        scaleAnimation = findViewById(R.id.scale_animation);
        translateAnimation = findViewById(R.id.translate_animation);
        imageView = findViewById(R.id.image);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.alpha_animation:
                animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
                imageView.startAnimation(animation);
                break;
            case R.id.rotate_animation:
                animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
                imageView.startAnimation(animation);
                break;
            case R.id.scale_animation:
                animation = AnimationUtils.loadAnimation(this, R.anim.scale);
                imageView.startAnimation(animation);
                break;
            case R.id.translate_animation:
                animation = AnimationUtils.loadAnimation(this, R.anim.translate);
                imageView.startAnimation(animation);
                break;
            case R.id.animationSet:
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                alphaAnimation.setDuration(100);
                alphaAnimation.setRepeatCount(10);
                alphaAnimation.setRepeatMode(Animation.REVERSE);
                imageView.startAnimation(alphaAnimation);
                break;
            case R.id.activityTransition:
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                break;
            default:
                break;
        }
    }
}
