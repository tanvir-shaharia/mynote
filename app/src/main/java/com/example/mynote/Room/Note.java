package com.example.mynote.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note {


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Note() {
    }

    public Note(int ID, String title, String description, long date) {
        this.ID = ID;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    @PrimaryKey(autoGenerate = true)
    int ID;

    @ColumnInfo
    String title;

    @ColumnInfo
    String description;

    @ColumnInfo
     long date;
}
