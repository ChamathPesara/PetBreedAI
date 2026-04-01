package com.example.petbreed;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ProgressBar progressBar = findViewById(R.id.progressBar);
        TextView statusText = findViewById(R.id.statusText);
        ImageView logo = findViewById(R.id.logo);

        // 🔥 Logo animation
        logo.setScaleX(0f);
        logo.setScaleY(0f);
        logo.animate()
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(800)
                .setInterpolator(new android.view.animation.OvershootInterpolator())
                .start();

        // 🔥 Progress animation
        new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                int progress = i;

                runOnUiThread(() -> {
                    progressBar.setProgress(progress);

                    if (progress < 30)
                        statusText.setText("Loading AI Model...");
                    else if (progress < 70)
                        statusText.setText("Analyzing Data...");
                    else
                        statusText.setText("Almost Ready...");
                });

                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            runOnUiThread(() -> {
                startActivity(new Intent(Splash.this, Login.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            });

        }).start();
    }
}