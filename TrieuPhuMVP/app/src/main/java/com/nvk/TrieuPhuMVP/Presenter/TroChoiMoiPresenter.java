package com.nvk.TrieuPhuMVP.Presenter;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nvk.TrieuPhuMVP.Model.LinhVuc;
import com.nvk.TrieuPhuMVP.Model.NguoiChoi;
import com.nvk.TrieuPhuMVP.View.UI.TroChoiMoiView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.KEY_LIMIT;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.KEY_PAGE;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.LIMIT_KHOI_TAO;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.PAGE_KHOI_TAO;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.PAGE_SIZE;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.BASE;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.URI_LINH_VUC;

public class TroChoiMoiPresenter {
    private TroChoiMoiView troChoiMoiView;
    private NguoiChoi nguoiChoi;
    private LinhVuc linhVuc;
    private boolean checkLoading = false;
    private boolean checkLastPage = false;
    private int currentPage = 1;


    public TroChoiMoiPresenter(TroChoiMoiView troChoiMoiView) {
        this.troChoiMoiView = troChoiMoiView;
    }

    public NguoiChoi getNguoiChoi() {
        return nguoiChoi;
    }

    public void setNguoiChoi(NguoiChoi nguoiChoi) {
        this.nguoiChoi = nguoiChoi;
    }

    public void handleLoadListPlayer(int countItem,int countChild,int findNextChild){
        if ((countChild + findNextChild) >= countItem && findNextChild >=0 && countItem >= PAGE_SIZE){
            if (!checkLoading && !checkLastPage){
                checkLoading=true;
                currentPage++;
                troChoiMoiView.updateItemAdapter();
                Bundle data = new Bundle();
                data.putInt(KEY_PAGE,currentPage);
                data.putInt(KEY_LIMIT,PAGE_SIZE);
                loadData(data);
            }
        }
    }

    public void loadData(Bundle data) {
        if (troChoiMoiView.checkInternet()){
            handleBangXepHang(data);
        }else{
            troChoiMoiView.setErrorInternet();
        }
    }

    private void handleBangXepHang(Bundle data){
        final Map<String,String> startMap = new HashMap<>();
        startMap.put(KEY_PAGE,String.valueOf(data == null ? PAGE_KHOI_TAO : data.getInt(KEY_PAGE)));
        startMap.put(KEY_LIMIT,String.valueOf(data == null ? LIMIT_KHOI_TAO : data.getInt(KEY_LIMIT)));

        final ProgressDialog dialog = troChoiMoiView.showDialog();
        dialog.show();
        final StringRequest request = new StringRequest(Request.Method.POST, BASE + URI_LINH_VUC, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    dialog.dismiss();
                    JSONObject objLinhVuc = new JSONObject(response);
                    int total = objLinhVuc.getInt("total");
                    int totalPage = total / PAGE_SIZE;
                    if (totalPage % PAGE_SIZE != 0){
                        totalPage++;
                    }
                    troChoiMoiView.removeItemAdapter();

                    JSONArray arrLinhVuc = objLinhVuc.getJSONArray("linh_vuc");
                    for (int i = 0; i < arrLinhVuc.length() ; i++) {
                        JSONObject objItem = arrLinhVuc.getJSONObject(i);
                        LinhVuc linhVuc = new LinhVuc(
                                objItem.getInt("id"),
                                objItem.getString("ten_linh_vuc"),
                                false);
                        troChoiMoiView.updateList(linhVuc);
                    }
                    troChoiMoiView.updateAllItemAdapter();
                    checkFinishLoading(totalPage);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                troChoiMoiView.setErrorServer();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return startMap;
            }
        };
        troChoiMoiView.loadBackGround(request);

    }

    private void checkFinishLoading(int totalPage) {
        checkLoading = false;
        checkLastPage = (currentPage == totalPage);
    }

}
