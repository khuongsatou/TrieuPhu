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

    private static final String TABLE_LUOCCHOI = "luotchoi";
    private static final String COLUMN_ID= "id";
    public static final String COLUMN_NGUOI_CHOI_ID= "nguoi_choi_id";
    private static final String COLUMN_SO_CAU = "so_cau";
    private static final String COLUMN_DIEM = "diem";
    private static final String COLUMN_NGAY_GIO = "ngay_gio";
    private static final String COLUMN_XOA= "xoa";
}
