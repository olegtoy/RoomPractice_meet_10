package com.practice.olegtojgildin.roompractice_meet_10;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;

import com.practice.olegtojgildin.roompractice_meet_10.RecyclerViewNotes.ItemOffsetDecoration;
import com.practice.olegtojgildin.roompractice_meet_10.RecyclerViewNotes.MyItemTouchHelper;
import com.practice.olegtojgildin.roompractice_meet_10.RecyclerViewNotes.NotesAdapter;
import com.practice.olegtojgildin.roompractice_meet_10.data.Note;
import com.practice.olegtojgildin.roompractice_meet_10.data.NoteDAO;
import com.practice.olegtojgildin.roompractice_meet_10.data.NotesDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NotesAdapter.OnNoteListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mManager;
    Button createNote;
    Button setSetting;
    List<Note> mNote;
    private MyItemTouchHelper myItemTouchHelper;
    private ItemTouchHelper itemTouchHelper;
    public static final int REQUEST_CODE = 1;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        initListener();
        mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mManager);

        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                NotesDatabase db = App.getInstance().getDatabase();
                NoteDAO noteDAO = db.getNoteDAO();
                mNote=noteDAO.getAllNotes();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                mAdapter = new NotesAdapter(mNote, MainActivity.this, MainActivity.this);
                mRecyclerView.setAdapter(mAdapter);
                myItemTouchHelper = new MyItemTouchHelper((MyItemTouchHelper.ItemTouchHelperAdapter) mAdapter);
                itemTouchHelper = new ItemTouchHelper(myItemTouchHelper);
                itemTouchHelper.attachToRecyclerView(mRecyclerView);
                mRecyclerView.addItemDecoration(new ItemOffsetDecoration(20));
            }
        }.execute();




    }


    public void initview() {
        mRecyclerView = findViewById(R.id.recycler_view);
        createNote = findViewById(R.id.createNote);
        setSetting = findViewById(R.id.setSetting);
    }

    public void initListener() {
        createNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(CreateNoteActivity.newIntent(MainActivity.this), REQUEST_CODE);
            }
        });
        setSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(SettingActivity.newIntent(MainActivity.this));
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                NotesDatabase db = App.getInstance().getDatabase();
                NoteDAO noteDAO = db.getNoteDAO();
                mNote=noteDAO.getAllNotes();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                mAdapter = new NotesAdapter(mNote, MainActivity.this, MainActivity.this);
                mRecyclerView.setAdapter(mAdapter);
            }
        }.execute();
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(this, ReadNoteActivity.class);
        intent.putExtra(NotesDatabase.TITLE, mNote.get(position).getTitle());
        startActivity(intent);
    }
}
