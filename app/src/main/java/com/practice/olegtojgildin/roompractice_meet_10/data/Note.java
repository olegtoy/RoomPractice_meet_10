package com.practice.olegtojgildin.roompractice_meet_10.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by olegtojgildin on 21/01/2019.
 */

@Entity(tableName = "notes")
public class Note {
    private String title;
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String text_note;

    public Note() {

    }

    public Note(String title, String text_note) {
        this.title = title;
        this.text_note = text_note;
    }
    public Note(int id,String title, String text_note) {
        this.id=id;
        this.title = title;
        this.text_note = text_note;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText_note() {
        return text_note;
    }

    public void setText_note(String text_note) {
        this.text_note = text_note;
    }
}