package com.nvk.doanailatrieuphu.Adapter;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nvk.doanailatrieuphu.Activity.MuaCreaditActivity;
import com.nvk.doanailatrieuphu.Controller.MuaCreditController;
import com.nvk.doanailatrieuphu.Controller.NguoiChoiController;
import com.nvk.doanailatrieuphu.Database.DBHelper;
import com.nvk.doanailatrieuphu.Model.GoiCredit;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MuaCreditAdapter extends RecyclerView.Adapter<MuaCreditAdapter.MuaCreditHolder> {


    private List<GoiCredit> goiCredits;
    private Context context;
    private MuaCreaditActivity muaCreaditActivity;



    public MuaCreditAdapter(List<GoiCredit> goiCredits, Context context) {
        this.goiCredits = goiCredits;
        this.context = context;
        this.muaCreaditActivity = (MuaCreaditActivity) context;
    }

    @NonNull
    @Override
    public MuaCreditHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_goi_credit,parent,false);
        return new MuaCreditHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MuaCreditHolder holder, int position) {
        GoiCredit goiCredit = goiCredits.get(position);
        holder.tvCredit.setText(goiCredit.getCredit()+"");
        holder.tvTien.setText(goiCredit.getSo_tien()+"");
    }

    @Override
    public int getItemCount() {
        return goiCredits.size();
    }

    public class MuaCreditHolder extends RecyclerView.ViewHolder{
        private TextView tvCredit,tvTien;


        public MuaCreditHolder(@NonNull final View itemView) {
            super(itemView);

            tvCredit = itemView.findViewById(R.id.tvCredit);
            tvTien = itemView.findViewById(R.id.tvTien);
            tvCredit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Mua Credit");
                        builder.setMessage("Bạn Có Muốn Mua Gem Không");
                        builder.setCancelable(false);
                        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int credit =Integer.parseInt(tvCredit.getText().toString());
                                muaCreaditActivity.CapNhatCredit(credit);

                        }});

          builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
          });


        }
    }
}
