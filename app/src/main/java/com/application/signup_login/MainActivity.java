package com.application.signup_login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.signup_login.adapter.UserDetailsAdpter;
import com.application.signup_login.api.ApiUtils;
import com.application.signup_login.model.Item;
import com.application.signup_login.model.UserDetails;
import com.application.signup_login.model.Users;
import com.application.signup_login.utils.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Item> itemArrayList = new ArrayList<>();
    private ProgressDialog dialog;
    Button profile_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent myintent = getIntent();

        profile_btn = findViewById(R.id.profile_btn);

        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Profile.class);
                intent.putExtra("userFname", myintent.getStringExtra("userFname"));
                intent.putExtra("userLname", myintent.getStringExtra("userLname"));
                intent.putExtra("userEmail", myintent.getStringExtra("userEmail"));
                intent.putExtra("userMobile", myintent.getStringExtra("userMobile"));
                intent.putExtra("userAddress", myintent.getStringExtra("userAddress"));
                MainActivity.this.startActivity(intent);
                finish();
            }
        });

        dialog = new ProgressDialog(this);
        dialog.setTitle("Please wait...");
        dialog.show();

        Call<Users> call = ApiUtils.getApiService().getUsers("1");
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (!response.isSuccessful()) {
                    Log.e("Server error", "" + response.code());
                    Toast.makeText(MainActivity.this, "Error code: " + response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    Users result = response.body();
                    List<UserDetails> userDetailsList = result.getData();

                    for (int i = 0; i < userDetailsList.size(); i++) {

                        UserDetails currentArticle = userDetailsList.get(i);
                        itemArrayList.add(new Item(currentArticle.getEmail(),
                                currentArticle.getFirstName(), currentArticle.getLastName()));

                    }
                    Collections.sort(itemArrayList, new Comparator<Item>() {
                        @Override
                        public int compare(Item o1, Item o2) {
                            if (o1.getFname().compareTo(o2.getFname()) == 0) {
                                return o1.getLname().compareTo(o2.getLname());
                            } else {
                                return o1.getFname().compareTo(o2.getFname());
                            }
                        }
                    });
                    RecyclerView recyclerView = findViewById(R.id.recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                    RecyclerViewClickListener listener = new RecyclerViewClickListener() {
                        @Override
                        public void onClick(View view, int position) {
                            CustomDialogClass cdd = new CustomDialogClass(MainActivity.this, position);
                            cdd.show();

                        }
                    };

                    UserDetailsAdpter adapter = new UserDetailsAdpter(itemArrayList, MainActivity.this, listener);
                    recyclerView.setAdapter(adapter);
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Log.e("Callback failure", t.getMessage());
                Toast.makeText(MainActivity.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });

    }


}