package com.daniel.notes.Models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {


    @PrimaryKey(autoGenerate = true)
    public  int id;


    public  String title;

    public  String description;

    public  String date;

    public Note(String title, String description,String date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }
}
