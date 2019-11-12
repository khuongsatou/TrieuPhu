package com.nvk.doanailatrieuphu.Adapter;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;

import java.util.List;

public class BangXepHangAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_BANG_XEP_HANG = 1;
    private static final int TYPE_LOADING = 0;
    private Context context;
    private List<NguoiChoi> nguoiChois;

    public BangXepHangAdapter(Context context, List<NguoiChoi> nguoiChois) {
        this.context = context;
        this.nguoiChois = nguoiChois;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_BANG_XEP_HANG){
            View view = LayoutInflater.from(context).inflate(R.layout.custom_item_bang_xep_hang,parent,false);
            return new BangXepHangHolder(view);
        }else if(viewType == TYPE_LOADING){
            View view = LayoutInflater.from(context).inflate(R.layout.custom_item_loading,parent,false);
            return new LoadingHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       NguoiChoi nguoiChoi = nguoiChois.get(position);
       if(holder instanceof BangXepHangHolder){
           BangXepHangHolder bangXepHangHolder = (BangXepHangHolder) holder;
           bangXepHangHolder.ivHinh.setImageResource(R.drawable.user);
           bangXepHangHolder.tvName.setText(nguoiChoi.getTenDangNhap());
           bangXepHangHolder.tvDiemCaoNhat.setText(nguoiChoi.getDiemCaoNhat()+"");
       }

    }

    @Override
    public int getItemCount() {
        return nguoiChois.size();
    }

    @Override
    public int getItemViewType(int position) {
        return nguoiChois.get(position) == null ? TYPE_LOADING : TYPE_BANG_XEP_HANG;
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

    public class LoadingHolder extends RecyclerView.ViewHolder{

        public LoadingHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
