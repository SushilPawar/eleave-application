package com.example.eleaveapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText emp_username, emp_pass, emp_email, confirm_password;
    Button register;
    Databasehelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emp_username = findViewById(R.id.username);
        emp_pass = findViewById(R.id.emppassword);
        emp_email = findViewById(R.id.empemail);
        confirm_password = findViewById(R.id.confpass);
        register = findViewById(R.id.register);
        DB = new Databasehelper(this);



       register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view)
           {
               String uname = emp_username.getText().toString();
               String pass = emp_pass.getText().toString();
               String email = emp_email.getText().toString();
               String repass = confirm_password.getText().toString();


               String emailPattern = "[a-z0-9._-]+@[a-z]+\\.+[a-z]+";
               if(email.matches(emailPattern))
               {
                   if (uname.equals("") || pass.equals("") || repass.equals("") || email.equals(""))
                   {
                       Toast.makeText(Register.this,"Please Fill all fields",Toast.LENGTH_SHORT).show();
                   }
                   else
                   {
                       if(pass.equals(repass))
                       {
                           Boolean checkuser = DB.checkuser(uname);
                           if(checkuser == false)
                           {
                               Boolean checkemail = DB.checkemail(email);
                               if(checkemail == false)
                               {
                                   Boolean insertuser = DB.addemp(uname,email,pass);
                                   if(insertuser == true)
                                   {
                                       Toast.makeText(Register.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                                       Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                       startActivity(intent);
                                       finish();
                                   }
                                   else
                                   {
                                       Toast.makeText(Register.this,"Registration failed",Toast.LENGTH_SHORT).show();
                                   }
                               }
                               else
                               {
                                   Toast.makeText(Register.this,"Email already Exist",Toast.LENGTH_SHORT).show();
                               }
                           }
                           else
                           {
                               Toast.makeText(Register.this,"User Already Exist",Toast.LENGTH_SHORT).show();
                           }
                       }
                       else
                       {
                           Toast.makeText(Register.this,"Password Does not match",Toast.LENGTH_SHORT).show();
                       }
                   }

               }
               else
               {
                   Toast.makeText(Register.this,"Invalid Email Type",Toast.LENGTH_SHORT).show();
               }
           }
       });
    }
}

