package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kenlyrice.ListActivity;
import com.example.kenlyrice.R;
import com.example.pojo.PastPolicy;

import java.util.List;

public class PolicyAdapter extends RecyclerView.Adapter<PolicyAdapter.PolicyHolder> {

    private Context context;
    private List<PastPolicy.Datum> list;

    public PolicyAdapter(Context context, List<PastPolicy.Datum> list) {

        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public PolicyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PolicyHolder(LayoutInflater.from(context).inflate(R.layout.pastrc_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PolicyHolder holder, int position) {

        holder.id_init_text.setText(list.get(position).getClient().toUpperCase().charAt(0)+"");
        holder.id_nameTxt.setText(list.get(position).getClient());
        holder.id_policyNum.setText(list.get(position).getPolicyNo());
        holder.id_date.setText(list.get(position).getDtc1()+" To "+list.get(position).getDtc2());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PolicyHolder extends RecyclerView.ViewHolder {

        TextView id_init_text, id_nameTxt, id_policyNum, id_date;


        public PolicyHolder(@NonNull View itemView) {
            super(itemView);

            id_init_text    = itemView.findViewById(R.id.id_init_text);
            id_nameTxt      = itemView.findViewById(R.id.id_nameTxt);
            id_policyNum    = itemView.findViewById(R.id.id_policyNum);
            id_date         = itemView.findViewById(R.id.id_date);

        }
    }
}
