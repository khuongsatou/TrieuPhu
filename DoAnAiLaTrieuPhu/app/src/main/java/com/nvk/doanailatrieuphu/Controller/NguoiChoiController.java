package com.nvk.doanailatrieuphu.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nvk.doanailatrieuphu.Database.DBHelper;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;

import java.util.ArrayList;
import java.util.List;

public class NguoiChoiController {
    private DBHelper db;
    private SQLiteDatabase sqLiteDatabase;

    public static final String TABLE_NGUOICHOI = "NguoiChoi";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TEN_DANG_NHAP = "ten_dang_nhap";
    public static final String COLUMN_MAT_KHAU = "mat_khau";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_CREDIT = "credit";
    public static final String COLUMN_HINH_DAI_DIEN = "hinh_dai_dien";
    public static final String COLUMN_DIEM_CAO_NHAT = "diem_cao_nhat";
    public static final String COLUMN_XOA = "xoa";
    private static final String SELECT_ALL = "SELECT * FROM " + TABLE_NGUOICHOI + " WHERE ";
    private static final String SELECT_ALL_NO_XOA = "SELECT * FROM " + TABLE_NGUOICHOI + " WHERE " + COLUMN_XOA + " = 0 ";


    public NguoiChoiController(Context context) {
        db = new DBHelper(context);
    }

