package com.promact.apirequest;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by grishma on 22-02-2017.
 */
public class GetResponseFromServerTask extends AsyncTask<String,String,String> {

    private Context mContext;
    private DownloadJsonString<ArrayList<Info>> callback;
    public GetResponseFromServerTask( Context context,DownloadJsonString<ArrayList<Info>> cb) {
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
        ArrayList<Info> serverArrayList = new ArrayList<Info>();
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
                    serverArrayList.add(new Info(serverId, serverTitle, serverDescripption));
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
        }
    }
}