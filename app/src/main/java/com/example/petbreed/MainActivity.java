package com.example.petbreed;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import java.io.File;

public class MainActivity extends BaseActivity {
    private static final int CAMERA_REQUEST = 100;
    private static final int GALLERY_REQUEST = 200;
    CardView btnTakePhoto, btnUploadGallery;
    ImageView latestImage;
    TextView latestBreed;
    CardView cardLatestScan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ImageButton btnLogout = findViewById(R.id.btnLogOut);
        btnTakePhoto = findViewById(R.id.btnTakePhoto);
        btnUploadGallery = findViewById(R.id.btnUploadGallery);
        btnTakePhoto.setOnClickListener(v -> {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        });
        btnUploadGallery.setOnClickListener(v -> {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, GALLERY_REQUEST);
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 1);
        }
        latestImage = findViewById(R.id.latestImage);
        latestBreed = findViewById(R.id.latestBreed);
        cardLatestScan = findViewById(R.id.cardLatestScan);
        cardLatestScan.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, HistoryActivity.class));
        });
        setupBottomNav(R.id.nav_home);
        btnLogout.setOnClickListener(v -> {
            showLogoutDialog();
        });
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {

            if (requestCode == CAMERA_REQUEST) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");

                Intent intent = new Intent(MainActivity.this, Upload.class);
                intent.putExtra("imageBitmap", photo);
                startActivity(intent);
            }

            if (requestCode == GALLERY_REQUEST) {
                Uri imageUri = data.getData();

                Intent intent = new Intent(MainActivity.this, Upload.class);
                intent.setData(imageUri);
                startActivity(intent);
            }
        }
    }
    protected void onResume() {
        super.onResume();
        loadLatestScan(); // put logic inside a method
    }
    private void loadLatestScan() {
        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
        String username = prefs.getString("username", null);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor cursor = dbHelper.getLatestScan(username);

        if (cursor != null && cursor.moveToFirst()) {

            String imageUri = cursor.getString(cursor.getColumnIndexOrThrow("image_uri"));
            String breed = cursor.getString(cursor.getColumnIndexOrThrow("breed"));

            if (breed.equals("Unknown")) {
                latestBreed.setText("Couldn't Identify");
            } else {
                latestBreed.setText(breed);
            }

            Glide.with(this)
                    .load(new File(imageUri))
                    .into(latestImage);

        } else {
            latestBreed.setText("No scans yet");
        }
    }
}