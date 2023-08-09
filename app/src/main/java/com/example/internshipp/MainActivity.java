package com.example.internshipp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {

    Button login,signup;
    EditText email,password;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db= openOrCreateDatabase("On_Internshipp",MODE_PRIVATE,null);
        String tableQuery = "CREATE TABLE IF NOT EXISTS USERS(USERID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR(100),EMAIL VARCHAR(100),CONTACT INT(10),PASSWORD VARCHAR(100),GENDER VARCHAR(6),CITY VARCHAR(50),DOB VARCHAR(10))";
        db.execSQL(tableQuery);


        login = findViewById(R.id.login);
        email =findViewById(R.id.main_email);
        password =findViewById(R.id.main_password);
        signup = findViewById(R.id.sign_up);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().trim().equals("")) {
                    email.setError("Email Id Required");
                } else if (!email.getText().toString().trim().matches(emailPattern)) {
                    email.setError("Valid Email Id Required");
                } else if (password.getText().toString().trim().equals("")) {
                    password.setError("Password Required");
                } else if (password.getText().toString().trim().length() < 6) {
                    password.setError("Min. 6 Char Password Required");
                } else {

                    String selectQuery = "SELECT * FROM USERS WHERE EMAIL='" + email.getText().toString() + "' AND PASSWORD='" + password.getText().toString() + "'";
                    Cursor cursor = db.rawQuery(selectQuery, null);
                    Log.d("CURSOR_COUNT", String.valueOf(cursor.getCount()));


                    if (cursor.getCount() > 0) {

                        while (cursor.moveToNext()){
                            String sUserId = cursor.getString(0);
                            String sName = cursor.getString(1);
                            String sEmail = cursor.getString(2);
                            String sContact = cursor.getString(3);
                            String sPassword = cursor.getString(4);
                            String sGender = cursor.getString(5);
                            String sCity = cursor.getString(6);
                            String sDob = cursor.getString(7);
                            Log.d("USER_DATA ",sUserId+"\n"+sName+"\n"+sEmail+"\n"+sContact+"\n"+sPassword+"\n"+sGender+"\n"+sCity+"\n"+sDob);
                        }

                        System.out.println("Log In Successfully");

                        //for showing login press button sucessfuly round shape
                        Toast.makeText(MainActivity.this, "Login Sucessfully", Toast.LENGTH_SHORT).show();
                        //new CommonMethod(MainActivity.this, "Login Successfully");

                        //for showing login press button sucessfully black reakangle shape in last
                        Snackbar.make(view, "login sucessfully", Snackbar.LENGTH_SHORT).show();
                        //new CommonMethod(view,"Login Successfully");

                        //intent using for one page to another page information
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                        //new CommonMethod(MainActivity.this, HomeActivity.class);

                    }
                    else {
                        Toast.makeText(MainActivity.this, "Login Unsucessfully", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });


    }
}