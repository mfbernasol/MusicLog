package com.michaelbernasol.musiclog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Button sessionPagebutton;
    private Button practiceToolsPageButton;
    private Button musicResourcesPageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionPagebutton = findViewById(R.id.session_button);
        sessionPagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSessionMainActivity();
            }
        });

        practiceToolsPageButton = findViewById(R.id.practice_tools_button);
        practiceToolsPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPracticeToolsActivity();
            }
        });

        musicResourcesPageButton = findViewById(R.id.music_resources_button);
        musicResourcesPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMusicResourcesActivity();
            }
        });






    }

    //Date Picker




    //opens Session Log Page
    public void openSessionMainActivity(){
        Intent intent = new Intent(this,SessionMain.class);
        startActivity(intent);
    }

    //opens to Practice Tools Page
    public void openPracticeToolsActivity(){
        Intent intent = new Intent(this,PracticeTools.class);
        startActivity(intent);
    }

    //opens to Music Resources Page
    public void openMusicResourcesActivity(){
        Intent intent = new Intent(this, MusicResources.class);
        startActivity(intent);
    }



}
