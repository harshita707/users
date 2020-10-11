package com.application.signup_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    public EditText fname;
    public EditText lname;
    public EditText email;
    public EditText password;
    public EditText confirmPassword;
    public EditText mobile;
    public EditText address;
    public Button signup_btn;
    public Button login_btn;

    MyDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fname = findViewById(R.id.first_name);
        lname = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);
        mobile = findViewById(R.id.mobile);
        address = findViewById(R.id.address);
        signup_btn = findViewById(R.id.signup_btn);
        login_btn = findViewById(R.id.login_btn);

        myDB = new MyDatabaseHelper(SignUpActivity.this);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fname.getText().toString().trim() != "" && fname.getText().toString().trim().length() != 0) {
                    if(lname.getText().toString().trim() != "" &&
                            lname.getText().toString().trim().length() != 0) {
                        if(validateEmail(email)) {
                            if(password.getText().toString().equals(confirmPassword.getText().toString())) {
                                if(validateMobile(mobile)) {
                                    if(address.getText().toString().trim() != "" &&
                                            address.getText().toString().trim().length() != 0) {

                                        myDB.addUser(fname.getText().toString().trim(),
                                                lname.getText().toString().trim(),
                                                email.getText().toString().trim(),
                                                password.getText().toString(),
                                                mobile.getText().toString().trim(),
                                                address.getText().toString().trim());

                                        Intent intent = new Intent(SignUpActivity.this,
                                                LoginActivity.class);
                                        SignUpActivity.this.startActivity(intent);
                                        finish();
                                    } else {
                                        address.setError("Please enter your address!");
                                    }
                                } else {
                                    mobile.setError("Please enter valid mobile number!");
                                }
                            } else {
                                confirmPassword.setError("Passwords don't match!");
                            }
                        } else {
                            email.setError("Please enter valid email!");
                        }
                    } else {
                        lname.setError("Please enter your last name!");
                    }
                } else {
                    fname.setError("Please enter your first name!");
                }
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                SignUpActivity.this.startActivity(intent);
                finish();
            }
        });

    }

    private boolean validateMobile(EditText mobile) {
        String regex = "(0/91)?[7-9][0-9]{9}";
        return mobile.getText().toString().trim().matches(regex);
    }

    private boolean validateEmail(EditText email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.getText().toString().trim().matches(regex);
    }
}