package com.example.petbreed;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;

public class Results extends BaseActivity {

    ImageView resultImage;
    TextView tvBreedName, tvConfidencePercent, tvConfidenceBadge,tvAnimalType;
    TextView tvRank1Name, tvRank2Name, tvRank3Name;
    TextView tvRank1Conf, tvRank2Conf, tvRank3Conf;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        setupBottomNav(R.id.nav_home);
        ImageButton btnLogout = findViewById(R.id.btnLogOut);
        LinearLayout layoutRanking = findViewById(R.id.layoutRanking);
        CardView btnTryAnother = findViewById(R.id.btnTryAnother);
        tvAnimalType = findViewById(R.id.tvAnimalType);
        resultImage = findViewById(R.id.resultPetImage);
        tvBreedName = findViewById(R.id.tvBreedName);
        tvConfidencePercent = findViewById(R.id.tvConfidencePercent);
        tvConfidenceBadge = findViewById(R.id.tvConfidenceBadge);
        progressBar = findViewById(R.id.confidenceProgressBar);
        tvRank1Name = findViewById(R.id.tvRank1Name);
        tvRank2Name = findViewById(R.id.tvRank2Name);
        tvRank3Name = findViewById(R.id.tvRank3Name);
        tvRank1Conf = findViewById(R.id.tvRank1Conf);
        tvRank2Conf = findViewById(R.id.tvRank2Conf);
        tvRank3Conf = findViewById(R.id.tvRank3Conf);
        TextView tvBreedTip = findViewById(R.id.tvBreedTip);
        CardView tipCard = findViewById(R.id.cardBreedTip);

        btnTryAnother.setOnClickListener(v ->{
            startActivity(new Intent(Results.this, MainActivity.class));
        });

        btnLogout.setOnClickListener(v -> {
            showLogoutDialog();
        });

        if (getIntent().getExtras() != null) {
            String path = getIntent().getStringExtra("imageUri");

            if (path == null) {
                Toast.makeText(this, "Image not received", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }

            Glide.with(this)
                    .load(new java.io.File(path))
                    .into(resultImage);
        }

        boolean isValid = getIntent().getBooleanExtra("isValid", true);
        if (!isValid) {
            tvAnimalType.setText("ANIMAL TYPE: UNKNOWN");
            tvBreedName.setText("Couldn't Identify");
            tvBreedName.setTextColor(Color.RED);
            tvConfidencePercent.setText("Low Confidence");
            tvConfidenceBadge.setText("Try another image");
            progressBar.setProgress(0);
            findViewById(R.id.layoutRanking).setVisibility(View.GONE);
            tipCard.setVisibility(View.GONE);
            return;
        }

        String breed = getIntent().getStringExtra("breed");
        int confidence = getIntent().getIntExtra("confidence", 0);
        String animal = getIntent().getStringExtra("animal");

        // set tip
        String tip = BreedTips.getTip(breed);
        tvBreedTip.setText(tip);

        String rank1 = getIntent().getStringExtra("rank1");
        String rank2 = getIntent().getStringExtra("rank2");
        String rank3 = getIntent().getStringExtra("rank3");

        int conf1 = getIntent().getIntExtra("conf1", 0);
        int conf2 = getIntent().getIntExtra("conf2", 0);
        int conf3 = getIntent().getIntExtra("conf3", 0);

        tvAnimalType.setText("ANIMAL TYPE: " + animal);
        tvBreedName.setText(breed);
        tvConfidencePercent.setText(confidence + "%");
        tvConfidenceBadge.setText(confidence + "% Confidence");
        progressBar.setProgress(confidence);

        if (rank1 != null) {
            layoutRanking.setVisibility(View.VISIBLE);
        }

        tvRank1Name.setText(rank1);
        tvRank1Conf.setText(conf1 + "%");

        tvRank2Name.setText(rank2);
        tvRank2Conf.setText(conf2 + "%");

        tvRank3Name.setText(rank3);
        tvRank3Conf.setText(conf3 + "%");

    }
}