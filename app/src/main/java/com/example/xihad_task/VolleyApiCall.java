package com.example.xihad_task;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VolleyApiCall {

    ArrayList<UserModel> userList = new ArrayList<>();
    ArrayList<UserDataModel> postList = new ArrayList<>();

    private static final String TAG = VolleyApiCall.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private final Context mContext;

    public VolleyApiCall(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public void getUserInfo(VolleyCallback callback) {

        String url = "https://jsonplaceholder.typicode.com/photos";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject singleObject = jsonArray.getJSONObject(i);
                                UserModel userModel = new UserModel(
                                        singleObject.getInt("albumId"),
                                        singleObject.getInt("id"),
                                        singleObject.getString("title"),
                                        singleObject.getString("url"),
                                        singleObject.getString("thumbnailUrl")
                                );
                                userList.add(userModel);
                            }
                            callback.onSuccess(userList);
                            Log.d(TAG, "onResponse: " + userList.size());
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "error: " + error.toString());
                callback.onFailure(error.toString());
            }
        });

// Add the request to the RequestQueue.
        mRequestQueue.add(stringRequest);
    }

    public void getPostInfo(VolleyCallBackOther callback) {

        String url = "https://jsonplaceholder.typicode.com/posts";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject singleObject = jsonArray.getJSONObject(i);
                                UserDataModel userDataModel = new UserDataModel(
                                        singleObject.getInt("userId"),
                                        singleObject.getInt("id"),
                                        singleObject.getString("title"),
                                        singleObject.getString("body")
                                        );
                                postList.add(userDataModel);
                                // create a new PostModel object and add it to a list
                            }
                            callback.onSuccess(postList);
                            Log.d(TAG, "onResponse: " + postList.size());
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "error: " + error.toString());
                callback.onFailure(error.toString());
            }
        });

        mRequestQueue.add(stringRequest);
    }


    public interface VolleyCallback {
        //void onSuccess(ArrayList<UserDataModel> userList);


        void onFailure(String errorMessage);

        void onSuccess(ArrayList<UserModel> userList);
    }

    public interface VolleyCallBackOther{

        void onSuccess(ArrayList<UserDataModel> postList);

        void onFailure(String toString);

    }

}

