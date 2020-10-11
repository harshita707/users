package com.application.signup_login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    public TextView fname_tv, lname_tv, email_tv,mobile_tv, address_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

         Intent myintent = getIntent();

         String fname = myintent.getStringExtra("userFname");
         String lname = myintent.getStringExtra("userLname");
         String email = myintent.getStringExtra("userEmail");
         String mobile = myintent.getStringExtra("userMobile");
         String address = myintent.getStringExtra("userAddress");

         fname_tv = findViewById(R.id.user_fname);
        lname_tv = findViewById(R.id.user_lname);
        email_tv = findViewById(R.id.user_email);
        mobile_tv = findViewById(R.id.user_mobile);
        address_tv = findViewById(R.id.user_address);

        fname_tv.setText(fname);
        lname_tv.setText(lname);
        email_tv.setText(email);
        mobile_tv.setText(mobile);
        address_tv.setText(address);


    }
}