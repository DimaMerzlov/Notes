package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class Edit extends AppCompatActivity {


    private EditText noteTitle, noteDetails;
    private NoteDataBase db;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent i = getIntent();
        Long id = i.getLongExtra("ID", 0);
        db = new NoteDataBase(this);
        note = db.getNote(id);

        getSupportActionBar().setTitle(note.getTitle());
        noteDetails = findViewById(R.id.noteDitail);
        noteTitle = findViewById(R.id.noteTitle);
        final String title=note.getTitle();
        String content=note.getContent();

        noteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                getSupportActionBar().setTitle(title);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) getSupportActionBar().setTitle(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        noteTitle.setText(title);
        noteDetails.setText(content);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            note.setTitle(noteTitle.getText().toString());
            note.setContent(noteDetails.getText().toString());
            int id = db.editNote(note);
            note.setTitle(noteTitle.getText().toString());
            note.setContent(noteDetails.getText().toString());
            if (id == note.getID()) {
                Toast.makeText(this, "Note Update", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error Update", Toast.LENGTH_SHORT).show();
            }
            Intent i = new Intent(getApplicationContext(), Details_Note.class);
            i.putExtra("ID", note.getID());
            startActivity(i);

        } else if (item.getItemId() == R.id.delete) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
