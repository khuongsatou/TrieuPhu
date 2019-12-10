package com.nvk.TrieuPhuMVP.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nvk.TrieuPhuMVP.Model.LuotChoi;
import com.nvk.TrieuPhuMVP.Model.NguoiChoi;
import com.nvk.TrieuPhuMVP.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.TYPE_ITEM;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.TYPE_LOADING;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.BASE_IMAGE;

public class LichSuChoiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<LuotChoi> luotChois;

    public LichSuChoiAdapter(Context context, List<LuotChoi> luotChois) {
        this.context = context;
        this.luotChois = luotChois;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM){
            View view = LayoutInflater.from(context).inflate(R.layout.custom_item_lich_su_choi,parent,false);
            return new LichSuChoiAdapter.LichSuChoiHolder(view);
        }else if(viewType == TYPE_LOADING){
            View view = LayoutInflater.from(context).inflate(R.layout.custom_item_loading,parent,false);
            return new LichSuChoiAdapter.LoadingHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LuotChoi luotChoi = luotChois.get(position);
        if(holder instanceof LichSuChoiAdapter.LichSuChoiHolder){
            LichSuChoiAdapter.LichSuChoiHolder lichSuChoiHolder = (LichSuChoiAdapter.LichSuChoiHolder) holder;
            lichSuChoiHolder.tvSoCauLSC.setText("Số Câu: "+luotChoi.getSo_cau()+"");
            lichSuChoiHolder.tvDiemLSC.setText(luotChoi.getDiem()+" Điểm");
            lichSuChoiHolder.tvDate.setText(luotChoi.getNgay_gio()+" PM");
        }
    }

    @Override
    public int getItemCount() {
        return luotChois.size();
    }

    public class LichSuChoiHolder extends RecyclerView.ViewHolder {
        public TextView tvSoCauLSC,tvDiemLSC,tvDate;
        public LichSuChoiHolder(@NonNull View itemView) {
            super(itemView);
            tvSoCauLSC = itemView.findViewById(R.id.tvSoCauLSC);
            tvDiemLSC = itemView.findViewById(R.id.tvDiemLSC);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return luotChois.get(position) == null ? TYPE_LOADING : TYPE_ITEM;
    }

    public class LoadingHolder extends RecyclerView.ViewHolder{
        public LoadingHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
