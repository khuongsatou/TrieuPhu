package com.nvk.doanailatrieuphu.Adapter;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nvk.doanailatrieuphu.Model.LuotChoi;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;

import java.util.List;

public class BangXepHangAdapter extends RecyclerView.Adapter<BangXepHangAdapter.BangXepHangHolder> {
    private Context context;
    private List<NguoiChoi> nguoiChois;

    public BangXepHangAdapter(Context context, List<NguoiChoi> nguoiChois) {
        this.context = context;
        this.nguoiChois = nguoiChois;
    }

    @NonNull
    @Override
    public BangXepHangAdapter.BangXepHangHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_bang_xep_hang,parent,false);
        return new BangXepHangHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BangXepHangAdapter.BangXepHangHolder holder, int position) {
       holder.ivHinh.setImageResource(R.drawable.user);
       holder.tvName.setText(nguoiChois.get(position).getTenDangNhap());
       holder.tvDiemCaoNhat.setText(nguoiChois.get(position).getDiemCaoNhat()+"");
    }

    @Override
    public int getItemCount() {
        return nguoiChois.size();
    }

    public class BangXepHangHolder extends RecyclerView.ViewHolder {
        public TextView tvName,tvDiemCaoNhat;
        public ImageView ivHinh;
        public BangXepHangHolder(@NonNull View itemView) {
            super(itemView);
            ivHinh = itemView.findViewById(R.id.ivHinh);
            tvName = itemView.findViewById(R.id.tvName);
            tvDiemCaoNhat = itemView.findViewById(R.id.tvDiemCaoNhat);
        }
    }
}
