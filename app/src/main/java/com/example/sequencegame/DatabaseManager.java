package com.example.sequencegame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context c) {
        context = c;
    }

    public DatabaseManager open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public long insert(String name, String points) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.COLUMN_NAME, name);
        contentValue.put(DBHelper.COLUMN_POINTS, points);
        return database.insert(DBHelper.TABLE_NAME, null, contentValue);
    }
    public Cursor getTop5Scores() throws SQLException {
        String[] columns = {DBHelper.COLUMN_NAME, DBHelper.COLUMN_POINTS};
        String orderBy = DBHelper.COLUMN_POINTS + " DESC";
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Assuming your table name is stored in DBHelper.TABLE_NAME
        Cursor cursor = db.query(DBHelper.TABLE_NAME, columns, null, null, null, null, orderBy, "5");

        return cursor;
    }

    public List<String> convertCursorToList(Cursor cursor) {
        List<String> topScores = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int nameIndex = cursor.getColumnIndex(DBHelper.COLUMN_NAME);
                int pointsIndex = cursor.getColumnIndex(DBHelper.COLUMN_POINTS);

                // Check if the column exists in the cursor
                if (nameIndex != -1 && pointsIndex != -1) {
                    String name = cursor.getString(nameIndex);
                    String points = cursor.getString(pointsIndex);
                    topScores.add(name + ": " + points);
                }
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        return topScores;
    }
}

