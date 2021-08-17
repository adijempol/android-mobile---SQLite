package com.latihan.biodatamahasiswa.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "db_Mhsbiodata";
    public static final String TABLE_USER = "tb_user";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_NAME = "name";

    public static final String TABLE_MAHASISWA = "tb_mahasiswa";
//    public static final String COL_ID_MHS = "id_mhs";
    public static final String COL_NAMA = "nama";
    public static final String COL_NIM = "nim";
    public static final String COL_JK = "jk";
    public static final String COL_TANGGAL = "tanggal";
    public static final String COL_PRODI = "prodi";

//    public static final String COL_ANAK = "anak";
//    public static final String TABLE_HARGA = "tb_harga";
//    public static final String COL_HARGA = "harga";
//    public static final String COL_HARGA_ANAK = "harga_anak";
//    public static final String COL_HARGA_TOTAL = "harga_total";

    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("PRAGMA foreign_keys=ON");

        db.execSQL("create table " + TABLE_USER + " (" + COL_USERNAME + " TEXT PRIMARY KEY , " + COL_PASSWORD +
                " TEXT, " + COL_NAME + " TEXT)");

        db.execSQL("create table " + TABLE_MAHASISWA + " (" + COL_NAMA + " TEXT PRIMARY KEY, " +
                COL_NIM + " TEXT, " + COL_JK + " TEXT" + ", " + COL_TANGGAL + " TEXT, " + COL_PRODI + " TEXT)");

//        db.execSQL("create table " + TABLE_HARGA + " (" + COL_USERNAME + " TEXT, " + COL_ID_BOOK + " INTEGER, " +
//                COL_HARGA + " TEXT, " + COL_HARGA_TOTAL + " TEXT, FOREIGN KEY(" + COL_USERNAME + ") REFERENCES " + TABLE_USER
//                + ", FOREIGN KEY(" + COL_ID_BOOK + ") REFERENCES " + TABLE_BOOK + ")");

        db.execSQL("insert into " + TABLE_USER + " values ('putu@gmail.com','putu','Putu Gede');");

        db.execSQL("insert into " + TABLE_MAHASISWA + " values ('Renol Dua', '20184200099', 'L', '1990-12-01','Teknik Informatika');");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public boolean Register(String username, String password, String name) throws SQLException {

        @SuppressLint("Recycle") Cursor mCursor = db.rawQuery("INSERT INTO " + TABLE_USER + "(" + COL_USERNAME + ", " + COL_PASSWORD + ", " + COL_NAME + ") VALUES (?,?,?)", new String[]{username, password, name});
        if (mCursor != null) {
            return mCursor.getCount() > 0;
        }
        return false;
    }

    public boolean Login(String username, String password) throws SQLException {
        @SuppressLint("Recycle") Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COL_USERNAME + "=? AND " + COL_PASSWORD + "=?", new String[]{username, password});
        if (mCursor != null) {
            return mCursor.getCount() > 0;
        }
        return false;
    }

}