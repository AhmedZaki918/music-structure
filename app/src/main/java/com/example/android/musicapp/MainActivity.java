package com.example.android.musicapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find id of image.
        ImageView img = findViewById(R.id.StartLibrary_ImageView);
        //Set on click listener on that image.
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to the library activity.
                startActivity(new Intent(MainActivity.this, LibraryActivity.class));
            }
        });
    }
}