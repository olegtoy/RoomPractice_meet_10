package com.practice.olegtojgildin.roompractice_meet_10;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by olegtojgildin on 21/01/2019.
 */

@Dao
public interface NoteDAO {

    @Insert
    long addNote(Note note);

    @Update
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Query("SELECT  * FROM  notes")
    List<Note> getAllNotes();

    @Query("SELECT  * FROM  notes where title=:title")
    Note getNote(String title);


}
