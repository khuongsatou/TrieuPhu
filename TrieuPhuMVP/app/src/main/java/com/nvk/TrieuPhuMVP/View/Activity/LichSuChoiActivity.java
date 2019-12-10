package com.nvk.TrieuPhuMVP.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nvk.TrieuPhuMVP.Adapter.BangXepHangAdapter;
import com.nvk.TrieuPhuMVP.Adapter.LichSuChoiAdapter;
import com.nvk.TrieuPhuMVP.Model.LuotChoi;
import com.nvk.TrieuPhuMVP.Model.NguoiChoi;
import com.nvk.TrieuPhuMVP.Presenter.BangXepHangPresenter;
import com.nvk.TrieuPhuMVP.Presenter.LichSuChoiPresenter;
import com.nvk.TrieuPhuMVP.R;
import com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis;
import com.nvk.TrieuPhuMVP.View.UI.LichSuChoiView;

import java.util.ArrayList;
import java.util.List;

import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.KEY_DANGNHAP;

public class LichSuChoiActivity extends AppCompatActivity implements LichSuChoiView {
    private RecyclerView rcvLichSuChoi;
    private LichSuChoiAdapter adapter;
    private LichSuChoiPresenter lichSuChoiPresenter = new LichSuChoiPresenter(this);
    private TextView tvCreditHeader,tvTenTaiKhoanHeader,tvTilte;
    private List<LuotChoi> luotChois = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_choi);

        initView();
        initAdapter();
        initAction();
        initLoad();
    }

    private void initAdapter() {
        adapter = new LichSuChoiAdapter(this,luotChois);
        rcvLichSuChoi.setLayoutManager(new LinearLayoutManager(this));
        rcvLichSuChoi.setAdapter(adapter);
    }

    private void initLoad() {
        lichSuChoiPresenter.setNguoiChoi((NguoiChoi) getIntent().getSerializableExtra(KEY_DANGNHAP));
        NguoiChoi nguoiChoi = lichSuChoiPresenter.getNguoiChoi();
        tvTilte.setText(getString(R.string.string_lich_su_choi));
        tvTenTaiKhoanHeader.setText(nguoiChoi.getTen_dang_nhap());
        tvCreditHeader.setText(nguoiChoi.getCredit()+"");
        lichSuChoiPresenter.loadData(null);
    }

    private void initAction() {
        rcvLichSuChoi.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager manager = (LinearLayoutManager) rcvLichSuChoi.getLayoutManager();
                lichSuChoiPresenter.handleLoadListPlayer(manager.getItemCount(),manager.getChildCount(),manager.findFirstVisibleItemPosition());

            }
        });
    }

    private void initView() {
        rcvLichSuChoi = findViewById(R.id.rcvLichSuChoi);
        View view = findViewById(R.id.i_header_lich_su_choi);
        tvTenTaiKhoanHeader = view.findViewById(R.id.tvTenTaiKhoanHeader);
        tvCreditHeader = view.findViewById(R.id.tvCreditHeader);
        tvTilte = view.findViewById(R.id.tvTitle);
    }

    @Override
    public void setErrorInternet() {
        Toast.makeText(this,getString(R.string.string_server_internet),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setErrorServer() {
        Toast.makeText(this,getString(R.string.string_server_disconnect),Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean checkInternet() {
        return  NetWorkUtilitis.checkConnect(this);
    }

    @Override
    public void closeApp() {
        NetWorkUtilitis.showDialogNetWork(getString(R.string.string_server_internet), this).show();
    }

    @Override
    public void closeDialog(ProgressDialog dialog) {
        dialog.dismiss();
    }

    @Override
    public void loadBackGround(StringRequest request) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    @Override
    public ProgressDialog showDialog() {
        return NetWorkUtilitis.showProress(this);
    }

    @Override
    public void updateItemAdapter() {
        luotChois.add(null);
        rcvLichSuChoi.post(new Runnable() {
            @Override
            public void run() {
                adapter.notifyItemInserted(luotChois.size()-1);
            }
        });
    }

    @Override
    public void removeItemAdapter() {
        if (luotChois.size() > 0){
            luotChois.remove(luotChois.size()-1);
            adapter.notifyItemRemoved(luotChois.size());
        }
    }

    @Override
    public void updateList(LuotChoi luotChoi) {
        luotChois.add(luotChoi);
    }

    @Override
    public void updateAllItemAdapter() {
        adapter.notifyDataSetChanged();
    }
}
