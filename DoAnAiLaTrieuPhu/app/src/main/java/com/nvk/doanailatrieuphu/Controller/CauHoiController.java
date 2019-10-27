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
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOI_DUNG = "noi_dung";
    private static final String COLUMN_LINH_VUC_ID = "linh_vuc_id";
    private static final String COLUMN_PHUONG_AN_A = "phuong_an_a";
    private static final String COLUMN_PHUONG_AN_B = "phuong_an_b";
    private static final String COLUMN_PHUONG_AN_C = "phuong_an_c";
    private static final String COLUMN_PHUONG_AN_D = "phuong_an_d";
    private static final String COLUMN_DAP_AN = "dap_an";
    private static final String COLUMN_XOA = "xoa";
    private static final String SELECT_ALL_XOA = "SELECT * FROM "+TABLE_CAUHOI + " WHERE "+COLUMN_XOA+" = 0 ";
    

    public CauHoiController(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public List<CauHoi> getAllCauHoiByLinhVucID(int linhvuc){
        sqLiteDatabase = dbHelper.getReadableDatabase();
        List<CauHoi> cauHois = new ArrayList<>();
        String sql =SELECT_ALL_XOA +" AND "
                + COLUMN_LINH_VUC_ID + " =  " + linhvuc ;
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String noiDung = cursor.getString(cursor.getColumnIndex(COLUMN_NOI_DUNG));
                String phuongAnA = cursor.getString(cursor.getColumnIndex(COLUMN_PHUONG_AN_A));
                String phuongAnB = cursor.getString(cursor.getColumnIndex(COLUMN_PHUONG_AN_B));
                String phuongAnC = cursor.getString(cursor.getColumnIndex(COLUMN_PHUONG_AN_C));
                String phuongAnD = cursor.getString(cursor.getColumnIndex(COLUMN_PHUONG_AN_D));
                String dapAn = cursor.getString(cursor.getColumnIndex(COLUMN_DAP_AN));
                int linhVucID = cursor.getInt(cursor.getColumnIndex(COLUMN_LINH_VUC_ID));
                boolean xoa = cursor.getInt(cursor.getColumnIndex(COLUMN_XOA)) > 0;
                CauHoi cauHoi = new CauHoi();
                cauHoi.setId(id);
                cauHoi.setNoiDung(noiDung);
                cauHoi.setPhuongAnA(phuongAnA);
                cauHoi.setPhuongAnB(phuongAnB);
                cauHoi.setPhuongAnC(phuongAnC);
                cauHoi.setPhuongAnD(phuongAnD);
                cauHoi.setDapAn(dapAn);
                cauHoi.setLinhVucId(linhVucID);
                cauHoi.setXoa(xoa);
                cauHois.add(cauHoi);

                cursor.moveToNext();
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return cauHois;
    }

}
