package com.example.app.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.app.data.Cafe;
import com.example.app.data.Coordinates;

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
    public void add(Cafe cafe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, cafe.getName());
        values.put(KEY_DESCRIPTION, cafe.getDescription());
        values.put(KEY_TYPE, cafe.getType());
        values.put(KEY_MIDDLE_COST, cafe.getMiddleCost());
        values.put(KEY_ADDRESS, cafe.getAddress());
        values.put(KEY_X, cafe.getCoordinates().getX());
        values.put(KEY_Y, cafe.getCoordinates().getY());
        db.insert(TABLE_CAFES, null, values);
        db.close();
    }

    @Override
    public List<Cafe> getAll() {
        List<Cafe> contactList = new ArrayList<Cafe>();
        String selectQuery = "SELECT * FROM " + TABLE_CAFES;

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
                cafe.setCoordinates(new Coordinates(Double.parseDouble(cursor.getString(6)),
                                                    Double.parseDouble(cursor.getString(7))));
                contactList.add(cafe);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return contactList;
    }

    @Override
    public List<Cafe> getAllWithCheckFromRemoteDb(){
        List<Cafe> actualCafes = this.getAll();
        List<Cafe> cafesFromRemote;
        List<Cafe> cafesToAdd;
        String test = "";

        try {
            IRemoteDbHandler dbHandler = new RemoteDbHandler();

            cafesFromRemote = dbHandler.getAll();
            cafesToAdd = new ArrayList<>();

            for(Cafe cafe: cafesFromRemote) {
                if(!actualCafes.contains(cafe)) {
                    cafesToAdd.add(cafe);
                }
            }

            for(Cafe cafe: cafesToAdd) {
                add(cafe);
            }
        } catch (Exception e) {
            Log.println(Log.ERROR, "Internet", "Can't connect to remote database server");
            return getAll();
        }

        return getAll();
    }

    public void delete(Cafe cafe) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CAFES, KEY_ID + " = ?", new String[] { String.valueOf(cafe.getId()) });
        db.close();
    }

}
