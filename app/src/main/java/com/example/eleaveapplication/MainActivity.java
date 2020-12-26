package com.example.eleaveapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button login;
    Databasehelper DB;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.emplogin);
        DB =new Databasehelper(this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uname = username.getText().toString();
                String pass = password.getText().toString();

                if(uname.equals("")||pass.equals(""))
                {
                    Toast.makeText(MainActivity.this,"Plead Fill Fields",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean checkuser = DB.checkusernamepassword(uname,pass);
                    if(checkuser == true)
                    {
                        sharedPreferences = getSharedPreferences("savedata",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username",uname);
                        editor.apply();

                        Toast.makeText(MainActivity.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,Employee_dashboard.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Wrong Password",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}