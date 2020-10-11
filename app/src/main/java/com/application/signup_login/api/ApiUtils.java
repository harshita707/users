package com.application.signup_login.api;

public class ApiUtils {

    public static final String BASE_URL = "https://reqres.in/api/";

    public static ApiInterface getApiService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiInterface.class);
    }
}
