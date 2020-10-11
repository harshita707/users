package com.application.signup_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    public EditText email;
    public EditText password;
    public Button signup_btn;
    public Button login_btn;

    MyDatabaseHelper myDB;
    ArrayList<String> user_id, user_fname,user_lname, user_email, user_password, user_mobile, user_address;
    int current_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signup_btn = findViewById(R.id.signup_btn);
        login_btn = findViewById(R.id.login_btn);

        myDB = new MyDatabaseHelper(LoginActivity.this);

        user_id = new ArrayList<>();
        user_fname = new ArrayList<>();
        user_lname = new ArrayList<>();
        user_email = new ArrayList<>();
        user_password = new ArrayList<>();
        user_mobile = new ArrayList<>();
        user_address = new ArrayList<>();

        storeDataInArray();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                current_user = user_email.indexOf(email.getText().toString().trim());

                if (current_user == -1) {
                    email.setError("User Does not exist");
                } else {
                    String password_input = user_password.get(current_user);
                    if (password.getText().toString().equals(password_input)) {
                        Toast.makeText(LoginActivity.this, "Successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("userFname", user_fname.get(current_user));
                        intent.putExtra("userLname", user_lname.get(current_user));
                        intent.putExtra("userEmail", user_email.get(current_user));
                        intent.putExtra("userMobile", user_mobile.get(current_user));
                        intent.putExtra("userAddress", user_address.get(current_user));
                        LoginActivity.this.startActivity(intent);
                        finish();
                    } else {
                        password.setError("Incorrect Password");
                    }
                }

            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                LoginActivity.this.startActivity(intent);
                finish();
            }
        });

    }

    private void storeDataInArray() {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        } else {
            while(cursor.moveToNext()) {
                user_id.add(cursor.getString(0));
                user_fname.add(cursor.getString(1));
                user_lname.add(cursor.getString(2));
                user_email.add(cursor.getString(3));
                user_password.add(cursor.getString(4));
                user_mobile.add(cursor.getString(5));
                user_address.add(cursor.getString(6));
            }
        }
    }
}