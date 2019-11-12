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
import com.nvk.doanailatrieuphu.Activity.MangHinhTroChoiActivity;
import com.nvk.doanailatrieuphu.Model.LinhVuc;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;

import java.util.List;

import static com.nvk.doanailatrieuphu.Activity.MangHinhChinhActivity.KEY_REQUESTCODE;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.KEY_DANGNHAP;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.KEY_LINHVUC;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.TYPE_ITEM;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.TYPE_LOADING;
public class LinhVucAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM){
            View view = LayoutInflater.from(context).inflate(R.layout.custom_item_linh_vuc,parent,false);
            return new LinhVucHolder(view);
        }else if(viewType == TYPE_LOADING){
            View view = LayoutInflater.from(context).inflate(R.layout.custom_item_loading,parent,false);
            return new LoadingHolder(view);
        }
        return  null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LinhVuc linhVuc = getItem(position);
        if (holder instanceof LinhVucHolder){
            LinhVucHolder linhVucHolder = (LinhVucHolder) holder;
            linhVucHolder.btnItemLinhVuc.setText(linhVuc.getTenLinhVuc());
        }

    }
    public LinhVuc getItem(int position){
        return  linhVucs.get(position);
    }

    @Override
    public int getItemCount() {
        return linhVucs.size();
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
            LinhVuc linhVuc = getItem(getLayoutPosition());
            MangHinhTroChoiActivity mangHinhTroChoiActivity = (MangHinhTroChoiActivity) context;
            Intent intent = new Intent(context, HienThiCauHoiActivity.class);
            intent.putExtra(KEY_LINHVUC,linhVuc);
            intent.putExtra(KEY_DANGNHAP,nguoiChoi);
            mangHinhTroChoiActivity.startActivityForResult(intent,KEY_REQUESTCODE);
        }
    }

    public class LoadingHolder extends RecyclerView.ViewHolder{

        public LoadingHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
