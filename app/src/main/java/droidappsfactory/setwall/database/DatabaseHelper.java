package droidappsfactory.setwall.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Rishi on 21-06-2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    private final static String DATABASE_NAME = "SetWall";
    private final static int DATABASE_VERSION = 1;
    int i = 0;
    // Table name
    private final static String TABLE_PICS = "setwalltable";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_IMAGES = "images";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_SETWALL_TABLE = "CREATE TABLE " + TABLE_PICS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_IMAGES + " BLOB " + ")";
        db.execSQL(CREATE_SETWALL_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PICS);

        // Create tables again
        onCreate(db);

    }

    public void addEntry(byte[] image) throws SQLiteException {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_IMAGES, image);
        db.insert(TABLE_PICS, null, cv);
    }

    public ArrayList<byte[]> getAllImages() {
        ArrayList<byte[]> imagelists = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PICS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.d("SQL", "sql for storing player details cursor in value" + cursor);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {


                // Adding contact to list
                byte[] image = cursor.getBlob(1);
                imagelists.add(image);

                //  playersLists.add(listplayersList);
                Log.d("SQL", "sql for storing player details player name in value");
            } while (cursor.moveToNext());
        }

        // return contact list
        return imagelists;


    }
}
