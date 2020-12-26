package com.example.eleaveapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.eleaveapplication.MainActivity;
import com.example.eleaveapplication.Register;

public class Databasehelper extends SQLiteOpenHelper
{
    private static final String db_name = "Eleave.db";
    Context context;
    public Databasehelper(@Nullable Context context)
    {
        super(context, db_name, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE employee(_id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT,email TEXT, password TEXT);";
        String query1 = "CREATE TABLE leaves(Leaveid INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT,reason TEXT, fromdate TEXT,todate TEXT,description TEXT,status TEXT);";
        db.execSQL(query);
        db.execSQL(query1);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS employee");
        db.execSQL("DROP TABLE IF EXISTS leaves");
    }

    public Boolean addemp (String username,String email, String password)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username",username);
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result = db.insert("employee",null,contentValues);
        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public Boolean apply_leaves (String username,String reason, String from,String to, String description, String status)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username",username);
        contentValues.put("reason",reason);
        contentValues.put("fromdate",from);
        contentValues.put("todate",to);
        contentValues.put("description",description);
        contentValues.put("status",status);
        long result = db.insert("leaves",null,contentValues);
        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean checkuser(String username)
    {
        SQLiteDatabase Mydb = this.getReadableDatabase();
        Cursor cursor = Mydb.rawQuery("SELECT * FROM employee WHERE username= ?",new String[] {username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean checkemail(String email)
    {
        SQLiteDatabase Mydb = this.getWritableDatabase();
        Cursor cursor = Mydb.rawQuery("SELECT * FROM employee WHERE email = ?",new String[]{email});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean checkusernamepassword(String username,String password)
    {
        SQLiteDatabase Mydb = this.getWritableDatabase();
        Cursor cursor = Mydb.rawQuery("SELECT * FROM employee WHERE username = ? and password = ?",new String[] {username,password});
        if (cursor.getCount()>0)
            return true;
        else return false;
    }
    public Cursor readalldata()
    {
        String query = "SELECT * FROM employee";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db !=null)
        {
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
    public Cursor pending_leaves()
    {
        String query = "SELECT * FROM leaves WHERE status = 'pending'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db !=null)
        {
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
    public boolean approve_leave(String username,String description)
    {
        SQLiteDatabase Mydb = this.getWritableDatabase();
        Cursor cursor = Mydb.rawQuery("UPDATE leaves SET status = 'approved' WHERE username = ? AND description = ?",new String[] {username,description});
        if (cursor.getCount()>0)
            return true;
        else
            {
            return false;
            }
    }

    public boolean reject_leave(String username,String description)
    {
        SQLiteDatabase Mydb = this.getWritableDatabase();
        Cursor cursor = Mydb.rawQuery("UPDATE leaves SET status = 'rejected' WHERE username = ? AND description = ?",new String[] {username,description});
        if (cursor.getCount()>0)
            return true;
        else return false;
    }

    public Cursor ar_approved_leave()
    {
        String query = "SELECT * FROM leaves WHERE status = 'approved'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db !=null)
        {
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
    public Cursor ar_rejected_leave() {
        String query = "SELECT * FROM leaves WHERE status = 'rejected'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    public Cursor all_employee_leaves(String username)
    {
        String query = "SELECT * FROM leaves WHERE username = '"+username+"'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}

