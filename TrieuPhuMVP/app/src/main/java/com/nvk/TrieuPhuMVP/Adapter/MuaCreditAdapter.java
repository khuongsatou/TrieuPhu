package com.nvk.TrieuPhuMVP.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.cardview.widget.CardView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nvk.TrieuPhuMVP.Model.GoiCredit;
import com.nvk.TrieuPhuMVP.Model.NguoiChoi;
import com.nvk.TrieuPhuMVP.Presenter.MuaCreditPressenter;
import com.nvk.TrieuPhuMVP.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.KEY_CREDIT;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.KEY_ID;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.BASE;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.URI_NGUOI_CHOI_UPDATE_CREDIT;

public class MuaCreditAdapter extends RecyclerView.Adapter<MuaCreditAdapter.MuaCreditHolder> {
    private List<GoiCredit> goiCredits;
    private Context context;
    private MuaCreditPressenter muaCreditPressenter;

    public MuaCreditAdapter(Context context, List<GoiCredit> goiCredits, MuaCreditPressenter muaCreditPressenter) {
        this.goiCredits = goiCredits;
        this.context = context;
        this.muaCreditPressenter = muaCreditPressenter;
    }

    @NonNull
    @Override
    public MuaCreditAdapter.MuaCreditHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_credit, parent, false);
        return new MuaCreditHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MuaCreditAdapter.MuaCreditHolder holder, int position) {
        GoiCredit goiCredit = goiCredits.get(position);
        holder.tvTenGoi.setText(goiCredit.getTen_goi());
        holder.tvSoTien.setText(goiCredit.getSo_tien()+"");

    }

    @Override
    public int getItemCount() {
        return goiCredits.size();
    }

    public class MuaCreditHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvSoTien, tvTenGoi;
        private CardView cvGoiCredit;

        public MuaCreditHolder(@NonNull View itemView) {
            super(itemView);

            tvSoTien = itemView.findViewById(R.id.tvSoTien);
            tvTenGoi = itemView.findViewById(R.id.tvTenGoi);
            cvGoiCredit = itemView.findViewById(R.id.cvGoiCredit);
            cvGoiCredit.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Mua Credit");
            builder.setMessage("Bạn Có Muốn Mua Gem Không?");
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    muaCreditPressenter.handleBuyCredit(Integer.parseInt(tvSoTien.getText().toString()));
                    dialogInterface.dismiss();
                }
            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.create().show();
        }
    }
}
