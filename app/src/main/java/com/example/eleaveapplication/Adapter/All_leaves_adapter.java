package com.example.eleaveapplication.Adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eleaveapplication.Databasehelper;
import com.example.eleaveapplication.Holder.All_emp_model;
import com.example.eleaveapplication.Holder.All_leaves_model;
import com.example.eleaveapplication.R;

import java.util.ArrayList;

public class All_leaves_adapter extends RecyclerView.Adapter<All_leaves_adapter.viewholder>
{
    ArrayList<All_leaves_model> arrayList;
    Context context;


    public All_leaves_adapter(Context context,ArrayList<All_leaves_model> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View  view = inflater.inflate(R.layout.custom_all_leaves,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position)
    {
        holder.username.setText(arrayList.get(position).getUsername());
        holder.reason.setText(arrayList.get(position).getReason());
        holder.fromdate.setText(arrayList.get(position).getFromdate());
        holder.todate.setText(arrayList.get(position).getTodate());
        holder.description.setText(arrayList.get(position).getDescription());

        String uname = (String) holder.username.getText();
        String desci = (String) holder.description.getText();
        holder.yes.setOnClickListener(view -> {
            Databasehelper db = new Databasehelper(context);
            Boolean yea =db.approve_leave(uname,desci);
            if (yea == false)
            {
                Toast.makeText(context,"Leave Approved",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context,"Operation Failed",Toast.LENGTH_SHORT).show();
            }
        });
        holder.no.setOnClickListener(view ->
        {
            Databasehelper db = new Databasehelper(context);
            Boolean nah = db.reject_leave(uname,desci);
            if (nah == false)
            {
                Toast.makeText(context,"Leave Rejected",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context,"Operation Failed",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView username,reason,fromdate,todate,description;
        CheckBox yes,no;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.leave_name);
            reason = itemView.findViewById(R.id.leave_reason);
            fromdate = itemView.findViewById(R.id.leave_fromdate);
            todate = itemView.findViewById(R.id.leave_todate);
            description = itemView.findViewById(R.id.leave_description);

            yes = itemView.findViewById(R.id.chkYes);
            no = itemView.findViewById(R.id.chkNo);

        }
    }
}
