package com.practice.olegtojgildin.roompractice_meet_10;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Database;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by olegtojgildin on 22/01/2019.
 */

public class ReadNoteActivity extends AppCompatActivity {

    TextView titleNote;
    EditText textNote;
    Button changeNote;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_note);
        initViews();
        initListener();
        initNote();
        initSetting();
    }

    private void initSetting() {
        SettingDataStore settingNote = new SettingDataStore(ReadNoteActivity.this);
        try {
            textNote.setTextSize(settingNote.getTextSize());
            textNote.setTextColor(Color.parseColor(settingNote.getTextColor()));
        } catch (RuntimeException ex) {
            Toast.makeText(this, "Неверные настройки текста", Toast.LENGTH_SHORT).show();
        }

    }

    public void initListener() {
        changeNote.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View view) {
                if (titleNote.getText() != null && textNote.getText() != null) {
                    new AsyncTask<String, Void, Void>() {
                        @Override
                        protected Void doInBackground(String... strings) {
                            NotesDatabase db = App.getInstance().getDatabase();
                            NoteDAO noteDAO = db.getNoteDAO();
                            Note note = noteDAO.getNote(titleNote.getText().toString());
                            note.setText_note(textNote.getText().toString());
                            noteDAO.updateNote(note);
                            return null;
                        }
                    }.execute(textNote.getText().toString());
                }
            }
        });
    }

    public void initViews() {
        textNote = findViewById(R.id.readText);
        titleNote = findViewById(R.id.readTitle);
        changeNote = findViewById(R.id.changeNote);
    }

    public void initNote() {
        NotesDatabase db = App.getInstance().getDatabase();
        NoteDAO noteDAO = db.getNoteDAO();
        Note newNote = noteDAO.getNote(getIntent().getStringExtra(NotesDatabase.TITLE));
        if (newNote.getTitle() != null && newNote.getText_note() != null) {
            titleNote.setText(newNote.getTitle());
            textNote.setText(newNote.getText_note());
        }
    }

    public static final Intent newIntent(Context context) {
        Intent intent = new Intent(context, ReadNoteActivity.class);
        return intent;
    }
}


