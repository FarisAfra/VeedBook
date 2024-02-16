package com.farisafra.projectuasmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DatabaseProduk extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "DataProduk.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "data_produk";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_JUDUL = "judul_buku";
    private static final String COLUMN_PENULIS = "penulis_buku";
    private static final String COLUMN_DESKRIPSI = "deskripsi_buku";
    private static final String COLUMN_HARGA = "harga_buku";
    private static final String COLUMN_SPEK = "spesifikasi_buku";
    private static final String COLUMN_CATEGORY = "category_buku";

    private static final String CREATE_TABLE_PRODUK = "CREATE TABLE "
            + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_JUDUL + " TEXT, " + COLUMN_PENULIS + " TEXT, "
            + COLUMN_DESKRIPSI + " TEXT, " + COLUMN_HARGA + " TEXT, " + COLUMN_SPEK
            + " TEXT, " + COLUMN_CATEGORY + " TEXT );";

    public DatabaseProduk(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PRODUK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addProduk(String judul, String penulis, String deskripsi,
                          String harga, String spek, String kategori){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_JUDUL, judul);
        cv.put(COLUMN_PENULIS, penulis);
        cv.put(COLUMN_DESKRIPSI, deskripsi);
        cv.put(COLUMN_HARGA, harga);
        cv.put(COLUMN_SPEK, spek);
        cv.put(COLUMN_CATEGORY, kategori);
        long insert = db.insert(TABLE_NAME,null, cv);
        if(insert == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
        return insert;
    }

    public ArrayList<Produk> getAllUsers() {
        ArrayList<Produk> userModelArrayList = new ArrayList<Produk>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Produk pdk = new Produk();
                pdk.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
                pdk.setJudul(c.getString(c.getColumnIndex(COLUMN_JUDUL)));
                pdk.setPenulis(c.getString(c.getColumnIndex(COLUMN_PENULIS)));
                pdk.setHarga(c.getString(c.getColumnIndex(COLUMN_HARGA)));
                pdk.setDeskripsi(c.getString(c.getColumnIndex(COLUMN_DESKRIPSI)));
                pdk.setSpek(c.getString(c.getColumnIndex(COLUMN_SPEK)));
                pdk.setKategori(c.getString(c.getColumnIndex(COLUMN_CATEGORY)));
                // adding to Book list
                userModelArrayList.add(pdk);
            } while (c.moveToNext());
        }
        return userModelArrayList;

    }

        public void deleteUser ( int id){
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                    new String[]{String.valueOf(id)});
        }


    public int updateUser(int _id, String judul, String penulis, String deskripsi,
                          String harga, String spek, String kategori) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_JUDUL, judul);
        values.put(COLUMN_PENULIS, penulis);
        values.put(COLUMN_DESKRIPSI, deskripsi);
        values.put(COLUMN_HARGA, harga);
        values.put(COLUMN_SPEK, spek);
        values.put(COLUMN_CATEGORY, kategori);

        return db.update(TABLE_NAME, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(_id)});

    }


}
