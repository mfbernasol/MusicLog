package com.michaelbernasol.musiclog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Arrays;


public class SessionMain extends AppCompatActivity {

    private static final int RC_RATING_ACTIVITY = 100;
    private LogEntryListAdapter adapter;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private static final int SIGN_IN =1;
    private FirebaseDatabase firebaseDatabase;
    private Query query;
    private FirebaseRecyclerAdapter<LogEntry,LogEntryListAdapter.RatingHolder> firebaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_main);


        //Firebase
        FirebaseApp.initializeApp(this);
        firebaseAuth = firebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user == null){
                    startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setIsSmartLockEnabled(true).setAvailableProviders(Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build())).build(),SIGN_IN);
                }
            }
        };

        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        firebaseDatabase = FirebaseDatabase.getInstance();
        query = firebaseDatabase.getReference().child("log_entry");
        FirebaseRecyclerOptions<LogEntry> options = new FirebaseRecyclerOptions.Builder<LogEntry>().setQuery(query,LogEntry.class).build();

        firebaseRecyclerAdapter = new LogEntryListAdapter(options);
        recyclerView.setAdapter(firebaseRecyclerAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SessionMain.this,Session.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        firebaseAuth.addAuthStateListener(authStateListener);
        firebaseRecyclerAdapter.startListening();
    }

    @Override
    protected void onPause() {
        super.onPause();
        firebaseAuth.removeAuthStateListener(authStateListener);
        firebaseRecyclerAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.sign_out:
                AuthUI.getInstance().signOut(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == RC_RATING_ACTIVITY){
            String entry = data.getStringExtra("songName");
            String instrumentEntry = data.getStringExtra("instrumentName");
            String durationEntry = data.getStringExtra("duration");


            String toastString = "Log Entered\n";
            toastString += "Song Name: " + entry + "\n";
            toastString += "Instrument: " + instrumentEntry + "\n";
            toastString += "Duration: " + durationEntry + "\n";

            Log.d("debug",toastString);

            Toast.makeText(getApplicationContext(),toastString,Toast.LENGTH_SHORT).show();

        }

        if(resultCode == RESULT_CANCELED && requestCode == SIGN_IN){
            finish();
        }
    }
}
