package com.nvk.TrieuPhuMVP.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nvk.TrieuPhuMVP.Model.NguoiChoi;
import com.nvk.TrieuPhuMVP.R;

import java.util.List;

import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.TYPE_ITEM;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.TYPE_LOADING;

public class CauHoiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<NguoiChoi> nguoiChois;

    public CauHoiAdapter(Context context, List<NguoiChoi> nguoiChois) {
        this.context = context;
        this.nguoiChois = nguoiChois;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM){
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
            bangXepHangHolder.imgHinhDaiDienBXH.setImageResource(R.drawable.user);
            bangXepHangHolder.tvTenDangNhapBXH.setText(nguoiChoi.getTen_dang_nhap());
            bangXepHangHolder.tvDiemBXH.setText(nguoiChoi.getDiem_cao_nhat()+"");
            bangXepHangHolder.tvRank.setText((position+1)+"");
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class BangXepHangHolder extends RecyclerView.ViewHolder {
        public TextView tvTenDangNhapBXH,tvDiemBXH,tvRank;
        public ImageView imgHinhDaiDienBXH;
        public BangXepHangHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhDaiDienBXH = itemView.findViewById(R.id.imgHinhDaiDienBXH);
            tvTenDangNhapBXH = itemView.findViewById(R.id.tvTenDangNhapBXH);
            tvDiemBXH = itemView.findViewById(R.id.tvDiemBXH);
            tvRank = itemView.findViewById(R.id.tvRank);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return nguoiChois.get(position) == null ? TYPE_LOADING : TYPE_ITEM;
    }

    public class LoadingHolder extends RecyclerView.ViewHolder{

        public LoadingHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
