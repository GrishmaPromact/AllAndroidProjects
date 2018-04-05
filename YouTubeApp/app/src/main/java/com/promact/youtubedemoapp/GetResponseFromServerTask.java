package com.promact.youtubedemoapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * Created by grishma on 22-02-2017.
 */
public class GetResponseFromServerTask extends AsyncTask<String,String,String> {

    private Context mContext;
    private String token;
    private DownloadJsonString<ArrayList<CategoryResponse>> callback;
    public GetResponseFromServerTask(Context context,String token, DownloadJsonString<ArrayList<CategoryResponse>> cb) {
        mContext = context;
        this.callback = cb;
        this.token=token;
    }
    @Override
    protected String doInBackground(String... strings)
    {
        String response="";
        try {
            response=new ApiClient().sendGet(token);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return response;
        }
    }
    @Override
    protected void onPostExecute(String response) {

        Log.d("----------response-----",response);
        Gson gson = new GsonBuilder().create();
        CategoryResponse categories = gson.fromJson(response, CategoryResponse.class);
        callback.onTaskComplete(categories);
    }
}