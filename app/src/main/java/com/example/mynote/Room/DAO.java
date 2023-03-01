package com.example.mynote.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DAO  {

    @Insert
    void insert(Note note);

    @Update
    void updateNote(Note note);

    @Delete
    void delete(Note note);

    @Query("select * from note")
    List<Note> getall();
}
