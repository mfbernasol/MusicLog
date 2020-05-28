package com.michaelbernasol.musiclog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PracticeTools extends AppCompatActivity {
    private Button recordAudioPage;
    private Button timerPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_tools);


        recordAudioPage = findViewById(R.id.recordAudio_button);
        timerPage = findViewById(R.id.timer_button);

        recordAudioPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecordAudioActivity();
            }
        });

        timerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimerActivity();
            }
        });

    }


    public void openRecordAudioActivity() {
        Intent intent = new Intent(this, RecordMain.class);
        startActivity(intent);

    }

    public void openTimerActivity(){
        Intent intent = new Intent(this, TimerMain.class);
        startActivity(intent);
    }

}
