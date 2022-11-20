package com.example.dogapp2.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dogapp2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FavActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);

        String name = getIntent().getStringExtra("DOG_DESCRIPTION");
        int image = getIntent().getIntExtra("IMAGE", 0);

        TextView nameTextView = findViewById(R.id.textAbout);
        ImageView imageView = findViewById(R.id.imageAbout);

        nameTextView.setText(name);
        imageView.setImageResource(image);

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.favourites);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.favourites:
                        return true;

                    case R.id.breeds:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.training:
                        startActivity(new Intent(getApplicationContext(), TrainingActivity.class));
                        overridePendingTransition(0,0);
                }

                return false;
            }
        });
    }

}