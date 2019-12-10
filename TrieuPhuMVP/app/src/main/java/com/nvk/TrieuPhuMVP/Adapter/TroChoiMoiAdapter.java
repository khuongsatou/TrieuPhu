package com.nvk.TrieuPhuMVP.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nvk.TrieuPhuMVP.Model.LinhVuc;
import com.nvk.TrieuPhuMVP.Presenter.TroChoiMoiPresenter;
import com.nvk.TrieuPhuMVP.R;
import com.nvk.TrieuPhuMVP.View.Activity.HienThiCauHoiActivity;
import com.nvk.TrieuPhuMVP.View.Activity.MangHinhTroChoiActivity;

import java.io.Serializable;
import java.util.List;

import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.KEY_DANGNHAP;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.KEY_LINHVUC;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.TYPE_ITEM;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.TYPE_LOADING;
import static com.nvk.TrieuPhuMVP.View.Activity.MangHinhChinhActivity.KEY_REQUESTCODE;

public class TroChoiMoiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<LinhVuc> linhVucs;
    private TroChoiMoiPresenter troChoiMoiPresenter;

    public TroChoiMoiAdapter( List<LinhVuc> linhVucs,Context context,TroChoiMoiPresenter troChoiMoiPresenter) {
        this.context = context;
        this.linhVucs = linhVucs;
        this.troChoiMoiPresenter = troChoiMoiPresenter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM){
            View view = LayoutInflater.from(context).inflate(R.layout.custom_item_cau_hoi,parent,false);
            return new LinhVucHolder(view);
        }else if(viewType == TYPE_LOADING){
            View view = LayoutInflater.from(context).inflate(R.layout.custom_item_loading,parent,false);
            return new LoadingHolder(view);
        }
        return  null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LinhVuc linhVuc = linhVucs.get(position);
        if (holder instanceof LinhVucHolder){
            LinhVucHolder linhVucHolder = (LinhVucHolder) holder;
            linhVucHolder.btnItemLinhVuc.setText(linhVuc.getTen_linh_vuc());
        }
    }



    @Override
    public int getItemCount() {
        return  linhVucs.size();
    }
    @Override
    public int getItemViewType(int position) {
        return linhVucs.get(position) == null ? TYPE_LOADING : TYPE_ITEM;
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
            LinhVuc linhVuc = linhVucs.get(getLayoutPosition());
            MangHinhTroChoiActivity mangHinhTroChoiActivity = (MangHinhTroChoiActivity) context;
            Intent intent = new Intent(context, HienThiCauHoiActivity.class);
            intent.putExtra(KEY_LINHVUC, linhVuc);
            intent.putExtra(KEY_DANGNHAP,troChoiMoiPresenter.getNguoiChoi());
            mangHinhTroChoiActivity.startActivityForResult(intent,KEY_REQUESTCODE);
        }
    }

    public class LoadingHolder extends RecyclerView.ViewHolder{

        public LoadingHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
