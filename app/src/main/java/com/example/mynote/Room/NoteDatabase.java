package com.example.mynote.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class},version = 4)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract DAO  getDao();
    public static NoteDatabase INSTANCE;
    public static NoteDatabase getInstance(Context context){
        if (INSTANCE==null){

            INSTANCE= Room.databaseBuilder(context,NoteDatabase.class,"NoteDatabase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
