package com.example.lukeobrien.gui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class BookController extends DatabaseHandler {

    public BookController(Context context) {
        super(context);
    }

    public boolean create(Bookobject bookobject) {

        ContentValues values = new ContentValues();

        values.put("title", bookobject.title);
        values.put("author", bookobject.author);

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("books", null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public List<Bookobject> read() {

        List<Bookobject> recordsList = new ArrayList<Bookobject>();

        String sql = "SELECT * FROM books ORDER BY id DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {

                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String studentFirstname = cursor.getString(cursor.getColumnIndex("title"));
                String studentEmail = cursor.getString(cursor.getColumnIndex("author"));

                Bookobject objectBook = new Bookobject();
                objectBook.id = id;
                objectBook.title = studentFirstname;
                objectBook.author = studentEmail;

                recordsList.add(objectBook);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return recordsList;
    }

    public Bookobject readSingleRecord(int bookId) {

        Bookobject bookobject = null;

        String sql = "SELECT * FROM books WHERE id = " + bookId;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
            String title = cursor.getString(cursor.getColumnIndex("Title"));
            String author = cursor.getString(cursor.getColumnIndex("Author"));

            bookobject = new Bookobject();
            bookobject.id = id;
            bookobject.title = author;
            bookobject.author = title;

        }

        cursor.close();
        db.close();

        return bookobject;

    }


    public boolean update(Bookobject objectbook) {

        ContentValues values = new ContentValues();

        values.put("Title", objectbook.title);
        values.put("Author", objectbook.author);

        String where = "id = ?";

        String[] whereArgs = { Integer.toString(objectbook.id) };

        SQLiteDatabase db = this.getWritableDatabase();

        boolean updateSuccessful = db.update("books", values, where, whereArgs) > 0;
        db.close();

        return updateSuccessful;

    }

    public boolean delete(int id) {
        boolean deleteSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("books", "id ='" + id + "'", null) > 0;
        db.close();

        return deleteSuccessful;

    }
}