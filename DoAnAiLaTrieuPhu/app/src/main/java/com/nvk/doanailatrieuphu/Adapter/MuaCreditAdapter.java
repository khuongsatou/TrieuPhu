package com.nvk.doanailatrieuphu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nvk.doanailatrieuphu.Model.GoiCredit;
import com.nvk.doanailatrieuphu.R;

import java.util.List;

public class MuaCreditAdapter extends RecyclerView.Adapter<MuaCreditAdapter.MuaCreditHolder> {

    private List<GoiCredit> goiCredits;
    private Context context;

    public MuaCreditAdapter(List<GoiCredit> goiCredits, Context context) {
        this.goiCredits = goiCredits;
        this.context = context;
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
        public MuaCreditHolder(@NonNull View itemView) {
            super(itemView);

            tvCredit = itemView.findViewById(R.id.tvCredit);
            tvTien = itemView.findViewById(R.id.tvTien);
        }
    }
}
