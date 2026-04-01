package com.example.petbreed;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends BaseActivity {

    EditText etUsername, etPassword;
    CardView btnLogin;
    LinearLayout btnSignUp;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        CardView btnExit = findViewById(R.id.btnExitCard);

        db = new DatabaseHelper(this);

        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
        String savedUser = prefs.getString("username", null);

        if (savedUser != null) {
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }

        // 🔹 LOGIN BUTTON
        btnLogin.setOnClickListener(v -> {

            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean isValid = db.loginUser(username, password);

            if (isValid) {
                prefs.edit().putString("username", username).apply();
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
            }
        });

        // 🔹 GO TO REGISTER
        btnSignUp.setOnClickListener(v -> {
            startActivity(new Intent(Login.this, Register.class));
        });

        btnExit.setOnClickListener(v -> {
            showExitDialog();
        });
    }
}