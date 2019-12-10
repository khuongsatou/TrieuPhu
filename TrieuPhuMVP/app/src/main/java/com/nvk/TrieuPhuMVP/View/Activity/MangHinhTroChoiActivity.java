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
import com.nvk.TrieuPhuMVP.Adapter.TroChoiMoiAdapter;
import com.nvk.TrieuPhuMVP.Model.LinhVuc;
import com.nvk.TrieuPhuMVP.Model.NguoiChoi;
import com.nvk.TrieuPhuMVP.Presenter.TroChoiMoiPresenter;
import com.nvk.TrieuPhuMVP.R;
import com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis;
import com.nvk.TrieuPhuMVP.View.UI.TroChoiMoiView;

import java.util.ArrayList;
import java.util.List;

import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.KEY_DANGNHAP;

public class MangHinhTroChoiActivity extends AppCompatActivity implements TroChoiMoiView {
    private RecyclerView rcvLinhVuc;
    private TroChoiMoiAdapter adapter;
    private TroChoiMoiPresenter troChoiMoiPresenter = new TroChoiMoiPresenter(this);
    private TextView tvCreditHeader,tvTenTaiKhoanHeader;
    private List<LinhVuc> linhVucs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mang_hinh_tro_choi);

        initView();
        initAdapter();
        initAction();
        initLoad();
    }

    private void initAdapter() {
        adapter = new TroChoiMoiAdapter(linhVucs,this,troChoiMoiPresenter);
        rcvLinhVuc.setLayoutManager(new LinearLayoutManager(this));
        rcvLinhVuc.setAdapter(adapter);
    }

    private void initLoad() {
        troChoiMoiPresenter.setNguoiChoi((NguoiChoi) getIntent().getSerializableExtra(KEY_DANGNHAP));
        NguoiChoi nguoiChoi = troChoiMoiPresenter.getNguoiChoi();
        tvTenTaiKhoanHeader.setText(nguoiChoi.getTen_dang_nhap());
        tvCreditHeader.setText(nguoiChoi.getCredit()+"");
        troChoiMoiPresenter.loadData(null);
    }

    private void initAction() {
        rcvLinhVuc.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager manager = (LinearLayoutManager) rcvLinhVuc.getLayoutManager();
                troChoiMoiPresenter.handleLoadListPlayer(manager.getItemCount(),manager.getChildCount(),manager.findFirstVisibleItemPosition());

            }
        });
    }

    private void initView() {
        rcvLinhVuc = findViewById(R.id.rcvLinhVuc);
        View view = findViewById(R.id.i_header_infor_2);
        tvTenTaiKhoanHeader = view.findViewById(R.id.tvTenTaiKhoanHeader_2);
        tvCreditHeader = view.findViewById(R.id.tvCreditHeader_1);
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
        linhVucs.add(null);
        rcvLinhVuc.post(new Runnable() {
            @Override
            public void run() {
                adapter.notifyItemInserted(linhVucs.size()-1);
            }
        });
    }

    @Override
    public void removeItemAdapter() {
        if (linhVucs.size() > 0){
            linhVucs.remove(linhVucs.size()-1);
            adapter.notifyItemRemoved(linhVucs.size());
        }
    }

    @Override
    public void updateList(LinhVuc linhVuc) {
        linhVucs.add(linhVuc);
    }

    @Override
    public void updateAllItemAdapter() {
        adapter.notifyDataSetChanged();
    }
}
