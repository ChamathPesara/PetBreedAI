package com.example.petbreed;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryActivity extends BaseActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        setupBottomNav(R.id.nav_history);
        ImageButton btnLogout = findViewById(R.id.btnLogOut);

        recyclerView = findViewById(R.id.recyclerHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
        String username = prefs.getString("username", "guest");

        List<HistoryItem> list = dbHelper.getUserHistory(username);

        recyclerView.setAdapter(new HistoryAdapter(this, list));

        btnLogout.setOnClickListener(v -> {
            showLogoutDialog();
        });
    }
}
