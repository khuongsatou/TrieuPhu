package com.nvk.doanailatrieuphu.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nvk.doanailatrieuphu.Database.DBHelper;
import com.nvk.doanailatrieuphu.Model.CauHoi;
import com.nvk.doanailatrieuphu.Model.GoiCredit;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;

import java.util.ArrayList;
import java.util.List;

public class MuaCreditController {
    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    private static final String TABLE_GOICREDIT= "goicredit";
    private static final String COLUMN_TEN_GOI= "ten_goi";
    private static final String COLUMN_CREDIT = "credit";
    private static final String COLUMN_SO_TIEN = "so_tien";


    public MuaCreditController(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public List<GoiCredit> getAllGoiCredit(){
        sqLiteDatabase = dbHelper.getReadableDatabase();
        List<GoiCredit> goiCredits = new ArrayList<>();
        String sql ="SELECT * FROM "+TABLE_GOICREDIT;
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                String ten_goi = cursor.getString(cursor.getColumnIndex(COLUMN_TEN_GOI));
                int credit = cursor.getInt(cursor.getColumnIndex(COLUMN_CREDIT));
                int soTien = cursor.getInt(cursor.getColumnIndex(COLUMN_SO_TIEN));

                GoiCredit goiCredit = new GoiCredit(ten_goi,credit,soTien);
                goiCredits.add(goiCredit);

                cursor.moveToNext();
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return goiCredits;
    }



}
