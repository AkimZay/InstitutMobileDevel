package com.example.lab2;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public static DBHelper dbHelper;

    public void onMyButtonClick(View view){
        if (checkLogin()) {
            Intent myIntent = new Intent(this, Main2Activity.class);
            startActivity(myIntent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),"Ошибка входа!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    Button btnGo;
    EditText etUser;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this, "MyDatabase", null, 1);

        btnGo = (Button) findViewById(R.id.btnGo);
        etUser = (EditText) findViewById(R.id.etUser);
        etPassword = (EditText) findViewById(R.id.etPassword);
    }

    public boolean checkLogin() {
        ContentValues cv = new ContentValues();
        String username = etUser.getText().toString();
        String password = etPassword.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor c = db.query("User",
                new String[] {"login", "password"},
                "login = ? AND password = ?",
                new String[] {username, password},
                null, null, null);

        //Cursor c = db.rawQuery("Select * from MyDatabase where MyDatabase.login='" + username +"' and MyDatabase.password='" + password + "'",null);

        if(c.getCount() <= 0) {
            c.close();
            db.close();
            return false;
        } else {
            c.close();
            db.close();
            return true;
        }
    }
}
