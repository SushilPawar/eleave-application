package com.example.eleaveapplication.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.example.eleaveapplication.R;

public class Admin_common extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_common);
        Intent intent = getIntent();
        int admin_position = intent.getIntExtra("admin_position",1);

        switch (admin_position)
        {
            case 1:
                Admin_pending_fragment pending = new Admin_pending_fragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.admincommonfrag,pending);
                transaction.commit();
                break;
            case 2:
                Admin_approved_fragment approved = new Admin_approved_fragment();
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                transaction1.add(R.id.admincommonfrag,approved);
                transaction1.commit();
                break;
            case 3:
                Admin_rejected_fragment rejected = new Admin_rejected_fragment();
                FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                transaction2.add(R.id.admincommonfrag,rejected);
                transaction2.commit();
                break;
            case 4:
                Admin_all_employee_fragment adminall = new Admin_all_employee_fragment();
                FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
                transaction3.add(R.id.admincommonfrag,adminall);
                transaction3.commit();
                break;

            default:
                Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show();
        }
    }

}