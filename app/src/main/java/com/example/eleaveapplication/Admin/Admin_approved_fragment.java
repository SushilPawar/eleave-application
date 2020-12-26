package com.example.eleaveapplication.Admin;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eleaveapplication.Adapter.Ar_leaves_adapter;
import com.example.eleaveapplication.Databasehelper;
import com.example.eleaveapplication.Holder.All_leaves_model;
import com.example.eleaveapplication.Holder.Ar_leaves_model;
import com.example.eleaveapplication.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Admin_approved_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Admin_approved_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView arrview;
    Databasehelper db;
    ArrayList<Ar_leaves_model> arrayList;
    Ar_leaves_adapter adapter;
    public Admin_approved_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Admin_approved_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Admin_approved_fragment newInstance(String param1, String param2) {
        Admin_approved_fragment fragment = new Admin_approved_fragment();
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
        View view = inflater.inflate(R.layout.fragment_admin_approved_fragment, container, false);
        arrview = view.findViewById(R.id.arrv);
        arrayList = new ArrayList<>();
        db = new Databasehelper(getContext());
        adapter = new Ar_leaves_adapter(approved_leaves(),getContext());
        arrview.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrview.setAdapter(adapter);
        return view;
    }
    public ArrayList<Ar_leaves_model> approved_leaves()
    {
        arrayList = new ArrayList<>();
        Cursor cursor = db.ar_approved_leave();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(getActivity(),"No DATA FOUND",Toast.LENGTH_SHORT);
        }
        else {

            cursor.moveToFirst();
            do {
                Ar_leaves_model aleave =new Ar_leaves_model();

                aleave.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                aleave.setReason(cursor.getString(cursor.getColumnIndex("reason")));
                aleave.setFromdate(cursor.getString(cursor.getColumnIndex("fromdate")));
                aleave.setTodate(cursor.getString(cursor.getColumnIndex("todate")));
                aleave.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                aleave.setStatus(cursor.getString(cursor.getColumnIndex("status")));
                arrayList.add(aleave);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return arrayList;
    }
}