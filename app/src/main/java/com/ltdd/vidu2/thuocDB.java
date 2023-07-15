package com.ltdd.vidu2;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class thuocDB extends SQLiteOpenHelper {
    public static final String DB_NAME="thuoc.db";
    public static final int DB_VERSION=1;
    //dinh nghia ten bang, ten cac cot
    public static final String TB_NAME="tbl_thuoc";
    public static final String ID="id";
    public static final String TEN="ten";
    public static final String SOLUONG="soluong";
    public static final String HSD="hsd";
    public static final String NSX="nsx";

    Context context;
    public thuocDB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //viet cau lenh tao bang
        String sql = "CREATE TABLE " + TB_NAME + " (" + ID + " STRING PRIMARY KEY, " +
                TEN + " TEXT, " + SOLUONG + " INTEGER, " + HSD + " DATETIME, " + NSX + " DATETIME)";
        //goi phuong thuc execSQL de thuc thi
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //xoa bang neu bang da ton tai trong db
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
        //goi lai ham onCreate de nang cap phien ban va tao lai bang
        onCreate(db);
    }

    public void initData(){
        //kiem tra xem database da co du lieu chua
        int count = getDbCount();
        if(count==0){
            //tao du lieu mau
            thongTinThuoc db1 = new thongTinThuoc("1", "THUOC BO", 16, "2025-12-12", "2023-12-12");
            thongTinThuoc db2 = new thongTinThuoc("2", "THUOC HA SOT", 17, "2025-12-12", "2023-12-12");
            //insert du lieu vao database
            if(insThuoc(db1)==-1||insThuoc(db2)==-1)
                Toast.makeText(this.context,"Insert failed! ",
                        Toast.LENGTH_LONG).show();
        }
    }

    public long insThuoc(thongTinThuoc db){
        //tao doi tuong ContentValues de chua gia tri can insert
        ContentValues values = new ContentValues();
        values.put(TEN,db.getTen());
        values.put(ID, db.getId());
        values.put(SOLUONG, db.getsoluong());
        values.put(HSD, db.gethsd());
        values.put(NSX, db.getnsx());
        //thuc hien insert du lieu
        return this.getWritableDatabase().
                insert(TB_NAME,null,values);
    }

    public int udpThuoc(thongTinThuoc db, String id){
        ContentValues values = new ContentValues();
        values.put(TEN,db.getTen());
        values.put(ID, db.getId());
        values.put(SOLUONG, db.getsoluong());
        values.put(HSD, db.gethsd());
        values.put(NSX, db.getnsx());
        String whereArg[] ={id+""};
        return this.getWritableDatabase().update(TB_NAME,values,
                ID+"=?", whereArg);
    }

    public int delThuoc(int id){
        String whereArg[]={id+""};
        return this.getWritableDatabase().delete(TB_NAME,
                ID+"=?", whereArg);
    }

    public int delThuoc(String ten){
        String whereArg[]={ten};
        return this.getWritableDatabase().delete(TB_NAME,
                TEN+"=?", whereArg);
    }
    public List<thongTinThuoc> getAllThuoc(){
        List<thongTinThuoc> kq = new ArrayList<thongTinThuoc>();
        Cursor cursor = this.getReadableDatabase().
                rawQuery("SELECT * FROM " + TB_NAME, null);
        //duyet qua danh sach ban ghi co trong con tro
        if(cursor.getCount()>0){
            if(cursor.moveToFirst()){
                //thuc hien insert du lieu vao list kq
                do{
                    thongTinThuoc db = new thongTinThuoc();
                    db.setId(cursor.getString(0));
                    db.setTen(cursor.getString(1));
                    db.setsoluong(cursor.getInt(2));
                    db.sethsd(cursor.getString(3));
                    db.setnsx(cursor.getString(4));
                    kq.add(db);
                }while(cursor.moveToNext());

            }
        }
        cursor.close();//dong con tro sau khi ket thuc truy van
        return kq;
    }

    public int getDbCount(){
        Cursor cursor = this.getReadableDatabase().
                rawQuery("SELECT * FROM "+ TB_NAME,null);
        return cursor.getCount();
    }
//    public List<thongTinThuoc> searchSanPham(String name) {
//        List<thongTinThuoc> results = new ArrayList<>();
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        String selection = TEN + " LIKE ?";
//        String[] selectionArgs = new String[]{"%" + name + "%"};
//        Cursor cursor = db.query(TB_NAME, null, selection, selectionArgs, null, null, null);
//
//        if (cursor != null && cursor.moveToFirst()) {
//            do {
//                thongTinThuoc thongTinSanPham = new thongTinThuoc();
//                thongTinSanPham.setId(cursor.getString(0));
//                thongTinSanPham.setTen(cursor.getString(1));
//                thongTinSanPham.setsoluong(cursor.getInt(2));
//                thongTinSanPham.setdongia(cursor.getInt(3));
//                thongTinSanPham.setngay(cursor.getString(4));
//                results.add(thongTinSanPham);
//            } while (cursor.moveToNext());
//            cursor.close();
//        }
//
//        return results;
//    }
//

}