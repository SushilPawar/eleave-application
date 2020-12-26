package com.example.eleaveapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Employee_dashboard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_dashboard);

        CardView apply,rejected,approved,all,logout;

        apply = findViewById(R.id.apply_leave);
//        rejected = findViewById(R.id.rejected_leave);
//        approved =findViewById(R.id.approved_leave);
        all = findViewById(R.id.viewall_leave);
        logout = findViewById(R.id.logout);



        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Employee_dashboard.this,Common_employee.class);
                intent.putExtra("position",1);
                startActivity(intent);
            }
        });
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Common_employee.class);
                intent.putExtra("position",4);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Employee_dashboard.this,Splash_screen.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void onBackPressed()
    { }

}