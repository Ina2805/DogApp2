package com.example.dogapp2.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseDogs extends SQLiteOpenHelper {

    private static int DB_VERSION = 1;
    private static String DATABASE_NAME = "Dogs.db";
    private static String TABLE_NAME = "breedsTable";
    public static String KEY_ID = "id";
    public static String ITEM_TITLE = "itemTitle";
    public static String ITEM_IMAGE = "itemImage";
    public static String FAVOURITE_STATUS = "fStatus";
    private static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID
            + " TEXT," + ITEM_TITLE+ " TEXT,"  + ITEM_IMAGE
            + " TEXT," + FAVOURITE_STATUS+" TEXT)";

    public DatabaseDogs(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertEmpty()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        for (int x = 1; x < 1; x++)
        {
            cv.put(KEY_ID, x);
            cv.put(FAVOURITE_STATUS, "0");

            db.insert(TABLE_NAME, null, cv);
        }
    }

    public void insertIntoDatabase(String item_title, int item_image, String id, String fav_status, String description)
    {
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ITEM_TITLE, item_title);
        cv.put(ITEM_IMAGE, item_image);
        cv.put(KEY_ID, id);
        cv.put(FAVOURITE_STATUS, fav_status);
        db.insert(TABLE_NAME, null, cv);
        Log.d("DatabaseDogs Status", item_title + ", favStatus -"+fav_status+" -. " + cv);

    }

    public Cursor read_all_data(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_NAME + " where " + KEY_ID
                +"="+id+"";
        return db.rawQuery(sql, null, null);
    }

    public void remove_fav(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE " + TABLE_NAME + " SET " + FAVOURITE_STATUS+" ='0' WHERE "
                + KEY_ID+"="+id+"";
        db.execSQL(sql);
        Log.d("remove", id.toString());

    }

    public Cursor select_all_fav_list()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE "
                + FAVOURITE_STATUS+" ='1'";
        return db.rawQuery(sql, null, null);
    }

}
