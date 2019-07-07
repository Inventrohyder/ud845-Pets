package com.example.android.pets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.pets.data.PetContract.PetEntry;

public class PetDbHelper extends SQLiteOpenHelper {

    /** Name of the Database file */
    private static final String DATABASE_NAME = "shelter.db";
    /**
     * Increment database version, when the database schema is changed
     */
    private static final int DATABASE_VERSION = 1;

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String NOT_NULL = " NOT NULL";
    private static final String DEFAULT = " DEFAULT";
    private static final String COMMA_SEP = ",";

    public PetDbHelper(Context context) {
        super(
                context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION
        );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /* CREATE TABLE pets
            (
                _id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                breed TEXT,
                gender INTEGER NOT NULL,
                weight INTEGER NOT NULL DEFAULT 0
            );
        */
        String SQL_CREATE_ENTRIES = "CREATE TABLE " + PetEntry.TABLE_NAME
                + "("
                + PetEntry._ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT, "
                + PetEntry.COLUMN_PET_NAME + TEXT_TYPE + NOT_NULL + COMMA_SEP
                + PetEntry.COLUMN_PET_BREED + TEXT_TYPE + COMMA_SEP
                + PetEntry.COLUMN_PET_GENDER + INTEGER_TYPE + NOT_NULL + COMMA_SEP
                + PetEntry.COLUMN_PET_WEIGHT + INTEGER_TYPE + NOT_NULL + DEFAULT + " 0"
                + ");";
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + PetEntry.TABLE_NAME;
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
