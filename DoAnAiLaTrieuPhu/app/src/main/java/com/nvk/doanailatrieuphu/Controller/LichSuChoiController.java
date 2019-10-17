package com.nvk.doanailatrieuphu.Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nvk.doanailatrieuphu.Database.DBHelper;
import com.nvk.doanailatrieuphu.Model.CauHoi;
import com.nvk.doanailatrieuphu.Model.LuotChoi;

import java.util.ArrayList;
import java.util.List;

public class LichSuChoiController {
    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;


    private static final String TABLE_LUOCCHOI = "luotchoi";
    private static final String COLUMN_NGUOI_CHOI_ID= "nguoi_choi_id";
    private static final String COLUMN_SO_CAU = "so_cau";
    private static final String COLUMN_DIEM = "diem";
    private static final String COLUMN_NGAY_GIO = "ngay_gio";

    public LichSuChoiController(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public List<LuotChoi> getAllLuotChoiID(int nguoiChoiID){
        sqLiteDatabase = dbHelper.getReadableDatabase();
        List<LuotChoi> luotChois = new ArrayList<>();
        String sql ="SELECT * FROM "+TABLE_LUOCCHOI + " WHERE "
                + COLUMN_NGUOI_CHOI_ID + " =  " + nguoiChoiID ;
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                int nguoiChoi = cursor.getInt(cursor.getColumnIndex(COLUMN_NGUOI_CHOI_ID));
                int soCau = cursor.getInt(cursor.getColumnIndex(COLUMN_SO_CAU));
                String diem = cursor.getString(cursor.getColumnIndex(COLUMN_DIEM));
                String ngayGio = cursor.getString(cursor.getColumnIndex(COLUMN_NGAY_GIO));

                LuotChoi luotChoi = new LuotChoi(nguoiChoi,soCau,diem,ngayGio);
                luotChois.add(luotChoi);
                cursor.moveToNext();
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return luotChois;
    }


}
