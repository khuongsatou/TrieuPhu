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
import com.nvk.TrieuPhuMVP.Model.NguoiChoi;
import com.nvk.TrieuPhuMVP.Presenter.BangXepHangPresenter;
import com.nvk.TrieuPhuMVP.R;
import com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis;
import com.nvk.TrieuPhuMVP.View.UI.BangXepHangView;

import java.util.ArrayList;
import java.util.List;

import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.KEY_DANGNHAP;

public class BangXepHangActivity extends AppCompatActivity implements BangXepHangView {
    private RecyclerView rcvBangXepHang;
    private BangXepHangAdapter adapter;
    private BangXepHangPresenter bangXepHangPresenter = new BangXepHangPresenter(this);
    private TextView tvCreditHeader,tvTenTaiKhoanHeader,tvTilte;
    private List<NguoiChoi> nguoiChois = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bang_xep_hang);

        initView();
        initAdapter();
        initAction();
        initLoad();
    }

    private void initAdapter() {
        adapter = new BangXepHangAdapter(this,nguoiChois);
        rcvBangXepHang.setLayoutManager(new LinearLayoutManager(this));
        rcvBangXepHang.setAdapter(adapter);
    }

    private void initLoad() {
        bangXepHangPresenter.setNguoiChoi((NguoiChoi) getIntent().getSerializableExtra(KEY_DANGNHAP));
        NguoiChoi nguoiChoi = bangXepHangPresenter.getNguoiChoi();
        tvTilte.setText(getString(R.string.string_bang_xep_hang));
        tvTenTaiKhoanHeader.setText(nguoiChoi.getTen_dang_nhap());
        tvCreditHeader.setText(nguoiChoi.getCredit()+"");

        bangXepHangPresenter.loadData(null);
    }

    private void initAction() {
        rcvBangXepHang.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager manager = (LinearLayoutManager) rcvBangXepHang.getLayoutManager();
                bangXepHangPresenter.handleLoadListPlayer(manager.getItemCount(),manager.getChildCount(),manager.findFirstVisibleItemPosition());

            }
        });
    }

    private void initView() {
        rcvBangXepHang = findViewById(R.id.rcvBangXepHang);
        View view = findViewById(R.id.i_header_bxh);
        tvTenTaiKhoanHeader = view.findViewById(R.id.tvTenTaiKhoanHeader_1);
        tvCreditHeader = view.findViewById(R.id.tvCreditHeader_1);
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
        nguoiChois.add(null);
        rcvBangXepHang.post(new Runnable() {
            @Override
            public void run() {
                adapter.notifyItemInserted(nguoiChois.size()-1);
            }
        });
    }

    @Override
    public void removeItemAdapter() {
        if (nguoiChois.size() > 0){
            nguoiChois.remove(nguoiChois.size()-1);
            adapter.notifyItemRemoved(nguoiChois.size());
        }
    }

    @Override
    public void updateList(NguoiChoi nguoiChoi) {
        nguoiChois.add(nguoiChoi);
    }

    @Override
    public void updateAllItemAdapter() {
        adapter.notifyDataSetChanged();
    }
}
