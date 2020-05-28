package com.michaelbernasol.musiclog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class LogEntryListAdapter extends FirebaseRecyclerAdapter<LogEntry,LogEntryListAdapter.RatingHolder> {

    class RatingHolder extends RecyclerView.ViewHolder{
        private final TextView songNameTextView;
        private final TextView instrumentTextView;
        private final TextView durationTextView;


        RatingHolder(View itemView){
            super(itemView);
            songNameTextView = itemView.findViewById(R.id.songNameTextView);
            instrumentTextView = itemView.findViewById(R.id.instrumentTextView);
            durationTextView = itemView.findViewById(R.id.durationTextView);



        }
    }

    private Context context;

    public LogEntryListAdapter(@NonNull FirebaseRecyclerOptions<LogEntry> options){
        super(options);
    }



    @NonNull
    @Override
    public RatingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_row,parent,false);

        return new LogEntryListAdapter.RatingHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull RatingHolder holder, int position, @NonNull LogEntry model) {
        holder.songNameTextView.setText(model.getSongName());
        holder.instrumentTextView.setText(model.getInstrumentName());
        holder.durationTextView.setText(model.getDuration());
    }

}
