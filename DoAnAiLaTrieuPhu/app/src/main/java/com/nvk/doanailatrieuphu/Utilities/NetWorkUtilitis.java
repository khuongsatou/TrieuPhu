package com.nvk.doanailatrieuphu.Utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class NetWorkUtilitis{
    //public static final String BASE = "http://localhost:8000/api/";//gốc
    //public static final String BASE = "http://192.168.43.137:8000/public/api/";//máy thật

    //url
    public static final String BASE = "http://192.168.1.14:8000/public/api/";

    //URI Người chơi
    public static final String URI_DANG_NHAP  = "nguoi_choi/dang_nhap";
    public static final String URI_NGUOI_CHOI_THEM ="nguoi_choi/them" ;
    public static final String URI_MAT_KHAU ="nguoi_choi/mat_khau";
    public static final String URI_NGUOI_CHOI_CAP_NHAT ="nguoi_choi/cap_nhat" ;
    public static final String URI_BANG_XEP_HANG = "nguoi_choi/danh_sach";
    public static final String URI_NGUOI_CHOI_UPDATE_CREDIT = "nguoi_choi/cap_nhat_credit";

    //URI Lượt Chơi
    public static final String URI_LUOT_CHOI = "luot_choi/tim";
    //URI Credit
    public static final String URI_CREDIT_DANH_SACH = "goi_credit/danh_sach";
    //URI Linh Vuc
    public static final String URI_LINH_VUC = "linh_vuc/danh_sach";

    public static boolean checkConnect(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= null;
        if (manager != null){
            networkInfo = manager.getActiveNetworkInfo();
        }
        return (networkInfo!= null && networkInfo.isConnected());
    }


    public static AlertDialog showDialogNetWork(String message, final Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Thông Báo").setMessage(message).setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
                dialog.dismiss();
            }
        });
        return builder.create();
    }

}
