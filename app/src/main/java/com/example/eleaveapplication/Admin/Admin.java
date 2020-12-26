package com.example.eleaveapplication.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eleaveapplication.R;

public class Admin extends AppCompatActivity {
    EditText id,password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        id = findViewById(R.id.adminid);
        password = findViewById(R.id.adminpass);
        login = findViewById(R.id.adminlogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id.getText().toString().equals("admin") && password.getText().toString().equals("admin"))
                {
                    Toast.makeText(Admin.this,"Welcome Admin",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Admin.this,Admin_dashboard.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(Admin.this,"Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}