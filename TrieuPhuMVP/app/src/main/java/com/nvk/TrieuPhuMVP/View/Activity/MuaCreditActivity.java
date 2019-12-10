package com.nvk.TrieuPhuMVP.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nvk.TrieuPhuMVP.Adapter.MuaCreditAdapter;
import com.nvk.TrieuPhuMVP.Model.GoiCredit;
import com.nvk.TrieuPhuMVP.Model.NguoiChoi;
import com.nvk.TrieuPhuMVP.Presenter.MuaCreditPressenter;
import com.nvk.TrieuPhuMVP.R;
import com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis;
import com.nvk.TrieuPhuMVP.View.UI.MuaCreditView;

import java.util.ArrayList;
import java.util.List;

import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.KEY_DANGNHAP;

public class MuaCreditActivity extends AppCompatActivity implements MuaCreditView {
    private RecyclerView rcvGoiCredit;
    private MuaCreditAdapter adapter;
    private TextView tvCreditHeader,tvTenTaiKhoanHeader,tvTilte;
    private List<GoiCredit> goiCredits = new ArrayList<>();
    private MuaCreditPressenter muaCreditPressenter = new MuaCreditPressenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mua_credit);

        initView();
        initLoad();
        initAdapter();
    }

    private void initLoad() {
        muaCreditPressenter.setNguoiChoi((NguoiChoi) getIntent().getSerializableExtra(KEY_DANGNHAP));
        NguoiChoi nguoiChoi = muaCreditPressenter.getNguoiChoi();
        tvTilte.setText(getString(R.string.string_mua_credit));
        tvTenTaiKhoanHeader.setText(nguoiChoi.getTen_dang_nhap());
        tvCreditHeader.setText(nguoiChoi.getCredit()+"");
        muaCreditPressenter.handleLoadGoiCredit();
    }

    private void initAdapter() {
        adapter = new MuaCreditAdapter(this,goiCredits,muaCreditPressenter);
        rcvGoiCredit.setLayoutManager(new GridLayoutManager(this,2));
        rcvGoiCredit.setAdapter(adapter);
    }



    private void initView() {
        rcvGoiCredit = findViewById(R.id.rcvGoiCredit);
        View view = findViewById(R.id.i_header_credit);
        tvTenTaiKhoanHeader = view.findViewById(R.id.tvTenTaiKhoanHeader_1);
        tvCreditHeader = view.findViewById(R.id.tvCreditHeader_1);
        tvTilte = view.findViewById(R.id.tvTitle);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        muaCreditPressenter.backPress(intent);
        setResult(RESULT_OK,intent);
        finish();

    }

    @Override
    public void buySuccess() {
        Toast.makeText(this,getString(R.string.string_buy_credit_success),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateCredit(int credit) {
        tvCreditHeader.setText(credit+"");
    }

    @Override
    public void setGoiCredit(GoiCredit goiCredit) {
        goiCredits.add(goiCredit);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void buyFail() {
        Toast.makeText(this,getString(R.string.string_buy_credit_fail),Toast.LENGTH_SHORT).show();
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
}
