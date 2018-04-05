/*
package com.promact.youtubeapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

*/
/**
 * Created by grishma on 15-03-2017.
 *//*

public class GetResponseFromCategoryItemsTask extends AsyncTask<String,String,String> {

    private Context mContext;
    private CategoryResponse.Items items;
    private JsonResponseCategory<ArrayList<CategoryChannelsResponse>> callback;
    public GetResponseFromCategoryItemsTask(Context context, CategoryResponse.Items items, JsonResponseCategory<ArrayList<CategoryChannelsResponse>> cb) {
        mContext = context;
        this.items=items;
        this.callback = cb;
    }
    @Override
    protected String doInBackground(String... strings)
    {
        String response="";
        try {
            response=new ApiClient().sendGetForChannels(items.getId());
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
        CategoryChannelsResponse categoriesChannels = gson.fromJson(response, CategoryChannelsResponse.class);
//        System.out.println("--------"+categoriesChannels.getItems().get(0).getId());
        callback.onTaskComplete(categoriesChannels);
    }
}
*/
