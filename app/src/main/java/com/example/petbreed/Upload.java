package com.example.petbreed;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Upload extends BaseActivity {

    ImageView imagePreview;
    Bitmap selectedBitmap;
    Uri imageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        setupBottomNav(R.id.nav_home);
        imagePreview = findViewById(R.id.petImage);
        CardView btnAnalyze = findViewById(R.id.btnAnalyze);
        CardView btnChooseImage = findViewById(R.id.btnChooseImage);
        ImageButton btnLogout = findViewById(R.id.btnLogOut);
        Intent intent = getIntent();
        if (intent.getData() != null) {
            imageUri = intent.getData();

            Glide.with(this)
                    .asBitmap()
                    .load(imageUri)
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                            selectedBitmap = resource;
                            imagePreview.setImageBitmap(resource);
                        }

                        @Override
                        public void onLoadCleared(Drawable placeholder) {}
                    });

        } else if (intent.getExtras() != null) {
            selectedBitmap = (Bitmap) intent.getExtras().get("imageBitmap");
            imagePreview.setImageBitmap(selectedBitmap);
        }
        // ANALYZE BUTTON
        btnAnalyze.setOnClickListener(v -> {

            if (selectedBitmap == null) {
                Toast.makeText(this, "Please select an image first", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                try {
                    TFLiteClassifier classifier = new TFLiteClassifier(this);

                    float[] results = classifier.classify(selectedBitmap);

                    // 🔹 Find top 3 predictions
                    int[] topIndices = new int[3];
                    float[] topValues = new float[3];

                    for (int i = 0; i < results.length; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (results[i] > topValues[j]) {
                                for (int k = 2; k > j; k--) {
                                    topValues[k] = topValues[k - 1];
                                    topIndices[k] = topIndices[k - 1];
                                }
                                topValues[j] = results[i];
                                topIndices[j] = i;
                                break;
                            }
                        }
                    }

                    // 🔹 Labels (IMPORTANT: must match training order)
                    String[] labels = Labels.getLabels(); // we'll define this below

                    String topBreed = labels[topIndices[0]];
                    float topConfidence = topValues[0];
                    boolean isValid = topConfidence >= 0.6;

                    // 🔹 Detect cat/dog
                    boolean isCat = Character.isUpperCase(topBreed.charAt(0));

                    runOnUiThread(() -> {
                        Intent resultIntent = new Intent(Upload.this, Results.class);
                        DatabaseHelper dbHelper = new DatabaseHelper(this);

                        // get logged user
                        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
                        String username = prefs.getString("username", "guest");

                        // current date time
                        String datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
                                .format(new Date());

                        String localPath = saveImageToInternalStorage(selectedBitmap);
                        resultIntent.putExtra("imageUri", localPath);

                        String finalBreed = isValid ? formatLabel(labels[topIndices[0]]) : "Unknown";
                        String finalAnimal = isValid ? (isCat ? "CAT" : "DOG") : "UNKNOWN";
                        int finalConfidence = (int)(topValues[0] * 100);

                        dbHelper.insertHistory(
                                username,
                                localPath,
                                finalAnimal,
                                finalBreed,
                                finalConfidence,
                                datetime
                        );

                        if (topConfidence < 0.6) {
                            resultIntent.putExtra("isValid", false);
                        } else {
                            resultIntent.putExtra("isValid", true);

                            resultIntent.putExtra("breed", formatLabel(labels[topIndices[0]]));
                            resultIntent.putExtra("confidence", (int)(topValues[0] * 100));
                            resultIntent.putExtra("animal", isCat ? "CAT" : "DOG");

                            resultIntent.putExtra("rank1", formatLabel(labels[topIndices[0]]));
                            resultIntent.putExtra("rank2", formatLabel(labels[topIndices[1]]));
                            resultIntent.putExtra("rank3", formatLabel(labels[topIndices[2]]));

                            resultIntent.putExtra("conf1", (int)(topValues[0]*100));
                            resultIntent.putExtra("conf2", (int)(topValues[1]*100));
                            resultIntent.putExtra("conf3", (int)(topValues[2]*100));
                        }

                        startActivity(resultIntent);
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        });
        btnChooseImage.setOnClickListener(v -> {
            startActivity(new Intent(Upload.this, MainActivity.class));
        });
        btnLogout.setOnClickListener(v -> {
            showLogoutDialog();
        });
    }

    // 🔥 Format label nicely
    private String formatLabel(String label) {
        label = label.replace("_", " ");
        String[] words = label.split(" ");
        StringBuilder formatted = new StringBuilder();

        for (String word : words) {
            formatted.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1))
                    .append(" ");
        }

        return formatted.toString().trim();
    }
    private String saveImageToInternalStorage(Bitmap bitmap) {
        try {
            File directory = getFilesDir();
            File file = new File(directory, "img_" + System.currentTimeMillis() + ".jpg");

            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
            fos.close();

            return file.getAbsolutePath(); // ✅ THIS is what we store in DB

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}