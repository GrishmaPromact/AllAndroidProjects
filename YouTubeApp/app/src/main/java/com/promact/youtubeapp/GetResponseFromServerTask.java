package com.promact.youtubeapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * Created by grishma on 22-02-2017.
 */
public class GetResponseFromServerTask extends AsyncTask<String,String,String> {

    private Context mContext;
    private DownloadJsonString<ArrayList<Category>> callback;
    public GetResponseFromServerTask(Context context, DownloadJsonString<ArrayList<Category>> cb) {
        mContext = context;
        this.callback = cb;
    }
    @Override
    protected String doInBackground(String... strings)
    {
        String response="";
        try {
            response=new ApiClient().sendGet();

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return response;
        }
    }
    @Override
    protected void onPostExecute(String response) {
           /* ArrayList<Category> serverArrayList = new ArrayList<Category>();
            Log.d("----------response-----",response);
            if (response != null) {
                JSONArray jsonArray=null;
                try {
                    jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String serverId = jsonObject.getString("id");
                        String serverTitle = jsonObject.getString("title");
                        String serverDescripption = jsonObject.getString("description");
                        serverArrayList.add(new Category(serverId, serverTitle, serverDescripption));
                        callback.onTaskComplete(serverArrayList);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else {
                Log.e("HELLO", "Couldn't get json from server.");
                Toast.makeText(mContext,
                        "Couldn't get json from server. Check LogCat for possible errors!",
                        Toast.LENGTH_LONG)
                        .show();
            }*/
            Log.d("----------response-----",response);
       /* Gson gson=new Gson();
        Type type=new TypeToken<Collection<Category>>(){}.getType();
        Category post = gson.fromJson(response, Category.class);
        //Category[] categories=enums.toArray(new Category[enums.size()]);
        //System.out.println("--------TITLE---"+categories[0].getTitle());
        //return categories[0].getTitle();*/
        Gson gson = new Gson();
        //Type collectionType = new TypeToken<ArrayList<Category>>(){}.getType();
        Category post = gson.fromJson(response, Category.class);
        ArrayList<String>title=post.getTitles(post.getItems());
        callback.onTaskComplete(title);
        System.out.println("---TITLE--"+title.toString()+"\n");
       // callback.onTaskComplete(details);
            /*GsonBuilder builder = new GsonBuilder();
            Gson mGson = builder.create();
            ArrayList<Category> posts = new ArrayList<>();
            posts = (ArrayList<Category>) Arrays.asList(mGson.fromJson(response, Category[].class));
            callback.onTaskComplete(posts);*/
    }
}