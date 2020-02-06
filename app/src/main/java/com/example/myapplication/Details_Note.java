package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Details_Note extends AppCompatActivity {
    TextView mDetails;
    NoteDataBase db;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details__note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDetails=findViewById(R.id.detailsOfNote);


        Intent i = getIntent();
        Long id = i.getLongExtra("ID", 0);
        //Toast.makeText(this,"ID->"+id,Toast.LENGTH_LONG).show();
        db = new NoteDataBase(this);
        note = db.getNote(id);
        getSupportActionBar().setTitle(note.getTitle());
        mDetails.setText(note.getContent());

        Toast.makeText(this, "Title->" + note.getTitle(), Toast.LENGTH_LONG).show();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Note is deleted",Toast.LENGTH_SHORT).show();
                db.deleteNote(note.getID());
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu,menu);
        return true;
    }
    public void clickOnEditNote(MenuItem item){
        //send user to edit activity
        Toast.makeText(this,"EDIT NOTE",Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,Edit.class);
        i.putExtra("ID",note.getID());
        startActivity(i);
    }

}
