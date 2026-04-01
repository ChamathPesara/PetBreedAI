package com.example.petbreed;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "PetBreedDB";
    private static final int DB_VERSION = 1;

    private static final String TABLE_USERS = "users";
    public static final String TABLE_HISTORY = "history";

    public static final String COL_ID = "id";
    public static final String COL_USERNAME = "username";
    public static final String COL_IMAGE_URI = "image_uri";
    public static final String COL_ANIMAL = "animal";
    public static final String COL_BREED = "breed";
    public static final String COL_CONFIDENCE = "confidence";
    public static final String COL_DATETIME = "datetime";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_USERS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT UNIQUE, " +
                "email TEXT, " +
                "password TEXT)";
        db.execSQL(CREATE_TABLE);
        db.execSQL("CREATE TABLE " + TABLE_HISTORY + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USERNAME + " TEXT, " +
                COL_IMAGE_URI + " TEXT, " +
                COL_ANIMAL + " TEXT, " +
                COL_BREED + " TEXT, " +
                COL_CONFIDENCE + " INTEGER, " +
                COL_DATETIME + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // 🔹 Insert User
    public boolean registerUser(String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        // 🔐 Hash password
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("email", email);
        values.put("password", hashedPassword);

        long result = db.insert(TABLE_USERS, null, values);
        return result != -1;
    }

    // 🔹 Check Login
    public boolean loginUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT password FROM " + TABLE_USERS + " WHERE username=?",
                new String[]{username}
        );

        if (cursor.moveToFirst()) {
            String storedHash = cursor.getString(0);
            cursor.close();

            // 🔐 Check password
            return BCrypt.checkpw(password, storedHash);
        }

        cursor.close();
        return false;
    }

    // 🔹 Check if username exists
    public boolean isUserExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_USERS + " WHERE username=?",
                new String[]{username}
        );

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
    public void insertHistory(String username, String imageUri, String animal,
                              String breed, int confidence, String datetime) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_USERNAME, username);
        values.put(COL_IMAGE_URI, imageUri);
        values.put(COL_ANIMAL, animal);
        values.put(COL_BREED, breed);
        values.put(COL_CONFIDENCE, confidence);
        values.put(COL_DATETIME, datetime);

        db.insert(TABLE_HISTORY, null, values);
    }
    public List<HistoryItem> getUserHistory(String username) {
        List<HistoryItem> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_HISTORY + " WHERE username=? ORDER BY id DESC",
                new String[]{username});

        if (cursor.moveToFirst()) {
            do {
                list.add(new HistoryItem(
                        cursor.getString(2), // imageUri
                        cursor.getString(3), // animal
                        cursor.getString(4), // breed
                        cursor.getInt(5),    // confidence
                        cursor.getString(6)  // datetime
                ));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return list;
    }
    public Cursor getLatestScan(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM " + TABLE_HISTORY + " WHERE username=? ORDER BY id DESC LIMIT 1",
                new String[]{username}
        );
    }
}
