package com.nvk.TrieuPhuMVP.Utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWorkUtilitis {
    public static final String BASE = "http://khuongsatou.club/api/"; //web
    public static final String BASE_IMAGE = "http://khuongsatou.club/upload/images/";//web

    //URI Người chơi
    public static final String URI_DANG_NHAP  = "nguoi_choi/dang_nhap";
    public static final String URI_NGUOI_CHOI_THEM ="nguoi_choi/them" ;
    public static final String URI_MAT_KHAU ="nguoi_choi/mat_khau";
    public static final String URI_NGUOI_CHOI_CAP_NHAT ="nguoi_choi/cap_nhat" ;
    public static final String URI_BANG_XEP_HANG = "nguoi_choi/danh_sach";
    public static final String URI_NGUOI_CHOI_UPDATE_CREDIT = "nguoi_choi/cap_nhat_credit";

    //URI Lượt Chơi
    public static final String URI_LUOT_CHOI = "luot_choi/tim";
    public static final String URI_LUOT_CHOI_THEM = "luot_choi/them";
    //URI Credit
    public static final String URI_CREDIT_DANH_SACH = "goi_credit/danh_sach";
    //URI Linh Vuc
    public static final String URI_LINH_VUC = "linh_vuc/danh_sach";
    //URI Câu hỏi
    public static final String URI_CAU_HOI = "cau_hoi/tim";

    public static ProgressDialog showProress(Context context){
        final ProgressDialog pgWait = new ProgressDialog(context);
        pgWait.setTitle("Thông Báo");
        pgWait.setMessage("Vui Lòng Đợi...");
        return pgWait;
    }

    //fuction check connect
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