    //Check
    public Boolean getTKTonTai(String tenTaiKhoan) {
        sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL + COLUMN_TEN_DANG_NHAP + " =  '" + tenTaiKhoan + "' " , null);
        Boolean result = false;
        if (cursor.getCount() > 0) {
            result = true;
        }
        cursor.close();
        db.close();
        return result;
    }

    public Boolean checkTKAndEmail(String tenTaiKhoan, String email) {
        sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL + COLUMN_TEN_DANG_NHAP + " =  '" + tenTaiKhoan + "' AND " + COLUMN_EMAIL + " = '" + email + "' ", null);
        Boolean result = false;
        if (cursor.getCount() > 0) {
            result = true;
        }
        cursor.close();
        db.close();
        return result;
    }

    public Boolean checkUser(String tenTaiKhoan, String matKhau) {
        sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_NO_XOA +" AND "
                        + COLUMN_TEN_DANG_NHAP + " =  '" + tenTaiKhoan + "' AND "
                        + COLUMN_MAT_KHAU + " =  '" + matKhau + "' "
                , null);
        boolean result = false;
        if (cursor.getCount() > 0) {
            result = true;
        }
        cursor.close();
        db.close();
        return result;
    }

    //Select
    public String getMatKhau(String tenTaiKhoan, String email) {
        sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_NO_XOA + " AND "
                        + COLUMN_TEN_DANG_NHAP + " =  '" + tenTaiKhoan + "' AND "
                        + COLUMN_EMAIL + " = '" + email + "' "
                , null);
        String matkhau = null;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            matkhau = cursor.getString(cursor.getColumnIndex(COLUMN_MAT_KHAU));
        }
        cursor.close();
        db.close();
        return matkhau;
    }

    public NguoiChoi getTK(String tenTaiKhoan) {
        sqLiteDatabase = db.getReadableDatabase();
        NguoiChoi nguoiChoi = new NguoiChoi();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_NO_XOA + " AND "+ COLUMN_TEN_DANG_NHAP +" = '"+ tenTaiKhoan+"'", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            //lấy từ csdl ra
            int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            String tenDangNhap = cursor.getString(cursor.getColumnIndex(COLUMN_TEN_DANG_NHAP));
            String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
            String matKhau = cursor.getString(cursor.getColumnIndex(COLUMN_MAT_KHAU));
            int credit = cursor.getInt(cursor.getColumnIndex(COLUMN_CREDIT));
            String hinhDaiDien = cursor.getString(cursor.getColumnIndex(COLUMN_HINH_DAI_DIEN));
            int diemCaoNhat = cursor.getInt(cursor.getColumnIndex(COLUMN_DIEM_CAO_NHAT));
            boolean xoa = cursor.getInt(cursor.getColumnIndex(COLUMN_XOA)) > 0;
            //set đối tượng người chơi
            nguoiChoi.setId(id);
            nguoiChoi.setTenDangNhap(tenDangNhap);
            nguoiChoi.setEmail(email);
            nguoiChoi.setMatKhau(matKhau);
            nguoiChoi.setCredit(credit);
            nguoiChoi.setHinhDaiDien(hinhDaiDien);
            nguoiChoi.setDiemCaoNhat(diemCaoNhat);
            nguoiChoi.setXoa(xoa);
        }
        cursor.close();
        db.close();
        return nguoiChoi;
    }

    public NguoiChoi getTKByID(int id) {
        sqLiteDatabase = db.getReadableDatabase();
        NguoiChoi nguoiChoi = new NguoiChoi();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_NO_XOA + " AND "+ COLUMN_ID +" = "+ id, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            //lấy từ csdl ra
            id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            String tenDangNhap = cursor.getString(cursor.getColumnIndex(COLUMN_TEN_DANG_NHAP));
            String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
            String matKhau = cursor.getString(cursor.getColumnIndex(COLUMN_MAT_KHAU));
            int credit = cursor.getInt(cursor.getColumnIndex(COLUMN_CREDIT));
            String hinhDaiDien = cursor.getString(cursor.getColumnIndex(COLUMN_HINH_DAI_DIEN));
            int diemCaoNhat = cursor.getInt(cursor.getColumnIndex(COLUMN_DIEM_CAO_NHAT));
            boolean xoa = cursor.getInt(cursor.getColumnIndex(COLUMN_XOA)) > 0;
            //set đối tượng người chơi
            nguoiChoi.setId(id);
            nguoiChoi.setTenDangNhap(tenDangNhap);
            nguoiChoi.setEmail(email);
            nguoiChoi.setMatKhau(matKhau);
            nguoiChoi.setCredit(credit);
            nguoiChoi.setHinhDaiDien(hinhDaiDien);
            nguoiChoi.setDiemCaoNhat(diemCaoNhat);
            nguoiChoi.setXoa(xoa);
        }
        cursor.close();
        db.close();
        return nguoiChoi;
    }
    //Insert

    public long insertNguoiChoi(NguoiChoi nguoiChoi) {
        sqLiteDatabase = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TEN_DANG_NHAP, nguoiChoi.getTenDangNhap());
        contentValues.put(COLUMN_MAT_KHAU, nguoiChoi.getMatKhau());
        contentValues.put(COLUMN_EMAIL, nguoiChoi.getEmail());
        long result = sqLiteDatabase.insert(TABLE_NGUOICHOI, null, contentValues);
        db.close();
        return result;
    }



    //update
    public Boolean updateNguoiChoi(NguoiChoi nguoiChoi) {
        sqLiteDatabase = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TEN_DANG_NHAP, nguoiChoi.getTenDangNhap());
        contentValues.put(COLUMN_MAT_KHAU, nguoiChoi.getMatKhau());
        contentValues.put(COLUMN_EMAIL, nguoiChoi.getEmail());

        //contentValues.put(COLUMN_HINH_DAI_DIEN,nguoiChoi.getHinhDaiDien());

        long result = sqLiteDatabase.update(TABLE_NGUOICHOI, contentValues, COLUMN_ID + " = ? ", new String[]{nguoiChoi.getId()+""});
        db.close();
        if (result > 0) {
            return true;
        }
        return false;
    }


    //chưa cần dùng đến
    public List<NguoiChoi> getBangXepHang() {
        sqLiteDatabase = db.getReadableDatabase();
        List<NguoiChoi> nguoiChois = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NGUOICHOI + "" +
                " ORDER BY " + COLUMN_DIEM_CAO_NHAT + " DESC ";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String tenDangNhap = cursor.getString(cursor.getColumnIndex(COLUMN_TEN_DANG_NHAP));
                String hinhDaiDien = cursor.getString(cursor.getColumnIndex(COLUMN_HINH_DAI_DIEN));
                int diemCaoNhat = cursor.getInt(cursor.getColumnIndex(COLUMN_DIEM_CAO_NHAT));
                NguoiChoi nguoiChoi = new NguoiChoi();
                nguoiChoi.setTenDangNhap(tenDangNhap);
                nguoiChoi.setHinhDaiDien(hinhDaiDien);
                nguoiChoi.setDiemCaoNhat(diemCaoNhat);
                nguoiChois.add(nguoiChoi);
                cursor.moveToNext();
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return nguoiChois;
    }


    // credit update
    public Boolean updateGoiCredit(NguoiChoi nguoiChoi) {
        sqLiteDatabase = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CREDIT, nguoiChoi.getCredit());
        long result = sqLiteDatabase.update(TABLE_NGUOICHOI, contentValues, COLUMN_ID + " = ? ", new String[]{nguoiChoi.getId() + ""});
        db.close();
        if (result > 0) {
            return true;
        }
        return false;
    }
}
