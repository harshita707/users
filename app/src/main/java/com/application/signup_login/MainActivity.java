package com.application.signup_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.application.signup_login.adapter.UserDetailsAdpter;
import com.application.signup_login.api.ApiUtils;
import com.application.signup_login.model.Item;
import com.application.signup_login.model.UserDetails;
import com.application.signup_login.model.Users;
import com.application.signup_login.utils.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Item> itemArrayList =new ArrayList<>();
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Please wait...");
        dialog.show();

        Call<Users> call = ApiUtils.getApiService().getUsers("1");
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (!response.isSuccessful()) {
                    Log.e("Server error", "" + response.code());
                    Toast.makeText(getApplicationContext(), "Error code: " + response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    Users result = response.body();
                    List<UserDetails> userDetailsList = result.getData();

                    for (int i = 0; i < userDetailsList.size(); i++) {

                        UserDetails currentArticle = userDetailsList.get(i);
                        itemArrayList.add(new Item(currentArticle.getEmail(),
                                currentArticle.getFirstName(), currentArticle.getLastName()));

                    }
                    RecyclerView recyclerView = findViewById(R.id.recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                    RecyclerViewClickListener listener = new RecyclerViewClickListener() {
                        @Override
                        public void onClick(View view, int position) {
                            Toast.makeText(getBaseContext(), "Position " + position,
                                    Toast.LENGTH_SHORT).show();
                            CustomDialogClass cdd=new CustomDialogClass(MainActivity.this);
                            cdd.show();

                        }
                    };

                    UserDetailsAdpter adapter = new UserDetailsAdpter(itemArrayList,getBaseContext(), listener);
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