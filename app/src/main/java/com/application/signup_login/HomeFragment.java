package com.application.signup_login;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class HomeFragment extends Fragment {

    private ArrayList<Item> itemArrayList =new ArrayList<>();
    private ProgressDialog dialog;

    View v;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        dialog = new ProgressDialog(getContext());
//        dialog.setTitle("Please wait...");
//        dialog.show();
//
//        Call<Users> call = ApiUtils.getApiService().getUsers("1");
//        call.enqueue(new Callback<Users>() {
//            @Override
//            public void onResponse(Call<Users> call, Response<Users> response) {
//                if (!response.isSuccessful()) {
//                    Log.e("Server error", "" + response.code());
//                    Toast.makeText(getContext(), "Error code: " + response.code(), Toast.LENGTH_SHORT).show();
//                } else {
//                    Users result = response.body();
//                    List<UserDetails> userDetailsList = result.getData();
//
//                    for (int i = 0; i < userDetailsList.size(); i++) {
//
//                        UserDetails currentArticle = userDetailsList.get(i);
//                        itemArrayList.add(new Item(currentArticle.getEmail(),
//                                currentArticle.getFirstName(), currentArticle.getLastName()));
//
//                    }
//
//
//                    dialog.dismiss();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Users> call, Throwable t) {
//                Log.e("Callback failure", t.getMessage());
//                Toast.makeText(getContext(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       v= inflater.inflate(R.layout.fragment_home, container, false);
//        RecyclerView recyclerView = v.findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                Toast.makeText(getContext(), "Position " + position,
//                        Toast.LENGTH_SHORT).show();
//                CustomDialogClass cdd=new CustomDialogClass(getActivity());
//                cdd.show();
//
//            }
//        };
//
//        UserDetailsAdpter adapter = new UserDetailsAdpter(itemArrayList,getContext(), listener);
//        recyclerView.setAdapter(adapter);

        return v;
    }
}