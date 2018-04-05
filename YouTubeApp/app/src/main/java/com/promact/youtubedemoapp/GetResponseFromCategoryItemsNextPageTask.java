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
 * Created by grishma on 20-03-2017.
 *//*

public class GetResponseFromCategoryItemsNextPageTask extends AsyncTask<String,String,String> {

    private Context mContext;
    private CategoryResponse.Items items;
    private String nextpageToken;
    private JsonResponseCategory<ArrayList<CategoryChannelsResponse>> callback;
    public GetResponseFromCategoryItemsNextPageTask(Context context, CategoryResponse.Items items, JsonResponseCategory<ArrayList<CategoryChannelsResponse>> cb,String nextpageToken) {
        mContext = context;
        this.items=items;
        this.callback = cb;
        this.nextpageToken=nextpageToken;
    }
    @Override
    protected String doInBackground(String... strings)
    {
        String response="";
        try {
            response=new ApiClient().sendGetForChannelsNextPage(items.getId(),nextpageToken);
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
