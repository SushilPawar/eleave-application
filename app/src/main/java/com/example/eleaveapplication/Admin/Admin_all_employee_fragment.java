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

import com.example.eleaveapplication.Adapter.All_emp_adapter;
import com.example.eleaveapplication.Databasehelper;
import com.example.eleaveapplication.Holder.All_emp_model;
import com.example.eleaveapplication.R;
import com.example.eleaveapplication.View_all_fragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Admin_all_employee_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Admin_all_employee_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView rview;
    Databasehelper mydb;
    ArrayList<All_emp_model> arrayList;
    All_emp_adapter customadapter;
    public Admin_all_employee_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Admin_all_employee_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Admin_all_employee_fragment newInstance(String param1, String param2) {
        Admin_all_employee_fragment fragment = new Admin_all_employee_fragment();
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
        View view = inflater.inflate(R.layout.fragment_admin_all_employee_fragment, container, false);
        rview = view.findViewById(R.id.viewallrview);
        arrayList = new ArrayList<>();
        mydb = new Databasehelper(getActivity());
        customadapter =new All_emp_adapter(getActivity(),displaydata());
        rview.setAdapter(customadapter);
        rview.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
    public ArrayList<All_emp_model> displaydata()
    {
        arrayList = new ArrayList<>();
        Cursor cursor =mydb.readalldata();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(getActivity(),"No DATA FOUND",Toast.LENGTH_SHORT);
        }
        else {

            cursor.moveToFirst();
            do {
                All_emp_model companyDetailsModel=new All_emp_model();
                companyDetailsModel.setName(cursor.getString(cursor.getColumnIndex("username")));
                companyDetailsModel.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                arrayList.add(companyDetailsModel);
            }
            while (cursor.moveToNext());
        }
        mydb.close();
        return arrayList;
    }
}