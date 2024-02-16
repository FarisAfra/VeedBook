package com.farisafra.projectuasmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DatabaseAkun extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "DataAkun.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "data_akun";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "nama_akun";
    private static final String COLUMN_USERNAME = "username_akun";
    private static final String COLUMN_PASSWORD = "password_akun";

    private static final String CREATE_TABLE_AKUN = "CREATE TABLE "
            + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT, " + COLUMN_USERNAME + " TEXT, " + COLUMN_PASSWORD + " TEXT );";

    public DatabaseAkun(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_AKUN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addAkun(String nama, String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, nama);
        cv.put(COLUMN_USERNAME, username);
        cv.put(COLUMN_PASSWORD, password);
        long insert = db.insert(TABLE_NAME,null, cv);
        if(insert == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
        return insert;
    }

    public ArrayList<Akun> getAllUsers() {
        ArrayList<Akun> userModelArrayList = new ArrayList<Akun>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Akun akn = new Akun();
                akn.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
                akn.setNama(c.getString(c.getColumnIndex(COLUMN_NAME)));
                akn.setUsername(c.getString(c.getColumnIndex(COLUMN_USERNAME)));
                akn.setPassword(c.getString(c.getColumnIndex(COLUMN_PASSWORD)));
                // adding to Students list
                userModelArrayList.add(akn);
            } while (c.moveToNext());
        }
        return userModelArrayList;
    }

    public void deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public int updateUser(int _id, String nama, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, nama);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);

        return db.update(TABLE_NAME, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(_id)});

    }

    public boolean periksaUser(String username, String password){
        String[] columns = { COLUMN_ID };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COLUMN_USERNAME + "=?" + " AND " + COLUMN_PASSWORD + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true;
        else
            return  false;
    }

}
