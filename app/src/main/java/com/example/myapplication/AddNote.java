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

public class AddNote extends AppCompatActivity {

    private EditText noteTitle, noteDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        getSupportActionBar().setTitle("");
        noteDetails = findViewById(R.id.noteDitail);
        noteTitle = findViewById(R.id.noteTitle);

        noteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) getSupportActionBar().setTitle(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return true;
    }

/*    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.save);
        if(noteTitle.getText().length()!=0){
            Note note=new Note(noteTitle.getText().toString(),noteDetails.getText().toString());
            note.setTitle(noteTitle.getText().toString());
            note.setContent(noteDetails.getText().toString());
            NoteDataBase db=new NoteDataBase(this);
            int id=db.editNote(note);
            if(id==note.getID()){
                Toast.makeText(this,"Note Saved",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"Title Can not be Blank",Toast.LENGTH_SHORT).show();
            }
            Intent i=new Intent(getApplicationContext(),Details_Note.class);
            i.putExtra("ID",note.getID());
            startActivity(i);
        }else {
            noteTitle.setError("Title can not be Blank");
        }
        return super.onOptionsItemSelected(item);
    }*/

    /*public void onClickSaveText(MenuItem item){
        Note note=new Note(noteTitle.getText().toString(),noteDetails.getText().toString());
        NoteDataBase db=new NoteDataBase(this);
        db.addNote(note);
        Toast.makeText(this,"has been pres key save",Toast.LENGTH_LONG).show();
        goToMain();
    }*/

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            Note note = new Note(noteTitle.getText().toString(), noteDetails.getText().toString());
            NoteDataBase db = new NoteDataBase(this);
            db.addNote(note);
            Toast.makeText(this, "has been pres key save", Toast.LENGTH_LONG).show();
            goToMain();
        }else if(item.getItemId()==R.id.delete){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToMain() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void onClickDeleteText(MenuItem item) {

        Toast.makeText(this, "has been pres key delete", Toast.LENGTH_LONG).show();
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
