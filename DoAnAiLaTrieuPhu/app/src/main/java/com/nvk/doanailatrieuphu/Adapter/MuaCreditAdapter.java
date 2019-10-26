package com.nvk.doanailatrieuphu.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nvk.doanailatrieuphu.Activity.MuaCreaditActivity;
import com.nvk.doanailatrieuphu.Controller.NguoiChoiController;
import com.nvk.doanailatrieuphu.Model.GoiCredit;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;

import java.util.List;

public class MuaCreditAdapter extends RecyclerView.Adapter<MuaCreditAdapter.MuaCreditHolder> {

    private List<GoiCredit> goiCredits;
    private Context context;
    private NguoiChoiController nguoiChoiController;

    public MuaCreditAdapter(List<GoiCredit> goiCredits, Context context, NguoiChoiController nguoiChoiController) {
        this.goiCredits = goiCredits;
        this.context = context;
        this.nguoiChoiController = nguoiChoiController;
    }

    @NonNull
    @Override
    public MuaCreditHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_goi_credit,parent,false);
        return new MuaCreditHolder(view,this);
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

    public class MuaCreditHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvCredit,tvTien;
        private CardView cvItem;
        private MuaCreditAdapter adapter;
        public MuaCreditHolder(@NonNull View itemView, MuaCreditAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            tvCredit = itemView.findViewById(R.id.tvCredit);
            tvTien = itemView.findViewById(R.id.tvTien);
            cvItem = itemView.findViewById(R.id.cvItem);
            cvItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Mua Credit");
            builder.setMessage("Bạn Có Muốn Mua Gem Không");
            builder.setCancelable(false);
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    int credit = Integer.parseInt(tvCredit.getText().toString());
                    MuaCreaditActivity muaCreaditActivity = (MuaCreaditActivity) context;
                    NguoiChoi nguoiChoi = nguoiChoiController.getTKByID(muaCreaditActivity.id_nguoiChoi);
                    credit+=nguoiChoi.getCredit();
                    nguoiChoi.setCredit(credit);
                    Boolean result = nguoiChoiController.updateGoiCredit(nguoiChoi);
                    if (result){
                        Toast.makeText(context,"OK",Toast.LENGTH_SHORT).show();
                        muaCreaditActivity.tvTinDung.setText(nguoiChoi.getCredit()+"");
                    }else{
                        Toast.makeText(context,"Fail",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
}
