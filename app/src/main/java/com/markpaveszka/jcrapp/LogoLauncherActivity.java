package com.markpaveszka.jcrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LogoLauncherActivity extends AppCompatActivity {

    private ImageView logoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_launcher);

        logoView = (ImageView) findViewById(R.id.logoIMGV);


        Thread timer = new Thread(){
            public void run(){
                try{sleep(5000);}
                catch (InterruptedException e) {
                    android.util.Log.e("EXCEPTION", e.getMessage());}
                finally {finishActivity();}
            }
        };
        timer.start();
        startFadeInAnimation(logoView);
    }


    private void finishActivity ()
    {
        android.content.Intent toMainactivityIntent = new android.content.Intent(this,
                MainActivity.class);
        startActivity(toMainactivityIntent);

        this.finish();
    }

    private void startFadeInAnimation(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_fade_bottom));

    }
}
