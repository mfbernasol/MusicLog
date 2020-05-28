package com.michaelbernasol.musiclog;

public class LogEntry {
    private int id;
    private String songName;
    private String instrumentName;
    private String duration;
    private int date;


    LogEntry(){
        songName = "";
        instrumentName = "";
        duration = "";
        date = 0;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public String toString(){
        return "songName= " + songName + "\n" +
                "instrumentName= " + instrumentName + "\n" +
                "duration= " + duration + "\n" +
                "date= " + date;

    }

}
