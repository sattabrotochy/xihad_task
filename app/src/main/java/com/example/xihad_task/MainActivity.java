package com.example.xihad_task;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //    private RecyclerView mRecyclerView;
//    private UserAdapterClass mAdapter;
//    private List<UserModel> mUserList;
    VolleyApiCall mVolleyApiCall;
    RecyclerView mRecyclerView;
    UserAdapterClass mAdapter;
    List<UserDataModel> mUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        database database=new database(this);

        mRecyclerView = findViewById(R.id.recycleView);
//
        mVolleyApiCall = new VolleyApiCall(this);
        mVolleyApiCall.getPostInfo(new VolleyApiCall.VolleyCallBackOther() {
            @Override
            public void onFailure(String errorMessage) {

            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSuccess(ArrayList<UserDataModel> userList) {
                mUserList = userList;
                mAdapter.setUserList(mUserList);
                mAdapter.notifyDataSetChanged();
                for (UserDataModel userData : mUserList) {
                    database.instertData(userData);
                }

            }
        });

        RetrofitApiCall mRetrofitApiCall = new RetrofitApiCall();
        mRetrofitApiCall.getUser(new RetrofitApiCall.RetrofitCallback() {
            @Override
            public void onSuccess(List<UserModel> userList) {

            }

            @Override
            public void onFailure(String errorMessage) {

            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new UserAdapterClass(this, mUserList);
        mRecyclerView.setAdapter(mAdapter);


    }


}