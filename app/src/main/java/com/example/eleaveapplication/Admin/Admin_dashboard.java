package com.example.eleaveapplication.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.eleaveapplication.Common_employee;
import com.example.eleaveapplication.Employee_dashboard;
import com.example.eleaveapplication.R;
import com.example.eleaveapplication.Splash_screen;

public class Admin_dashboard extends AppCompatActivity {
    CardView pending,rejected,approved,allemp,lo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        pending = findViewById(R.id.adminpending);
        rejected = findViewById(R.id.adminrejected);
        approved = findViewById(R.id.adminapproved);
        allemp = findViewById(R.id.admin_allemployee);
        lo = findViewById(R.id.logout);

        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Admin_dashboard.this, Admin_common.class);
                intent.putExtra("admin_position",1);
                startActivity(intent);
            }
        });
        approved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Admin_common.class);
                intent.putExtra("admin_position",2);
                startActivity(intent);
            }
        });
        rejected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Admin_common.class);
                intent.putExtra("admin_position",3);
                startActivity(intent);
            }
        });
        allemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Admin_common.class);
                intent.putExtra("admin_position",4);
                startActivity(intent);
            }
        });
        lo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Splash_screen.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void onBackPressed()
    {

    }
}