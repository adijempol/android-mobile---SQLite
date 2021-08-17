package com.latihan.biodatamahasiswa.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private static final String DATABASE_NAME = "biodataMhs.db";
    public static final String TABLE_USER = "user";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_NAME = "nama";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlUser = "create table user(username text primary key, nama text null, password text null);";
        Log.d("Data", "onCreate: " + sqlUser);
        db.execSQL(sqlUser);

        String sql = "create table mahasiswa(nama text primary key, nim text null, jk text null, tanggal text null, prodi text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);

        sqlUser = "INSERT INTO user (username, nama, password) VALUES ('putu@gmail.com', 'Putu gede', 'putu');";
        db.execSQL(sqlUser);

        sql = "INSERT INTO mahasiswa (nama, nim, jk, tanggal, prodi) VALUES ('Putu Gede', '2018430001', 'laki-laki', '1998-02-1','Informatika');";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db , int arg1, int arg2) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public boolean Register(String username, String password, String nama) throws SQLException {

        //statement untuk mengirimkan data ke db
        @SuppressLint("Recycle") Cursor mCursor = db.rawQuery("INSERT INTO " + TABLE_USER + "(" + COL_USERNAME + ", " + COL_PASSWORD + ", " + COL_NAME + ") VALUES (?,?,?)", new String[]{username, password, nama});
        if (mCursor != null) {
            return mCursor.getCount() > 0;
        }
        return false;
    }

    public boolean Login(String username, String password) throws SQLException {
        //statement untuk mengambul data dari db
        @SuppressLint("Recycle") Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COL_USERNAME + "=? AND " + COL_PASSWORD + "=?", new String[]{username, password});
        if (mCursor != null) {
            return mCursor.getCount() > 0;
        }
        return false;
    }

}
