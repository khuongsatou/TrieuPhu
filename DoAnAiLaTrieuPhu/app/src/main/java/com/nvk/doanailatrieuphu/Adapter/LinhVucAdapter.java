package com.nvk.doanailatrieuphu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nvk.doanailatrieuphu.Activity.HienThiCauHoiActivity;
import com.nvk.doanailatrieuphu.Model.LinhVuc;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;

import java.util.List;

import static com.nvk.doanailatrieuphu.Activity.DangNhapActivity.KEY_DANGNHAP;
import static com.nvk.doanailatrieuphu.Activity.MangHinhTroChoiActivity.KEY_LINHVUC;

public class LinhVucAdapter extends RecyclerView.Adapter<LinhVucAdapter.LinhVucHolder> {
    private Context context;
    private List<LinhVuc> linhVucs;
    private NguoiChoi nguoiChoi;

    public LinhVucAdapter(Context context, List<LinhVuc> linhVucs,NguoiChoi nguoiChoi) {
        this.context = context;
        this.linhVucs = linhVucs;
        this.nguoiChoi = nguoiChoi;
    }

    @NonNull
    @Override
    public LinhVucAdapter.LinhVucHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_linh_vuc,parent,false);
        return new LinhVucHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LinhVucAdapter.LinhVucHolder holder, int position) {
        LinhVuc linhVuc = getItem(position);
        holder.btnItemLinhVuc.setText(linhVuc.getTenLinhVuc());
    }
    public LinhVuc getItem(int position){
        return  linhVucs.get(position);
    }

    @Override
    public int getItemCount() {
        return linhVucs.size();
    }

    public class LinhVucHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Button btnItemLinhVuc;
        public LinhVucHolder(@NonNull View itemView) {
            super(itemView);
            btnItemLinhVuc = itemView.findViewById(R.id.btnItemLinhVuc);
            btnItemLinhVuc.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, HienThiCauHoiActivity.class);
            LinhVuc linhVuc =getItem(getLayoutPosition());
            intent.putExtra(KEY_LINHVUC,linhVuc);
            intent.putExtra(KEY_DANGNHAP,nguoiChoi);
            context.startActivity(intent);
        }
    }
}
