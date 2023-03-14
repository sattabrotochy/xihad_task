package com.example.xihad_task;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RetrofitApiCall {
    private UserApiService mUserApiService;

    public RetrofitApiCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mUserApiService = retrofit.create(UserApiService.class);
    }

    public void getUser(final RetrofitCallback callback) {
        Call<List<UserModel>> call = mUserApiService.getUsers();
        call.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                if (response.isSuccessful()) {
                    List<UserModel> userList = response.body();
                    callback.onSuccess(userList);
                } else {
                    String errorMessage = "Error getting users: " + response.code();
                    callback.onFailure(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                String errorMessage = "Error getting users: " + t.getMessage();
                callback.onFailure(errorMessage);
            }
        });
    }

    public interface UserApiService {
        @GET("/photos")
        Call<List<UserModel>> getUsers();
    }

    public interface RetrofitCallback {
        void onSuccess(List<UserModel> userList);

        void onFailure(String errorMessage);
    }
}
