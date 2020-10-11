package com.application.signup_login.api;

import com.application.signup_login.model.UserDetails;
import com.application.signup_login.model.Users;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("users")
    Call<Users> getUsers(
            @Query("page") String page
    );
}
