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
import com.example.eleaveapplication.Holder.Ar_leaves_model;
import com.example.eleaveapplication.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Admin_rejected_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Admin_rejected_fragment extends Fragment {

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

    public Admin_rejected_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Admin_rejected_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Admin_rejected_fragment newInstance(String param1, String param2) {
        Admin_rejected_fragment fragment = new Admin_rejected_fragment();
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
        View view = inflater.inflate(R.layout.fragment_admin_rejected_fragment, container, false);
        rview = view.findViewById(R.id.arfview);
        db = new Databasehelper(getActivity());
        arrayList = new ArrayList<>();
        adapter = new Ar_leaves_adapter(rejected_leave(),getActivity());
        rview.setAdapter(adapter);
        rview.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
    public ArrayList<Ar_leaves_model> rejected_leave()
    {
        arrayList = new ArrayList<>();
        Cursor cursor = db.ar_rejected_leave();
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