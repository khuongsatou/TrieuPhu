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
    public static final String TABLE_NGUOICHOI = "NguoiChoi";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TEN_DANG_NHAP = "ten_dang_nhap";
    public static final String COLUMN_MAT_KHAU = "mat_khau";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_CREDIT = "credit";
    public static final String COLUMN_HINH_DAI_DIEN = "hinh_dai_dien";
    public static final String COLUMN_DIEM_CAO_NHAT = "diem_cao_nhat";
    public static final String COLUMN_XOA = "xoa";
}
