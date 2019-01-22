package com.practice.olegtojgildin.roompractice_meet_10.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by olegtojgildin on 21/01/2019.
 */

@Database(entities = {Note.class},version = 2)
public abstract class NotesDatabase extends RoomDatabase {
    public static final String DB_NAME="notes_database.db";
    public static final String NOTES_TABLE="NOTES";
    public static final String TITLE="title";
    public static final String ID="id";
    public static final String TEXT_NOTE="text_note";
    public abstract NoteDAO getNoteDAO();
}
