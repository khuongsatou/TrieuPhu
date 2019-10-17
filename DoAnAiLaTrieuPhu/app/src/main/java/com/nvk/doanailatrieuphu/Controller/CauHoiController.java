package com.nvk.doanailatrieuphu.Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nvk.doanailatrieuphu.Database.DBHelper;
import com.nvk.doanailatrieuphu.Model.CauHoi;

import java.util.ArrayList;
import java.util.List;

public class CauHoiController {
    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    private static final String TABLE_CAUHOI = "cauhoi";
    private static final String COLUMN_NOI_DUNG = "noi_dung";
    private static final String COLUMN_LINH_VUC_ID = "linh_vuc_id";
    private static final String COLUMN_PHUONG_AN_A = "phuong_an_a";
    private static final String COLUMN_PHUONG_AN_B = "phuong_an_b";
    private static final String COLUMN_PHUONG_AN_C = "phuong_an_c";
    private static final String COLUMN_PHUONG_AN_D = "phuong_an_d";
    private static final String COLUMN_DAP_AN = "dap_an";

    public CauHoiController(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public List<CauHoi> getAllCauHoiByLinhVucID(int linhvuc){
        sqLiteDatabase = dbHelper.getReadableDatabase();
        List<CauHoi> cauHois = new ArrayList<>();
        String sql ="SELECT * FROM "+TABLE_CAUHOI + " WHERE "
                + COLUMN_LINH_VUC_ID + " =  " + linhvuc ;
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                String noiDung = cursor.getString(cursor.getColumnIndex(COLUMN_NOI_DUNG));
                String phuongAnA = cursor.getString(cursor.getColumnIndex(COLUMN_PHUONG_AN_A));
                String phuongAnB = cursor.getString(cursor.getColumnIndex(COLUMN_PHUONG_AN_B));
                String phuongAnC = cursor.getString(cursor.getColumnIndex(COLUMN_PHUONG_AN_C));
                String phuongAnD = cursor.getString(cursor.getColumnIndex(COLUMN_PHUONG_AN_D));
                String dapAn = cursor.getString(cursor.getColumnIndex(COLUMN_DAP_AN));
                int linhVucID = cursor.getInt(cursor.getColumnIndex(COLUMN_LINH_VUC_ID));
                CauHoi cauHoi = new CauHoi();
                cauHoi.setNoiDung(noiDung);
                cauHoi.setPhuongAnA(phuongAnA);
                cauHoi.setPhuongAnB(phuongAnB);
                cauHoi.setPhuongAnC(phuongAnC);
                cauHoi.setPhuongAnD(phuongAnD);
                cauHoi.setDapAn(dapAn);
                cauHoi.setLinhVucId(linhVucID);
                cauHois.add(cauHoi);

                cursor.moveToNext();
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return cauHois;
    }

    public String getDapAn(String noiDung,int linhVuc){
        sqLiteDatabase = dbHelper.getReadableDatabase();
        String dapan = null;
        String sql ="SELECT * FROM "+TABLE_CAUHOI + " WHERE "
                + COLUMN_NOI_DUNG + " =  '" + noiDung + "' AND "
                + COLUMN_LINH_VUC_ID + " =  " + linhVuc;
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            dapan = cursor.getString(cursor.getColumnIndex(COLUMN_DAP_AN));
        }
        cursor.close();
        sqLiteDatabase.close();
        return dapan;
    }



}
