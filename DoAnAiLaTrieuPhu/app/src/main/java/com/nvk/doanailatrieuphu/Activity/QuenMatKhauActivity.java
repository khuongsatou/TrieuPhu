package com.nvk.doanailatrieuphu.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nvk.doanailatrieuphu.Controller.NguoiChoiController;
import com.nvk.doanailatrieuphu.R;
import com.nvk.doanailatrieuphu.Utilities.Validation;

public class QuenMatKhauActivity extends AppCompatActivity {
    private EditText edtTenDangNhap,edtEmail;
    private Button btnClose;
    private TextView tvHienThiPass;
    private NguoiChoiController nguoiChoiController = new NguoiChoiController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau);
        Radiation();
    }

    private void Radiation() {
        edtTenDangNhap = findViewById(R.id.edtTenDangNhap);
        edtEmail = findViewById(R.id.edtEmail);
    }


    public void QuenMatKhau(View view) {
        // lấy text
        String tenDangNhap  = edtTenDangNhap.getText().toString();
        final String email        = edtEmail.getText().toString();
        //kiểm tra tồn tại
        Boolean result      = nguoiChoiController.checkTKAndEmail(tenDangNhap,email);
        if (result){
            //lấy pass
            String matKhau  = nguoiChoiController.getMatKhau(tenDangNhap,email);
            //tạo 1 dialog
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.custom_dialog_quen_mat_khau);
            btnClose = dialog.findViewById(R.id.btnClose);
            tvHienThiPass = dialog.findViewById(R.id.tvHienThiPass);
            tvHienThiPass.setText(matKhau);
            Toast.makeText(this,getString(R.string.tb_quen_mat_khau_tc),Toast.LENGTH_LONG).show();
            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }else{
            Toast.makeText(this,getString(R.string.tb_quen_mat_khau_tb),Toast.LENGTH_LONG).show();
        }

    }
}
