package com.michaelbernasol.musiclog;

        import androidx.appcompat.app.AppCompatActivity;

        import android.app.DatePickerDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.TextView;

        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

        import org.w3c.dom.Text;
        import java.util.Calendar;
//Detail Activity
public class Session extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private final String TAG = Session.class.getSimpleName();
    private LogEntry logEntry;
    private EditText songNameEditText;
    private EditText instrumentEditText;
    private EditText durationEditText;
    private Button submitButton;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private TextView dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        dateText = findViewById(R.id.dateText);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("log_entry");


        logEntry = new LogEntry();

        songNameEditText = findViewById(R.id.songNameEditText);
        instrumentEditText = findViewById(R.id.instrumentEditText);
        durationEditText = findViewById(R.id.durationEditText);

        songNameEditText.addTextChangedListener(new NameTextListener(songNameEditText));
        instrumentEditText.addTextChangedListener(new NameTextListener(instrumentEditText));
        durationEditText.addTextChangedListener(new NameTextListener(durationEditText));


        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new SubmitClickListener());


        //Date Picker Button
        findViewById(R.id.showDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

    }

    private class NameTextListener implements TextWatcher {
        private View editText;

        public NameTextListener(View v){
            editText = v;
        }


        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(editText == songNameEditText) {
                logEntry.setSongName(charSequence.toString());
                android.util.Log.d(TAG, "updated song name to " + logEntry.getSongName());
            }else if(editText == instrumentEditText) {
                logEntry.setInstrumentName(charSequence.toString());
                android.util.Log.d(TAG, "updated instrument name to " + logEntry.getInstrumentName());
            }else if(editText == durationEditText){
                logEntry.setDuration(charSequence.toString());
                android.util.Log.d(TAG,"updated duration to " + logEntry.getDuration());
            }
        }



        @Override
        public void afterTextChanged(Editable editable) {

        }


    }

    private class SongTypeSelectedListener implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String song = (String)adapterView.getItemAtPosition(i);
            if (i!=0) {
                logEntry.setSongName(song);
                Log.d(TAG, "Song Name " + song);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }


    private class SubmitClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            databaseReference.push().setValue(logEntry);
            Intent returnIntent = new Intent();
            returnIntent.putExtra("songName",logEntry.getSongName());
            returnIntent.putExtra("instrumentName",logEntry.getInstrumentName());
            returnIntent.putExtra("duration",logEntry.getDuration());
            setResult(RESULT_OK,returnIntent);
            finish();
        }
    }


    //Date Method
    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    //Date string
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "Date: " + month + "/" + dayOfMonth + "/" + year;
        dateText.setText(date);
    }


}
