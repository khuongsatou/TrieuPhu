package com.nvk.doanailatrieuphu.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;

import com.nvk.doanailatrieuphu.Adapter.LinhVucAdapter;
import com.nvk.doanailatrieuphu.Controller.LinhVucController;
import com.nvk.doanailatrieuphu.Controller.NguoiChoiController;
import com.nvk.doanailatrieuphu.Model.LinhVuc;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;

import java.util.ArrayList;
import java.util.List;

import static com.nvk.doanailatrieuphu.Activity.DangNhapActivity.KEY_DANGNHAP;
import static com.nvk.doanailatrieuphu.Activity.MangHinhChinhActivity.KEY_REQUESTCODE;

public class MangHinhTroChoiActivity extends AppCompatActivity {

    public static final String KEY_LINHVUC = "linhvuc";
    private TextView tvTen,tvTinDung;
    private RecyclerView rcvLinhVuc;
    private LinhVucAdapter adapter;
    private List<LinhVuc> linhVucs;
    private NguoiChoi nguoiChoi;
    private LinhVucController linhVucController = new LinhVucController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mang_hinh_tro_choi);

        radiation();
        showNameAndCredit();
        createAdapter();
    }

    private void createAdapter() {
        linhVucs = new ArrayList<>();
        linhVucs.addAll(linhVucController.getLinhVuc());
        adapter = new LinhVucAdapter(this,linhVucs,this.nguoiChoi);
        rcvLinhVuc.setLayoutManager(new LinearLayoutManager(this));
        rcvLinhVuc.setHasFixedSize(true);
        rcvLinhVuc.setAdapter(adapter);
    }

    private void showNameAndCredit() {

        //Muốn chạy thì bật chỗ này nhé
        this.nguoiChoi = (NguoiChoi) getIntent().getSerializableExtra(KEY_DANGNHAP);

        //xóa dòng này đi
        // begin
//        NguoiChoiController nguoiChoiController = new NguoiChoiController(this);
//        this.nguoiChoi=nguoiChoiController.getTKByID(1);
        //end
        tvTen.setText(this.nguoiChoi.getTenDangNhap());
        tvTinDung.setText(this.nguoiChoi.getCredit()+"");
    }

    private void radiation() {
        View vHeader = findViewById(R.id.vHeader);
        tvTen =  vHeader.findViewById(R.id.tvTen);
        tvTinDung = vHeader.findViewById(R.id.tvTinDung);
        rcvLinhVuc = findViewById(R.id.rcvLinhVuc);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == KEY_REQUESTCODE && resultCode == RESULT_OK && data!=null){
            this.nguoiChoi = (NguoiChoi) data.getSerializableExtra(KEY_DANGNHAP);
            tvTinDung.setText(this.nguoiChoi.getCredit()+"");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(KEY_DANGNHAP,nguoiChoi);
        setResult(RESULT_OK,intent);
        finish();
    }
}
