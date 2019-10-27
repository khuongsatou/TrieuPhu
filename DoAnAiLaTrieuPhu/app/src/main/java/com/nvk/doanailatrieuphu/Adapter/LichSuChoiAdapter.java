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

public class LichSuChoiAdapter extends RecyclerView.Adapter<LichSuChoiAdapter.LichSuHolder> {
    private Context context;
    private List<LuotChoi> luotChois;

    public LichSuChoiAdapter(Context context, List<LuotChoi> luotChois) {
        this.context = context;
        this.luotChois = luotChois;
    }

    @NonNull
    @Override
    public LichSuChoiAdapter.LichSuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_lich_su_choi,parent,false);
        return new LichSuChoiAdapter.LichSuHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuChoiAdapter.LichSuHolder holder, int position) {
        holder.tvDate.setText(luotChois.get(position).getNgayGio()+" PM");
        holder.tvSoCau.setText("Số Câu: "+luotChois.get(position).getSoCau()+"");
        holder.tvDiem.setText(luotChois.get(position).getDiem()+" Điểm");
    }

    @Override
    public int getItemCount() {
        return luotChois.size();
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
}
