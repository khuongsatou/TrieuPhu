package com.nvk.doanailatrieuphu.Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nvk.doanailatrieuphu.Database.DBHelper;
import com.nvk.doanailatrieuphu.Model.LinhVuc;

import java.util.ArrayList;
import java.util.List;

public class LinhVucController {
    private static final String TABLE_LINHVUC = "LinhVuc";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TEN_LINH_VUC = "ten_linh_vuc";
    private static final String COLUMN_XOA= "xoa";
}
