package com.homelauncher;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView btnApps = findViewById(R.id.btnApps);
        btnApps.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AppListActivity.class));
        });
    }

    @Override
    public void onBackPressed() {

    }
}