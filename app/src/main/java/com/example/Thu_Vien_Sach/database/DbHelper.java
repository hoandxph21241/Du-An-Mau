package com.example.Thu_Vien_Sach.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    static final String dbName = "PNLIB";
    static final int dbVersion = 1;
    public DbHelper( Context context) {
        super(context, dbName, null, dbVersion);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableThuThu = "create table ThuThu (" +
                "maTT TEXT PRIMARY KEY," +
                "hoTen TEXT NOT NULL," +
                "matKhau TEXT NOT NULL )";
        db.execSQL(createTableThuThu);
        String createTableThanhVien = "create table ThanhVien (" +
                "maTV INTEGER PRIMARY KEY AUTOINCREMENT," +
                "hoTen TEXT NOT NULL," +
                "namSinh TEXT NOT NULL )";
        db.execSQL(createTableThanhVien);
        String createTableLoaiSach = "create table LoaiSach (" +
                "maLoai INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nhaSX TEXT NOT NULL ,"+
                "tenLoai TEXT NOT NULL )";
        db.execSQL(createTableLoaiSach);
        String createTableSach = "create table Sach (" +
                "maSach INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenSach TEXT NOT NULL ," +
                "giaThue INTEGER NOT NULL ," +
                "maLoai INTEGER REFERENCES LoaiSach(maLoai))";
        db.execSQL(createTableSach);
        String createTablePhieuMuon = "create table PhieuMuon (" +
                "maPM INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maTT TEXT REFERENCES ThuThu(maTT) ," +
                "maTV INTEGER REFERENCES ThanhVien(maTV) ," +
                "maSach INTEGER REFERENCES Sach(maSach) ," +
                "tienThue INTEGER NOT NULL ," +
                "ngay DATE NOT NULL ,"+
                "traSach INTEGER NOT NULL)";
        db.execSQL(createTablePhieuMuon);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ThuThu");
        db.execSQL("DROP TABLE IF EXISTS ThanhVien");
        db.execSQL("DROP TABLE IF EXISTS LoaiSach");
        db.execSQL("DROP TABLE IF EXISTS Sach");
        db.execSQL("DROP TABLE IF EXISTS PhieuMuon");

        onCreate(db);
    }
}
