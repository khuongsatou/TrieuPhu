package com.nvk.TrieuPhuMVP.Presenter;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nvk.TrieuPhuMVP.Model.NguoiChoi;
import com.nvk.TrieuPhuMVP.View.UI.BangXepHangView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.KEY_PAGE;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.LIMIT_KHOI_TAO;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.PAGE_KHOI_TAO;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.PAGE_SIZE;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.BASE;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.URI_BANG_XEP_HANG;
import static com.nvk.TrieuPhuMVP.View.Activity.MangHinhChinhActivity.KEY_LIMIT;

public class BangXepHangPresenter {
    private BangXepHangView bangXepHangView;
    private NguoiChoi nguoiChoi;
    private boolean checkLoading = false;
    private boolean checkLastPage = false;
    private int currentPage=1;


    public BangXepHangPresenter(BangXepHangView bangXepHangView) {
        this.bangXepHangView = bangXepHangView;
    }

    public NguoiChoi getNguoiChoi() {
        return nguoiChoi;
    }

    public void setNguoiChoi(NguoiChoi nguoiChoi) {
        this.nguoiChoi = nguoiChoi;
    }

    public void handleLoadListPlayer(int countItem,int countChild,int findNextChild){
        if ((countChild+ findNextChild) >= countItem && findNextChild >=0 && countItem >= PAGE_SIZE){
            if (!checkLoading && !checkLastPage){
                checkLoading=true;
                currentPage++;
                bangXepHangView.updateItemAdapter();
                Bundle data = new Bundle();
                data.putInt(KEY_PAGE,currentPage);
                data.putInt(KEY_LIMIT,PAGE_SIZE);
                loadData(data);
            }
        }
    }

    public void loadData(Bundle data) {
        if (bangXepHangView.checkInternet()){
            handleBangXepHang(data);
        }else{
            bangXepHangView.setErrorInternet();
        }
    }

    private void handleBangXepHang(Bundle data){
        final Map<String,String> startMap = new HashMap<>();
        startMap.put(KEY_PAGE,String.valueOf(data == null ? PAGE_KHOI_TAO : data.getInt(KEY_PAGE)));
        startMap.put(KEY_LIMIT,String.valueOf(data == null ? LIMIT_KHOI_TAO : data.getInt(KEY_LIMIT)));

        final ProgressDialog dialog = bangXepHangView.showDialog();
        dialog.show();
        final StringRequest request = new StringRequest(Request.Method.POST, BASE + URI_BANG_XEP_HANG, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    dialog.dismiss();
                    JSONObject objNguoiChoi = new JSONObject(response);
                    int total = objNguoiChoi.getInt("total");
                    int totalPage = total / PAGE_SIZE;
                    if (totalPage % PAGE_SIZE != 0){
                        totalPage++;
                    }
                    bangXepHangView.removeItemAdapter();
                    JSONArray arrNguoiChoi = objNguoiChoi.getJSONArray("nguoi_choi");
                    for (int i = 0; i < arrNguoiChoi.length(); i++) {
                        JSONObject objItem = arrNguoiChoi.getJSONObject(i);
                        NguoiChoi nguoiChoiBXH = new NguoiChoi(
                                objItem.getInt("id"),
                                objItem.getString("ten_dang_nhap"),
                                objItem.getString("hinh_dai_dien"),
                                objItem.getInt("diem_cao_nhat"));
                        bangXepHangView.updateList(nguoiChoiBXH);
                    }
                    bangXepHangView.updateAllItemAdapter();
                    checkFinishLoading(totalPage);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               bangXepHangView.setErrorServer();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return startMap;
            }
        };
        bangXepHangView.loadBackGround(request);

    }

    private void checkFinishLoading(int totalPage) {
        checkLoading = false;
        checkLastPage = (currentPage == totalPage);
    }
}
