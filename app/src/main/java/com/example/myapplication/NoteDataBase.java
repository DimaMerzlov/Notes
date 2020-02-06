package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NoteDataBase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "notedataBase";
    private static final String DATABASE_TABLE = "noteTabless";


    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";


    NoteDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table nametable(id INT PRIMARY KEY, title TEXT, date TEXT, time TEXt)
        String query = "CREATE TABLE " + DATABASE_TABLE + " ( " + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_TITLE + " TEXT," + KEY_CONTENT + " TEXT" + ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion) return;
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    public long addNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(KEY_TITLE, note.getTitle());
        c.put(KEY_CONTENT, note.getContent());
        long ID = db.insert(DATABASE_TABLE, null, c);

        Log.d("Inserted", "ID->" + ID);

        return ID;
    }

    public void deleteNote(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, KEY_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public Note getNote(long id) {
        //select *from databaseTable where id=id
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE, new String[]{KEY_ID, KEY_TITLE, KEY_CONTENT}, KEY_ID + "=?", new
                String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null) cursor.moveToFirst();

        Note note = new Note(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
        return note;
    }

    public List<Note> getNotes() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<Note> allnotes = new ArrayList<>();
        //select from dataBaseName
        String query = "SELECT * FROM " + DATABASE_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setID(cursor.getLong(0));
                note.setTitle(cursor.getString(1));
                note.setContent(cursor.getString(2));
                allnotes.add(note);

            } while (cursor.moveToNext());

        }
        return allnotes;
    }

    public int editNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        Log.d("Eited", "Eited Title" + note.getTitle() + "\n ID-> " + note.getID());
        c.put(KEY_TITLE, note.getTitle());
        c.put(KEY_CONTENT, note.getContent());
        int update = db.update(DATABASE_TABLE, c, KEY_ID + "=?", new String[]{String.valueOf(note.getID())});
        return update;
    }
}
