package com.michaelbernasol.musiclog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;


public class MusicResources extends AppCompatActivity {

    private Button theoryBtn;
    private Button glossaryBtn;
    private Button scalesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_resources);

        theoryBtn = findViewById(R.id.theory_button);
        glossaryBtn = findViewById(R.id.glossary_button);
        scalesBtn = findViewById(R.id.scales_button);

        theoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMusicTheorySite();
            }
        });


        glossaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGlossarySite();
            }
        });


        scalesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScalesSite();
            }
        });



    }

    public void openMusicTheorySite(){
        Uri uri = Uri.parse("https://www.teoria.com/");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

    public void openGlossarySite(){
        Uri uri = Uri.parse("https://www.musicnotes.com/now/tips/glossary-of-musical-terms/");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

    public void openScalesSite(){
        Uri uri = Uri.parse("http://www.simplifyingtheory.com/music-scales/");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

}
