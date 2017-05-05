package com.example.app.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.app.data.Cafe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ярослав on 05.05.2017.
 */
public class DbHandler extends SQLiteOpenHelper implements IDbHandler {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db";
    private static final String TABLE_CAFES = "cafes";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_TYPE = "type";
    private static final String KEY_MIDDLE_COST = "cost";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_X = "x";
    private static final String KEY_Y = "y";


    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CAFES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT," + KEY_TYPE + " TEXT,"
                + KEY_MIDDLE_COST + " REAL," + KEY_ADDRESS + " TEXT,"
                + KEY_X + " REAL," + KEY_Y + " REAL" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CAFES);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void addCafe(Cafe cafe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, cafe.getName());
        values.put(KEY_DESCRIPTION, cafe.getDescription());
        values.put(KEY_TYPE, cafe.getType());
        values.put(KEY_MIDDLE_COST, cafe.getMiddleCost());
        values.put(KEY_ADDRESS, cafe.getAddress());
        values.put(KEY_X, cafe.getX());
        values.put(KEY_Y, cafe.getY());

        db.insert(TABLE_CAFES, null, values);
        db.close();
    }

    @Override
    public List<Cafe> getAllCafes() {
        List<Cafe> contactList = new ArrayList<Cafe>();
        String selectQuery = "SELECT  * FROM " + TABLE_CAFES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Cafe cafe = new Cafe();
                cafe.setId(Integer.parseInt(cursor.getString(0)));
                cafe.setName(cursor.getString(1));
                cafe.setDescription(cursor.getString(2));
                cafe.setType(cursor.getString(3));
                cafe.setMiddleCost(Double.parseDouble(cursor.getString(4)));
                cafe.setAddress(cursor.getString(5));
                cafe.setX(Double.parseDouble(cursor.getString(6)));
                cafe.setY(Double.parseDouble(cursor.getString(7)));
                contactList.add(cafe);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return contactList;
    }
}
