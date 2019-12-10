package com.nvk.TrieuPhuMVP.Presenter;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nvk.TrieuPhuMVP.Model.LuotChoi;
import com.nvk.TrieuPhuMVP.Model.NguoiChoi;
import com.nvk.TrieuPhuMVP.View.UI.BangXepHangView;
import com.nvk.TrieuPhuMVP.View.UI.LichSuChoiView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.nvk.TrieuPhuMVP.Model.LuotChoi.COLUMN_NGUOI_CHOI_ID;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.KEY_PAGE;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.LIMIT_KHOI_TAO;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.PAGE_KHOI_TAO;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.PAGE_SIZE;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.BASE;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.URI_BANG_XEP_HANG;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.URI_LUOT_CHOI;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.KEY_LIMIT;

public class LichSuChoiPresenter {
    private LichSuChoiView lichSuChoiView;
    private NguoiChoi nguoiChoi;
    private boolean checkLoading = false;
    private boolean checkLastPage = false;
    private int currentPage=1;


    public LichSuChoiPresenter(LichSuChoiView lichSuChoiView) {
        this.lichSuChoiView = lichSuChoiView;
    }

    public NguoiChoi getNguoiChoi() {
        return nguoiChoi;
    }

    public void setNguoiChoi(NguoiChoi nguoiChoi) {
        this.nguoiChoi = nguoiChoi;
    }

    public void handleLoadListPlayer(int countItem, int countChild, int findNextChild){
        if ((countChild+ findNextChild) >= countItem && findNextChild >=0 && countItem >= PAGE_SIZE){
            if (!checkLoading && !checkLastPage){
                checkLoading=true;
                currentPage++;
                lichSuChoiView.updateItemAdapter();
                Bundle data = new Bundle();
                data.putInt(KEY_PAGE,currentPage);
                data.putInt(KEY_LIMIT,PAGE_SIZE);
                loadData(data);
            }
        }
    }

    public void loadData(Bundle data) {
        if (lichSuChoiView.checkInternet()){
            handleBangXepHang(data);
        }else{
            lichSuChoiView.setErrorInternet();
        }
    }

    private void handleBangXepHang(Bundle data){
        final Map<String,String> startMap = new HashMap<>();
        startMap.put(COLUMN_NGUOI_CHOI_ID,String.valueOf(this.nguoiChoi.getId()));
        startMap.put(KEY_PAGE,String.valueOf(data == null ? PAGE_KHOI_TAO : data.getInt(KEY_PAGE)));
        startMap.put(KEY_LIMIT,String.valueOf(data == null ? LIMIT_KHOI_TAO : data.getInt(KEY_LIMIT)));
        final ProgressDialog dialog = lichSuChoiView.showDialog();
        dialog.show();
        final StringRequest request = new StringRequest(Request.Method.POST, BASE + URI_LUOT_CHOI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    dialog.dismiss();
                    JSONObject objLuotChoi = new JSONObject(response);
                    int total = objLuotChoi.getInt("total");
                    int totalPage = total / PAGE_SIZE;
                    if (totalPage % PAGE_SIZE != 0){
                        totalPage++;
                    }
                    lichSuChoiView.removeItemAdapter();
                    JSONArray arrLuotChoi = objLuotChoi.getJSONArray("luot_choi");
                    for (int i = 0; i < arrLuotChoi.length() ; i++) {
                        JSONObject objItem = arrLuotChoi.getJSONObject(i);
                        LuotChoi luotChoi = new LuotChoi(objItem.getInt("id"),
                                objItem.getInt("nguoi_choi_id"),
                                objItem.getInt("so_cau"),objItem.getInt("diem"),
                                objItem.getString("ngay_gio"));
                        lichSuChoiView.updateList(luotChoi);
                    }
                    lichSuChoiView.updateAllItemAdapter();
                    checkFinishLoading(totalPage);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                lichSuChoiView.setErrorServer();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return startMap;
            }
        };
        lichSuChoiView.loadBackGround(request);

    }

    private void checkFinishLoading(int totalPage) {
        checkLoading = false;
        checkLastPage = (currentPage == totalPage);
    }
}
