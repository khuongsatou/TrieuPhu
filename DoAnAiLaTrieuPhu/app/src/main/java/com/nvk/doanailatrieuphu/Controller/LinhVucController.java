package com.nvk.doanailatrieuphu.Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nvk.doanailatrieuphu.Database.DBHelper;
import com.nvk.doanailatrieuphu.Model.LinhVuc;

import java.util.ArrayList;
import java.util.List;

public class LinhVucController {
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private Context context;

    private static final String TABLE_LINHVUC = "LinhVuc";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TEN_LINH_VUC = "ten_linh_vuc";
    private static final String COLUMN_XOA= "xoa";
    private static final String SELECT_ALL_XOA = "SELECT * FROM "+TABLE_LINHVUC+" WHERE "+COLUMN_XOA +" = 0";

    public LinhVucController(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public List<LinhVuc> getLinhVuc(){
        db = dbHelper.getReadableDatabase();
        List<LinhVuc> linhVucs = new ArrayList<>();
        Cursor cursor = db.rawQuery(SELECT_ALL_XOA,null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String tenLinhVuc = cursor.getString(cursor.getColumnIndex(COLUMN_TEN_LINH_VUC));
                boolean xoa = cursor.getInt(cursor.getColumnIndex(COLUMN_XOA)) >0;
                linhVucs.add(new LinhVuc(id,tenLinhVuc,xoa));
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return linhVucs;
    }



}
