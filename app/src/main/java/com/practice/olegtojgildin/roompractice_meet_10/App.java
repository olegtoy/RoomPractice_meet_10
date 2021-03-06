package com.practice.olegtojgildin.roompractice_meet_10;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.practice.olegtojgildin.roompractice_meet_10.data.NotesDatabase;

/**
 * Created by olegtojgildin on 21/01/2019.
 */

public class App extends Application {

    public static App instance;

    private NotesDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, NotesDatabase.class, "database")
                .fallbackToDestructiveMigration()
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public NotesDatabase getDatabase() {
        return database;
    }
}