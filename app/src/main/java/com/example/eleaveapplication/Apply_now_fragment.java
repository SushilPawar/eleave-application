package com.example.eleaveapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class Apply_now_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Spinner spinner;
    TextView to,from;
    Button apply;
    RadioButton singleday, multipledays;
    EditText et1, et2, description;
    Databasehelper db;
    String dfrom,dto;
    public Apply_now_fragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static Apply_now_fragment newInstance(String param1, String param2) {
        Apply_now_fragment fragment = new Apply_now_fragment();
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
        View view = inflater.inflate(R.layout.fragment_apply_now_fragment, container, false);
        spinner = view.findViewById(R.id.reasonspinner);
        singleday = view.findViewById(R.id.singleday);
        et1 = view.findViewById(R.id.et1);
        et2 = view.findViewById(R.id.et2);
        to = view.findViewById(R.id.todatetv);
        from = view.findViewById(R.id.fromdatetv);
        multipledays = view.findViewById(R.id.multipledays);
        apply = view.findViewById(R.id.apply);
        description = view.findViewById(R.id.description);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.reasonlist, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        db = new Databasehelper(getActivity());
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        et1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth ,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date = day+"-"+month+"-"+year;
                        et1.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        et2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date = day+"-"+month+"-"+year;
                        et2.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        singleday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et2.setVisibility(View.GONE);
                to.setVisibility(View.GONE);
                from.setText("Date:");

            }
        });
        multipledays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et2.setVisibility(View.VISIBLE);
                to.setVisibility(View.VISIBLE);
                from.setText("From:");
            }
        });


        SharedPreferences result = getActivity().getSharedPreferences("savedata",MODE_PRIVATE);
        String username = result.getString("username",null);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(singleday.isChecked())
                {
                    dfrom=et1.getText().toString();
                    dto=et1.getText().toString();
                }
                else if(multipledays.isChecked())
                {

                    dfrom=et1.getText().toString();
                    dto=et2.getText().toString();
                }

                Boolean result = db.apply_leaves(username,spinner.getSelectedItem().toString(),dfrom,dto,description.getText().toString(),"pending");
                if(result ==true)
                {
                    Toast.makeText(getActivity(),"Applied Successfully",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(),Employee_dashboard.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getActivity(),"Application Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}
