package com.example.eleaveapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Common_employee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_employee);
        Intent intent = getIntent();
        int position =intent.getIntExtra("position",1);

        switch (position)
        {
            case 1:
                Apply_now_fragment apply = new Apply_now_fragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.common_employe_activity,apply);
                transaction.commit();
                break;
            case 4:
                View_all_fragment all = new View_all_fragment();
                FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
                transaction3.add(R.id.common_employe_activity,all);
                transaction3.commit();
                break;
            default:
                Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show();

        }

    }
}