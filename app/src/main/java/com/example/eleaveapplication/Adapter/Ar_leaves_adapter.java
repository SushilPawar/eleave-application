package com.example.eleaveapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.eleaveapplication.Holder.Ar_leaves_model;
import com.example.eleaveapplication.R;

import java.util.ArrayList;

public class Ar_leaves_adapter extends RecyclerView.Adapter<Ar_leaves_adapter.holder> {

    ArrayList<Ar_leaves_model> arrayList;
    Context context;

    public Ar_leaves_adapter(ArrayList<Ar_leaves_model> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_arleaves,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position)
    {
        holder.uname.setText(arrayList.get(position).getUsername());
        holder.reason.setText(arrayList.get(position).getReason());
        holder.fromdate.setText(arrayList.get(position).getFromdate());
        holder.todate.setText(arrayList.get(position).getTodate());
        holder.description.setText(arrayList.get(position).getDescription());
        holder.status.setText(arrayList.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class holder extends RecyclerView.ViewHolder{
        TextView uname,reason,fromdate,todate,description,status;
        public holder(@NonNull View itemView) {
            super(itemView);

            uname = itemView.findViewById(R.id.arleave_name);
            reason = itemView.findViewById(R.id.arleave_reason);
            fromdate = itemView.findViewById(R.id.arleave_fromdate);
            todate = itemView.findViewById(R.id.arleave_todate);
            description = itemView.findViewById(R.id.arleave_description);
            status = itemView.findViewById(R.id.arleave_status);

        }
    }
}
