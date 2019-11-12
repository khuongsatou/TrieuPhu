package com.nvk.doanailatrieuphu.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nvk.doanailatrieuphu.Activity.MuaCreaditActivity;
import com.nvk.doanailatrieuphu.Controller.NguoiChoiController;
import com.nvk.doanailatrieuphu.Model.GoiCredit;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.KEY_CREDIT;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.KEY_ID;
import static com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis.BASE;
import static com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis.URI_NGUOI_CHOI_UPDATE_CREDIT;

public class MuaCreditAdapter extends RecyclerView.Adapter<MuaCreditAdapter.MuaCreditHolder> {
    private List<GoiCredit> goiCredits;
    private Context context;
    private NguoiChoi nguoiChoi;

    public MuaCreditAdapter(List<GoiCredit> goiCredits, Context context,NguoiChoi nguoiChoi) {
        this.goiCredits = goiCredits;
        this.context = context;
        this.nguoiChoi = nguoiChoi;
    }

    @NonNull
    @Override
    public MuaCreditHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_goi_credit,parent,false);
        return new MuaCreditHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MuaCreditHolder holder, int position) {
        GoiCredit goiCredit = goiCredits.get(position);
        holder.tvCredit.setText(goiCredit.getCredit()+"");
        holder.tvTien.setText(goiCredit.getSo_tien()+"");
    }

    @Override
    public int getItemCount() {
        return goiCredits.size();
    }

    public class MuaCreditHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvCredit,tvTien;
        private CardView cvItem;
        public MuaCreditHolder(@NonNull View itemView) {
            super(itemView);
            tvCredit = itemView.findViewById(R.id.tvCredit);
            tvTien = itemView.findViewById(R.id.tvTien);
            cvItem = itemView.findViewById(R.id.cvItem);
            cvItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Mua Credit");
            builder.setMessage("Bạn Có Muốn Mua Gem Không?");
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    int credit = Integer.parseInt(tvCredit.getText().toString());
                    credit+= nguoiChoi.getCredit();
                    nguoiChoi.setCredit(credit);
                    final Map<String,Integer> startMap = new HashMap<>();
                    startMap.put(KEY_ID,nguoiChoi.getId());
                    startMap.put(KEY_CREDIT,nguoiChoi.getCredit());
                    StringRequest request = new StringRequest(Request.Method.POST, BASE + URI_NGUOI_CHOI_UPDATE_CREDIT, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject objCredit = new JSONObject(response);
                                boolean result = objCredit.getBoolean("success");
                                if (result){
                                    MuaCreaditActivity muaCreaditActivity = (MuaCreaditActivity) context;
                                    muaCreaditActivity.tvTinDung.setText(nguoiChoi.getCredit() + "");
                                    Toast.makeText(context, "Đã Mua", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context,"Không thể Mua",Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context,"Server Offline",Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> map = new HashMap<>();
                            map.put(KEY_ID,String.valueOf(startMap.get(KEY_ID)));
                            map.put(KEY_CREDIT,String.valueOf(startMap.get(KEY_CREDIT)));
                            return map;
                        }
                    };
                    RequestQueue  queue = Volley.newRequestQueue(context);
                    queue.add(request);
                    dialogInterface.dismiss();
                }
            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
}
