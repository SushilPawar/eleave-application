package com.example.eleaveapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eleaveapplication.Holder.All_emp_model;
import com.example.eleaveapplication.R;

import java.util.ArrayList;

public class All_emp_adapter extends RecyclerView.Adapter<All_emp_adapter.Myviewholder>
{
    ArrayList<All_emp_model> arrayList;
    Context context;

    public All_emp_adapter( Context context,ArrayList<All_emp_model> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View  view = inflater.inflate(R.layout.custom_viewall,parent,false);
        return new Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        holder.name.setText(arrayList.get(position).getName());
        holder.email.setText(arrayList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class Myviewholder extends RecyclerView.ViewHolder{
        TextView name,email;

        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
        }
    }
}
