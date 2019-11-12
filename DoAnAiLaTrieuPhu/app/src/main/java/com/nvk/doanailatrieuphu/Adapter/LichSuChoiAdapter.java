package com.nvk.doanailatrieuphu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nvk.doanailatrieuphu.Model.LuotChoi;
import com.nvk.doanailatrieuphu.R;

import java.util.List;

public class LichSuChoiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_LOADING = 0;
    private static final int TYPE_LICH_SU_CHOI =1 ;
    private Context context;
    private List<LuotChoi> luotChois;


    public LichSuChoiAdapter(Context context, List<LuotChoi> luotChois) {
        this.context = context;
        this.luotChois = luotChois;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_LICH_SU_CHOI){
            View view = LayoutInflater.from(context).inflate(R.layout.custom_item_lich_su_choi,parent,false);
            return new LichSuChoiAdapter.LichSuHolder(view);
        }else if(viewType == TYPE_LOADING){
            View view = LayoutInflater.from(context).inflate(R.layout.custom_item_loading,parent,false);
            return new LoadingHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LuotChoi luotChoi = luotChois.get(position);
        if (holder instanceof LichSuHolder){
            LichSuHolder lichSuHolder = (LichSuHolder) holder;
            lichSuHolder.tvDate.setText(luotChoi.getNgayGio()+" PM");
            lichSuHolder.tvSoCau.setText("Số Câu: "+luotChoi.getSoCau()+"");
            lichSuHolder.tvDiem.setText(luotChoi.getId()+" Điểm");
        }

    }

    @Override
    public int getItemCount() {
        return luotChois.size();
    }

    @Override
    public int getItemViewType(int position) {
        return luotChois.get(position) == null ? TYPE_LOADING : TYPE_LICH_SU_CHOI;
    }

    public class LichSuHolder extends RecyclerView.ViewHolder {
        public TextView tvDate,tvSoCau,tvDiem;
        public LichSuHolder(@NonNull View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.tvDate);
            tvSoCau = itemView.findViewById(R.id.tvSoCau);
            tvDiem = itemView.findViewById(R.id.tvDiem);
        }
    }

    public class LoadingHolder extends RecyclerView.ViewHolder{

        public LoadingHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
