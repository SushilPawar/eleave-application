package com.example.eleaveapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eleaveapplication.Adapter.All_emp_adapter;
import com.example.eleaveapplication.Adapter.Ar_leaves_adapter;
import com.example.eleaveapplication.Admin.Admin_dashboard;
import com.example.eleaveapplication.Holder.All_emp_model;
import com.example.eleaveapplication.Holder.Ar_leaves_model;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link View_all_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class View_all_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView rview;
    Databasehelper db;
    ArrayList<Ar_leaves_model> arrayList;
    Ar_leaves_adapter adapter;
    public View_all_fragment() {
        // Required empty public constructor
    }

    public static View_all_fragment newInstance(String param1, String param2) {
        View_all_fragment fragment = new View_all_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_all_fragment, container, false);
        rview =view.findViewById(R.id.employee_viewall);
        db = new Databasehelper(getActivity());
        arrayList = new ArrayList<>();
        adapter = new Ar_leaves_adapter(viewall(),getActivity());
        rview.setAdapter(adapter);
        rview.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    public ArrayList<Ar_leaves_model> viewall()
    {
        SharedPreferences result = getActivity().getSharedPreferences("savedata",MODE_PRIVATE);
        String username = result.getString("username",null);
        arrayList = new ArrayList<>();
        Cursor cursor =db.all_employee_leaves(username);
        if(cursor.getCount()==0)
        {
            Toast.makeText(getActivity(),"No DATA FOUND",Toast.LENGTH_SHORT);
        }
        else
        {
            cursor.moveToFirst();
            do
            {
                Ar_leaves_model rleave =new Ar_leaves_model();

                rleave.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                rleave.setReason(cursor.getString(cursor.getColumnIndex("reason")));
                rleave.setFromdate(cursor.getString(cursor.getColumnIndex("fromdate")));
                rleave.setTodate(cursor.getString(cursor.getColumnIndex("todate")));
                rleave.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                rleave.setStatus(cursor.getString(cursor.getColumnIndex("status")));
                arrayList.add(rleave);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return arrayList;
    }

}